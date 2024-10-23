package it.unich.studenti.tana.vincenzonicolas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String TAG = "LIFE";
    String scoreKey = "score";
    int score = 0;
    Button btnScore;
    TextView txtScore;


    // Bundle is an associative array, it can be used to save variable persistently
    // across different activities
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Callback method onCreate() called");
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();

        // it's important to not have a type, it must not be a variable local of "Create"
        btnScore = findViewById(R.id.btnScore);
        txtScore = findViewById(R.id.txtScore);

        Button btnNext = findViewById(R.id.btnNext1);

        View.OnClickListener listenerNewAct = (View v) -> {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        };
        btnNext.setOnClickListener(listenerNewAct);
        // Intents are associative arrays (like Bundles)
        // They contain: extras(Key, Value)
        // Bundle is only in an activity lifecycle


        View.OnClickListener addScore = (View v) -> { // variable declaration with a lambda function
            score++;
            txtScore.setText("Score: " + score);
        };
        btnScore.setOnClickListener(addScore);

    }


    // 2 callback functions that allow to keep data values when onDestroy() is called
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("SAVE", "onSaveInstanceState() called");
        Toast.makeText(this, "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        outState.putInt(scoreKey, score);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("LOAD", "onRestoreInstanceState() called");
        Toast.makeText(this, "onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        if(savedInstanceState != null){
            score = savedInstanceState.getInt(scoreKey, 0);
            txtScore.setText("Score: " + score);
        }
    }


    // The 7 lifecycle callback functions
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Callback method onStart() called");
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Callback method onResume() called");
        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Callback method onPause() called");
        Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Callback method onStop() called");
        Toast.makeText(this, "onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Callback method onDestroy() called");
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Callback method onRestart() called");
        Toast.makeText(this, "onRestart()", Toast.LENGTH_SHORT).show();
    }
}