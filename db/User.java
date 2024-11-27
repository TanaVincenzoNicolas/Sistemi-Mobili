package it.unich.studenti.tana.database.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)        // <-- Le annotazioni "@" valgono per le righe immediatamente successive
    public int id;                          // L'attributo (boolean.autoGenerate) significa auto increment

    @ColumnInfo(name = "first_name")        // Rinomino (disaccoppia) variabili di istanza da quello della tabella (ASTRAZIONE)
    public String firstName;
    public String lastName;


    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    /*
    ALTRI COMANDI UTILI:

     @Entity(primaryKeys = {,})
     <Costruttore>

     @Ignore                                <-- il database ignora questi oggetti
     Bitmap picture;
    */
}


// CREIAMO UNA INTERFACE DENTRO IL PACKAGE "db" <NomeClasse><Dao>
