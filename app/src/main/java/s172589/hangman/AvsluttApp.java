package s172589.hangman;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class AvsluttApp extends DialogFragment {
    private DialogClickListener svar;

    public interface DialogClickListener {
        void jaKlikk();
        void neiKlikk();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            svar = (DialogClickListener) getActivity();
        } catch(ClassCastException cce) {
            throw new ClassCastException("Må implementere interfacet.");
        }
    }

    public static AvsluttApp newInstance(int Title){
        AvsluttApp fragment = new AvsluttApp();
        Bundle b = new Bundle();
        b.putInt("tittel", Title);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.avslutt)
                .setPositiveButton(R.string.ja,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton){
                                svar.jaKlikk();
                            }
                        }
                )
                .setNegativeButton(R.string.nei,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton){
                                svar.neiKlikk();
                            }
                        }
                )
                .create();
    }
}
