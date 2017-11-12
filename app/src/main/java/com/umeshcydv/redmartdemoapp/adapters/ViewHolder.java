package com.umeshcydv.redmartdemoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeshcydv.redmartdemoapp.R;
import com.umeshcydv.redmartdemoapp.Util;
import com.umeshcydv.redmartdemoapp.models.PricingInfo;
import com.umeshcydv.redmartdemoapp.models.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by umeshcydv on 12/11/17.
 */

class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.main_container)
    LinearLayout mainContainer;

    @BindView(R.id.item_default_image)
    ImageView productImage;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.price)
    TextView price;

    @BindView(R.id.discounted_price)
    TextView discountedPrice;

    @BindView(R.id.discount_text)
    TextView discountText;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Product product) {
        title.setText(product.getTitle());
        setpricing(product.getPricing());
        Glide.with(itemView.getContext()).load(Util.getImageUrl(product.getImage().getName())).into(productImage);
    }

    private void setpricing(PricingInfo pricingInfo) {
        if (pricingInfo.getPromoPrice().intValue() > 0) {
            discountedPrice.setText("$" + String.format("%.2f", pricingInfo.getPromoPrice().floatValue()));
            price.setText("$" + String.format("%.2f", pricingInfo.getPrice().floatValue()));
            price.setVisibility(View.VISIBLE);
            if (pricingInfo.getSavintText() != null) {
                discountText.setText(pricingInfo.getSavintText().toUpperCase());
                discountText.setVisibility(View.VISIBLE);
            }
            return;
        }
        discountedPrice.setText("$" + pricingInfo.getPrice().toString());
        price.setVisibility(View.GONE);
        discountText.setVisibility(View.GONE);
    }
}