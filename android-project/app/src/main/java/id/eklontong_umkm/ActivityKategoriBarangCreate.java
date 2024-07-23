package id.eklontong_umkm;

import android.app.ProgressDialog;
import android.content.Context;
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
import id.eklontong_umkm.connection.response.RespKategoriBarang;
import id.eklontong_umkm.connection.response.RespPengeluaran;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.KategoriBarang;
import id.eklontong_umkm.model.ProductList;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamKategoriBarang;
import id.eklontong_umkm.model.param.ParamPengeluaran;
import id.eklontong_umkm.utils.Tools;
import id.eklontong_umkm.utils.ViewAnimation;

public class ActivityKategoriBarangCreate extends AppCompatActivity {

    public static void navigate(Context activity, KategoriBarang object) {
        Intent i = new Intent(activity.getApplicationContext(), ActivityKategoriBarangCreate.class);
        i.putExtra("OBJ", object);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(i);
    }

    private View bannerTop;
    KategoriBarang kategoriBaranglist;
    String id_category = "";
    private TextView tvMessageBanner, sign_up;
    private TextInputEditText et_nama_kategori, et_deskripsi;
    private RelativeLayout lyt_image;
    private ImageView image;
    private AppCompatButton btn_submit;
    private ProgressDialog submitProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_barang_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Form Kategori Barang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.primary_register_dark);

        initComponent();
    }
    private void initComponent() {

        bannerTop =  (View) findViewById(R.id.banner_top);
        tvMessageBanner = (TextView) findViewById(R.id.tv_message_banner);

        et_nama_kategori = (TextInputEditText) findViewById(R.id.et_nama_kategori);
        et_deskripsi = (TextInputEditText) findViewById(R.id.et_deskripsi);

        btn_submit = (AppCompatButton) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(v -> {
            delayAndRequest();
        });


        kategoriBaranglist = (KategoriBarang) getIntent().getSerializableExtra("OBJ");

        if (kategoriBaranglist != null) {
            et_nama_kategori.setText(kategoriBaranglist.category_name);
            et_deskripsi.setText(kategoriBaranglist.description);
        }


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
        ParamKategoriBarang params = new ParamKategoriBarang();
        if (kategoriBaranglist != null) {
            params.id = kategoriBaranglist.id;
        } else {
            params.id = "";
        }

        params.category_name = et_nama_kategori.getText().toString();
        params.description = et_deskripsi.getText().toString();
        params.company_id = user.company_id;
        params.users_id = Long.toString(user.id);

        new Request(this).kategoriBarangSave(params, new RequestListener<RespKategoriBarang>() {
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
            public void onSuccess(RespKategoriBarang resp) {
                showHideBanner(true, true, getString(R.string.success));
                Intent intent = new Intent(ActivityKategoriBarangCreate.this, ActivityKategoriBarang.class);
                startActivity(intent);
                finish();;
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
