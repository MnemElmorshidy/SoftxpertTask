package com.example.softxperttask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeLayout = findViewById(R.id.swipe_container);
        recyclerView = findViewById(R.id.car_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        ItemViewModel carViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        final CarAdapter adapter = new CarAdapter(this);
        getCarData(carViewModel, adapter);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                carViewModel.refresh();
                getCarData(carViewModel, adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.brand), Toast.LENGTH_SHORT).show();

            }
        });

        recyclerView.setAdapter(adapter);
    }
    private void getCarData(ItemViewModel carViewModel, CarAdapter adapter) {
        carViewModel.itemPagedList.observe(this, new Observer<PagedList<CarItem>>() {
            @Override
            public void onChanged(@Nullable PagedList<CarItem> items) {
                adapter.submitList(items);
                swipeLayout.setRefreshing(false);
            }
        });
    }
}