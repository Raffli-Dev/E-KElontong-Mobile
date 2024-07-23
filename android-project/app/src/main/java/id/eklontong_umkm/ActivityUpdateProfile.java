package id.eklontong_umkm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespPengeluaran;
import id.eklontong_umkm.connection.response.RespUser;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamPengeluaran;
import id.eklontong_umkm.model.param.ParamUpdateProfile;
import id.eklontong_umkm.utils.Tools;
import id.eklontong_umkm.utils.ViewAnimation;

public class ActivityUpdateProfile extends AppCompatActivity {
    private View bannerTop;

    String id_category = "";
    private TextView tvMessageBanner, sign_up;
    private TextInputEditText et_nama_lengkap, et_email, et_no_telepon, et_password;
    private RelativeLayout lyt_image;
    private AppCompatButton btn_submit;
    private ProgressDialog submitProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Form Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.primary_register_dark);

        initComponent();
    }
    private void initComponent() {

        bannerTop =  (View) findViewById(R.id.banner_top);
        tvMessageBanner = (TextView) findViewById(R.id.tv_message_banner);

        et_nama_lengkap = (TextInputEditText) findViewById(R.id.et_nama_lengkap);
        et_email = (TextInputEditText) findViewById(R.id.et_email);
        et_no_telepon = (TextInputEditText) findViewById(R.id.et_no_telepon);
        et_password = (TextInputEditText) findViewById(R.id.et_password);


        btn_submit = (AppCompatButton) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(v -> {
            delayAndRequest();
        });

        User user = DataApp.global().getUser();
        et_nama_lengkap.setText(user.name);
        et_email.setText(user.email);
        et_no_telepon.setText(user.phone);

        submitProgress = new ProgressDialog(this);
        submitProgress.setCancelable(false);
        submitProgress.setMessage("Loading...");

        showHideBanner(false, false, null);
    }


    private void delayAndRequest() {
        btn_submit.setVisibility(View.INVISIBLE);
        new Handler(getMainLooper()).postDelayed(() -> requestProductSaveOrUpdate(null, null), 250);
    }



    private void requestProductSaveOrUpdate(String provider, String image_url) {
        User user = DataApp.global().getUser();
        ParamUpdateProfile params = new ParamUpdateProfile();
        params.id = user.id.toString();
        params.name = et_nama_lengkap.getText().toString();
        params.email = et_email.getText().toString();
        params.phone = et_no_telepon.getText().toString();
        params.password = et_password.getText().toString();

        new Request(this).updateProfile(params, new RequestListener<RespUser>() {
            @Override
            public void onFinish() {
                btn_submit.setVisibility(View.VISIBLE);
                super.onFinish();
            }

            @Override
            public void onFailed(String messages) {
                showHideBanner(true, false, messages);
                if (DataApp.global().getUser().id == -1L) DataApp.global().logout();
                super.onFailed(messages);
            }

            @Override
            public void onSuccess(RespUser resp) {
                showHideBanner(true, true, getString(R.string.success));
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
        if (submitProgress.isShowing()) submitProgress.hide();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
