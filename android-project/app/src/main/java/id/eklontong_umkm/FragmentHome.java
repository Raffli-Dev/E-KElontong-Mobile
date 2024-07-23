package id.eklontong_umkm;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.tabs.TabLayout;

import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespDashboard;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.Dashboard;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamDashboard;
import id.eklontong_umkm.utils.Tools;

public class FragmentHome extends Fragment {
    private View rootView;
    private TextView txt_nama_perusahaan, txt_total_penjualan_hari_ini_title, txt_total_transaksi_penjualan_hari_ini,
            txt_total_produk, txt_total_penjualan_total, txt_total_penjualan_hari_ini_subtitle, txt_total_pengeluaran_hari_ini,
            txt_tanggal_update_terakhir_1, txt_tanggal_update_terakhir_2, txt_tanggal_update_terakhir_3, txt_tanggal_update_terakhir_4, txt_laba_kotor,
            txt_tanggal_update_laba_kotor, txt_laba_bersih, txt_tanggal_laba_bersih, txt_tanggal_mulai, txt_tanggal_selesai;
    private LinearLayout layout_form_add_produk, lyt_penjualan, lyt_pengeluaran, layout_add_tanggal_mulai, layout_add_tanggal_selesai,
            lyt_kategori_produk, lyt_satuan_barang, lyt_retur_penjualan;
    private NestedScrollView nested_scroll_view;

    private Dashboard dashboardModal;

    public static FragmentHome instance() {
        return new FragmentHome();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // launch instruction when first launch
        if (DataApp.pref().isFirstLaunch()) {
            DataApp.pref().setFirstLaunch(false);
        }

        initComponent();

        checkNewVersion();
        init_data();
        return rootView;
    }


    private void initComponent() {
        nested_scroll_view = (NestedScrollView) rootView.findViewById(R.id.nested_scroll_view);

        txt_nama_perusahaan = rootView.findViewById(R.id.txt_nama_perusahaan);
        txt_total_penjualan_hari_ini_title = rootView.findViewById(R.id.txt_total_penjualan_hari_ini_title);
        txt_total_transaksi_penjualan_hari_ini = rootView.findViewById(R.id.txt_total_transaksi_penjualan_hari_ini);
        txt_total_produk = rootView.findViewById(R.id.txt_total_produk);
        txt_total_penjualan_total = rootView.findViewById(R.id.txt_total_penjualan_total);
        txt_total_penjualan_hari_ini_subtitle =rootView.findViewById(R.id.txt_total_penjualan_hari_ini_subtitle);
        txt_total_pengeluaran_hari_ini = rootView.findViewById(R.id.txt_total_pengeluaran_hari_ini);
        txt_tanggal_update_terakhir_1 = rootView.findViewById(R.id.txt_tanggal_update_terakhir_1);
        txt_tanggal_update_terakhir_2 = rootView.findViewById(R.id.txt_tanggal_update_terakhir_2);
        txt_tanggal_update_terakhir_3 = rootView.findViewById(R.id.txt_tanggal_update_terakhir_3);
        txt_tanggal_update_terakhir_4 = rootView.findViewById(R.id.txt_tanggal_update_terakhir_4);
        txt_laba_kotor = rootView.findViewById(R.id.txt_laba_kotor);
        txt_laba_bersih = rootView.findViewById(R.id.txt_laba_bersih);
        layout_form_add_produk = rootView.findViewById(R.id.layout_form_add_produk);
        lyt_penjualan = rootView.findViewById(R.id.lyt_penjualan);
        lyt_pengeluaran = rootView.findViewById(R.id.lyt_pengeluaran);
        txt_tanggal_mulai = rootView.findViewById(R.id.txt_tanggal_mulai);
        txt_tanggal_selesai = rootView.findViewById(R.id.txt_tanggal_selesai);

        layout_add_tanggal_mulai = rootView.findViewById(R.id.layout_add_tanggal_mulai);
        layout_add_tanggal_selesai = rootView.findViewById(R.id.layout_add_tanggal_selesai);
        lyt_kategori_produk = rootView.findViewById(R.id.lyt_kategori_produk);
        lyt_satuan_barang = rootView.findViewById(R.id.lyt_satuan_barang);
        lyt_retur_penjualan = rootView.findViewById(R.id.lyt_retur_penjualan);


        layout_form_add_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ActivityProduct.class);
                startActivity(i);
            }
        });

        lyt_penjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ActivityPenjualan.class);
                startActivity(i);
            }
        });
        lyt_pengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ActivityPengeluaran.class);
                startActivity(i);
            }
        });
        lyt_kategori_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ActivityKategoriBarang.class);
                startActivity(i);
            }
        });

        lyt_satuan_barang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ActivitySatuanBarang.class);
                startActivity(i);
            }
        });

        lyt_retur_penjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ActivityReturPenjualan.class);
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
            Intent i = new Intent(getActivity(), AuthActivity.class);
            startActivity(i);
            getActivity().finish();
        }
    }

    private void showDashboard() {
        User user = DataApp.global().getUser();
        ParamDashboard paramDashboard = new ParamDashboard();
        paramDashboard.company_id = user.company_id;
        paramDashboard.users_id = user.id;
        paramDashboard.tanggal_mulai = txt_tanggal_mulai.getText().toString();
        paramDashboard.tanggal_selesai = txt_tanggal_selesai.getText().toString();
        new Request(getActivity()).showDashboard(paramDashboard, new RequestListener<RespDashboard>() {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.title_info);
        builder.setMessage(R.string.msg_app_new_version);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.UPDATE, (dialog, which) -> {
            dialog.dismiss();
            Tools.rateAction(getActivity());
            getActivity().finish();
        });
        builder.setNegativeButton(R.string.Skip, null);
        builder.show();
    }
    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getActivity(), R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            getActivity().finish();
        }
    }

    static boolean active = false;

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    public void dialogFailed(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
        if (!Tools.isConnect(getActivity())) {
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
            materialDatePicker.show(getActivity().getSupportFragmentManager(), "DATE_PICKER");
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
            materialDatePicker.show(getActivity().getSupportFragmentManager(), "DATE_PICKER");
            materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                @Override
                public void onPositiveButtonClick(Object selection) {
                    txt_tanggal_selesai.setText(materialDatePicker.getHeaderText());
                    showDashboard();
                }
            });
        }
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        validateSession();
    }
}
