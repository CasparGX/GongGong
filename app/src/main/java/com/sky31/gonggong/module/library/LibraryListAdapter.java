package com.sky31.gonggong.module.library;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.sky31.gonggong.R;
import com.sky31.gonggong.model.LibraryRentListModel;

import java.util.ArrayList;

/**
 * Created by root on 16-4-1.
 */
public class LibraryListAdapter extends RecyclerView.Adapter<LibraryListAdapter.BookListViewHolder> {
    private ArrayList<LibraryRentListModel.DataEntity> rentList;
    private Context context;
    private Resources resources;

    public LibraryListAdapter(Context context, ArrayList<LibraryRentListModel.DataEntity> rentList) {
        this.rentList = rentList;
        this.context = context;
        this.resources = context.getResources();
    }

    @Override
    public BookListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.library_cardview, viewGroup, false);
        BookListViewHolder blvh = new BookListViewHolder(v);
        return blvh;
    }

    @Override
    public void onBindViewHolder(BookListViewHolder holder, int position) {
        LibraryRentListModel.DataEntity book = rentList.get(position);
        if (book.getStatus().equals(resources.getString(R.string.library_status_0))) {
            holder.btnRenew.getTextView().setText(resources.getString(R.string.library_renew));
            holder.btnRenew.setClickable(true);
        } else {
            //holder.btnRenew.getTextView().setText(resources.getString(R.string.library_renewed));
            //holder.btnRenew.getTextView();
            holder.btnRenew.setClickable(false);
        }
        holder.tvBookName.setText(book.getName());
        holder.tvBookDeadline.setText(book.getDeadline());
        //holder.tvBookName.setText(book.getName());
    }

    @Override
    public int getItemCount() {
        return rentList == null ? 0 : rentList.size();
    }

    public static class BookListViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBookName;
        private TextView tvBookCountdown;
        private TextView tvBookDeadline;
        private ButtonRectangle btnRenew;

        public BookListViewHolder(View itemView) {
            super(itemView);
            tvBookCountdown = (TextView) itemView.findViewById(R.id.tv_book_countdown);
            tvBookName = (TextView) itemView.findViewById(R.id.tv_book_name);
            tvBookDeadline = (TextView) itemView.findViewById(R.id.tv_book_deadline);
            btnRenew = (ButtonRectangle) itemView.findViewById(R.id.btn_renew);
        }
    }
}
