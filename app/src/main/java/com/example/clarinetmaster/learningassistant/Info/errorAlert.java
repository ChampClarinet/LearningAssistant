package com.example.clarinetmaster.learningassistant.Info;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.clarinetmaster.learningassistant.R;

public class errorAlert {

    private Context context;
    private String msg;

    public errorAlert(Context context, String msg) {
        this.context = context;
        this.msg = msg;
    }

    public errorAlert(Context context){
        this.context = context;
        this.msg = context.getResources().getString(R.string.errMessage);
    }

    public void alert(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(context.getResources().getString(R.string.error));
        dialog.setMessage(msg);
        dialog.setNeutralButton(context.getResources().getString(R.string.okButton), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}
