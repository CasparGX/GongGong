package com.sky31.gonggong.module.library;

import android.content.Context;
import android.support.annotation.Nullable;

import com.sky31.gonggong.model.LibraryReaderInfoModel;
import com.sky31.gonggong.model.LibraryRentListModel;

/**
 * Created by root on 16-3-7.
 */
public interface LibraryView {
    void onGetLibraryReaderInfo(int code, @Nullable LibraryReaderInfoModel libraryReaderInfoModel);
    void getLibraryRentLsit(int code, @Nullable LibraryRentListModel libraryRentListModel);

    Context getViewContext();
}
