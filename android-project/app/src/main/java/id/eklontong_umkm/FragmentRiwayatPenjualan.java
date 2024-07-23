package id.eklontong_umkm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.List;

import id.eklontong_umkm.adapter.AdapterListener;
import id.eklontong_umkm.adapter.AdapterRiwayatPenjualan;
import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespRiwayatPenjualan;
import id.eklontong_umkm.data.Constant;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.Sales;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamList;
import id.eklontong_umkm.utils.Tools;

public class FragmentRiwayatPenjualan extends Fragment {
    private View rootView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;

    private AdapterRiwayatPenjualan adapter;

    private boolean allLoaded = false;
    private int failedPage = 0;

    private TextView txt_tanggal_mulai, txt_tanggal_selesai;
    private LinearLayout layout_form_add_produk, lyt_penjualan, lyt_pengeluaran, layout_add_tanggal_mulai, layout_add_tanggal_selesai;

    public static FragmentRiwayatPenjualan instance() {
        return new FragmentRiwayatPenjualan();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_riwayat_penjualan, container, false);
        initComponent();

        requestAction(1);
        return rootView;
    }

    private void initComponent() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        swipeRefresh = rootView.findViewById(R.id.swipe_refresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        txt_tanggal_mulai = rootView.findViewById(R.id.txt_tanggal_mulai);
        txt_tanggal_selesai = rootView.findViewById(R.id.txt_tanggal_selesai);

        layout_add_tanggal_mulai = rootView.findViewById(R.id.layout_add_tanggal_mulai);
        layout_add_tanggal_selesai = rootView.findViewById(R.id.layout_add_tanggal_selesai);

        //set data and list adapter
        adapter = new AdapterRiwayatPenjualan(getActivity(), recyclerView, Constant.NEWS_EVENT_PAGE);
        recyclerView.setAdapter(adapter);

        // detect when scroll reach bottom
        adapter.setListener(new AdapterListener<Sales>(){
            @Override
            public void onClick(View view, String type, Sales obj, int position) {
                super.onClick(view, type, obj, position);
//                ActivityNewsEventDetails.navigate(getActivity(), obj);
                ActivityNota.navigate(getActivity().getApplicationContext(), obj.id);
                try {

                } catch (Exception e) {
                }
            }

            @Override
            public void onLoadMore(int page) {
                super.onLoadMore(page);
                if(allLoaded){
                    adapter.setLoaded();
                } else {
                    int next_page = page + 1;
                    requestAction(next_page);
                }
            }
        });

        swipeRefresh.setOnRefreshListener(() -> {
            allLoaded = false;
            failedPage = 0;
            adapter.resetListData();
            requestAction(1);
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

    private void requestAction(final int page_no) {
        if (page_no == 1) {
            showNoItemView(false);
            swipeProgress(true);
        } else {
            adapter.setLoadingOrFailed(null);
        }
        requestRIwayatPenjualan(page_no);
    }

    private void requestRIwayatPenjualan(final Integer page_no) {
        User user = DataApp.global().getUser();
        ParamList params = new ParamList();
        params.users_id = user.id.toString();
        params.company_id = user.company_id;
        params.tanggal_mulai = txt_tanggal_mulai.getText().toString();
        params.tanggal_selesai = txt_tanggal_selesai.getText().toString();
        params.count = Constant.NEWS_EVENT_PAGE.toString();
        new Request(getActivity()).riwayatPenjualan(params, new RequestListener<RespRiwayatPenjualan>() {
            @Override
            public void onSuccess(RespRiwayatPenjualan resp) {
                allLoaded = resp.list.size() < Constant.NEWS_EVENT_PAGE || resp.list.size() == 0;
                displayApiResult(resp.list);
            }

            @Override
            public void onFailed(String messages) {
                onFailRequest(page_no);
            }

        });
    }

    private void onFailRequest(int page_no) {
        failedPage = page_no;
        swipeProgress(false);
        if (Tools.isConnect(getActivity())) {
            adapter.setLoadingOrFailed(getString(R.string.failed_text));
        } else {
            adapter.setLoadingOrFailed(getString(R.string.no_internet_text));
        }
    }

    private void showNoItemView(boolean show) {
        View lyt_no_item = rootView.findViewById(R.id.lyt_failed);
        ((TextView) rootView.findViewById(R.id.failed_title)).setText(getString(R.string.empty_state_text));
        ((TextView) rootView.findViewById(R.id.failed_subtitle)).setText(getString(R.string.empty_state_no_data));
        if (show) {
            lyt_no_item.setVisibility(View.VISIBLE);
        } else {
            lyt_no_item.setVisibility(View.GONE);
        }
    }

    private void swipeProgress(final boolean show) {
        swipeRefresh.post(() -> swipeRefresh.setRefreshing(show));
    }

    private void displayApiResult(final List<Sales> items) {
        adapter.insertData(items);
        swipeProgress(false);
        showNoItemView(adapter.getItemCount() == 0);
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
                    adapter.resetListData();
                    requestAction(1);
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
                    adapter.resetListData();
                    requestAction(1);
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
    }
}
