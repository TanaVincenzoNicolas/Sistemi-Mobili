package it.unich.studenti.tana.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import it.unich.studenti.tana.database.db.AppDatabase;
import it.unich.studenti.tana.database.db.User;
import it.unich.studenti.tana.database.db.UserDao;

public class MainActivity extends AppCompatActivity {

    /*
     Data Access Object (DAO)
     Per ogni tabella del database, avremo un DAO diverso,
     ogni DAO è collegato alle Entities che eseguono le query.
     L'oggetto Room è il database, restituisce tutti DAO delle tabelle
     -   https://developer.android.com/training/data-storage/room#setup

     Per il setup aggiungo dentro lo script "build.gradle.kts(Module:app)"
         val room_version = "2.6.1"
         implementation("androidx.room:room-runtime:$room_version")
         annotationProcessor("androidx.room:room-compiler:$room_version")
     Abbiamo creato un package "db"
    */


    AppDatabase db;
    // I database NON possono essere usati nel main thread

    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(db == null){                                                 // Come i PDO di php
            Thread dbThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    db = AppDatabase.getDatabase(getApplicationContext());
                    userDao = db.userDao();
                    User paolo = new User("Paolo", "Rossi");
                    userDao.insert(paolo);
                }
            });
            dbThread.start();
            // Per visualizzare il database -> App Insepction
        }
    }
}