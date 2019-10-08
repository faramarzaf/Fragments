package com.faramarz.tictacdev.fragments.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.faramarz.tictacdev.fragments.R;
import com.faramarz.tictacdev.fragments.model.Car;
import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {

    private Context mContext;
    private CarAdapterListener listener;
    List<Car> carsList = new ArrayList<>();
    private SparseBooleanArray selectedItems;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView car_name,car_price;
        ImageView car_avatar;
        LinearLayout car_container;
        public MyViewHolder(View view) {
            super(view);
           car_name = view.findViewById(R.id.car_name);
            car_price = view.findViewById(R.id.car_price);
            car_avatar = view.findViewById(R.id.car_avatar);
            car_container = view.findViewById(R.id.car_container);
            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public CarAdapter(Context mContext, List<Car> carsList, CarAdapterListener listener) {
        this.mContext = mContext;
        this.carsList = carsList;
        this.carsList = carsList;
        this.listener = listener;

    }
    @NonNull
    @Override
    public CarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.MyViewHolder holder, int position) {
        final Car car = carsList.get(position);
        holder.car_name.setText(car.getName());
        holder.car_price.setText(car.getPrice());
        applyAvatarCar(holder,car);
        applyClickEvents(holder, position);
    }

    @Override
    public int getItemCount() {
        return carsList.size();
    }

    @Override
    public long getItemId(int position) {
        return carsList.get(position).getId();
    }

    private void applyAvatarCar(MyViewHolder holder, Car car) {
            Glide.with(mContext).load(car.getAvatar())
                   // make avatar circle with this .apply(RequestOptions.circleCropTransform())
                    .into(holder.car_avatar);

    }

    private void applyClickEvents(MyViewHolder holder, final int position) {
          holder.car_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCarRowClicked(position);
            }
        });
    }

    public interface CarAdapterListener {
        void onCarRowClicked(int position);
    }

}
