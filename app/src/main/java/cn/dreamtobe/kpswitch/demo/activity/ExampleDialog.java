package cn.dreamtobe.kpswitch.demo.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import cn.dreamtobe.kpswitch.demo.R;
import cn.dreamtobe.kpswitch.util.KPSwitchConflictUtil;
import cn.dreamtobe.kpswitch.util.KeyboardUtil;
import cn.dreamtobe.kpswitch.widget.KPSwitchFSPanelLinearLayout;

public class ExampleDialog extends Dialog {


    private KPSwitchFSPanelLinearLayout panelRoot;
    private EditText sendEdt;
    private ImageView plusIv;

    public ExampleDialog(Context context) {
        super(context, R.style.Theme_Dialog_NoTitle);
        init(context);
    }

    public ExampleDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }


    private void init(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        getWindow().setGravity(Gravity.BOTTOM);
        setContentView(R.layout.dialog_chatting_resolved);
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        getWindow().getAttributes().width = metrics.widthPixels;

        panelRoot = (KPSwitchFSPanelLinearLayout) findViewById(R.id.panel_root);
        sendEdt = (EditText) findViewById(R.id.send_edt);
        plusIv = (ImageView) findViewById(R.id.plus_iv);


        KeyboardUtil.attach(getWindow(), panelRoot);
        KPSwitchConflictUtil.attach(getWindow(), panelRoot, plusIv, sendEdt,
                new KPSwitchConflictUtil.SwitchClickListener() {
                    @Override
                    public void onClickSwitch(View v, boolean switchToPanel) {
                        if (switchToPanel) {
                            sendEdt.clearFocus();
                        } else {
                            sendEdt.requestFocus();
                        }
                    }
                });

    }
}
