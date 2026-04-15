package com.geely.lib.oneosapi.navi.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.geely.lib.oneosapi.C0836R;

/* loaded from: classes.dex */
public class SetDefaultMapDialog extends Dialog {
    private Button btnCancel;
    private Button btnConfirm;
    private String cancel;
    private String confirm;
    private String content;
    private OnCancelClickListener onCancelClickListener;
    private OnConfirmClickListener onConfirmClickListener;
    private String title;
    private TextView tvContent;
    private TextView tvTitle;

    public interface OnCancelClickListener {
        void CancelClick();
    }

    public interface OnConfirmClickListener {
        void ConfirmClick();
    }

    public SetDefaultMapDialog(Context context) {
        super(context);
    }

    public SetDefaultMapDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0836R.layout.dialog_set_default_map);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        this.tvTitle = (TextView) findViewById(C0836R.id.tv_title);
        this.tvContent = (TextView) findViewById(C0836R.id.tv_content);
        this.btnConfirm = (Button) findViewById(C0836R.id.btn_confirm);
        this.btnCancel = (Button) findViewById(C0836R.id.btn_cancel);
    }

    private void initData() {
        this.tvTitle.setText(this.title);
        this.tvContent.setText(this.content);
        this.btnConfirm.setText(this.confirm);
        this.btnCancel.setText(this.cancel);
    }

    private void initEvent() {
        this.btnConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.geely.lib.oneosapi.navi.utils.SetDefaultMapDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (SetDefaultMapDialog.this.onConfirmClickListener != null) {
                    SetDefaultMapDialog.this.onConfirmClickListener.ConfirmClick();
                }
            }
        });
        this.btnCancel.setOnClickListener(new View.OnClickListener() { // from class: com.geely.lib.oneosapi.navi.utils.SetDefaultMapDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (SetDefaultMapDialog.this.onCancelClickListener != null) {
                    SetDefaultMapDialog.this.onCancelClickListener.CancelClick();
                }
            }
        });
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setConfirmClickListener(String confirm, OnConfirmClickListener onConfirmClickListener) {
        this.confirm = confirm;
        this.onConfirmClickListener = onConfirmClickListener;
    }

    public void setCancelClickListener(String cancel, OnCancelClickListener onCancelClickListener) {
        this.cancel = cancel;
        this.onCancelClickListener = onCancelClickListener;
    }

    @Override // android.app.Dialog
    public void show() {
        getWindow().setType(2038);
        super.show();
    }
}