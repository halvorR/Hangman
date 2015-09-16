package s172589.hangman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.Locale;

public class BokstavMetoder extends BaseAdapter{
    private String[] alfabet;
    private LayoutInflater bokstavInflater;

    public BokstavMetoder(Context c) {
        if(Locale.getDefault().getDisplayLanguage().equals("norsk bokmål")){
            alfabet = new String[29];
            for(int i = 0; i < 26; i++){
                alfabet[i] = "" + (char)(i + 'A');
            }
            alfabet[26] = "Æ";
            alfabet[27] = "Ø";
            alfabet[28] = "Å";
        } else {
            alfabet = new String[26];
            for(int i = 0; i < alfabet.length; i++){
                alfabet[i] = "" + (char)(i + 'A');
            }
        }

        bokstavInflater = LayoutInflater.from(c);
    }


    @Override
    public int getCount() {
        return alfabet.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int plass, View konverterView, ViewGroup parent) {
        // Lager en knapp for hver bokstav i alfabetet
        Button bokstavKnapp;
        if(konverterView == null){
            bokstavKnapp = (Button) bokstavInflater.inflate(R.layout.bokstav, parent, false);
        } else {
            bokstavKnapp = (Button) konverterView;
        }
        // Setter tekst til denne bokstaven
        bokstavKnapp.setText(alfabet[plass]);
        return bokstavKnapp;
    }
}
