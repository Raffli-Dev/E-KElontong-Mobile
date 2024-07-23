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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.List;

import id.eklontong_umkm.adapter.AdapterPengeluaran;
import id.eklontong_umkm.adapter.AdapterPengeluaranLabaRugi;
import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespUntungRugi;
import id.eklontong_umkm.data.Constant;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.Pengeluaran;
import id.eklontong_umkm.model.UntungRugi;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamDashboard;
import id.eklontong_umkm.utils.Tools;

public class FragmentUntungRugiV2 extends Fragment {
    private View rootView;
    private TextView txt_total_penjualan_bersih, txt_harga_pokok_penjualan, txt_total_laba_kotor,
            txt_total_beban_usaha, txt_tanggal_mulai, txt_tanggal_selesai, txt_total_laba_usaha, txt_laba_bersih_sebelum_pajak, price_total;
    private RecyclerView recyclerView;
    private LinearLayout layout_add_tanggal_mulai, layout_add_tanggal_selesai;

    private UntungRugi untungRugi;

    private AdapterPengeluaranLabaRugi adapter;

    public static FragmentUntungRugiV2 instance() {
        return new FragmentUntungRugiV2();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_untung_rugi_v2, container, false);

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

        txt_total_penjualan_bersih = rootView.findViewById(R.id.txt_total_penjualan_bersih);
        txt_harga_pokok_penjualan = rootView.findViewById(R.id.txt_harga_pokok_penjualan);
        txt_total_laba_kotor = rootView.findViewById(R.id.txt_total_laba_kotor);
        txt_tanggal_mulai = rootView.findViewById(R.id.txt_tanggal_mulai);
        txt_tanggal_selesai = rootView.findViewById(R.id.txt_tanggal_selesai);
        txt_total_beban_usaha = rootView.findViewById(R.id.txt_total_beban_usaha);
        txt_total_laba_usaha = rootView.findViewById(R.id.txt_total_laba_usaha);
        txt_laba_bersih_sebelum_pajak = rootView.findViewById(R.id.txt_laba_bersih_sebelum_pajak);
        price_total = rootView.findViewById(R.id.price_total);
        layout_add_tanggal_mulai = rootView.findViewById(R.id.layout_add_tanggal_mulai);
        layout_add_tanggal_selesai = rootView.findViewById(R.id.layout_add_tanggal_selesai);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        adapter = new AdapterPengeluaranLabaRugi(getActivity().getApplicationContext(), recyclerView, Constant.NEWS_EVENT_PAGE);
        recyclerView.setAdapter(adapter);

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
        adapter.resetListData();
        new Request(getActivity()).showUntungRugi(paramDashboard, new RequestListener<RespUntungRugi>() {
            @Override
            public void onSuccess(RespUntungRugi resp) {
                untungRugi = resp.data;
                txt_total_penjualan_bersih.setText(untungRugi.sal_total);
                txt_harga_pokok_penjualan.setText(untungRugi.total_purchase_price);
                txt_total_laba_kotor.setText(untungRugi.total_laba_kotor);
                txt_total_beban_usaha.setText(untungRugi.exp_total);
                txt_total_laba_usaha.setText(untungRugi.laba_usaha);
                txt_laba_bersih_sebelum_pajak.setText(untungRugi.laba_usaha);
                price_total.setText(untungRugi.laba_usaha);

                displayApiResult(resp.data.expense_list);
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

    private void displayApiResult(final List<Pengeluaran> items) {
        adapter.insertData(items);
    }
}
