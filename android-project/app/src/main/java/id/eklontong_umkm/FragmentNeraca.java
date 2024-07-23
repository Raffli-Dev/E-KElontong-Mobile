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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespNeraca;
import id.eklontong_umkm.connection.response.RespUntungRugi;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.Neraca;
import id.eklontong_umkm.model.UntungRugi;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamDashboard;
import id.eklontong_umkm.utils.Tools;

public class FragmentNeraca extends Fragment {
    private View rootView;
    private TextView txt_aset_lancar_kas, txt_aset_lancar_bank, txt_aset_lancar_piutang_usaha, txt_aset_lancar_persediaan_barang_dagang,
            txt_aset_lancar_uang_muka_biaya, txt_aset_tetap_tanah, txt_aset_tetap_bangunan,
            txt_aset_tetap_akumulasi_penyusutan_bangunan, txt_aset_tetap_peralatan, txt_aset_tetap_akumulasi_penyusutan_peralatan,
            txt_total_aset, txt_kewajiban_beban_yang_harus_dibayar, txt_kewajiban_hutang_pajak,
            txt_kewajiban_hutang_usaha, txt_kewajiban_hutang_bank, txt_modal_pemilik, txt_modal_laba_berjalan, txt_modal_total_kewajiban_modal,
            txt_tanggal_mulai, txt_tanggal_selesai;
    private RecyclerView recyclerView;
    private LinearLayout layout_add_tanggal_mulai, layout_add_tanggal_selesai;

    private Neraca neraca;

    public static FragmentNeraca instance() {
        return new FragmentNeraca();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_neraca, container, false);

        // launch instruction when first launch
        if (DataApp.pref().isFirstLaunch()) {
            DataApp.pref().setFirstLaunch(false);
        }

        initComponent();
//
//        checkNewVersion();
        init_data();
        return rootView;
    }
    private void initComponent() {
        txt_tanggal_mulai = rootView.findViewById(R.id.txt_tanggal_mulai);
        txt_tanggal_selesai = rootView.findViewById(R.id.txt_tanggal_selesai);

        txt_aset_lancar_kas = rootView.findViewById(R.id.txt_aset_lancar_kas);
        txt_aset_lancar_bank = rootView.findViewById(R.id.txt_aset_lancar_bank);
        txt_aset_lancar_piutang_usaha = rootView.findViewById(R.id.txt_aset_lancar_piutang_usaha);
        txt_aset_lancar_persediaan_barang_dagang = rootView.findViewById(R.id.txt_aset_lancar_persediaan_barang_dagang);
        txt_aset_lancar_uang_muka_biaya = rootView.findViewById(R.id.txt_aset_lancar_uang_muka_biaya);
        txt_aset_tetap_tanah = rootView.findViewById(R.id.txt_aset_tetap_tanah);
        txt_aset_tetap_bangunan = rootView.findViewById(R.id.txt_aset_tetap_bangunan);
        txt_aset_tetap_akumulasi_penyusutan_bangunan = rootView.findViewById(R.id.txt_aset_tetap_akumulasi_penyusutan_bangunan);
        txt_aset_tetap_peralatan = rootView.findViewById(R.id.txt_aset_tetap_peralatan);
        txt_aset_tetap_akumulasi_penyusutan_peralatan = rootView.findViewById(R.id.txt_aset_tetap_akumulasi_penyusutan_peralatan);
        txt_total_aset = rootView.findViewById(R.id.txt_total_aset);
        txt_kewajiban_beban_yang_harus_dibayar = rootView.findViewById(R.id.txt_kewajiban_beban_yang_harus_dibayar);
        txt_kewajiban_hutang_pajak = rootView.findViewById(R.id.txt_kewajiban_hutang_pajak);
        txt_kewajiban_hutang_usaha = rootView.findViewById(R.id.txt_kewajiban_hutang_usaha);
        txt_kewajiban_hutang_bank = rootView.findViewById(R.id.txt_kewajiban_hutang_bank);
        txt_modal_pemilik = rootView.findViewById(R.id.txt_modal_pemilik);
        txt_modal_laba_berjalan = rootView.findViewById(R.id.txt_modal_laba_berjalan);
        txt_modal_total_kewajiban_modal = rootView.findViewById(R.id.txt_modal_total_kewajiban_modal);
        layout_add_tanggal_mulai = rootView.findViewById(R.id.layout_add_tanggal_mulai);
        layout_add_tanggal_selesai = rootView.findViewById(R.id.layout_add_tanggal_selesai);

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

        new Request(getActivity()).showNeraca(paramDashboard, new RequestListener<RespNeraca>() {
            @Override
            public void onSuccess(RespNeraca resp) {
                neraca = resp.data;
                txt_aset_lancar_kas.setText(neraca.aset_lancar_kas);
                txt_aset_lancar_bank.setText(neraca.aset_lancar_bank);
                txt_aset_lancar_piutang_usaha.setText(neraca.aset_lancar_piutang_usaha);
                txt_aset_lancar_persediaan_barang_dagang.setText(neraca.aset_lancar_persediaan_barang_dagang);
                txt_aset_lancar_uang_muka_biaya.setText(neraca.aset_lancar_uang_muka_biaya);
                txt_aset_tetap_tanah.setText(neraca.aset_tetap_tanah);
                txt_aset_tetap_bangunan.setText(neraca.aset_tetap_bangunan);
                txt_aset_tetap_peralatan.setText(neraca.aset_tetap_peralatan);
                txt_kewajiban_beban_yang_harus_dibayar.setText(neraca.kewajiban_beban_yang_harus_dibayar);
                txt_kewajiban_hutang_pajak.setText(neraca.kewajiban_hutang_pajak);
                txt_kewajiban_hutang_usaha.setText(neraca.kewajiban_hutang_usaha);
                txt_kewajiban_hutang_bank.setText(neraca.kewajiban_hutang_bank);
                txt_modal_pemilik.setText(neraca.modal_pemilik);
                txt_modal_laba_berjalan.setText(neraca.modal_laba_berjalan);
                txt_total_aset.setText(neraca.total_aset);
                txt_modal_total_kewajiban_modal.setText(neraca.total_kewajiban_dan_modal);
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
