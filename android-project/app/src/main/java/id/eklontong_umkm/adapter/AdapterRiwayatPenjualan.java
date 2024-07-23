package id.eklontong_umkm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.ArrayList;
import java.util.List;

import id.eklontong_umkm.ActivityNota;
import id.eklontong_umkm.R;
import id.eklontong_umkm.model.Sales;

public class AdapterRiwayatPenjualan extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private List<Sales> items = new ArrayList<>();
    private RecyclerView recyclerView;

    private boolean loading;
    private String status;
    private int page = 0;

    private Context ctx;
    private AdapterListener<Sales> listener;

    public void setListener(AdapterListener<Sales> listener) {
        this.listener = listener;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterRiwayatPenjualan(Context context, RecyclerView view, int page) {
        this.ctx = context;
        this.page = page;
        this.recyclerView = view;
        lastItemViewDetector(view);
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_no_faktur, txt_tanggal, txt_grand_total;
        public MaterialRippleLayout lyt_detail;
        public View lytParent;

        public OriginalViewHolder(View v) {
            super(v);
            txt_no_faktur = v.findViewById(R.id.txt_no_faktur);
            txt_tanggal = v.findViewById(R.id.txt_tanggal);
            txt_grand_total = v.findViewById(R.id.txt_grand_total);
            lytParent = v.findViewById(R.id.lyt_parent);
            lyt_detail = v.findViewById(R.id.lyt_detail);
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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_riwayat_penjualan_row, parent, false);
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
            final Sales sales = items.get(position);
            OriginalViewHolder v = (OriginalViewHolder) holder;

            v.txt_no_faktur.setText(sales.sales_code);
            v.txt_tanggal.setText(sales.sales_date);
            v.txt_grand_total.setText(sales.grand_total);

            v.lyt_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityNota.navigate(ctx, sales.id);
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

    public void insertData(List<Sales> items) {
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
}
