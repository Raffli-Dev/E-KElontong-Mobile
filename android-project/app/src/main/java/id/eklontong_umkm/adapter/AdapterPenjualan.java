package id.eklontong_umkm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.ArrayList;
import java.util.List;

import id.eklontong_umkm.ActivityFormUpdateProduct;
import id.eklontong_umkm.ActivityManajemenStok;
import id.eklontong_umkm.ActivityPenjualan;
import id.eklontong_umkm.R;
import id.eklontong_umkm.data.DatabaseHandler;
import id.eklontong_umkm.model.Cart;
import id.eklontong_umkm.model.ProductList;
import id.eklontong_umkm.utils.Tools;

public class AdapterPenjualan extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private List<ProductList> items = new ArrayList<>();
    private RecyclerView recyclerView;

    private boolean loading;
    private String status;
    private int page = 0;
    private Long product_id_db_sqlite = 0L;

    private Context ctx;
    private Activity activity;
    private AdapterListener<ProductList> listener;

    private boolean flag_wishlist = false;
    private boolean flag_cart = false;
    private DatabaseHandler db;

    private MaterialRippleLayout lyt_add_cart;
    private TextView tv_add_cart;


    private View cart_badge;
    private int cart_count = -1;


    public void setListener(AdapterListener<ProductList> listener) {
        this.listener = listener;
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterPenjualan(Context context, RecyclerView view, int page) {
        this.ctx = context;
        this.page = page;
        this.recyclerView = view;
        lastItemViewDetector(view);
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView name, txt_harga_jual, txt_jumlah_stok, txt_kategori, tv_add_cart;
        public ImageView image;
        public View lytParent;
        public MaterialRippleLayout lyt_add_cart;


        public OriginalViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            txt_harga_jual = v.findViewById(R.id.txt_harga_jual);
            txt_jumlah_stok = v.findViewById(R.id.txt_jumlah_stok);
            txt_kategori = v.findViewById(R.id.txt_kategori);
            image = v.findViewById(R.id.image);
            lytParent = v.findViewById(R.id.lyt_parent);
            lyt_add_cart = v.findViewById(R.id.lyt_add_cart);
            tv_add_cart = v.findViewById(R.id.tv_add_cart);
        }
    }
    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progress_loading;
        public TextView status_loading;

        public ProgressViewHolder(View v) {
            super(v);
            progress_loading = v.findViewById(R.id.progress_loading);
            status_loading = v.findViewById(R.id.status_loading);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_penjualan_row, parent, false);
            vh = new OriginalViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {

            db = new DatabaseHandler(ctx);


            final ProductList product_list = items.get(position);
            OriginalViewHolder v = (OriginalViewHolder) holder;

            v.name.setText(product_list.item_name);
            v.txt_harga_jual.setText(product_list.final_price);
            v.txt_jumlah_stok.setText(product_list.stock+" Items");
            v.txt_kategori.setText(product_list.category_name);

            Tools.displayImageThumb(ctx, v.image, product_list.item_image, 0.5f);
            v.lytParent.setOnClickListener(view -> {
                if (listener == null) return;
                listener.onClick(view, null, product_list, position);
            });

            v.lyt_add_cart.setOnClickListener(view -> {
                if (product_list == null || (product_list.item_name != null && product_list.item_name.equals(""))) {
                    Toast.makeText(ctx.getApplicationContext(), R.string.please_wait_text, Toast.LENGTH_SHORT).show();
                    return;
                }
                toggleCartButton(product_list, v, Long.parseLong(product_list.id));
            });

            refreshCartButton(product_list, v, Long.parseLong(product_list.id));

        } else {
            final ProgressViewHolder v = (ProgressViewHolder) holder;
            v.progress_loading.setVisibility(status == null ? View.VISIBLE : View.INVISIBLE);
            v.status_loading.setVisibility(status == null ? View.INVISIBLE : View.VISIBLE);

            if (status == null) return;
            v.status_loading.setText(status);
            v.status_loading.setOnClickListener(view -> {
                setLoaded();
                onLoadMore();
            });
        }
    }
    // Return the size of your data set (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.items.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    public void insertData(List<ProductList> items) {
        setLoaded();
        int positionStart = getItemCount();
        int itemCount = items.size();
        this.items.addAll(items);
        notifyItemRangeInserted(positionStart, itemCount);
    }

    public void setLoaded() {
        status = null;
        loading = false;
        int last_index = getItemCount() - 1;
        if (last_index > -1 && items.get(last_index) == null) {
            items.remove(last_index);
            notifyItemRemoved(last_index);
        }
    }

    public void setLoadingOrFailed(String status) {
        this.status = status;
        if (getItemCount() == 0 || this.items.get(getItemCount() - 1) != null) {
            this.items.add(null);
        }
        recyclerView.post(() -> notifyItemChanged(getItemCount() - 1));
        loading = true;
    }
    public void resetListData() {
        this.items = new ArrayList<>();
        notifyDataSetChanged();
    }

    private void lastItemViewDetector(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int lastPos = layoutManager.findLastVisibleItemPosition();
                    if (!loading && lastPos == getItemCount() - 1 && listener != null) {
                        onLoadMore();
                    }
                }
            });
        }
    }

    private void onLoadMore() {
        int current_page = getItemCount() / page;
        listener.onLoadMore(current_page);
        loading = true;
        status = null;
    }

    private void toggleCartButton(ProductList product_list, OriginalViewHolder v, Long product_id) {
        Cart c = db.getCart(Long.parseLong(product_list.id));
        if (c != null) {
            product_id_db_sqlite = c.product_id;
        }
        if (product_id == product_id_db_sqlite ) {
            db.deleteActiveCart(Long.parseLong(product_list.id));

            Cart cc = db.getCart(Long.parseLong(product_list.id));
            if (cc != null) {
                product_id_db_sqlite = c.product_id;
            }
            v.lyt_add_cart.setBackgroundColor(ctx.getResources().getColor(R.color.colorAddCart));
            v.tv_add_cart.setText(R.string.bt_add_cart);
            Toast.makeText(ctx, R.string.remove_cart, Toast.LENGTH_SHORT).show();

        } else {
            // check stock product
            if (Long.parseLong(product_list.stock) < 1) {
                Toast.makeText(ctx, R.string.msg_out_of_stock, Toast.LENGTH_SHORT).show();
                return;
            }
            if (product_list.status.equalsIgnoreCase("0")) {
                Toast.makeText(ctx, R.string.msg_suspend, Toast.LENGTH_SHORT).show();
                return;
            }
            Double selected_price = Double.parseDouble(product_list.final_price);
            Cart cart = new Cart(Long.parseLong(product_list.id), product_list.item_name, product_list.item_image, 1, Long.parseLong(product_list.stock), selected_price, System.currentTimeMillis());
            db.saveCart(cart);
            Toast.makeText(ctx, R.string.add_cart, Toast.LENGTH_SHORT).show();
//            Toast.makeText(ctx, "Produk Id : " + Long.parseLong(product_list.id)+", Name : "+ product_list.item_name+", Imafes" + product_list.item_image+", Stok : "+ Long.parseLong(product_list.stock)+", Selected Price " + selected_price, Toast.LENGTH_LONG).show();
            refreshCartButton(product_list, v, product_id);
        }
    }

    private void refreshCartButton(ProductList product_list, OriginalViewHolder v, Long product_idd) {
        Cart c = db.getCart(Long.parseLong(product_list.id));
        flag_cart = (c != null);
        if (c != null) {
            product_id_db_sqlite = c.product_id;
        }
        if (product_idd == product_id_db_sqlite) {
            v.lyt_add_cart.setBackgroundColor(ctx.getResources().getColor(R.color.colorRemoveCart));
            v.tv_add_cart.setText(R.string.bt_remove_cart);
        } else {
            v.lyt_add_cart.setBackgroundColor(ctx.getResources().getColor(R.color.colorAddCart));
            v.tv_add_cart.setText(R.string.bt_add_cart);
        }
    }

    private void refreshCartBadge() {
        int new_cart_count = db.getActiveCartSize();
        if (new_cart_count != cart_count) {
            cart_count = new_cart_count;
            setupBadge();
        }
    }

    private void setupBadge() {
        if (cart_count == 0) {
            cart_badge.setVisibility(View.GONE);
        } else {
            cart_badge.setVisibility(View.VISIBLE);
            String count_txt = cart_count + "";
            if (cart_count > 9) count_txt = "9+";
            ((TextView) cart_badge.findViewById(R.id.counter)).setText(count_txt);
        }
    }
}
