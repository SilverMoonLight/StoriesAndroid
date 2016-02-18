package jonathan.stories;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by Joe on 2/11/2016.
 */
public class MultiChoiceDialog extends DialogFragment {

    private static final String CHOICE_1 = "c1";
    private static final String CHOICE_2 = "c2";
    private static final String CHOICE_3 = "c3";
    private static final String CHOICE_CORRECT = "c4";
    Button choice1;
    Button choice2;
    Button choice3;




    public static MultiChoiceDialog newInstance(String c1, String c2, String c3, String correct){
        Bundle args = new Bundle();
        args.putSerializable(CHOICE_1, c1);
        args.putSerializable(CHOICE_2, c2);
        args.putSerializable(CHOICE_3, c3);
        args.putSerializable(CHOICE_CORRECT, correct);

        MultiChoiceDialog fragment = new MultiChoiceDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String c1 = (String) getArguments().getSerializable(CHOICE_1);
        String c2 = (String) getArguments().getSerializable(CHOICE_2);
        String c3 = (String) getArguments().getSerializable(CHOICE_3);
        final String correct = (String) getArguments().getSerializable(CHOICE_CORRECT);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_choice, null);

        choice1 = (Button) view.findViewById(R.id.choice1);
        choice1.setText(c1);
        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choice1.getText().toString().equals(correct)) {
                    correctDialog();

                } else {
                    incorrectDialog();
                }
            }
        });

        choice2 = (Button) view.findViewById(R.id.choice2);
        choice2.setText(c2);
        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choice2.getText().toString().equals(correct)) {
                    correctDialog();
                } else {
                    incorrectDialog();
                }
            }
        });
        choice3 = (Button) view.findViewById(R.id.choice3);
        choice3.setText(c3);
        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choice3.getText().toString().equals(correct)) {
                    correctDialog();
                } else  {
                    incorrectDialog();
                }
            }
        });

        return new AlertDialog.Builder(getActivity()).setView(view).setPositiveButton(android.R.string.ok, null).create();
    }

    public void correctDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Correct").setPositiveButton("Ok", null);
        builder.show();
    }
    public void incorrectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Incorrect").setPositiveButton("Ok", null);
        builder.show();
    }
}
