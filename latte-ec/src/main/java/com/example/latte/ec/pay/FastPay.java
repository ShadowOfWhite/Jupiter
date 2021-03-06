package com.example.latte.ec.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.latte.ec.R;
import com.example.latte_core.fragments.LatteFragment;

/**
 * 作者：贪欢
 * 时间：2019/7/10
 * 描述：
 */
public class FastPay implements View.OnClickListener{

    //设置支付回调监听
    private IAlPayResultListener mIAlPayResultListener = null;
    private Activity mActivity = null;

    private AlertDialog mDialog = null;
    private int mOrderID = -1;

    private FastPay(LatteFragment delegate){
        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay create(LatteFragment delegate){

        return new FastPay(delegate);
    }

    public void beginPayDialog(){
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null){
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//去掉弹框四周padding
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;//包含这个弹框的window变灰暗
//            params.dimAmount = 1.0f;//控制灰暗度
            window.setAttributes(params);

            window.findViewById(R.id.btn_dialog_pay_alpay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.btn_dialog_pay_alpay){

            mDialog.cancel();
        }else if (id == R.id.btn_dialog_pay_wechat){

            mDialog.cancel();

        }else if (id == R.id.btn_dialog_pay_cancel){


            mDialog.cancel();
        }


    }
}
