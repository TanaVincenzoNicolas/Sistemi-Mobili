package it.unich.studenti.tana.database.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// Essendo un'interfaccia NON si scrive il corpo del metodo (và implementato)
// Dao deve comprendere tutti im metodi di accesso al database
@Dao
public interface UserDao {

    @Insert // (Gestito da Room)
    void insert(User user);
    @Insert
    void insert2Friends(User user1, User user2);

    @Insert
    void insertAll(User... users);          // Posso passargli oggetti multipli

    @Delete
    void delete(User user);

    @Query("SELECT * FROM User")
    List<User> getAll();                    // List è una Collection Parametrica
}


// CREIAMO UNA CLASSE AppDatabase
