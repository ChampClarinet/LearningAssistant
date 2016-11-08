package com.example.clarinetmaster.learningassistant.Info;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.clarinetmaster.learningassistant.R;

public class uncompleteFieldAlert {

    private Context context;

    public uncompleteFieldAlert(Context context) {
        this.context = context;
    }

    public void alert(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(context.getResources().getString(R.string.error));
        dialog.setMessage(context.getResources().getString(R.string.errMessage));
        dialog.setNeutralButton(context.getResources().getString(R.string.okButton), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}
