package jonathan.stories;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Joe on 2/8/2016.
 */
public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.error_title).setMessage(R.string.error_message).setPositiveButton(android.R.string.ok, null);

        AlertDialog dialog = builder.create();
        return dialog;
    }
}
