package id.eklontong_umkm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.eklontong_umkm.R;
import id.eklontong_umkm.data.SharedPref;
import id.eklontong_umkm.model.Cart;
import id.eklontong_umkm.utils.Tools;


public class AdapterShoppingCart extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public enum ActionType {
        ITEM, DELETE, INCREASE, DECREASE,
    }

    private Context ctx;
    private SharedPref sharedPref;
    private List<Cart> items;
    private Boolean is_cart = true;

    private AdapterListener<Cart> listener;

    public void setListener(AdapterListener<Cart> listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, amount, price, stock;
        public ImageView image, img_decrease, img_increase;
        public RelativeLayout lyt_image;
        public View bt_remove, lyt_parent;

        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            amount = v.findViewById(R.id.amount);
            price = v.findViewById(R.id.price);
            stock = v.findViewById(R.id.stock);
            image = v.findViewById(R.id.image);
            img_decrease = v.findViewById(R.id.img_decrease);
            img_increase = v.findViewById(R.id.img_increase);
            bt_remove = v.findViewById(R.id.bt_remove);
            lyt_parent = v.findViewById(R.id.lyt_parent);
            lyt_image = v.findViewById(R.id.lyt_image);
        }
    }

    public class ViewHolderCheckout extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, amount, price, total_price;

        public ViewHolderCheckout(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            amount = v.findViewById(R.id.amount);
            price = v.findViewById(R.id.price);
            total_price = v.findViewById(R.id.total_price);
        }
    }

    public AdapterShoppingCart(Context ctx, boolean is_cart, List<Cart> items) {
        this.ctx = ctx;
        this.items = items;
        this.is_cart = is_cart;
        sharedPref = new SharedPref(ctx);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v;
        if (is_cart) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_shoping_cart_rows, parent, false);
            vh = new ViewHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkout_cart, parent, false);
            vh = new ViewHolderCheckout(v);
        }
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (is_cart) {
            if (holder instanceof ViewHolder) {
                ViewHolder vItem = (ViewHolder) holder;
                final Cart c = items.get(position);
                vItem.title.setText(c.product_name);
//                vItem.price.setText(Tools.getFormattedPrice(c.price_item, ctx));
                vItem.price.setText(c.price_item.toString());
//                Toast.makeText(ctx, "Price Item " + c.price_item, Toast.LENGTH_LONG).show();
                vItem.amount.setText(c.amount.toString());

                vItem.stock.setText(ctx.getString(R.string.stock) + c.stock);
                Tools.displayImageThumbnail(ctx, vItem.image, c.image, 0.5f);

                vItem.lyt_parent.setOnClickListener(v -> {
                    if (listener == null) return;
                    listener.onClick(v, AdapterShoppingCart.ActionType.ITEM.name(), c, position);
                });

                vItem.bt_remove.setOnClickListener(v -> {
                    if (listener == null) return;
                    listener.onClick(v, ActionType.DELETE.name(), c, position);
                });

                vItem.img_decrease.setOnClickListener(v -> {
                    if (listener == null) return;
                    listener.onClick(v, ActionType.DECREASE.name(), c, position);
                });

                vItem.img_increase.setOnClickListener(v -> {
                    if (listener == null) return;
                    listener.onClick(v, ActionType.INCREASE.name(), c, position);
                });
            }
        } else {
            if (holder instanceof ViewHolderCheckout) {
                ViewHolderCheckout vItem = (ViewHolderCheckout) holder;
                final Cart c = items.get(position);
                vItem.title.setText(c.product_name);
                vItem.price.setText(c.price_item.toString());
                vItem.amount.setText(c.amount.toString());
                Double total_price = c.amount * c.price_item;
                vItem.total_price.setText(total_price.toString());
            }
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<Cart> getItem() {
        return items;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setItems(List<Cart> items) {
        this.items = items;
        notifyDataSetChanged();
    }


}