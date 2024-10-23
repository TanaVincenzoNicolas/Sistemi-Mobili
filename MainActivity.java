package it.unich.studenti.tana.testintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String KEY = "i";
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn1 = findViewById(R.id.btn1);
        TextView txtView1 = findViewById(R.id.txtView1);
        EditText editTxtName = findViewById(R.id.editTxtName);



        TextView.OnEditorActionListener listenerName = new TextView.OnEditorActionListener() {      // It can be called using a lambda function
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                txtView1.setText(editTxtName.getText());
                return false;
            }
        };
        editTxtName.setOnEditorActionListener(listenerName);    // Used for listen when pressing "insert"



        Intent intentStart = getIntent();                       // <-- Return the initial app intent
        if(intentStart.getExtras() != null){                    // <-- Check if it's the first time the activity get launched
            i = intentStart.getIntExtra(KEY,0);      // (Default value is mandatory because int does not support null)
            i++;
            txtView1.setText("The value of i = " + i);
        }


        View.OnClickListener listener = (View v) -> {
            Intent intent = new Intent(this, MainActivity.class);   // (Extra is an associative array of an intent object)
            intent.putExtra(KEY,i);                                              // <-- We can put extras here
            startActivity(intent);
        };
        btn1.setOnClickListener(listener);

    }
}