package com.sky31.gonggong.module.library;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rey.material.widget.Button;
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
            holder.btnRenew.setText(resources.getString(R.string.library_renew));
            holder.btnRenew.setClickable(true);
            holder.btnRenew.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_with_bgcolor));
        } else if(book.getStatus().equals(resources.getString(R.string.library_status_1))){
            holder.btnRenew.setText(resources.getString(R.string.library_renewed));
            holder.btnRenew.setClickable(false);
            holder.btnRenew.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_default));
        }else if(book.getStatus().equals(resources.getString(R.string.library_status_2))){
            holder.btnRenew.setText(resources.getString(R.string.library_status_2));
            holder.btnRenew.setClickable(false);
            holder.btnRenew.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_error));
        }
        if (book.getInterval()>=15){

        }
        holder.tvBookName.setText(book.getName());
        holder.tvBookDeadline.setText(book.getDeadline());
        holder.tvBookName.setText(book.getName());
        holder.tvBookCountdown.setText(book.getInterval()+"");
    }

    @Override
    public int getItemCount() {
        return rentList == null ? 0 : rentList.size();
    }

    public static class BookListViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBookName;
        private TextView tvBookCountdown;
        private TextView tvBookDeadline;
        private Button btnRenew;

        public BookListViewHolder(View itemView) {
            super(itemView);
            tvBookCountdown = (TextView) itemView.findViewById(R.id.tv_book_countdown);
            tvBookName = (TextView) itemView.findViewById(R.id.tv_book_name);
            tvBookDeadline = (TextView) itemView.findViewById(R.id.tv_book_deadline);
            btnRenew = (Button) itemView.findViewById(R.id.btn_renew);
        }
    }
}
