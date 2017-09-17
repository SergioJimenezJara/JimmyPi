package com.example.jimmypi.jimmypi;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity {

    // constante NPI
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button BtnGps = (Button) findViewById(R.id.BtnGps);
        Button BtnLlamar = (Button) findViewById(R.id.BtnLlamar);
        Button BtnMusica = (Button) findViewById(R.id.BtnMusica);
        Button BtnVoz = (Button) findViewById(R.id.BtnVoz);
        Button BtnAjustes = (Button) findViewById(R.id.BtnAjustes);

        // Escuchador del boton central para recoger voz
        BtnVoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EscucharVoz();
            }
        });

        // Escuchador del boton de ajustes
        BtnAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AjustesActivity.class);
                startActivity(intent);
            }
        });


    }

    /**
     * Muestra la entrada de voz
     */
    private void EscucharVoz() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Que quieres hacer?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Algo ha fallado", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Recibe la entrada de voz
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    // Mostramos el mensaje con un toast
                    Toast.makeText(getApplicationContext(),
                            result.get(0),
                            Toast.LENGTH_SHORT).show();
                }
                break;
            }

        }
    }

}


