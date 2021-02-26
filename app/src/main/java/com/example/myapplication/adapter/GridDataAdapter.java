package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.GridDataModel;
import com.example.myapplication.model.ProductListModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridDataAdapter extends RecyclerView.Adapter<GridDataAdapter.GridViewHolder>{

    private Context context;
    private ArrayList<GridDataModel> gridDataModels;

    public GridDataAdapter(Context context, ArrayList<GridDataModel> gridDataModels) {
        this.context = context;
        this.gridDataModels = gridDataModels;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_list_item, parent, false);

        GridViewHolder viewHolder = new GridViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        try {
            GridDataModel gridDataModel = gridDataModels.get(position);
            holder.item_name.setText(gridDataModel.getItem_name());
            Glide.with(context).asBitmap().load(gridDataModel.getItem_image()).placeholder(R.mipmap.ic_launcher).into(holder.item_image);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return gridDataModels.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {

        ImageView item_image;
        TextView item_name;
        public GridViewHolder(View view){
            super(view);

            item_image = view.findViewById(R.id.item_image);
            item_name = view.findViewById(R.id.item_name);

        }

    }
}
