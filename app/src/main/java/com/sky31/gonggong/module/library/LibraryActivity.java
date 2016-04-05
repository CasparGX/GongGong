package com.sky31.gonggong.module.library;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sky31.gonggong.R;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.LibraryRentListModel;
import com.sky31.gonggong.model.UserModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LibraryActivity extends Activity {

    @Bind(R.id.rec_library_list)
    RecyclerView recLibraryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        recLibraryList.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<LibraryRentListModel.DataEntity> list = new ArrayList<>();
        LibraryRentListModel.DataEntity item = new LibraryRentListModel.DataEntity();
        item.setName("asd");
        item.setStatus("0");
        list.add(item);
        list.add(item);
        list.add(item);
        //recLibraryList.setAdapter(new LibraryListAdapter(this, list));
        recLibraryList.setAdapter(new LibraryListAdapter(this, (ArrayList<LibraryRentListModel.DataEntity>) UserModel.getaCache().getAsObject(Constants.Key.LIBRARY_RENT_LIST)));
    }
}
