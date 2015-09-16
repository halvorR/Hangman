package s172589.hangman;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;

// Selve Hangman-aktiviteten
public class Hangman extends AppCompatActivity {
    // Visning av ord og alfabet
    private String[] ord;
    private Random tilfeldig;
    private String gjeldendeOrd;
    private LinearLayout ordVisning;
    private TextView[] bokstavVisning;
    private GridView bokstaver;
    private BokstavMetoder bokstavMetoder;

    // For å skjule kroppsdelene
    private ImageView[] kroppsdeler;
    private int antallKroppsdeler = 6;
    private int gjeldendeKroppsdel;
    private int antallBokstaver;
    private int antallRiktigGjettet;

    // Historikk
    private int gangerSpilt;
    private int gangerTapt;
    private int gangerVunnet;


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

        // Legger kroppsdelene inn slik rekkefølgen blir når det blir gjettet feil
        kroppsdeler = new ImageView[antallKroppsdeler];
        kroppsdeler[0] = (ImageView)findViewById(R.id.hode);
        kroppsdeler[1] = (ImageView)findViewById(R.id.kropp);
        kroppsdeler[2] = (ImageView)findViewById(R.id.armHoyre);
        kroppsdeler[3] = (ImageView)findViewById(R.id.armVenstre);
        kroppsdeler[4] = (ImageView)findViewById(R.id.beinHoyre);
        kroppsdeler[5] = (ImageView)findViewById(R.id.beinVenstre);

        // Starter nytt spill
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
            bokstavVisning[i].setTextColor(Color.WHITE);
            bokstavVisning[i].setBackgroundResource(R.drawable.bak_bokstav);

            ordVisning.addView(bokstavVisning[i]);
        }
        bokstavMetoder = new BokstavMetoder(this);
        bokstaver.setAdapter(bokstavMetoder);

        gjeldendeKroppsdel = 0;
        antallBokstaver = gjeldendeOrd.length();
        antallRiktigGjettet = 0;

        for(int i = 0; i < antallKroppsdeler; i++) {
            kroppsdeler[i].setVisibility(View.INVISIBLE);
        }
    }

    public void knappTrykket(View v) {
        String bokstav = ((TextView) v).getText().toString();
        char bokstavChar = bokstav.charAt(0);
        v.setEnabled(false);
        v.setBackgroundResource(R.drawable.gjett_bokstav_bg_trykket);

        // Løper igjennom ordet for å finne ut om bokstaven finnes
        boolean finnes = false;
        for(int i = 0; i < gjeldendeOrd.length(); i++) {
            if(gjeldendeOrd.charAt(i) == bokstavChar){
                finnes = true;
                antallRiktigGjettet++;
                bokstavVisning[i].setTextColor(Color.BLACK);
            }
        }
        if(finnes) {
            // Gjettet riktig
            // Trengs vel egentlig ikke
        } else if(gjeldendeKroppsdel < antallKroppsdeler) {
            kroppsdeler[gjeldendeKroppsdel].setVisibility(v.VISIBLE);
            gjeldendeKroppsdel++;
            System.out.println(gjeldendeKroppsdel + " | " + antallKroppsdeler);
        }
        if(gjeldendeKroppsdel == antallKroppsdeler){
            slåAvKnapper();

            // Dialogboks for å gi brukeren svaret, og valget på hva de vil gjøre videre
            System.out.println("Brukeren har tapt");

            AlertDialog.Builder valgVedTap = new AlertDialog.Builder(this);
            valgVedTap.setTitle(R.string.tapTittel);
            valgVedTap.setMessage(R.string.tapMelding1 + gjeldendeOrd + R.string.tapMelding2);
            valgVedTap.setPositiveButton(R.string.tapNyttSpill,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Hangman.this.spill();
                        }
                    });

            valgVedTap.setNegativeButton(R.string.tapTilHovedmeny,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                           Hangman.this.tilStartside();

                        }
                    });

            valgVedTap.show();

        }

        if(antallRiktigGjettet == antallBokstaver){
            slåAvKnapper();


            System.out.println("Spillet er ferdig, bruker har vunnet");
        }
    }

    public void slåAvKnapper(){
        int antall = bokstaver.getChildCount();
        for(int i = 0; i < antall; i++){
            bokstaver.getChildAt(i).setEnabled(false);
        }
    }

    public void tilStartside(){
        Intent i = new Intent(this, StartMenu.class);
        startActivity(i);
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
