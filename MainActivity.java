package it.unich.studenti.tana.threadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Counter counter = new Counter();


        Runnable runnable1 = new Runnable(){        // <-- Runnable è un'interfaccia con un solo metodo, potremmo usare la lambda expression
            @Override
            public void run() {
                for(int i=0; i<10000; i++){
                    counter.increment();
                }
                runOnUiThread( () -> counter.updateResult() ); // Si possono omettere le graffe se su una riga sola
            }
        };
        Thread t1 = new Thread(runnable1);          // Il Thread non parte da solo ("start()")
        Thread t2 = new Thread(
            () -> {                                 // <-- Poichè runnable non ha parametri la lista è vuota ()
                for(int i=0; i<10000; i++){
                    counter.increment();
                }
                runOnUiThread( () -> counter.updateResult() );
                // I thread non scrivono direttamente sull'interfaccia grafica
                // chiamano il metodo synchronized di java per farlo
            }
        );
        t1.start();
        t2.start();

        /*
        // Esempio di thread che NON dovremmo mai fare
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t.start();

        // Attendiamo il completamento del thread
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // NON andrebbe messo il join (blocca gli altri thread)
        // Genere un rerrore "skipping frames" nel LogCat
        */


        try {
            t1.join();                              // <-- Aspetta la fine di un'altro thread,
            t2.join();                              // siccome genera eccezioni bisogna usare try catch
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //text.setText("Counter: " + counter.getCount());
    }

    class Counter{
        private int count = 0;
        TextView text = findViewById(R.id.text);

        public int getCount(){
            return count;
        }

        //public void increment(){
        //    count++;
        //}
        // Il metodo incrementa non è atomico

        public synchronized void increment(){       // <-- Da usare con i thread
            count++;
        }

        public void updateResult(){                 // <-- I thread NON devono chiamare questo metodo (android non è thread safe)
            text.setText("Counter: " + getCount());
        }
    }
}