package it.unich.studenti.tana.funzioniavanzate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btn = findViewById(R.id.btnFinish);
        EditText editText = findViewById(R.id.editTextSomething);

        View.OnClickListener listener2 = (View v) ->{
            if(editText.getText().toString().equals("")) {
                Toast.makeText(this, "The text field is empty", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent2 = new Intent();                                              // <-- Non mettiamo ne il contesto e ne l'attività da chiamare
                intent2.putExtra("editTextValue", editText.getText().toString());           // <-- toString() perchè android non legge i char array!!!
                setResult(Activity.RESULT_OK, intent2);                                     // <-- quando un processo termina restituisce sempre un codice
                finish();                                                                   // (0 se a buon fine, altrimenti un'altro numero) andoid usa -1
            }
        };
        btn.setOnClickListener(listener2);
    }
}
