package com.example.softxperttask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class ItemViewModel extends ViewModel {

        LiveData<PagedList<CarItem>> itemPagedList;
        LiveData<PageKeyedDataSource<Integer, CarItem>> liveDataSource;
        ItemDataSourceFactory carDataSourceFactory;

        public ItemViewModel() {
            carDataSourceFactory = new ItemDataSourceFactory();
            liveDataSource = carDataSourceFactory.getItemLiveDataSource();
            PagedList.Config pagedListConfig =
                    (new PagedList.Config.Builder())
                            .setEnablePlaceholders(false)
                            .setPageSize(5).build();
            itemPagedList = (new LivePagedListBuilder(carDataSourceFactory, pagedListConfig))
                    .build();
        }

        public void refresh() {
            carDataSourceFactory.getItemLiveDataSource().getValue().invalidate();
        }
    }

