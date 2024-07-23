package id.eklontong_umkm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import id.eklontong_umkm.ActivityFormUpdateProduct;
import id.eklontong_umkm.ActivityManajemenStok;
import id.eklontong_umkm.ActivityPengeluaran;
import id.eklontong_umkm.R;
import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespDeletePengeluaran;
import id.eklontong_umkm.connection.response.RespInfo;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.Pengeluaran;
import id.eklontong_umkm.model.ProductList;
import id.eklontong_umkm.utils.Tools;

public class AdapterPengeluaran extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private List<Pengeluaran> items = new ArrayList<>();
    private RecyclerView recyclerView;

    private boolean loading;
    private String status;
    private int page = 0;

    private Context ctx;
    private Activity activity;
    private AdapterListener<Pengeluaran> listener;

    public void setListener(AdapterListener<Pengeluaran> listener) {
        this.listener = listener;
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterPengeluaran(Context context, RecyclerView view, int page) {
        this.ctx = context;
        this.page = page;
        this.recyclerView = view;
        lastItemViewDetector(view);
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_tanggal, txt_no_referensi, txt_biaya_untuk,
                txt_jumlah, txt_catatan;
        public ImageView bt_remove;
        public View lytParent;

        public OriginalViewHolder(View v) {
            super(v);
            txt_tanggal = v.findViewById(R.id.txt_tanggal);
            txt_no_referensi = v.findViewById(R.id.txt_no_referensi);
            txt_biaya_untuk = v.findViewById(R.id.txt_biaya_untuk);
            txt_jumlah = v.findViewById(R.id.txt_jumlah);
            txt_catatan = v.findViewById(R.id.txt_catatan);
            lytParent = v.findViewById(R.id.lyt_parent);
            bt_remove = v.findViewById(R.id.bt_remove);
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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pengeluaran_row, parent, false);
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
            final Pengeluaran pengeluaran = items.get(position);
            OriginalViewHolder v = (OriginalViewHolder) holder;

            v.txt_tanggal.setText(pengeluaran.expense_date);
            v.txt_no_referensi.setText(pengeluaran.reference_no);
            v.txt_biaya_untuk.setText(pengeluaran.expense_for);
            v.txt_jumlah.setText(pengeluaran.expense_amt);
            v.txt_catatan.setText(pengeluaran.note);

            v.lytParent.setOnClickListener(view -> {
                if (listener == null) return;
                listener.onClick(view, null, pengeluaran, position);
            });

            v.bt_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requestDelete(pengeluaran.id);
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

    public void insertData(List<Pengeluaran> items) {
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
    private void requestDelete(String id) {
        new Request(ctx).delete_pengeluaran(id, new RequestListener<RespDeletePengeluaran>() {
            @Override
            public void onSuccess(RespDeletePengeluaran resp) {
                ActivityPengeluaran.navigate(ctx, id);
            }

            @Override
            public void onFailed(String messages) {
//                dialogFailed("Something went wrong, unable connect to server",id);
                Toast.makeText(ctx, "Something went wrong, unable connect to server", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void dialogFailed(String message, String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle(R.string.failed);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.RETRY, (dialog, which) -> {
            dialog.dismiss();
            retryOpenApplication(id);
        });
        builder.show();
    }
    private void retryOpenApplication(String id) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> requestDelete(id), 1000);
    }

    private void onLoadMore() {
        int current_page = getItemCount() / page;
        listener.onLoadMore(current_page);
        loading = true;
        status = null;
    }
}
