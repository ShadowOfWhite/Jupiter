package com.example.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte_core.fragments.LatteFragment;
import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.callback.IError;
import com.example.latte_core.net.callback.IFailure;
import com.example.latte_core.net.callback.ISuccess;
import com.example.latte_core.util.log.LatteLogger;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：贪欢
 * 时间：2019/5/30
 * 描述：登录
 */
public class SignInFragment extends LatteFragment {


    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText editSignInEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText editSignInPassword;
    @BindView(R2.id.btn_sign_in)
    AppCompatButton btnSignIn;
    @BindView(R2.id.tv_link_sign_up)
    AppCompatTextView tvLinkSignUp;
    @BindView(R2.id.icon_in_we_chat)
    IconTextView iconInWeChat;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    private boolean checkForm() {
        String email = editSignInEmail.getText().toString();
        String password = editSignInPassword.getText().toString();
        boolean isPass = true;

        if (email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignInEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            editSignInEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            editSignInPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            editSignInPassword.setError(null);
        }
        return isPass;

    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }


    @OnClick(R2.id.tv_link_sign_up)
    void onClikLink() {
//        start(new SignUpDelegate(),SupportFragment.SINGLETASK);
        getSupportDelegate().startWithPop(new SignUpFragment());
    }

    @OnClick(R2.id.icon_in_we_chat)
    void onClickWeChat() {

    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {

        if (true) {
//        if (checkForm()){
            RestClient.builder()
                    .url("http://mock.fulingjie.com/mock-android/data/user_profile.json")
                    .params("", "")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();

                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);

                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure(String msg) {
                            Toast.makeText(getContext(), "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {

                            //无管是否登录成功
                            LatteLogger.json("USER_PROFILE", msg);
                            SignHandler.onSignIn(msg, mISignListener);
                            Toast.makeText(getContext(), code + "," + msg, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build()
                    .post();


        }
    }
}
