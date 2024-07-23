package id.eklontong_umkm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.tabs.TabLayout;

import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespDashboard;
import id.eklontong_umkm.connection.response.RespInfo;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.Dashboard;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamDashboard;
import id.eklontong_umkm.utils.Tools;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab_layout;
    private TextView txt_nama_perusahaan, txt_total_penjualan_hari_ini_title, txt_total_transaksi_penjualan_hari_ini,
            txt_total_produk, txt_total_penjualan_total, txt_total_penjualan_hari_ini_subtitle, txt_total_pengeluaran_hari_ini,
            txt_tanggal_update_terakhir_1, txt_tanggal_update_terakhir_2, txt_tanggal_update_terakhir_3, txt_tanggal_update_terakhir_4, txt_laba_kotor,
            txt_tanggal_update_laba_kotor, txt_laba_bersih, txt_tanggal_laba_bersih, txt_tanggal_mulai, txt_tanggal_selesai;
    private LinearLayout layout_form_add_produk, lyt_penjualan, lyt_pengeluaran, layout_add_tanggal_mulai, layout_add_tanggal_selesai;
    private NestedScrollView nested_scroll_view;

    private Dashboard dashboardModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // launch instruction when first launch
        if (DataApp.pref().isFirstLaunch()) {
            DataApp.pref().setFirstLaunch(false);
        }

        initToolbar();
        initComponent();


        checkNewVersion();
        init_data();
        Tools.RTLMode(getWindow());

    }
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        Tools.setSystemBarColor(this, R.color.primary_register);
    }
    private void initComponent() {
        nested_scroll_view = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);

        txt_nama_perusahaan = (TextView)findViewById(R.id.txt_nama_perusahaan);
        txt_total_penjualan_hari_ini_title = (TextView) findViewById(R.id.txt_total_penjualan_hari_ini_title);
        txt_total_transaksi_penjualan_hari_ini = (TextView) findViewById(R.id.txt_total_transaksi_penjualan_hari_ini);
        txt_total_produk = (TextView) findViewById(R.id.txt_total_produk);
        txt_total_penjualan_total = (TextView) findViewById(R.id.txt_total_penjualan_total);
        txt_total_penjualan_hari_ini_subtitle = (TextView) findViewById(R.id.txt_total_penjualan_hari_ini_subtitle);
        txt_total_pengeluaran_hari_ini = (TextView) findViewById(R.id.txt_total_pengeluaran_hari_ini);
        txt_tanggal_update_terakhir_1 = (TextView) findViewById(R.id.txt_tanggal_update_terakhir_1);
        txt_tanggal_update_terakhir_2 = (TextView) findViewById(R.id.txt_tanggal_update_terakhir_2);
        txt_tanggal_update_terakhir_3 = (TextView) findViewById(R.id.txt_tanggal_update_terakhir_3);
        txt_tanggal_update_terakhir_4 = (TextView) findViewById(R.id.txt_tanggal_update_terakhir_4);
        txt_laba_kotor = (TextView) findViewById(R.id.txt_laba_kotor);
        txt_laba_bersih = (TextView) findViewById(R.id.txt_laba_bersih);
        layout_form_add_produk = (LinearLayout) findViewById(R.id.layout_form_add_produk);
        lyt_penjualan = (LinearLayout) findViewById(R.id.lyt_penjualan);
        lyt_pengeluaran = (LinearLayout) findViewById(R.id.lyt_pengeluaran);
        txt_tanggal_mulai = findViewById(R.id.txt_tanggal_mulai);
        txt_tanggal_selesai = findViewById(R.id.txt_tanggal_selesai);

        layout_add_tanggal_mulai = (LinearLayout) findViewById(R.id.layout_add_tanggal_mulai);
        layout_add_tanggal_selesai = (LinearLayout) findViewById(R.id.layout_add_tanggal_selesai);


        layout_form_add_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityProduct.class);
                startActivity(i);
            }
        });

        lyt_penjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityPenjualan.class);
                startActivity(i);
            }
        });
        lyt_pengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityPengeluaran.class);
                startActivity(i);
            }
        });




        layout_add_tanggal_mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate("Tanggal Mulai");
            }
        });
        layout_add_tanggal_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate("Tanggal Selesai");
            }
        });


        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.baseline_home_24), 0);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.baseline_calendar_month_24), 1);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.baseline_chrome_reader_mode_24), 2);
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.ic_person), 3);

        // set icon color pre-selected
        tab_layout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.primary_register_dark), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
        tab_layout.getTabAt(3).getIcon().setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.primary_register_dark), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    private void init_data(){

    }
    public void validateSession() {
        if (DataApp.global().isLogin()) {
            User user = DataApp.global().getUser();
            showDashboard();
        } else {
            validateUserSession();
        }

    }
    public void validateUserSession() {
        boolean is_login = DataApp.global().isLogin();
        User user = DataApp.global().getUser();


        if (is_login) {
            showDashboard();
        } else {
            Intent i = new Intent(MainActivity.this, AuthActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void showDashboard() {
        User user = DataApp.global().getUser();
        ParamDashboard paramDashboard = new ParamDashboard();
        paramDashboard.company_id = user.company_id;
        paramDashboard.users_id = user.id;
        paramDashboard.tanggal_mulai = txt_tanggal_mulai.getText().toString();
        paramDashboard.tanggal_selesai = txt_tanggal_selesai.getText().toString();
        new Request(this).showDashboard(paramDashboard, new RequestListener<RespDashboard>() {
            @Override
            public void onSuccess(RespDashboard resp) {
                dashboardModal = resp.data;
                txt_nama_perusahaan.setText(dashboardModal.company_name);
                txt_total_penjualan_hari_ini_title.setText(Tools.formatRupiah(Integer.parseInt(dashboardModal.total_penjualan_hari_ini)));
                txt_total_transaksi_penjualan_hari_ini.setText(dashboardModal.total_transaksi_penjualan_hari_ini + " Transaksi");
                txt_total_produk.setText(dashboardModal.total_produk);
                txt_total_penjualan_total.setText(Tools.formatRupiah(Integer.parseInt(dashboardModal.jumlah_penjualan_total)));
                txt_total_penjualan_hari_ini_subtitle.setText(Tools.formatRupiah(Integer.parseInt(dashboardModal.total_penjualan_hari_ini)));
                txt_total_pengeluaran_hari_ini.setText(Tools.formatRupiah(Integer.parseInt(dashboardModal.total_pengeluaran_hari_ini)));
                txt_tanggal_update_terakhir_1.setText(dashboardModal.tanggal_update_terakhir);
                txt_tanggal_update_terakhir_2.setText(dashboardModal.tanggal_update_terakhir);
                txt_tanggal_update_terakhir_3.setText(dashboardModal.tanggal_update_terakhir);
                txt_tanggal_update_terakhir_4.setText(dashboardModal.tanggal_update_terakhir);

                txt_laba_kotor.setText(dashboardModal.laba_kotor);
                txt_laba_bersih.setText(dashboardModal.laba_bersih);
            }

            @Override
            public void onFailed(String messages) {
                dialogFailed(getString(R.string.unable_connect_server));
            }
        });
    }

    public void checkNewVersion() {
        if (DataApp.pref().getAppStatus().equals("SUGGEST-UPDATE")) {
            showNewVersion();
        }
    }
    private void showNewVersion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_info);
        builder.setMessage(R.string.msg_app_new_version);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.UPDATE, (dialog, which) -> {
            dialog.dismiss();
            Tools.rateAction(MainActivity.this);
            finish();
        });
        builder.setNegativeButton(R.string.Skip, null);
        builder.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        validateSession();
    }
    @Override
    public void onBackPressed() {
        doExitApp();
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    static boolean active = false;

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        active = false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            startProcess();
        }
        return super.onOptionsItemSelected(item);
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
    private void startProcess() {
        if (!Tools.isConnect(this)) {
            dialogFailed(getString(R.string.no_internet_connection));
        } else {
            if (DataApp.global().isLogin()) {
                User user = DataApp.global().getUser();
                showDashboard();
            } else {
                validateUserSession();
            }
        }
    }
    private void retryOpenApplication() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> startProcess(), 1000);
    }

    private void showDate(String value) {
        if(value == "Tanggal Mulai") {
            MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
            materialDateBuilder.setTitleText("Select date");
            MaterialDatePicker materialDatePicker = materialDateBuilder.build();
            materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                @Override
                public void onPositiveButtonClick(Object selection) {
                    txt_tanggal_mulai.setText(materialDatePicker.getHeaderText());
                    showDashboard();
                }
            });
        }
        if(value == "Tanggal Selesai") {
            MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
            materialDateBuilder.setTitleText("Select date");
            MaterialDatePicker materialDatePicker = materialDateBuilder.build();
            materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                @Override
                public void onPositiveButtonClick(Object selection) {
                    txt_tanggal_selesai.setText(materialDatePicker.getHeaderText());
                    showDashboard();
                }
            });
        }
    }
}