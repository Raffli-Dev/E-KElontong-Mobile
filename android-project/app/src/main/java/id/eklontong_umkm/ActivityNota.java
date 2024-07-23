package id.eklontong_umkm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.eklontong_umkm.adapter.AdapterPenjualan;
import id.eklontong_umkm.adapter.AdapterProductList;
import id.eklontong_umkm.adapter.AdapterSalesItem;
import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespDashboard;
import id.eklontong_umkm.connection.response.RespDetailSales;
import id.eklontong_umkm.connection.response.RespDetailSalesItem;
import id.eklontong_umkm.connection.response.RespInfo;
import id.eklontong_umkm.connection.response.RespListProduct;
import id.eklontong_umkm.data.Constant;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.Dashboard;
import id.eklontong_umkm.model.ProductList;
import id.eklontong_umkm.model.Sales;
import id.eklontong_umkm.model.SalesItems;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.model.param.ParamDashboard;
import id.eklontong_umkm.model.param.ParamList;
import id.eklontong_umkm.model.param.ParamSalesDetail;
import id.eklontong_umkm.utils.Tools;
import id.eklontong_umkm.utils.ViewAnimation;

public class ActivityNota extends AppCompatActivity {
    public static void navigate(Context activity, String id_sales) {
        Intent i = new Intent(activity.getApplicationContext(), ActivityNota.class);
        i.putExtra("OBJ", id_sales);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(i);
    }

    private Sales sales;

    private ImageButton bt_toggle_items;
    private View lyt_expand_items, lyt_expand_address, lyt_expand_description;
    private NestedScrollView nested_scroll_view;
    private TextView txt_total, txt_date_sales, tv_invoice_code;

    private AdapterSalesItem adapter;

    String id_sales;
    private RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_invoice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Form Update Barang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.primary_register_dark);

        id_sales = (String) getIntent().getSerializableExtra("OBJ");
        initToolbar();
        initComponent();
        requestSalesDetail(id_sales);
        requestNewsEvent(1);

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.teal_700);
    }

    private void initComponent() {

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new AdapterSalesItem(getApplicationContext(), recycler_view, Constant.NEWS_EVENT_PAGE);
        recycler_view.setAdapter(adapter);

        nested_scroll_view = (NestedScrollView) findViewById(R.id.nested_scroll_view);



        // section items
        bt_toggle_items = (ImageButton) findViewById(R.id.bt_toggle_items);
        lyt_expand_items = (View) findViewById(R.id.lyt_expand_items);

        txt_total = (TextView) findViewById(R.id.txt_total);
        txt_date_sales = (TextView) findViewById(R.id.txt_date_sales);

        bt_toggle_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSection(view, lyt_expand_items);
            }
        });

        // section address

        // copy to clipboard
        tv_invoice_code = (TextView) findViewById(R.id.txt_invoice_code);
        ImageButton bt_copy_code = (ImageButton) findViewById(R.id.bt_copy_code);
        bt_copy_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tools.copyToClipboard(getApplicationContext(), tv_invoice_code.getText().toString());
            }
        });

    }
    private void toggleSection(View bt, final View lyt) {
        boolean show = toggleArrow(bt);
        if (show) {
            ViewAnimation.expand(lyt, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nested_scroll_view, lyt);
                }
            });
        } else {
            ViewAnimation.collapse(lyt);
        }
    }

    public boolean toggleArrow(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }

    private void requestSalesDetail(String id_sales) {
        User user = DataApp.global().getUser();
        ParamSalesDetail paramSalesDetail = new ParamSalesDetail();
        paramSalesDetail.id_sales = id_sales;
        new Request(this).salesDetail(paramSalesDetail, new RequestListener<RespDetailSales>() {
            @Override
            public void onSuccess(RespDetailSales resp) {
                sales = resp.data;

                txt_date_sales.setText(sales.created_date);
                txt_total.setText(sales.grand_total);
                tv_invoice_code.setText(sales.sales_code);

            }

            @Override
            public void onFailed(String messages) {
                dialogFailed(getString(R.string.unable_connect_server));
            }
        });
    }

    private void requestNewsEvent(final Integer page_no) {
        User user = DataApp.global().getUser();
        ParamList params = new ParamList();
        params.page = page_no.toString();
        params.count = Constant.NEWS_EVENT_PAGE.toString();
        params.count = Constant.NEWS_EVENT_PAGE.toString();
        params.company_id = user.company_id;
        params.users_id = Long.toString(user.id);
        params.id_sales = id_sales;
        new Request(this).salesItems(params, new RequestListener<RespDetailSalesItem>() {
            @Override
            public void onSuccess(RespDetailSalesItem resp) {
                displayApiResult(resp.list);
            }

            @Override
            public void onFailed(String messages) {

            }

        });
    }
    public void dialogFailed(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.failed);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.RETRY, (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }
    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityNota.this, ActivityMain.class);
        startActivity(intent);
        finish();
    }

    private void displayApiResult(final List<SalesItems> items) {
        adapter.insertData(items);
    }

    @Override
    public void onPause() {
        super.onPause();
    }


}
