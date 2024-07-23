package id.eklontong_umkm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import id.eklontong_umkm.adapter.AdapterShoppingCartSalesRetur;
import id.eklontong_umkm.connection.API;
import id.eklontong_umkm.connection.RestAdapter;
import id.eklontong_umkm.connection.response.RespOrder;
import id.eklontong_umkm.connection.response.RespOrderReturSales;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.data.DatabaseHandler;
import id.eklontong_umkm.data.SharedPref;
import id.eklontong_umkm.model.BuyerProfile;
import id.eklontong_umkm.model.Cart;
import id.eklontong_umkm.model.CartReturSales;
import id.eklontong_umkm.model.Checkout;
import id.eklontong_umkm.model.Info;
import id.eklontong_umkm.model.Order;
import id.eklontong_umkm.model.OrderReturSales;
import id.eklontong_umkm.model.ProductOrder;
import id.eklontong_umkm.model.ProductOrderDetail;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.utils.CallbackDialog;
import id.eklontong_umkm.utils.DialogUtils;
import id.eklontong_umkm.utils.Tools;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityReturPenjualanConfirm extends AppCompatActivity {
    private View parent_view;
    private TextView shipping;
    private TextView date_shipping;
    private RecyclerView recyclerView;
    private MaterialRippleLayout lyt_add_cart;
    private TextView subtotal, tax, price_tax, shipping_rate, total, price_total;
    private TextInputLayout buyer_name_lyt, email_lyt, phone_lyt, address_lyt, comment_lyt;
    private EditText buyer_name, email, phone, address, comment;
    private RadioGroup shipping_class;
    private RadioButton radio_economy, radio_regular, radio_express;

    private DatePickerDialog datePickerDialog;
    private AdapterShoppingCartSalesRetur adapter;
    private DatabaseHandler db;
    private SharedPref sharedPref;
    private Info info;
    private BuyerProfile buyerProfile;
    //    private Shipping selected_shipping = null;
    private Long date_ship_millis = 0L;
    private Double _total = 0D;
    private String _total_str;
    private ProductOrder productOrder = new ProductOrder();

    private Call<RespOrderReturSales> callbackCall = null;
    // construct dialog progress
    ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retur_penjualan_confirm);

        db = new DatabaseHandler(this);
        sharedPref = new SharedPref(this);
        info = sharedPref.getInfoData();

        iniComponent();
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Tools.setSystemBarColor(this, R.color.primary_register);
    }

    private void iniComponent() {
        parent_view = findViewById(android.R.id.content);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lyt_add_cart = findViewById(R.id.lyt_add_cart);

        // cost view
        subtotal = findViewById(R.id.total_order);
        tax = findViewById(R.id.tax);
        price_tax = findViewById(R.id.price_tax);
        total = findViewById(R.id.total_fees);
        price_total = findViewById(R.id.price_total);


        (findViewById(R.id.toolbar_menu_back)).setOnClickListener(v -> onBackPressed());

        progressDialog = new ProgressDialog(ActivityReturPenjualanConfirm.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(R.string.title_please_wait);
        progressDialog.setMessage(getString(R.string.content_submit_payment_retur_penjualan));


        lyt_add_cart.setOnClickListener(view -> submitForm());
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
        List<CartReturSales> items = db.getActiveCarReturSalestList();
        adapter = new AdapterShoppingCartSalesRetur(this, false, items);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        setTotalPrice();
    }

    private void setTotalPrice() {
        List<CartReturSales> items = adapter.getItem();
        Double _subtotal = 0D, _price_tax = 0D, _shipping_rate = 0D;
        String _total_order_str, _price_tax_str, _shipping_rate_str = "";
        for (CartReturSales c : items) {
            _subtotal = _subtotal + (c.amount * c.price_item);
        }
        _price_tax = _subtotal * 0 / 100;
        _total = _subtotal + _price_tax;
//        _price_tax_str = Tools.getFormattedPrice(_price_tax, this);
        _price_tax_str = _price_tax.toString();
//        _total_order_str = Tools.getFormattedPrice(_subtotal, this);
        _total_order_str = _subtotal.toString();
//        _total_str = Tools.getFormattedPrice(_total, this);
        _total_str = _total.toString();

        // set to display
        subtotal.setText(_total_order_str);
        tax.setText("Pajak");
        price_tax.setText(_price_tax_str);
        total.setText(_total_str);
        price_total.setText(_total_str);
    }

    private void submitForm() {
        User user = DataApp.global().getUser();
        buyerProfile = new BuyerProfile();
        buyerProfile.name = user.name;
        buyerProfile.email = user.email;
        buyerProfile.phone = user.phone;
        buyerProfile.address = "";

        // hide keyboard
        hideKeyboard();

        // show dialog confirmation
        dialogConfirmCheckout();
    }

    private void submitOrderData() {
        User user = DataApp.global().getUser();
        // prepare checkout data
        Checkout checkout = new Checkout();
        productOrder.setBuyerProfile(buyerProfile);
        productOrder.date_ship = date_ship_millis;
        productOrder.comment = "";
        productOrder.status = "WAITING";
        productOrder.total_fees = _total;
        productOrder.tax = 0.0;
        productOrder.serial = Tools.getDeviceID(this);
        productOrder.users_id = user.id.toString();
        productOrder.company_id = user.company_id;

        checkout.product_order = productOrder;
        checkout.product_order_detail = new ArrayList<>();
        for (CartReturSales c : adapter.getItem()) {
            ProductOrderDetail pod = new ProductOrderDetail(c.product_id, c.product_name, c.amount, c.price_item, c.sales_id, c.sales_qty);
            checkout.product_order_detail.add(pod);
        }

        // submit data to server
        API api = RestAdapter.createAPI();
        callbackCall = api.submitProductOrderSalesRetur(AppConfig.general.security_code, checkout);
        callbackCall.enqueue(new Callback<RespOrderReturSales>() {
            @Override
            public void onResponse(Call<RespOrderReturSales> call, Response<RespOrderReturSales> response) {
                RespOrderReturSales resp = response.body();
                if (resp != null && resp.status.equals("success")) {
                    OrderReturSales order = new OrderReturSales(resp.data.id, resp.data.code, _total.toString());
                    for (CartReturSales c : adapter.getItem()) {
                        c.order_id = order.id;
                        order.cart_list.add(c);
                    }
//                    db.saveOrderReturSales(order);
                    db.deleteActiveCartReturSales();
                    dialogSuccess(order.code, order.id.toString());
                } else {
                    dialogFailedRetry();
                }

            }

            @Override
            public void onFailure(Call<RespOrderReturSales> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                if (!call.isCanceled()) dialogFailedRetry();
            }
        });

    }
    // give delay when submit data to give good UX
    private void delaySubmitOrderData() {
        progressDialog.show();
        new Handler().postDelayed(() -> submitOrderData(), 2000);
    }
    public void dialogConfirmCheckout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirmation);
        builder.setMessage(getString(R.string.confirm_payment_sales_retur));
        builder.setPositiveButton(R.string.YES, (dialogInterface, i) -> delaySubmitOrderData());
        builder.setNegativeButton(R.string.NO, null);
        builder.show();
    }

    public void dialogFailedRetry() {
        progressDialog.dismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.failed);
        builder.setMessage(getString(R.string.failed_checkout_sales_retur));
        builder.setPositiveButton(R.string.TRY_AGAIN, (dialogInterface, i) -> delaySubmitOrderData());
        builder.show();
    }

    public void dialogSuccess(final String code, final String id_sales) {
        progressDialog.dismiss();
        Dialog dialog = new DialogUtils(this).buildDialogInfo(
                getString(R.string.success_checkout),
                String.format(getString(R.string.msg_success_checkout_sales_retur), code),
                getString(R.string.OK),
                getString(R.string.KEMBALI),
                R.drawable.img_checkout_success,
                new CallbackDialog() {
                    @Override
                    public void onPositiveClick(Dialog dialog) {

                        Intent intent = new Intent(ActivityReturPenjualanConfirm.this, ActivityReturPenjualan.class);
                        startActivity(intent);
                        finish();
                        dialog.dismiss();
                    }

                    @Override
                    public void onNegativeClick(Dialog dialog) {
//                        Tools.directLinkToCustomTabs(ActivityCheckout.this, Constant.getURLPayment(code));
                        Intent intent = new Intent(ActivityReturPenjualanConfirm.this, ActivityReturPenjualan.class);
                        startActivity(intent);
                        finish();
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
