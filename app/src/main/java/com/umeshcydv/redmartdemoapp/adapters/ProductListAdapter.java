package com.umeshcydv.redmartdemoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeshcydv.redmartdemoapp.R;
import com.umeshcydv.redmartdemoapp.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by umeshcydv on 12/11/17.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private List<Product> mProductList;

    public ProductListAdapter(OnItemClickListener onItemClickListener, LayoutInflater layoutInflater) {
        this.mOnItemClickListener = onItemClickListener;
        this.mLayoutInflater = layoutInflater;
        this.mProductList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.product_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bind(mProductList.get(position));
        holder.mainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(mProductList.get(position), holder.itemView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public void addProducts(List<Product> products) {
        mProductList.addAll(products);
    }

    public interface OnItemClickListener {
        void onClick(Product product, View itemView);
    }
}
