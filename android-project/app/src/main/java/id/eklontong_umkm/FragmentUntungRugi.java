package id.eklontong_umkm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespDashboard;
import id.eklontong_umkm.connection.response.RespUntungRugi;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.Dashboard;
import id.eklontong_umkm.model.UntungRugi;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamDashboard;
import id.eklontong_umkm.utils.Tools;

public class FragmentUntungRugi extends Fragment {

    private View rootView;
    private TextView txt_total_pembelian, txt_total_pajak_pembelian, txt_total_pajak_pembelian_lainya,
            txt_total_diskon_pembelian, txt_total_jumlah_pembayaran_pembelian, txt_total_pengembalian_pembelian, txt_total_pajak_pengembalian_pembelian,
            txt_total_biaya_pengembalian_pembelian_lainya, txt_total_diskon_pengembalian_pembelian, txt_pembayaran_pengembalian_pembelian,
            txt_total_pengeluaran, txt_total_penjualan, txt_total_pajak_penjualan,
            txt_total_benan_penjualan_lainya, txt_total_diskon_pada_penjualan, txt_total_pembayaran_penjualan,
            txt_karena_penjualan, txt_total_pengembalian_penjualan, txt_total_pajak_penghasilan_penjualan,
            txt_total_beban_lain_dari_pengembalian_penjualan, txt_total_diskon_untuk_pengembalian_penjualan,
            txt_total_jumlah_pembayaran_pengembalian, txt_laba_kotor, txt_laba_bersih,
            txt_tanggal_mulai, txt_tanggal_selesai;
    private LinearLayout layout_form_add_produk, lyt_penjualan, lyt_pengeluaran, layout_add_tanggal_mulai, layout_add_tanggal_selesai;

    private UntungRugi untungRugi;

    public static FragmentUntungRugi instance() {
        return new FragmentUntungRugi();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_report_untung_rugi, container, false);

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

        txt_total_pembelian = rootView.findViewById(R.id.txt_total_pembelian);
        txt_total_pajak_pembelian = rootView.findViewById(R.id.txt_total_pajak_pembelian);
        txt_total_pajak_pembelian_lainya = rootView.findViewById(R.id.txt_total_pajak_pembelian_lainya);
        txt_total_diskon_pembelian = rootView.findViewById(R.id.txt_total_diskon_pembelian);
        txt_total_jumlah_pembayaran_pembelian = rootView.findViewById(R.id.txt_total_jumlah_pembayaran_pembelian);
        txt_total_pengembalian_pembelian = rootView.findViewById(R.id.txt_total_pengembalian_pembelian);
        txt_total_pajak_pengembalian_pembelian = rootView.findViewById(R.id.txt_total_pajak_pengembalian_pembelian);
        txt_total_biaya_pengembalian_pembelian_lainya = rootView.findViewById(R.id.txt_total_biaya_pengembalian_pembelian_lainya);
        txt_total_diskon_pengembalian_pembelian = rootView.findViewById(R.id.txt_total_diskon_pengembalian_pembelian);
        txt_pembayaran_pengembalian_pembelian = rootView.findViewById(R.id.txt_pembayaran_pengembalian_pembelian);
        txt_total_pengeluaran = rootView.findViewById(R.id.txt_total_pengeluaran);
        txt_total_penjualan = rootView.findViewById(R.id.txt_total_penjualan);
        txt_total_pajak_penjualan = rootView.findViewById(R.id.txt_total_pajak_penjualan);
        txt_total_benan_penjualan_lainya = rootView.findViewById(R.id.txt_total_benan_penjualan_lainya);
        txt_total_diskon_pada_penjualan = rootView.findViewById(R.id.txt_total_diskon_pada_penjualan);
        txt_total_pembayaran_penjualan = rootView.findViewById(R.id.txt_total_pembayaran_penjualan);
        txt_karena_penjualan = rootView.findViewById(R.id.txt_karena_penjualan);
        txt_total_pengembalian_penjualan = rootView.findViewById(R.id.txt_total_pengembalian_penjualan);
        txt_total_pajak_penghasilan_penjualan = rootView.findViewById(R.id.txt_total_pajak_penghasilan_penjualan);
        txt_total_beban_lain_dari_pengembalian_penjualan = rootView.findViewById(R.id.txt_total_beban_lain_dari_pengembalian_penjualan);
        txt_total_diskon_untuk_pengembalian_penjualan = rootView.findViewById(R.id.txt_total_diskon_untuk_pengembalian_penjualan);
        txt_total_jumlah_pembayaran_pengembalian = rootView.findViewById(R.id.txt_total_jumlah_pembayaran_pengembalian);
        txt_laba_kotor = rootView.findViewById(R.id.txt_laba_kotor);
        txt_laba_bersih = rootView.findViewById(R.id.txt_laba_bersih);
        layout_add_tanggal_mulai = rootView.findViewById(R.id.layout_add_tanggal_mulai);
        layout_add_tanggal_selesai = rootView.findViewById(R.id.layout_add_tanggal_selesai);
        txt_tanggal_mulai = rootView.findViewById(R.id.txt_tanggal_mulai);
        txt_tanggal_selesai = rootView.findViewById(R.id.txt_tanggal_selesai);

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
        new Request(getActivity()).showUntungRugi(paramDashboard, new RequestListener<RespUntungRugi>() {
            @Override
            public void onSuccess(RespUntungRugi resp) {
                untungRugi = resp.data;
                txt_total_pembelian.setText(untungRugi.pur_total);
                txt_total_pajak_pembelian.setText(untungRugi.purchase_tax_amt);
                txt_total_pajak_pembelian_lainya.setText(untungRugi.pur_other_charges_amt);
                txt_total_diskon_pembelian.setText(untungRugi.purchase_discount_amt);
                txt_total_jumlah_pembayaran_pembelian.setText(untungRugi.purchase_paid_amount);
                txt_total_pengembalian_pembelian.setText(untungRugi.pur_return_total);
                txt_total_pajak_pengembalian_pembelian.setText(untungRugi.purchase_return_tax_amt);
                txt_total_biaya_pengembalian_pembelian_lainya.setText(untungRugi.pur_return_other_charges_amt);
                txt_total_diskon_pengembalian_pembelian.setText(untungRugi.purchase_return_discount_amt);
                txt_pembayaran_pengembalian_pembelian.setText(untungRugi.purchase_return_paid_amount);
                txt_total_pengeluaran.setText(untungRugi.exp_total);
                txt_total_penjualan.setText(untungRugi.sal_total);
                txt_total_pajak_penjualan.setText(untungRugi.sales_tax_amt);
                txt_total_benan_penjualan_lainya.setText(untungRugi.sal_other_charges_amt);
                txt_total_diskon_pada_penjualan.setText(untungRugi.sales_discount_amt);
                txt_total_pembayaran_penjualan.setText(untungRugi.sales_paid_amount);
                txt_karena_penjualan.setText(untungRugi.sales_due_total);
                txt_total_pengembalian_penjualan.setText(untungRugi.sal_return_total);
                txt_total_pajak_penghasilan_penjualan.setText(untungRugi.sales_return_tax_amt);
                txt_total_beban_lain_dari_pengembalian_penjualan.setText(untungRugi.sal_return_other_charges_amt);
                txt_total_diskon_untuk_pengembalian_penjualan.setText(untungRugi.sales_return_discount_amt);
                txt_total_jumlah_pembayaran_pengembalian.setText(untungRugi.sales_return_paid_amount);
                txt_laba_kotor.setText(untungRugi.gross_profit);
                txt_laba_bersih.setText(untungRugi.tot_net_profit);
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