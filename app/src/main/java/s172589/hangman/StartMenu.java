package s172589.hangman;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class StartMenu extends AppCompatActivity implements AvsluttApp.DialogClickListener {

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
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setClassName("com.android.settings","com.android.settings.LanguageSettings");
        startActivity(i);
    }

    public boolean avslutt(View view) {
        DialogFragment d = new AvsluttApp();
        d.show(getFragmentManager(), "Avslutt");
        return true;
    }

//  Håndtere klikk fra bruker på om han vil avslutte eller fortsette.
    @Override
    public void jaKlikk() {
        finish();
    }
    @Override
    public void neiKlikk() {
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.instillinger) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
