package id.eklontong_umkm;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import id.eklontong_umkm.adapter.AdapterShoppingCart;
import id.eklontong_umkm.model.Cart;

public class ActivityCheckout extends AppCompatActivity {
    private TextView price_total;
    private AdapterShoppingCart adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan);
        iniComponent();
    }

    private void iniComponent() {
        price_total = (TextView) findViewById(R.id.price_total);

        findViewById(R.id.toolbar_menu_back).setOnClickListener(v -> onBackPressed());

        setTotalPrice();
    }

    private void setTotalPrice() {
        List<Cart> items = adapter.getItem();
        double _price_total = 0D;
        for (Cart c : items) {
            _price_total += (c.amount * c.price_item);
        }
        String _price_total_tax_str = String.format("%.2f", _price_total);
        price_total.setText(" " + _price_total_tax_str);
    }
}
