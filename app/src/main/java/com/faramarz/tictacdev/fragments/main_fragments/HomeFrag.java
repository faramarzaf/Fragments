package com.faramarz.tictacdev.fragments.main_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.faramarz.tictacdev.fragments.R;
import com.faramarz.tictacdev.fragments.adapter.CarAdapter;
import com.faramarz.tictacdev.fragments.model.Car;
import com.faramarz.tictacdev.fragments.network.ApiClient;
import com.faramarz.tictacdev.fragments.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFrag extends Fragment implements SwipeRefreshLayout.OnRefreshListener, CarAdapter.CarAdapterListener {

    private List<Car> carList;
    private CarAdapter carAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    public HomeFrag() {

    }

    public static HomeFrag newInstance() {
        Bundle args = new Bundle();
        HomeFrag fragment = new HomeFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carList = new ArrayList<>();
        carAdapter = new CarAdapter(getContext(), carList, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getCars();
    }

    private void initialize(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(carAdapter);


    }

    private void getCars() {
        swipeRefreshLayout.setRefreshing(true);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Car>> call = apiService.getCars();
        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                // clear the list
                carList.clear();
                for (Car car : response.body()) {
                    carList.add(car);
                }
                carAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Toast.makeText(getContext(), "Unable to fetch json: " + t.getMessage(), Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }

        });
    }

    @Override
    public void onRefresh() {
        getCars();
    }

    @Override
    public void onCarRowClicked(int position) {
        if (carList != null && carList.size() > 0) {
            Car car = carList.get(position);
            carList.set(position, car);
            carAdapter.notifyDataSetChanged();
            Toast.makeText(getContext(), car.getName(), Toast.LENGTH_SHORT).show();

        }
    }


}
