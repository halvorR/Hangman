package s172589.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class StartMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.startskjerm);
    }

    public void startSpill(View v) {
        Intent i = new Intent(this, Hangman.class);
        startActivity(i);
    }

    public void regler(View v) {
        Intent i = new Intent(this, Regler.class);
        startActivity(i);
    }

    public void endreSprak(View view) {
        System.out.println("Kaller språkvelger på telefonen");
    }

    public void avslutt(View view) {
        super.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i;
        switch (id) {
            case R.id.startSpill:
                i = new Intent(this, Hangman.class);
                startActivity(i);
                return true;
            case R.id.regler:
                i = new Intent(this, Regler.class);
                startActivity(i);
                return true;
            case R.id.endreSprak:
                System.out.println("Kalle språkvelger på telefon");
                return true;
            case R.id.avslutt:
                super.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
