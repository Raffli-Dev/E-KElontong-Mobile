package id.eklontong_umkm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.ParamUpdateCompany;
import id.eklontong_umkm.connection.response.RespCompanyDetail;
import id.eklontong_umkm.connection.response.RespDashboard;
import id.eklontong_umkm.connection.response.RespUser;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.Company;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamDashboard;
import id.eklontong_umkm.model.param.ParamUpdateProfile;
import id.eklontong_umkm.utils.Tools;
import id.eklontong_umkm.utils.ViewAnimation;

public class ActivityCompanyUpdate extends AppCompatActivity {
    private View bannerTop;

    String id_category = "";
    private TextView tvMessageBanner, sign_up;
    private TextInputEditText et_owner, et_company_name, et_mobile, et_email, et_alamat;
    private RelativeLayout lyt_image;
    private AppCompatButton btn_submit;
    private ProgressDialog submitProgress;

    private Company company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_company);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Form Edit Company");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.primary_register_dark);

        initComponent();
        showDetailCompany();
    }
    private void initComponent() {

        bannerTop =  (View) findViewById(R.id.banner_top);
        tvMessageBanner = (TextView) findViewById(R.id.tv_message_banner);

        et_owner = (TextInputEditText) findViewById(R.id.et_owner);
        et_company_name = (TextInputEditText) findViewById(R.id.et_company_name);
        et_mobile = (TextInputEditText) findViewById(R.id.et_mobile);
        et_email = (TextInputEditText) findViewById(R.id.et_email);
        et_alamat = (TextInputEditText) findViewById(R.id.et_alamat);


        btn_submit = (AppCompatButton) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(v -> {
            delayAndRequest();
        });


        submitProgress = new ProgressDialog(this);
        submitProgress.setCancelable(false);
        submitProgress.setMessage("Loading...");

        showHideBanner(false, false, null);
    }


    private void delayAndRequest() {
        btn_submit.setVisibility(View.INVISIBLE);
        new Handler(getMainLooper()).postDelayed(() -> requestProductSaveOrUpdate(null, null), 250);
    }


    private void showDetailCompany() {
        User user = DataApp.global().getUser();
        ParamDashboard paramDashboard = new ParamDashboard();
        paramDashboard.company_id = user.company_id;
        paramDashboard.users_id = user.id;
        new Request(this).showCompanyDetail(paramDashboard, new RequestListener<RespCompanyDetail>() {
            @Override
            public void onSuccess(RespCompanyDetail resp) {
                company = resp.data;

                et_owner.setText(company.owner);
                et_company_name.setText(company.company_name);
                et_mobile.setText(company.mobile);
                et_email.setText(company.email);
                et_alamat.setText(company.address);


            }

            @Override
            public void onFailed(String messages) {
                dialogFailed(getString(R.string.unable_connect_server));
            }
        });
    }

    public void dialogFailed(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.failed);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.RETRY, (dialog, which) -> {
            dialog.dismiss();
            retryOpenApplication();
        });
        builder.show();
    }
    private void retryOpenApplication() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> showDetailCompany(), 1000);
    }
    private void requestProductSaveOrUpdate(String provider, String image_url) {
        User user = DataApp.global().getUser();
        ParamUpdateCompany params = new ParamUpdateCompany();
        params.id = user.id.toString();
        params.owner = et_owner.getText().toString();
        params.company_name = et_company_name.getText().toString();
        params.mobile = et_mobile.getText().toString();
        params.address = et_alamat.getText().toString();
        params.email = et_email.getText().toString();
        params.company_id = user.company_id;
        params.users_id = user.id.toString();

        new Request(this).updateCompany(params, new RequestListener<RespCompanyDetail>() {
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
            public void onSuccess(RespCompanyDetail resp) {
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
