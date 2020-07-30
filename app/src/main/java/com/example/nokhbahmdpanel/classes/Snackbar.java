package com.example.nokhbahmdpanel.classes;

import android.graphics.Color;
import android.view.View;

public class Snackbar {

    public static void SnackBarMessage(View view, CharSequence message, int duration, int color) {
        //****************************** SnackBar *************************
        /**/
        com.google.android.material.snackbar.Snackbar snack = com.google.android.material.snackbar.Snackbar.make(view, message, duration);
        /**/
        snack.setAction("Ok", View::onCancelPendingInputEvents);//

        /**/
        snack.setActionTextColor(Color.WHITE);
        /**/
        View snackview = snack.getView();
        /**/
        snackview.setBackgroundColor(color);
        /**/
        snack.show();
        //*****************************************************************
    }
}
