package it.unich.studenti.tana.database.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)        // <-- Le annotazioni "@" valgono per le righe immediatamente successive
    public int id;                          // L'attributo (boolean.autoGenerate) significa auto increment

    public String firstName;
    public String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
