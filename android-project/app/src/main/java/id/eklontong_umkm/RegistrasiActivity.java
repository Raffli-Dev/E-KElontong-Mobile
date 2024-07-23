package id.eklontong_umkm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespUser;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamUserRegister;
import id.eklontong_umkm.utils.Tools;
import id.eklontong_umkm.utils.ViewAnimation;

public class RegistrasiActivity extends AppCompatActivity {

    private View bannerTop;
    private TextView tvMessageBanner, sign_up;
    private EditText et_nomor_whatsapp, et_nama_pemilik_usaha, et_email, et_password, et_nama_usaha, et_alamat_usaha;
    private Button btn_submit;
    private ProgressDialog signUpProgress;
    private User user = null;
    private LinearLayout button_sign_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponent();
        Tools.setSystemBarColor(this, R.color.primary_register);
    }

    private void initComponent() {

        bannerTop =  findViewById(R.id.banner_top);
        tvMessageBanner = findViewById(R.id.tv_message_banner);

        et_nomor_whatsapp = findViewById(R.id.et_nomor_whatsapp);
        et_nama_pemilik_usaha = findViewById(R.id.et_nama_pemilik_usaha);
        et_email = findViewById(R.id.et_email);
        et_email.setText(DataApp.global().getTempEmail());
        et_password = findViewById(R.id.et_password);
        et_nama_usaha = findViewById(R.id.et_nama_usaha);
        et_alamat_usaha = findViewById(R.id.et_alamat_usaha);
        button_sign_in = findViewById(R.id.button_sign_in);

        (findViewById(R.id.show_pass)).setOnClickListener(v -> {
            v.setActivated(!v.isActivated());
            if (v.isActivated()) {
                et_password.setTransformationMethod(null);
            } else {
                et_password.setTransformationMethod(new PasswordTransformationMethod());
            }
            et_password.setSelection(et_password.getText().length());
        });

        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(v -> {
            delayAndRequest();
        });

        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAuthActivity();
            }
        });

        signUpProgress = new ProgressDialog(this);
        signUpProgress.setCancelable(false);
        signUpProgress.setMessage("Loading...");

        showHideBanner(false, false, null);
    }

    private void delayAndRequest() {
        btn_submit.setVisibility(View.INVISIBLE);
        showHideBanner(false, false, null);
        DataApp.global().setTempEmail("");
        new Handler(getMainLooper()).postDelayed(() -> requestUserRegister(null, null), 250);

    }
    private void requestUserRegister(String provider, String image_url) {
        ParamUserRegister params = new ParamUserRegister();

        params.name = et_nama_pemilik_usaha.getText().toString();
        params.email = et_email.getText().toString();
        params.password = et_password.getText().toString();
        params.phone = et_nomor_whatsapp.getText().toString();
        params.owner = et_nama_pemilik_usaha.getText().toString();
        params.company_name = et_nama_usaha.getText().toString();
        params.address = et_alamat_usaha.getText().toString();
        if (provider != null) params.provider = provider;
        if (image_url != null) params.image_url = image_url;
        if (user != null) {
            params.id = user.id.toString();
            params.provider = user.provider;
        }
        params.device_id = Tools.getDeviceID(this);

        new Request(this).userRegister(params, new RequestListener<RespUser>() {
            @Override
            public void onFinish() {
                btn_submit.setVisibility(View.VISIBLE);
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
        Intent i = new Intent(RegistrasiActivity.this, ActivityMain.class);
        startActivity(i);
        finish();
    }
    private void startAuthActivity() {
        Intent i = new Intent(RegistrasiActivity.this, AuthActivity.class);
        startActivity(i);
        finish();
    }

}
