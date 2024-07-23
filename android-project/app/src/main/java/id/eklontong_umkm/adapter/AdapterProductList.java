package id.eklontong_umkm.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.eklontong_umkm.ActivityFormUpdateProduct;
import id.eklontong_umkm.ActivityKategoriBarang;
import id.eklontong_umkm.ActivityManajemenStok;
import id.eklontong_umkm.ActivityProduct;
import id.eklontong_umkm.R;
import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespDeleteBarang;
import id.eklontong_umkm.connection.response.RespDeleteKategoriBarang;
import id.eklontong_umkm.model.ProductList;
import id.eklontong_umkm.utils.Tools;

public class AdapterProductList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private List<ProductList> items = new ArrayList<>();
    private RecyclerView recyclerView;

    private boolean loading;
    private String status;
    private int page = 0;

    private Context ctx;
    private Activity activity;
    private AdapterListener<ProductList> listener;

    public void setListener(AdapterListener<ProductList> listener) {
        this.listener = listener;
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterProductList(Context context, RecyclerView view, int page) {
        this.ctx = context;
        this.page = page;
        this.recyclerView = view;
        lastItemViewDetector(view);
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView name, txt_harga_jual, txt_jumlah_stok, txt_kategori, txt_edit_product, txt_tambah_stok, txt_delete_barang;
        public ImageView image;
        public View lytParent;

        public OriginalViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            txt_harga_jual = v.findViewById(R.id.txt_harga_jual);
            txt_jumlah_stok = v.findViewById(R.id.txt_jumlah_stok);
            txt_kategori = v.findViewById(R.id.txt_kategori);
            txt_edit_product = v.findViewById(R.id.txt_edit_product);
            txt_tambah_stok = v.findViewById(R.id.txt_tambah_stok);
            txt_delete_barang = v.findViewById(R.id.txt_delete_barang);
            image = v.findViewById(R.id.image);
            lytParent = v.findViewById(R.id.lyt_parent);
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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_product_rows, parent, false);
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
            final ProductList product_list = items.get(position);
            OriginalViewHolder v = (OriginalViewHolder) holder;

            v.name.setText(product_list.item_name);
            v.txt_harga_jual.setText(product_list.final_price);
            v.txt_jumlah_stok.setText(product_list.stock + " Items");
            v.txt_kategori.setText(product_list.category_name);

            Tools.displayImageThumb(ctx, v.image, product_list.item_image, 0.5f);
            v.lytParent.setOnClickListener(view -> {
                if (listener == null) return;
                listener.onClick(view, null, product_list, position);
            });

            v.txt_edit_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityFormUpdateProduct.navigate(ctx, product_list);
                }
            });

            v.txt_tambah_stok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityManajemenStok.navigate(ctx, product_list);
                }
            });
            v.txt_delete_barang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requestDelete(product_list.id);
                }
            });

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

    private void requestDelete(String id) {
        new Request(ctx).delete_barang(id, new RequestListener<RespDeleteBarang>() {
            @Override
            public void onSuccess(RespDeleteBarang resp) {
                ActivityProduct.navigate(ctx, id);
            }

            @Override
            public void onFailed(String messages) {
                Toast.makeText(ctx, "Something went wrong, unable connect to server", Toast.LENGTH_LONG).show();
            }
        });
    }
}
