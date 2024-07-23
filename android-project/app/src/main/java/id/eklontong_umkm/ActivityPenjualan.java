package id.eklontong_umkm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import id.eklontong_umkm.adapter.AdapterListener;
import id.eklontong_umkm.adapter.AdapterPenjualan;
import id.eklontong_umkm.adapter.AdapterShoppingCart;
import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespListProduct;
import id.eklontong_umkm.data.Constant;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.data.DatabaseHandler;
import id.eklontong_umkm.model.Cart;
import id.eklontong_umkm.model.ProductList;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamList;
import id.eklontong_umkm.utils.Tools;

public class ActivityPenjualan extends AppCompatActivity {


    private boolean flag_wishlist = false;
    private boolean flag_cart = false;

    private View rootView;
    private RecyclerView recycler_view;
    private LinearLayout lyt_pembayaran;
    private ImageButton button_filter;
    private EditText et_search;

    private AdapterPenjualan adapter;

    private DatabaseHandler db;
    private ImageView toolbar_menu_cart;

    private boolean allLoaded = false;
    private int failedPage = 0;

    private LinearLayout lyt_failed;
    private MaterialRippleLayout lyt_add_cart;
    private TextView tv_add_cart;

    private View cart_badge;
    private int cart_count = -1;

    private AdapterShoppingCart adapterShopingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan);


        db = new DatabaseHandler(this);

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
        lyt_pembayaran = (LinearLayout) findViewById(R.id.lyt_pembayaran);
        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //set data and list adapter
        adapter = new AdapterPenjualan(getApplicationContext(), recycler_view, Constant.NEWS_EVENT_PAGE);
        recycler_view.setAdapter(adapter);
        et_search = (EditText) findViewById(R.id.et_search);
        button_filter = (ImageButton) findViewById(R.id.button_filter);
        cart_badge = (View) findViewById(R.id.cart_badge);
        toolbar_menu_cart = (ImageView) findViewById(R.id.toolbar_menu_cart);
        // detect when scroll reach bottom

        toolbar_menu_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityShoppingCart.navigate(ActivityPenjualan.this);
            }
        });
//        (findViewById(R.id.toolbar_menu_cart)).setOnClickListener(v -> ActivityShoppingCart.navigate(this));

        adapter.setListener(new AdapterListener<ProductList>(){
            @Override
            public void onClick(View view, String type, ProductList obj, int position) {
                super.onClick(view, type, obj, position);
//                ActivityNewsEventDetails.navigate(getActivity(), obj);
                refreshCartBadge();
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
        button_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.resetListData();
                requestAction(1);
            }
        });

        lyt_pembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Cart> items = db.getActiveCartList();
                adapterShopingCart = new AdapterShoppingCart(getApplicationContext(), true, items);
                if (adapterShopingCart.getItemCount() > 0) {
                    Intent intent = new Intent(ActivityPenjualan.this, Pembayaran.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.msg_cart_empty, Toast.LENGTH_LONG).show();
                }



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
        params.search = et_search.getText().toString();
        new Request(this).productList(params, new RequestListener<RespListProduct>() {
            @Override
            public void onSuccess(RespListProduct resp) {
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
//        swipeProgress(false);
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
    private void displayApiResult(final List<ProductList> items) {
        adapter.insertData(items);
//        swipeProgress(false);
        showNoItemView(adapter.getItemCount() == 0);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshCartBadge();
    }

    public void refreshCartBadge() {
        int new_cart_count = db.getActiveCartSize();
        if (new_cart_count != cart_count) {
            cart_count = new_cart_count;
            setupBadge();
        }
    }

    public void setupBadge() {
        if (cart_count == 0) {
            cart_badge.setVisibility(View.GONE);
        } else {
            cart_badge.setVisibility(View.VISIBLE);
            String count_txt = cart_count + "";
            if (cart_count > 9) count_txt = "9+";
            ((TextView) cart_badge.findViewById(R.id.counter)).setText(count_txt);
        }
    }
    public void setQty(String qty) {
        ((TextView) cart_badge.findViewById(R.id.counter)).setText(qty);
    }
}
