package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.ProductListModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>{

    private Context context;
    private ArrayList<ProductListModel> productListModels;

    public ProductListAdapter(Context context, ArrayList<ProductListModel> productListModels) {
        this.context = context;
        this.productListModels = productListModels;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);

        ProductViewHolder viewHolder = new ProductViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        try {
            ProductListModel productListModel = productListModels.get(position);

            holder.tv_item_name.setText(productListModel.getItem_name());
            holder.tv_item_quantity.setText(productListModel.getItem_quantity());
            Glide.with(context).asBitmap().load(productListModel.getItem_image()).placeholder(R.mipmap.ic_launcher).centerCrop().into(holder.item_image);
            holder.tv_mrp.setText("MRP "+context.getResources().getString(R.string.rupee)+String.valueOf(productListModel.getItem_marked_price()));
            holder.tv_dmart_price.setText("DMart "+context.getResources().getString(R.string.rupee)+String.valueOf(productListModel.getItem_selling_price()));
            holder.tv_saved_amount.setText("Save "+context.getResources().getString(R.string.rupee)+String.valueOf(productListModel.getItem_marked_price()-productListModel.getItem_selling_price()));

            holder.tv_mrp.setPaintFlags(holder.tv_mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return productListModels.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView item_image;
        TextView tv_item_name,tv_mrp,
                tv_dmart_price,tv_saved_amount,tv_item_quantity;
        public ProductViewHolder(View view){
            super(view);

            item_image = view.findViewById(R.id.item_image);
            tv_item_name = view.findViewById(R.id.tv_item_name);
            tv_mrp = view.findViewById(R.id.tv_mrp);
            tv_dmart_price = view.findViewById(R.id.tv_dmart_price);
            tv_saved_amount = view.findViewById(R.id.tv_saved_amount);
            tv_item_quantity = view.findViewById(R.id.tv_item_quantity);

        }

    }
}

