package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dgreenhalgh.android.simpleitemdecoration.grid.GridDividerItemDecoration;
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;
import com.example.myapplication.adapter.GridDataAdapter;
import com.example.myapplication.adapter.ProductListAdapter;
import com.example.myapplication.model.GridDataModel;
import com.example.myapplication.model.ProductListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView adjust_image;
    private Context context;
    private RecyclerView rv_grid,rv_list;
    private ArrayList<GridDataModel> gridDataModels = null;
    private ArrayList<ProductListModel> productListModels = null;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        adjust_image = findViewById(R.id.adjust_image);
        rv_grid = findViewById(R.id.rv_grid);
        rv_list = findViewById(R.id.rv_list);

        setValues();
    }

    private void setValues() {
        gridDataModels = new ArrayList<>();
        productListModels = new ArrayList<>();
        String image_url = "";
        int column_count;
        try {
            JSONObject productobj = new JSONObject(loadJSONFromAsset());
            System.out.println("Json fILE dATA"+productobj.toString());
            JSONObject jsonObject = productobj.getJSONObject("object");
            image_url = jsonObject.getString("image_url");
            column_count = jsonObject.getInt("grid_items_column_count");
            JSONArray gridArray = jsonObject.getJSONArray("grid_items_list");
            JSONArray productArray = jsonObject.getJSONArray("product_list_items");

            for (int i=0;i<gridArray.length();i++){
                JSONObject gridobj = gridArray.getJSONObject(i);
                GridDataModel gridDataModel = new GridDataModel();
                gridDataModel.setItem_id(gridobj.getString("item_id"));
                gridDataModel.setItem_image(gridobj.getString("item_image"));
                gridDataModel.setItem_name(gridobj.getString("item_name"));
                gridDataModels.add(gridDataModel);
            }

            for (int j=0;j<productArray.length();j++){
                JSONObject productlistobj = productArray.getJSONObject(j);
                ProductListModel productListModel = new ProductListModel();
                productListModel.setItem_id(productlistobj.getString("item_id"));
                productListModel.setItem_name(productlistobj.getString("item_name"));
                productListModel.setItem_image(productlistobj.getString("item_image"));
                productListModel.setItem_quantity(productlistobj.getString("item_quantity"));
                productListModel.setItem_marked_price(productlistobj.getInt("item_marked_price"));
                productListModel.setItem_selling_price(productlistobj.getInt("item_selling_price"));

                productListModels.add(productListModel);
            }

//            Grid Items
            GridDataAdapter gridDataAdapter = new GridDataAdapter(MainActivity.this,gridDataModels);
            gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
            gridLayoutManager.setSpanCount(column_count);
            rv_grid.setLayoutManager(gridLayoutManager);
            rv_grid.setAdapter(gridDataAdapter);
            Drawable horizontalDivider = ContextCompat.getDrawable(this, R.drawable.grid_divider);
            Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.grid_divider);
            rv_grid.addItemDecoration(new GridDividerItemDecoration(horizontalDivider,verticalDivider,column_count));

//            Product List
            ProductListAdapter productListAdapter = new ProductListAdapter(MainActivity.this,productListModels);
            linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
            rv_list.setLayoutManager(linearLayoutManager);
            rv_list.setAdapter(productListAdapter);
            Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.grid_divider);
            rv_list.addItemDecoration(new DividerItemDecoration(dividerDrawable));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Glide.with(MainActivity.this).asBitmap().load(image_url).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                int width = adjust_image.getMeasuredWidth();
                int diw = resource.getWidth();
                if (diw > 0) {
                    int height = 0;
                    height = width * resource.getHeight() / diw;
                    resource = Bitmap.createScaledBitmap(resource, width, height, false);
                }
                adjust_image.setImageBitmap(resource);
            }
        });
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("product.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}