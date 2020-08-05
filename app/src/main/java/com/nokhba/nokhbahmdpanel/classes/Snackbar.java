package com.nokhba.nokhbahmdpanel.classes;

import android.graphics.Color;
import android.view.View;

import com.nokhba.nokhbahmdpanel.R;

public class Snackbar {

    public static void SnackBarMessage(View view, CharSequence message, int duration, int color) {
        //****************************** SnackBar *************************
        /**/
        com.google.android.material.snackbar.Snackbar snack = com.google.android.material.snackbar.Snackbar.make(view, message, duration);
        /**/
        snack.setAction(view.getResources().getString(R.string.ok), View::onCancelPendingInputEvents);//

        /**/
        snack.setActionTextColor(android.R.color.holo_green_light);
        /**/
        View snackview = snack.getView();
        /**/
        snackview.setBackgroundColor(color);
        /**/
        snack.show();
        //*****************************************************************
    }
}
