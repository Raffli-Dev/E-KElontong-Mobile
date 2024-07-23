package id.eklontong_umkm;

import android.app.Activity;
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

public class ActivityFormUpdateProduct extends AppCompatActivity {

    // activity transition
    public static void navigate(Context activity, ProductList object) {
        Intent i = new Intent(activity.getApplicationContext(), ActivityFormUpdateProduct.class);
        i.putExtra("OBJ", object);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(i);
    }
    ProductList productList;
    Product product;
    private RespProduct respProduct;
    private View bannerTop;
    private List<Category> category = new ArrayList<>();
    private List<UnitSatuan> unitSatuans = new ArrayList<>();

    String id_category = "";
    private MaterialAutoCompleteTextView dropdown_category, dropdown_unit_satuan;
    private MaterialButton btn_search_qrcode;
    private TextView tvMessageBanner, sign_up;
    private TextInputEditText et_nama_barang, et_harga_pembelian, et_harga_jual,
            et_kode_produk_barcode, et_jumlah_stok, et_stok_limit, et_tanggal_kadaluarsa,
            et_deskripsi_barang;
    private RelativeLayout lyt_image;
    private ImageView image;
    private AppCompatButton btn_submit;
    private Bitmap bitmap = null;

    private ProgressDialog submitProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_update_produk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Form Update Barang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.primary_register_dark);

        productList = (ProductList) getIntent().getSerializableExtra("OBJ");

        initComponent();
        loadDetails();
        requestCategories();

        requestUnitSatuan();
    }
    private void initComponent() {

        bannerTop =  (View) findViewById(R.id.banner_top);
        tvMessageBanner = (TextView) findViewById(R.id.tv_message_banner);

        dropdown_category = (MaterialAutoCompleteTextView) findViewById(R.id.dropdown_category);
        dropdown_unit_satuan = (MaterialAutoCompleteTextView) findViewById(R.id.dropdown_unit_satuan);

        et_nama_barang = (TextInputEditText) findViewById(R.id.et_nama_barang);
        et_harga_pembelian = (TextInputEditText) findViewById(R.id.et_harga_pembelian);
        et_harga_jual = (TextInputEditText) findViewById(R.id.et_harga_jual);
        et_kode_produk_barcode = (TextInputEditText) findViewById(R.id.et_kode_produk_barcode);
        et_jumlah_stok = (TextInputEditText) findViewById(R.id.et_jumlah_stok);
        et_stok_limit = (TextInputEditText) findViewById(R.id.et_stok_limit);
        et_tanggal_kadaluarsa = (TextInputEditText) findViewById(R.id.et_tanggal_kadaluarsa);
        et_deskripsi_barang = (TextInputEditText) findViewById(R.id.et_deskripsi_barang);
        lyt_image = (RelativeLayout) findViewById(R.id.lyt_image);
        image = (ImageView) findViewById(R.id.image);
        btn_submit = (AppCompatButton) findViewById(R.id.btn_submit);
        btn_search_qrcode = (MaterialButton) findViewById(R.id.btn_search_qrcode);

//        et_nama_barang.setText(product.item_name);


        dropdown_category.setOnClickListener(v -> showCategoryFilter());
        dropdown_unit_satuan.setOnClickListener(v -> showUnitSatuanFilter());

        lyt_image.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            selectImageResult.launch(intent);
        });

        btn_submit.setOnClickListener(v -> {
            delayAndRequest();
        });

        et_tanggal_kadaluarsa.setOnClickListener(new View.OnClickListener() {
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

    ActivityResultLauncher<Intent> selectImageResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            Uri image_uri = result.getData().getData();
            bitmap = AvatarUtils.getBitmapFormUri(getApplicationContext(), image_uri);
            if (bitmap != null) {
                Tools.displayImageOriginal(getApplicationContext(), image, bitmap);
            }
        }
    });

    private void loadDetails() {
        new Request(this).productDetails(productList.id, new RequestListener<RespProduct>() {
            @Override
            public void onSuccess(RespProduct resp) {
                respProduct = resp;
                product = resp.data;

                et_nama_barang.setText(product.item_name);
                et_harga_pembelian.setText(product.purchase_price);
                et_harga_jual.setText(product.final_price);
                et_kode_produk_barcode.setText(product.custom_barcode);
                et_jumlah_stok.setText(product.stock);
                et_stok_limit.setText(product.alert_qty);
                et_tanggal_kadaluarsa.setText(product.expire_date);
                et_deskripsi_barang.setText(product.description);
                dropdown_category.setText(product.category_id);
                dropdown_unit_satuan.setText(product.unit_id);
                Tools.displayImageOriginalUrl(getApplicationContext(), image, product.item_image);
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
        params.alert_qty = et_stok_limit.getText().toString();
        params.purchase_price = et_harga_pembelian.getText().toString();
        params.sales_price = et_harga_jual.getText().toString();
        params.new_opening_stock = et_jumlah_stok.getText().toString();
        params.category_id = dropdown_category.getText().toString();
        params.unit_id = dropdown_unit_satuan.getText().toString();
        params.custom_barcode = et_kode_produk_barcode.getText().toString();
        params.expire_date = et_tanggal_kadaluarsa.getText().toString();
        params.description = et_deskripsi_barang.getText().toString();
        params.company_id = user.company_id;
        params.users_id = Long.toString(user.id);
        if (image_url != null) params.image_url = image_url;

        new Request(this).productSave(bitmap, params, new RequestListener<RespProduct>() {
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
                Intent intent = new Intent(ActivityFormUpdateProduct.this, ActivityProduct.class);
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
                et_tanggal_kadaluarsa.setText(materialDatePicker.getHeaderText());
            }
        });
    }

    private void showCategoryFilter() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = new String[category.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = category.get(i).category_code+'-'+category.get(i).category_name;
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_drop_down, items);
        builder.setAdapter(arrayAdapter, (dialog, which) -> {
            dropdown_category.setText(arrayAdapter.getItem(which));
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showUnitSatuanFilter() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = new String[unitSatuans.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = unitSatuans.get(i).unit_name;
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_drop_down, items);
        builder.setAdapter(arrayAdapter, (dialog, which) -> {
            dropdown_unit_satuan.setText(arrayAdapter.getItem(which));
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void requestCategories() {
        ParamList params = new ParamList();
        params.page = "1";
        params.count = "1000";
        new Request(this).categories(params, new RequestListener<RespListCategory>() {
            @Override
            public void onSuccess(RespListCategory resp) {
                category = resp.list;
//                category.add(0, new Category(-1L, "", "","","",""));
            }
        });
    }
    private void requestUnitSatuan() {
        ParamList params = new ParamList();
        params.page = "1";
        params.count = "1000";
        new Request(this).unitSatuan(params, new RequestListener<RespListUnitSatuan>() {
            @Override
            public void onSuccess(RespListUnitSatuan resp) {
                unitSatuans = resp.list;
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
