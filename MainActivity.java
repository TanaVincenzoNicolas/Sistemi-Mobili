package it.unich.studenti.tana.testintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    // VARIABLES ---------------------------------------
    String KEY = "i";
    int i = 0;
    // VARIABLES ---------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // OBJECTS ---------------------------------------
        Button btn1 = findViewById(R.id.btn1);
        TextView txtView1 = findViewById(R.id.txtView1);
        EditText editTxtName = findViewById(R.id.editTxtName);
        CheckBox boxStudent = findViewById(R.id.boxStudent);
        RadioButton radioChieti = findViewById(R.id.radioChieti);
        RadioButton radioPescara = findViewById(R.id.radioPescara);
        ToggleButton togglePresence = findViewById(R.id.togglePresence);
        Switch switchResponse = findViewById(R.id.switchResponse);

        Spinner spinnerCDS = findViewById(R.id.spinnerCDS);
        // OBJECTS ----------------------------------------



        // Create dynamic spinner list using android default spinner layout
        ArrayAdapter<CharSequence> adapterCDS = ArrayAdapter.createFromResource(    // <-- Creates an adapter object using the "strings.xml" inside "res" folder
                this, R.array.CDS, android.R.layout.simple_spinner_item
        );
        adapterCDS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // <-- Creates a dropdown menu layout
        spinnerCDS.setAdapter(adapterCDS);



        // Listener for switchButton
        View.OnClickListener listenerResponse = (View v) ->{
            boolean statusResponse = switchResponse.isChecked();
            if(statusResponse){
                txtView1.setText("Selected");
            }
            else {
                txtView1.setText("Not Selected");
            }
        };
        switchResponse.setOnClickListener(listenerResponse);



        // Listener for toggleButton
        View.OnClickListener listenerPresence = (View v) ->{
            if(togglePresence.isChecked()){
                txtView1.setText("Hello! You're present");
            }
            else {
                txtView1.setText("You're absent :(");
            }
        };
        togglePresence.setOnClickListener(listenerPresence);



        // Listener for City radioGroup
        View.OnClickListener listenerCity = (View v) -> {
            // Since radios are an external constant, you can't use switch statement inside a method
            if(v.getId() == R.id.radioChieti) {
                txtView1.setText("Congratulations, you're from Chieti");
            }
            else{
                txtView1.setText("Fantastic! You're from Pescara");
            }
        };
        radioChieti.setOnClickListener(listenerCity);
        radioPescara.setOnClickListener(listenerCity);



        // Listener for Student checkBox
        View.OnClickListener listenerStudent = (View v) -> {
            if(boxStudent.isChecked()){
                txtView1.setText("You're a student");
            }
            else {
                txtView1.setText("You're NOT a student");
            }
        };
        boxStudent.setOnClickListener(listenerStudent);



        // Listener for textEditor
        TextView.OnEditorActionListener listenerName = new TextView.OnEditorActionListener() {      // It can be called using a lambda function
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                txtView1.setText(editTxtName.getText());
                return false;   // Gli eventi possono essere collegati tra loro
            }                   // return smette di far ascoltarel'evento
        };
        editTxtName.setOnEditorActionListener(listenerName);    // Used for listen when pressing "insert"

        // Listener for textEditor with different status
        editTxtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txtView1.setText(editTxtName.getText());
            }
        });



        // Checks the "Extra" array of theinitial intent
        Intent intentStart = getIntent();                       // <-- Return the initial app intent
        if(intentStart.getExtras() != null){                    // <-- Check if it's the first time the activity get launched
            i = intentStart.getIntExtra(KEY,0);      // (Default value is mandatory because int does not support null)
            i++;
            txtView1.setText("The value of i = " + i);
        }

        // Creates a new activity whith the current i value
        View.OnClickListener listenerNewActivity = (View v) -> {
            Intent intent = new Intent(this, MainActivity.class);   // (Extra is an associative array of an intent object)
            intent.putExtra(KEY,i);                                              // <-- We can put extras here
            startActivity(intent);
        };
        btn1.setOnClickListener(listenerNewActivity);

    }
}
