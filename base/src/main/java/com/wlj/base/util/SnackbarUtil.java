package com.wlj.base.util;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wlj.base.R;


/**
 * Created by 赵晨璞 on 2016/5/1.
 */
public class SnackbarUtil {

    public static final int Info = 1;
    public static final int Confirm = 2;
    public static final int Warning = 3;
    public static final int Alert = 4;


    public static int red = 0xfff44336;
    public static int green = 0xff4caf50;
    public static int blue = 0xff2195f3;
    public static int orange = 0xffffc107;

    /**
     * 短显示Snackbar，自定义颜色
     *
     * @param view
     * @param message
     * @param messageColor
     * @param backgroundColor
     * @return
     */
    public static Snackbar ShortSnackbar(View view, String message, int messageColor, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        setSnackbarColor(snackbar, messageColor, backgroundColor);
        return snackbar;
    }

    /**
     * 长显示Snackbar，自定义颜色
     *
     * @param view
     * @param message
     * @param messageColor
     * @param backgroundColor
     * @return
     */
    public static Snackbar LongSnackbar(View view, String message, int messageColor, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        setSnackbarColor(snackbar, messageColor, backgroundColor);
        return snackbar;
    }

    /**
     * 自定义时常显示Snackbar，自定义颜色
     *
     * @param view
     * @param message
     * @param messageColor
     * @param backgroundColor
     * @return
     */
    public static Snackbar IndefiniteSnackbar(View view, String message, int duration, int messageColor, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setDuration(duration);
        setSnackbarColor(snackbar, messageColor, backgroundColor);
        return snackbar;
    }

    /**
     * 短显示Snackbar，可选预设类型
     *
     * @param view
     * @param message
     * @param type
     * @return
     */
    public static Snackbar ShortSnackbar(View view, String message, int type) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        switchType(snackbar, type);
        return snackbar;
    }

    /**
     * 长显示Snackbar，可选预设类型
     *
     * @param view
     * @param message
     * @param type
     * @return
     */
    public static Snackbar LongSnackbar(View view, String message, int type) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        switchType(snackbar, type);
        return snackbar;
    }

    /**
     * 自定义时常显示Snackbar，可选预设类型
     *
     * @param view
     * @param message
     * @param type
     * @return
     */
    public static Snackbar IndefiniteSnackbar(View view, String message, int duration, int type) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setDuration(duration);
        switchType(snackbar, type);
        return snackbar;
    }

    //选择预设类型
    private static void switchType(Snackbar snackbar, int type) {
        switch (type) {
            case Info:
                setSnackbarColor(snackbar, blue);
                break;
            case Confirm:
                setSnackbarColor(snackbar, green);
                break;
            case Warning:
                setSnackbarColor(snackbar, orange);
                break;
            case Alert:
                setSnackbarColor(snackbar, Color.YELLOW, red);
                break;
        }
    }

    /**
     * 设置Snackbar背景颜色
     *
     * @param snackbar
     * @param backgroundColor
     */
    public static void setSnackbarColor(Snackbar snackbar, int backgroundColor) {
        View view = snackbar.getView();
        if (view != null) {
            view.setBackgroundColor(backgroundColor);
        }
    }

    /**
     * 设置Snackbar文字和背景颜色
     *
     * @param snackbar
     * @param messageColor
     * @param backgroundColor
     */
    public static void setSnackbarColor(Snackbar snackbar, int messageColor, int backgroundColor) {
        View view = snackbar.getView();
        if (view != null) {
            view.setBackgroundColor(backgroundColor);
            ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(messageColor);
        }
    }

    /**
     * 向Snackbar中添加view
     *
     * @param snackbar
     * @param layoutId
     * @param index    新加布局在Snackbar中的位置
     */
    public static void SnackbarAddView(Snackbar snackbar, int layoutId, int index) {
        View snackbarview = snackbar.getView();
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarview;

        View add_view = LayoutInflater.from(snackbarview.getContext()).inflate(layoutId, null);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.gravity = Gravity.CENTER_VERTICAL;

        snackbarLayout.addView(add_view, index, p);
    }

    /**
     *  有输入框的Snackbar
     *
     * @param v     The view to find a parent from.
     * @param editText
     * @param listenter  只有editText里面有内容时才回掉，没时关闭Snackbar
     * @return
     */
    public static Snackbar SnackbarAddView(View v, EditText editText, final View.OnClickListener listenter) {

        final Snackbar snackbar = Snackbar.make(v, "", Snackbar.LENGTH_INDEFINITE);

        snackbar.setAction("关闭", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object tag = v.getTag();
                if (tag == null) {
                    snackbar.dismiss();
                } else {
                    listenter.onClick(v);
                }
            }
        });

        editText.setHint("说点什么吧！");
        View snackbarview = snackbar.getView();
        Button snackbar_action = null;

        if (snackbarview != null) {
            //把原来的textview隐藏，设置setVisibility有问题
            snackbarview.findViewById(R.id.snackbar_text).setLayoutParams(new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.WRAP_CONTENT));
            snackbar_action = (Button) snackbarview.findViewById(R.id.snackbar_action);
        }
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarview;

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.gravity = Gravity.CENTER_VERTICAL;
        p.weight = 1;

        snackbarLayout.addView(editText, 0, p);

        final Button finalSnackbar_action = snackbar_action;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtils.isEmpty(s.toString())) {
                    if (finalSnackbar_action != null) {
                        finalSnackbar_action.setText("关闭");
                        finalSnackbar_action.setTag(null);
                    }
                } else {
                    if (finalSnackbar_action != null) {
                        finalSnackbar_action.setText("回复");
                        finalSnackbar_action.setTag(s + "");
                    }
                }
            }
        });

        snackbarview.setBackgroundColor(Color.WHITE);
        snackbar.show();

        return snackbar;
    }


}