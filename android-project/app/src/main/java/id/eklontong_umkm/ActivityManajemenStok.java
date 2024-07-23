package id.eklontong_umkm;

import android.app.ProgressDialog;
import android.content.Context;
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
import androidx.appcompat.widget.AppCompatSpinner;
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
import id.eklontong_umkm.connection.response.RespProduct;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.Category;
import id.eklontong_umkm.model.Product;
import id.eklontong_umkm.model.ProductList;
import id.eklontong_umkm.model.UnitSatuan;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamList;
import id.eklontong_umkm.model.param.ParamProcust;
import id.eklontong_umkm.utils.AvatarUtils;
import id.eklontong_umkm.utils.Tools;
import id.eklontong_umkm.utils.ViewAnimation;

public class ActivityManajemenStok extends AppCompatActivity {
    // activity transition
    public static void navigate(Context activity, ProductList object) {
        Intent i = new Intent(activity.getApplicationContext(), ActivityManajemenStok.class);
        i.putExtra("OBJ", object);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(i);
    }
    ProductList productList;
    Product product;
    private View bannerTop;

    private AppCompatSpinner dropdown_pilih_type;
    private TextView tvMessageBanner;
    private TextInputEditText et_nama_barang, et_jumlah_stok;

    private AppCompatButton btn_submit;

    private ProgressDialog submitProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manajemen_stok_produk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Form Manajemen Stok");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.primary_register_dark);

        productList = (ProductList) getIntent().getSerializableExtra("OBJ");

        initComponent();
        loadDetails();
        show_type();
    }
    private void initComponent() {

        bannerTop =  (View) findViewById(R.id.banner_top);
        tvMessageBanner = (TextView) findViewById(R.id.tv_message_banner);

        dropdown_pilih_type = (AppCompatSpinner) findViewById(R.id.dropdown_pilih_type);

        et_nama_barang = (TextInputEditText) findViewById(R.id.et_nama_barang);
        et_jumlah_stok = (TextInputEditText) findViewById(R.id.et_jumlah_stok);

        btn_submit = (AppCompatButton) findViewById(R.id.btn_submit);




        btn_submit.setOnClickListener(v -> {
            delayAndRequest();
        });


        submitProgress = new ProgressDialog(this);
        submitProgress.setCancelable(false);
        submitProgress.setMessage("Loading...");

        showHideBanner(false, false, null);
    }
    private void loadDetails() {
        new Request(this).productDetails(productList.id, new RequestListener<RespProduct>() {
            @Override
            public void onSuccess(RespProduct resp) {
                product = resp.data;

                et_nama_barang.setText(product.item_name);
            }

            @Override
            public void onFailed(String messages) {

            }
        });
    }
    private void delayAndRequest() {
        btn_submit.setVisibility(View.INVISIBLE);
        new Handler(getMainLooper()).postDelayed(() -> requestProductSaveOrUpdate(null, null), 250);
    }
    private void requestProductSaveOrUpdate(String provider, String image_url) {
        User user = DataApp.global().getUser();
        ParamProcust params = new ParamProcust();
        params.id = productList.id;
        params.item_name = et_nama_barang.getText().toString();
        params.new_opening_stock = et_jumlah_stok.getText().toString();
        params.company_id = user.company_id;
        params.type = dropdown_pilih_type.getSelectedItem().toString();
        params.users_id = Long.toString(user.id);
        if (image_url != null) params.image_url = image_url;

        new Request(this).userRegister(params, new RequestListener<RespProduct>() {
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
            public void onSuccess(RespProduct resp) {
                showHideBanner(true, true, getString(R.string.success));
                Intent intent = new Intent(ActivityManajemenStok.this, ActivityProduct.class);
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

    private void show_type(){
        String[] timezones = getResources().getStringArray(R.array.penambahan_pengurangan_stok);
        ArrayAdapter<String> array = new ArrayAdapter<>(this, R.layout.simple_spinner_item, timezones);
        array.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        dropdown_pilih_type.setAdapter(array);
        dropdown_pilih_type.setSelection(0);
    }
}
