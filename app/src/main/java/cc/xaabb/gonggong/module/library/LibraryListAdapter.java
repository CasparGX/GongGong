package cc.xaabb.gonggong.module.library;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rey.material.widget.Button;
import com.sky31.gonggong.R;
import cc.xaabb.gonggong.model.LibraryReaderInfoModel;
import cc.xaabb.gonggong.model.LibraryRentListModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 16-4-1.
 */
public class LibraryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private ArrayList<LibraryRentListModel.DataEntity> rentList;
    private Context context;
    private Resources resources;
    private View headerView;
    private LibraryReaderInfoModel.DataEntity headerInfo;

    public LibraryListAdapter(Context context, ArrayList<LibraryRentListModel.DataEntity> rentList) {
        this.rentList = new ArrayList<LibraryRentListModel.DataEntity>();
        this.rentList.addAll(rentList);
        this.context = context;
        this.resources = context.getResources();
    }

    public View getHeaderView() {
        return headerView;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        notifyItemInserted(0);
    }

    public void setHeaderInfo(LibraryReaderInfoModel.DataEntity headerInfo) {
        this.headerInfo = headerInfo;
        notifyItemChanged(0);
    }

    @Override
    public int getItemViewType(int position) {
        if (headerView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    public void setRentList(ArrayList<LibraryRentListModel.DataEntity> rentList) {
        this.rentList = rentList;
    }

    public void updateData(ArrayList<LibraryRentListModel.DataEntity> rentList) {
        this.rentList.clear();
        this.rentList.addAll(rentList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (headerView != null && viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_recycler_library, viewGroup, false);
            HeadViewHolder blvh = new HeadViewHolder(v);
            return blvh;
        } else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_library_cardview, viewGroup, false);
            ItemViewHolder blvh = new ItemViewHolder(v);
            return blvh;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder) {
            ItemViewHolder holder = ((ItemViewHolder) viewHolder);
            LibraryRentListModel.DataEntity book = rentList.get(position);
            if (book.getStatus().equals(resources.getString(R.string.library_status_0))) {
                holder.btnRenew.setText(resources.getString(R.string.library_renew));
                holder.btnRenew.setClickable(true);
                holder.btnRenew.setTextColor(resources.getColor(R.color.white));
                holder.btnRenew.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_with_bgcolor));
            } else if (book.getStatus().equals(resources.getString(R.string.library_status_1))) {
                holder.btnRenew.setText(resources.getString(R.string.library_renewed));
                holder.btnRenew.setClickable(false);
                holder.btnRenew.setTextColor(resources.getColor(R.color.textColorPrimary));
                holder.btnRenew.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_default));
            } else if (book.getStatus().equals(resources.getString(R.string.library_status_2))) {
                holder.btnRenew.setText(resources.getString(R.string.library_status_2));
                holder.btnRenew.setClickable(false);
                holder.btnRenew.setTextColor(resources.getColor(R.color.textColorPrimary));
                holder.btnRenew.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_default));
            }
            if (book.getInterval() < 0) {
                holder.libraryCardviewLine.setBackground(resources.getDrawable(R.drawable.cardview_bottom_line_error));
            } else if (book.getInterval() < 15) {
                holder.libraryCardviewLine.setBackground(resources.getDrawable(R.drawable.cardview_bottom_line_warning));

            } else if (book.getInterval() >= 15) {
                holder.libraryCardviewLine.setBackground(resources.getDrawable(R.drawable.cardview_bottom_line_info));

            }
            holder.tvBookName.setText(book.getName());
            holder.tvBookDeadline.setText(book.getDeadline());
            holder.tvBookName.setText(book.getName());
            holder.tvBookCountdown.setText(book.getInterval() + "");
        } else if (viewHolder instanceof HeadViewHolder && headerInfo != null) {
            HeadViewHolder holder = ((HeadViewHolder) viewHolder);
            holder.libraryName.setText(headerInfo.getName());
            holder.libraryStartTime.setText(headerInfo.getValid_date_start());
            holder.libraryEndTime.setText(headerInfo.getValid_date_end());
            holder.libraryDebt.setText(headerInfo.getDebt());
        }
    }

    @Override
    public int getItemCount() {
        int count = rentList == null ? 0 : rentList.size();
        if (headerView != null) {
            count += 1;
        }
        return count;
    }

    public static class HeadViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.library_name)
        TextView libraryName;
        @Bind(R.id.library_start_time)
        TextView libraryStartTime;
        @Bind(R.id.library_end_time)
        TextView libraryEndTime;
        @Bind(R.id.library_debt)
        TextView libraryDebt;

        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBookName;
        private TextView tvBookCountdown;
        private TextView tvBookDeadline;
        private Button btnRenew;
        private View libraryCardviewLine;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvBookCountdown = (TextView) itemView.findViewById(R.id.tv_book_countdown);
            tvBookName = (TextView) itemView.findViewById(R.id.tv_book_name);
            tvBookDeadline = (TextView) itemView.findViewById(R.id.tv_book_deadline);
            btnRenew = (Button) itemView.findViewById(R.id.btn_renew);
            libraryCardviewLine = (View) itemView.findViewById(R.id.library_cardview_line);
        }
    }
}
