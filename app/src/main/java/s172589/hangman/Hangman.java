package s172589.hangman;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

// Selve Hangman-aktiviteten
public class Hangman extends AppCompatActivity{
    private String[] ord;
    private Random tilfeldig;
    private String gjeldendeOrd;
    private LinearLayout ordVisning;
    private TextView[] bokstavVisning;
    private GridView bokstaver;
    private BokstavMetoder bokstavMetoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.hangman);
        Resources r = getResources();
        ord = r.getStringArray(R.array.ord);
        tilfeldig = new Random();
        gjeldendeOrd = "";
        ordVisning = (LinearLayout) findViewById(R.id.gjetteFelt);
        bokstaver = (GridView) findViewById(R.id.bokstavFelt);
        spill();
    }

    private void spill() {
        String nyttOrd = ord[tilfeldig.nextInt(ord.length)];
        while(nyttOrd.equals(gjeldendeOrd)){
            nyttOrd = ord[tilfeldig.nextInt(ord.length)];
        }
        gjeldendeOrd = nyttOrd;

        bokstavVisning = new TextView[gjeldendeOrd.length()];
        ordVisning.removeAllViews();

        for(int i = 0; i < gjeldendeOrd.length(); i++){
            bokstavVisning[i] = new TextView(this);
            bokstavVisning[i].setText("" + gjeldendeOrd.charAt(i));

            bokstavVisning[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            bokstavVisning[i].setGravity(Gravity.CENTER);
            bokstavVisning[i].setBackgroundResource(R.drawable.bak_bokstav);

            ordVisning.addView(bokstavVisning[i]);
        }
        bokstavMetoder = new BokstavMetoder(this);
        bokstaver.setAdapter(bokstavMetoder);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
