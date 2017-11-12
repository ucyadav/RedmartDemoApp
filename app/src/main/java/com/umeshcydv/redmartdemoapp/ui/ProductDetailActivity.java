package com.umeshcydv.redmartdemoapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.umeshcydv.redmartdemoapp.R;
import com.umeshcydv.redmartdemoapp.RedmartDemoApplication;
import com.umeshcydv.redmartdemoapp.Util;
import com.umeshcydv.redmartdemoapp.models.PricingInfo;
import com.umeshcydv.redmartdemoapp.models.Product;
import com.umeshcydv.redmartdemoapp.models.ProductDesc;
import com.umeshcydv.redmartdemoapp.models.ProductDetail;
import com.umeshcydv.redmartdemoapp.presenter.ProductDetailPresenter;
import com.umeshcydv.redmartdemoapp.services.RedmartAPI;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Single;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailPresentation {

    @Inject
    RedmartAPI redmartAPIServices;

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.price)
    TextView mPrice;

    @BindView(R.id.discounted_price)
    TextView mDiscountedPrice;

    @BindView(R.id.discount_text)
    TextView mDiscountText;

    @BindView(R.id.progressbar)
    ProgressBar mPreProgressBar;

    @BindView(R.id.view_switcher)
    ViewSwitcher mViewSwitcher;

    @BindView(R.id.desc_layout)
    LinearLayout mDescriptionLayout;

    private String productid;
    private ProductDetailPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);

        ((RedmartDemoApplication) getApplication()).getApiComponent().inject(this);

        productid = getIntent().getStringExtra("ITEM_ID");
        mPresenter = new ProductDetailPresenter(this);
        mPresenter.onCreate();
        mPresenter.fetchProductDetail();
    }

    @Override
    public void onSuccess(Product product) {
        configureViews(product);
    }

    @Override
    public void onError(String message) {
        //todo have to add error reporting tool
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void switchView() {
        mViewSwitcher.showNext();
    }

    @Override
    public Single<ProductDetail> getProductDetail() {
        return redmartAPIServices.getProductDetail(productid);
    }

    private void configureViews(Product product){
        Glide.with(this).load(Util.getImageUrl(product.getImage().getName())).into(mProductImage);
        setpricing(product.getPricing());
        mTitle.setText(product.getTitle());
        List<ProductDesc> productDescPrimary = product.getProductDescMap().get("primary");
        for (ProductDesc productDesc : productDescPrimary) {
            View view = LayoutInflater.from(this).inflate(R.layout.product_desc_layout, null);
            TextView heading = ButterKnife.findById(view, R.id.heading);
            TextView description = ButterKnife.findById(view, R.id.desc);
            heading.setText(productDesc.getName());
            description.setText(productDesc.getContent());
            mDescriptionLayout.addView(view);
        }

        List<ProductDesc> productDescSecondary = product.getProductDescMap().get("secondary");
        for (ProductDesc productDesc : productDescSecondary) {
            View view = LayoutInflater.from(this).inflate(R.layout.product_desc_layout, null);
            TextView heading = ButterKnife.findById(view, R.id.heading);
            TextView description = ButterKnife.findById(view, R.id.desc);
            heading.setText(productDesc.getName());
            description.setText(productDesc.getContent());
            mDescriptionLayout.addView(view);
        }
        switchView();

    }

    private void setpricing(PricingInfo pricingInfo) {
        if (pricingInfo.getPromoPrice().intValue() > 0) {
            mDiscountedPrice.setText("$" + String.format("%.2f", pricingInfo.getPromoPrice().floatValue()));
            mPrice.setText("$" + String.format("%.2f", pricingInfo.getPrice().floatValue()));
            mPrice.setVisibility(View.VISIBLE);
            if (pricingInfo.getSavintText() != null) {
                mDiscountText.setText(pricingInfo.getSavintText().toUpperCase());
                mDiscountText.setVisibility(View.VISIBLE);
            }
            return;
        }
        mDiscountedPrice.setText("$" + pricingInfo.getPrice().toString());
    }


}
