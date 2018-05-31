package com.emedinaa.myfirstapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emedinaa.myfirstapp.R;
import com.emedinaa.myfirstapp.model.Product;

import java.util.List;

/**
 * Created by emedinaa on 30/09/17.
 */

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private final List<Product> products;
    private final Context context;


    public ProductAdapter(Context context, List<Product> mProducts) {
        this.products = mProducts;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_product, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - replace the contents of the view with that element
        Product product= products.get(position);

        final String productName=product.getName();
        final String productDesc=product.getDesc();
        final String productPrice="S/."+product.getPrice();

        holder.textViewTitle.setText(productName);
        holder.textViewDesc.setText(productDesc);
        holder.textViewPrice.setText(productPrice);

        if(product.getImage()!=0){
            holder.imageViewProduct.setImageResource(product.getImage());
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewTitle;
        public TextView textViewDesc;
        public TextView textViewPrice;
        public ImageView imageViewProduct;
        public View view;
        public ViewHolder(View  v) {
            super(v);
            this.view = v;
            textViewTitle=  v.findViewById(R.id.textViewTitle);
            textViewDesc=  v.findViewById(R.id.textViewDesc);
            textViewPrice=  v.findViewById(R.id.textViewPrice);
            imageViewProduct=  v.findViewById(R.id.imageViewProduct);
        }
    }
}
