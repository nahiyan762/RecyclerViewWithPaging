package com.example.recyclerviewwithpaging.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.recyclerviewwithpaging.R;
import com.example.recyclerviewwithpaging.adapter.ItemAdapter;
import com.example.recyclerviewwithpaging.datasource.ItemDataSourceFactory;
import com.example.recyclerviewwithpaging.model.Item;
import com.example.recyclerviewwithpaging.model.StackApiResponse;
import com.example.recyclerviewwithpaging.network.StackOverFlowService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwipeRefreshLayout usersSwipeRefreshLayout = findViewById(R.id.usersSwipeRefreshLayout);
        RecyclerView usersRecyclerView = findViewById(R.id.usersRecyclerView);

        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersRecyclerView.setHasFixedSize(true);


//        LiveData<PageKeyedDataSource<Integer, Item>> liveDataSource;

//        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        ItemAdapter itemAdapter = new ItemAdapter(this);

        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
//        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(15).build();
        LiveData<PagedList<Item>> itemPagedList = new LivePagedListBuilder(itemDataSourceFactory, config).build();

        itemPagedList.observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(PagedList<Item> items) {
                itemAdapter.submitList(items);
            }
        });

        usersRecyclerView.setAdapter(itemAdapter);

    }
}
