package it.unich.studenti.tana.database.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/*
 - "entities" distingue i database
 - "version" è obbligatoria, se modifichiamo il database bisogna
 informare Room su come migrare i dati in un'altra tabella
*/

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase db; // volatile per variabili di istanza synchronized (restituisce db ogni volta che si chiama getDatabase)
    public abstract UserDao userDao();      // Associamo il Dao (il metodo userDao() crea una classe astratta UserDao)
                                            // Dao è un'interface, lo implemento con un'abstract

    // getDatabase() deve poter essere chiamato da qualsiasi punto
    // static non è ad oggetti, e i parametri devono essere final
    public static AppDatabase getDatabase(final Context context){
        if(db == null){
            db = Room.databaseBuilder(
                    context.getApplicationContext(),            // <-- il context lo passiamo dalla activity
                    AppDatabase.class,
                    "User_db"
            ).build();
        }
        return db;
    }
}
