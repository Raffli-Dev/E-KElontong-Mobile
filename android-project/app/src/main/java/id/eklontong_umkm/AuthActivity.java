package id.eklontong_umkm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespUser;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.param.ParamLogin;
import id.eklontong_umkm.utils.Tools;
import id.eklontong_umkm.utils.ViewAnimation;

public class AuthActivity extends AppCompatActivity {
    private View bannerTop;
    private TextView tvMessageBanner, sign_up;
    private TextInputEditText et_email, et_password;
    private Button btnSubmit;
    private ProgressDialog signUpProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponent();
        Tools.setSystemBarColor(this, R.color.primary_register);
    }
    private void initComponent() {

        bannerTop =  findViewById(R.id.banner_top);
        tvMessageBanner = findViewById(R.id.tv_message_banner);
        et_email = findViewById(R.id.et_email);
        et_email.setText(DataApp.global().getTempEmail());
        et_password = findViewById(R.id.et_password);
        sign_up = findViewById(R.id.sign_up);

        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            delayAndRequest();
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthActivity.this, RegistrasiActivity.class);
                startActivity(intent);
                finish();
            }
        });


        showHideBanner(false, false, null);
    }
    private void delayAndRequest() {
        btnSubmit.setVisibility(View.INVISIBLE);
        showHideBanner(false, false, null);
        DataApp.global().setTempEmail("");
        new Handler(getMainLooper()).postDelayed(() -> requestUserLogin(), 250);

    }
    private void requestUserLogin() {
        ParamLogin params = new ParamLogin();
        params.email = et_email.getText().toString();
        params.password = et_password.getText().toString();
        params.device_id = Tools.getDeviceID(getApplicationContext());

        new Request(this).userLogin(params, new RequestListener<RespUser>() {
            @Override
            public void onFinish() {
                btnSubmit.setVisibility(View.VISIBLE);
                super.onFinish();
            }

            @Override
            public void onFailed(String messages) {
                showHideBanner(true, false, messages);
                super.onFailed(messages);
            }

            @Override
            public void onSuccess(RespUser resp) {
                showHideBanner(true, true, getString(R.string.login_success));
                DataApp.global().setUser(resp.data);
                new Handler(getMainLooper()).postDelayed(() -> startMainActivity(), 1000);
                super.onSuccess(resp);
            }
        });
    }

    private void showHideBanner(boolean show, boolean success, String message) {
        bannerTop.setBackgroundColor(getApplicationContext().getResources().getColor(success ? R.color.success : R.color.error));
        if (show) {
            tvMessageBanner.setText(message);
            ViewAnimation.expand(bannerTop);
        } else {
            if (bannerTop.getVisibility() == View.GONE) {
                ViewAnimation.collapse(bannerTop);
            } else {
                bannerTop.setVisibility(View.GONE);
            }
        }
//        if (signUpProgress.isShowing()) signUpProgress.hide();
    }
    private void startMainActivity() {
        Intent i = new Intent(AuthActivity.this, ActivityMain.class);
        startActivity(i);
        finish();
    }

}
