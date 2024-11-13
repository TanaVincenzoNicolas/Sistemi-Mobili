package it.unich.studenti.tana.funzioniavanzate;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// L'activity1 manda un intent1 ad android che lo passa all'activity2,
// successivamente crea l'intent2

// L'activity2 chiama un metodo chiamato setResult()
// restituisce un codice di ritorno per comunicare all'activity chiamante
// se l'esecuzione è andata a buon fine e viene passato l'intent2
// successivamente terminiamo l'activity utilizzando
// il metodo finish(), che passa i dati ad android

// android prende l'intent2 e lo passa al launcher

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnGoActivity2);
        TextView textView = findViewById(R.id.textView);


        // Inseriamo nel launcher l'intent2
        // In questo caso il <Tipo> di dato è un Intent
        ActivityResultLauncher<Intent> mStartForResult =
                registerForActivityResult(                                              // <-- (Parametri)
                        new ActivityResultContracts.StartActivityForResult(),           // Contract = modo in cui comunichiamo con android
                        new ActivityResultCallback<ActivityResult>() {                  // Callback = è un oggetto con un solo metodo
                            @Override
                            public void onActivityResult(ActivityResult result) {       // result = Codice di ritorno e Intent
                                if(result.getResultCode() == Activity.RESULT_OK) {
                                    Intent i = result.getData();                        // result.getData() = intent2
                                    String editTextValue = i.getStringExtra("editTextValue");
                                    textView.setText("Got " + editTextValue + " from Activity2");
                                } else {
                                    textView.setText("Something went wrong!");
                                }
                            }
                        }
                );

        // Lanchiamo l'activity utilizzando il launcher
        View.OnClickListener listener1 = (View v) ->{
            Intent intent1 = new Intent(this, MainActivity2.class);
            //startActivity(intent1);
            mStartForResult.launch(intent1);
        };
        btn.setOnClickListener(listener1);
    }
}
