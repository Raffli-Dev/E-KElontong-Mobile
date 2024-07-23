package id.eklontong_umkm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.eklontong_umkm.adapter.AdapterKategoriBarang;
import id.eklontong_umkm.adapter.AdapterListener;
import id.eklontong_umkm.adapter.AdapterSatuanBarang;
import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespKategoriBarang;
import id.eklontong_umkm.connection.response.RespSatuanBarang;
import id.eklontong_umkm.data.Constant;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.KategoriBarang;
import id.eklontong_umkm.model.SatuanBarang;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamList;
import id.eklontong_umkm.utils.Tools;

public class ActivitySatuanBarang extends AppCompatActivity {
    public static void navigate(Context activity, String id) {
        Intent i = new Intent(activity.getApplicationContext(), ActivitySatuanBarang.class);
        i.putExtra("OBJ", id);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(i);
    }

    private View rootView;
    private RecyclerView recycler_view;
    private LinearLayout lyt_tambah_product, lyt_tambah;
    private ImageButton button_filter;

    private AdapterSatuanBarang adapter;

    private boolean allLoaded = false;
    private int failedPage = 0;

    private LinearLayout lyt_failed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satuan_barang);


        initToolbar();
        initComponent();

        requestAction(1);
        Tools.RTLMode(getWindow());

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Tools.setSystemBarColor(this, R.color.primary_register);
    }

    private void initComponent() {
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        lyt_tambah_product = (LinearLayout) findViewById(R.id.lyt_tambah_product);
        lyt_tambah = (LinearLayout) findViewById(R.id.lyt_tambah);
        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //set data and list adapter
        adapter = new AdapterSatuanBarang(getApplicationContext(), recycler_view, Constant.NEWS_EVENT_PAGE);
        recycler_view.setAdapter(adapter);
        button_filter = (ImageButton) findViewById(R.id.button_filter);

        // detect when scroll reach bottom
        adapter.setListener(new AdapterListener<SatuanBarang>(){
            @Override
            public void onClick(View view, String type, SatuanBarang obj, int position) {
                super.onClick(view, type, obj, position);
//                ActivityNewsEventDetails.navigate(getActivity(), obj);
                try {
//                    ((ActivityMain) getActivity()).showInterstitialAd();
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

        lyt_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySatuanBarang.this, ActivitySatuanBarangCreate.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void requestAction(final int page_no) {
        if (page_no == 1) {
            showNoItemView(false);
//            swipeProgress(true);
        } else {
            adapter.setLoadingOrFailed(null);
        }
        requestNewsEvent(page_no);
    }

    private void requestNewsEvent(final Integer page_no) {
        User user = DataApp.global().getUser();
        ParamList params = new ParamList();
        params.page = page_no.toString();
        params.count = Constant.NEWS_EVENT_PAGE.toString();
        params.count = Constant.NEWS_EVENT_PAGE.toString();
        params.company_id = user.company_id;
        params.users_id = Long.toString(user.id);
        new Request(this).satuanBarangList(params, new RequestListener<RespSatuanBarang>() {
            @Override
            public void onSuccess(RespSatuanBarang resp) {
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
        if (Tools.isConnect(this)) {
            adapter.setLoadingOrFailed(getString(R.string.failed_text));
        } else {
            adapter.setLoadingOrFailed(getString(R.string.no_internet_text));
        }
    }

    private void showNoItemView(boolean show) {
        View lyt_no_item = (LinearLayout) findViewById(R.id.lyt_failed);
        ((TextView) findViewById(R.id.failed_title)).setText(getString(R.string.empty_state_text));
        ((TextView) findViewById(R.id.failed_subtitle)).setText(getString(R.string.empty_state_no_data));
        if (show) {
            lyt_no_item.setVisibility(View.VISIBLE);
        } else {
            lyt_no_item.setVisibility(View.GONE);
        }
    }
    private void displayApiResult(final List<SatuanBarang> items) {
        adapter.insertData(items);
        showNoItemView(adapter.getItemCount() == 0);
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
