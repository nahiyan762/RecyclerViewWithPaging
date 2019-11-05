package com.example.recyclerviewwithpaging.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.recyclerviewwithpaging.datasource.ItemDataSourceFactory;
import com.example.recyclerviewwithpaging.model.Item;

public class ItemViewModel extends ViewModel {

    public LiveData<PagedList<Item>> itemPagedList;
    public LiveData<PageKeyedDataSource<Integer, Item>> liveDataSource;

    public ItemViewModel(){
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(15).build();
        itemPagedList = new LivePagedListBuilder(itemDataSourceFactory, config).build();
    }
}
