package id.eklontong_umkm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespListCategory;
import id.eklontong_umkm.connection.response.RespListUnitSatuan;
import id.eklontong_umkm.connection.response.RespPengeluaran;
import id.eklontong_umkm.connection.response.RespProduct;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.Category;
import id.eklontong_umkm.model.Pengeluaran;
import id.eklontong_umkm.model.UnitSatuan;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamList;
import id.eklontong_umkm.model.param.ParamPengeluaran;
import id.eklontong_umkm.model.param.ParamProcust;
import id.eklontong_umkm.utils.AvatarUtils;
import id.eklontong_umkm.utils.Tools;
import id.eklontong_umkm.utils.ViewAnimation;

public class ActivityPengeluaranCreate extends AppCompatActivity {
    private View bannerTop;

    String id_category = "";
    private TextView tvMessageBanner, sign_up;
    private TextInputEditText et_tanggal_pengeluaran, et_biaya_untuk, et_jumlah,
            et_no_referensi, et_catatan;
    private RelativeLayout lyt_image;
    private ImageView image;
    private AppCompatButton btn_submit;
    private ProgressDialog submitProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_create_pengeluaran);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Form Tambah Pengeluaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.primary_register_dark);

        initComponent();
    }
    private void initComponent() {

        bannerTop =  (View) findViewById(R.id.banner_top);
        tvMessageBanner = (TextView) findViewById(R.id.tv_message_banner);

        et_tanggal_pengeluaran = (TextInputEditText) findViewById(R.id.et_tanggal_pengeluaran);
        et_biaya_untuk = (TextInputEditText) findViewById(R.id.et_biaya_untuk);
        et_jumlah = (TextInputEditText) findViewById(R.id.et_jumlah);
        et_no_referensi = (TextInputEditText) findViewById(R.id.et_no_referensi);
        et_catatan = (TextInputEditText) findViewById(R.id.et_catatan);

        btn_submit = (AppCompatButton) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(v -> {
            delayAndRequest();
        });

        et_tanggal_pengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateCreatedAt();
            }
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



    private void requestProductSaveOrUpdate(String provider, String image_url) {
        User user = DataApp.global().getUser();
        ParamPengeluaran params = new ParamPengeluaran();
        params.expense_date = et_tanggal_pengeluaran.getText().toString();
        params.reference_no = et_no_referensi.getText().toString();
        params.expense_for = et_biaya_untuk.getText().toString();
        params.expense_amt = et_jumlah.getText().toString();
        params.note = et_catatan.getText().toString();
        params.company_id = user.company_id;
        params.users_id = Long.toString(user.id);

        new Request(this).pengeluaranSave(params, new RequestListener<RespPengeluaran>() {
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
            public void onSuccess(RespPengeluaran resp) {
                showHideBanner(true, true, getString(R.string.success));
                Intent intent = new Intent(ActivityPengeluaranCreate.this, ActivityPengeluaran.class);
                startActivity(intent);
                finish();;
                super.onSuccess(resp);
            }
        });
    }

    private void showDateCreatedAt() {
        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Select date");
        MaterialDatePicker materialDatePicker = materialDateBuilder.build();
        materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                et_tanggal_pengeluaran.setText(materialDatePicker.getHeaderText());
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
