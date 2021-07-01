package com.sachin.aislesystemassessment.utils;

import android.app.Activity;
import android.app.ProgressDialog;

import com.sachin.aislesystemassessment.R;

public class CommonUtil {

    static ProgressDialog progressDialog;

    public static void showProgressBar(Activity activity) {
        progressDialog = new ProgressDialog(activity, R.style.ProgressDialogTheme);
        progressDialog.setCancelable(false);
        progressDialog.show();
        progressDialog.setContentView(R.layout.custom_progressbar);
    }

    public static void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
