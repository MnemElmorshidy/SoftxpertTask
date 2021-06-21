package com.example.softxperttask;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, CarItem>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, CarItem> create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        itemLiveDataSource.postValue(itemDataSource);

        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, CarItem>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}