package id.eklontong_umkm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

import id.eklontong_umkm.adapter.AdapterListener;
import id.eklontong_umkm.adapter.AdapterShoppingCart;
import id.eklontong_umkm.data.DatabaseHandler;
import id.eklontong_umkm.data.SharedPref;
import id.eklontong_umkm.model.Cart;
import id.eklontong_umkm.model.Info;
import id.eklontong_umkm.utils.Tools;

public class ActivityShoppingCart extends AppCompatActivity {
    public static void navigate(Activity activity) {
        Intent i = new Intent(activity, ActivityShoppingCart.class);
        activity.startActivity(i);
    }

    private View parent_view;
    private RecyclerView recyclerView;
    private DatabaseHandler db;
    private AdapterShoppingCart adapter;
    private TextView price_total;
    private SharedPref sharedPref;
    private Info info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping_cart);
        db = new DatabaseHandler(this);
        sharedPref = new SharedPref(this);
        info = sharedPref.getInfoData();

        initToolbar();
        iniComponent();
    }
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Tools.setSystemBarColor(this, R.color.primary_register);
    }

    private void iniComponent() {
        parent_view = findViewById(android.R.id.content);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        price_total = (TextView) findViewById(R.id.price_total);


        (findViewById(R.id.toolbar_menu_back)).setOnClickListener(v -> onBackPressed());

        (findViewById(R.id.toolbar_menu_delete)).setOnClickListener(v -> {
            if (adapter.getItemCount() == 0) {
                Snackbar.make(parent_view, R.string.msg_cart_empty, Snackbar.LENGTH_SHORT).show();
                return;
            }
            dialogDeleteAllConfirmation();
        });

        (findViewById(R.id.lyt_continue)).setOnClickListener(v -> {
            if (adapter.getItemCount() > 0) {
                Intent intent = new Intent(ActivityShoppingCart.this, Pembayaran.class);
                startActivity(intent);
            } else {
                Snackbar.make(parent_view, R.string.msg_cart_empty, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayData();
    }

    private void displayData() {
        List<Cart> items = db.getActiveCartList();
        adapter = new AdapterShoppingCart(getApplicationContext(), true, items);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        adapter.setListener(new AdapterListener<Cart>() {
            @Override
            public void onClick(View view, String type, Cart obj, int position) {
                super.onClick(view, type, obj, position);
                if (type.equals(AdapterShoppingCart.ActionType.ITEM.name())) {
                    ActivityProductDetails.navigate(ActivityShoppingCart.this, obj.product_id, false);
                } else if (type.equals(AdapterShoppingCart.ActionType.DELETE.name())) {
                    dialogDeleteConfirmation(obj);
                } else if (type.equals(AdapterShoppingCart.ActionType.DECREASE.name())) {
                    if (obj.amount > 1) {
                        obj.amount = obj.amount - 1;
                        db.saveCart(obj);
                        displayData();
                    }
                } else if (type.equals(AdapterShoppingCart.ActionType.INCREASE.name())) {
                    if (obj.amount < obj.stock) {
                        obj.amount = obj.amount + 1;
                        db.saveCart(obj);
                        displayData();
                    }
                }
            }
        });
        View lyt_no_item = (View) findViewById(R.id.lyt_no_item);
        if (adapter.getItemCount() == 0) {
            lyt_no_item.setVisibility(View.VISIBLE);
        } else {
            lyt_no_item.setVisibility(View.GONE);
        }
        setTotalPrice();
    }

    private void setTotalPrice() {
        List<Cart> items = adapter.getItem();
        Double _price_total = 0D;
        String _price_total_tax_str;
        for (Cart c : items) {
            _price_total = _price_total + (c.amount * c.price_item);
        }
        _price_total_tax_str = _price_total.toString();
        price_total.setText(" " + _price_total_tax_str);
    }

    public void dialogDeleteAllConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_delete_confirm);
        builder.setMessage(getString(R.string.content_delete_confirm) + getString(R.string.title_activity_cart));
        builder.setPositiveButton(R.string.YES, (di, i) -> {
            di.dismiss();
            db.deleteActiveCart();
            onResume();
            Snackbar.make(parent_view, R.string.delete_success, Snackbar.LENGTH_SHORT).show();
        });
        builder.setNegativeButton(R.string.CANCEL, null);
        builder.show();
    }

    public void dialogDeleteConfirmation(Cart cart) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.cart_delete_confirm) + " \"" + cart.product_name + "\" ?");
        builder.setPositiveButton(R.string.YES, (di, i) -> {
            di.dismiss();
            db.deleteActiveCart(cart.product_id);
            displayData();
            Snackbar.make(parent_view, R.string.delete_success, Snackbar.LENGTH_SHORT).show();
        });
        builder.setNegativeButton(R.string.CANCEL, null);
        builder.show();
    }
}
