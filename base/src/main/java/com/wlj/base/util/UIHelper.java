package com.wlj.base.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Toast;

import com.wlj.base.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class UIHelper {

//    public final static int LISTVIEW_ACTION_INIT = 51;
//    public final static int LISTVIEW_ACTION_REFRESH = 52;
//    public final static int LISTVIEW_ACTION_SCROLL = 53;
//    public final static int LISTVIEW_ACTION_CHANGE_CATALOG = 54;

    private static AlertDialog Progressbardlg;

    /**
     * 弹出Toast消息
     *
     * @param msg
     */
    public static void toastMessage(Context cont, String msg) {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toastMessage(Context cont, int msg) {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toastMessage(Context cont, String msg, int time) {
        Toast.makeText(cont, msg, time).show();
    }

    private static ProgressDialog progressDialog;

    public static void loading(String str, Activity mContext) {
        loadingClose();
        progressDialog = ProgressDialog.show(mContext, "提示", str);
    }

    public static void loadingCir(Activity context) {
        loadingClose();
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    public static void loadingClose() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    /**
     * 点击返回监听事件
     *
     * @param activity
     * @return
     */
    public static OnClickListener finish(final Activity activity) {
        return new OnClickListener() {
            public void onClick(View v) {
                activity.finish();
            }
        };
    }

    /**
     * 打开gps提示框
     *
     * @param cont
     * @param crashReport
     */
    public static void GpsOpenTip(final Context cont, final String crashReport) {

        Builder builder = new Builder(cont);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle(R.string.app_tipr);
        builder.setMessage(crashReport);
        builder.setPositiveButton("幸福地开启",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        cont.startActivity(intent);
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("残忍地拒绝",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }


    public static void showProgressbar(Activity paramActivity, DialogInterface.OnCancelListener paramOnCancelListener) {
        closeProgressbar();

        Progressbardlg = new AlertDialog.Builder(paramActivity).create();
        Progressbardlg.setCancelable(false);
        Window localWindow = Progressbardlg.getWindow();
        localWindow.setBackgroundDrawable(new ColorDrawable(0));
        Progressbardlg.show();
        localWindow.setContentView(R.layout.progressbar);
        Progressbardlg.setOnCancelListener(paramOnCancelListener);
    }

    public static void closeProgressbar() {
        if (Progressbardlg != null && Progressbardlg.isShowing()) {
            Progressbardlg.dismiss();
            Progressbardlg = null;
        }
    }


    static SweetAlertDialog pDialog;

    public static void showLoading(Context context) {

        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public static void closeLoading() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    public static void tip(Context cont, String message, SweetAlertDialog.OnSweetClickListener confirmClickListener, SweetAlertDialog.OnSweetClickListener cancelClickListener) {

        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(cont)
                .setTitleText("提示")
                .setContentText(message);

        if (confirmClickListener != null) {
            sweetAlertDialog.setConfirmText("确认")
                    .setConfirmClickListener(confirmClickListener);
        }

        if(cancelClickListener != null){
            sweetAlertDialog.setCancelText("取消")
                    .setCancelClickListener(cancelClickListener);
        }
        sweetAlertDialog.show();

    }

}
