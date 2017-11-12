package com.umeshcydv.redmartdemoapp.ui;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.umeshcydv.redmartdemoapp.R;
import com.umeshcydv.redmartdemoapp.RedmartDemoApplication;
import com.umeshcydv.redmartdemoapp.adapters.ProductListAdapter;
import com.umeshcydv.redmartdemoapp.models.ListPageInfo;
import com.umeshcydv.redmartdemoapp.models.Product;
import com.umeshcydv.redmartdemoapp.presenter.ProductListPresenter;
import com.umeshcydv.redmartdemoapp.services.RedmartAPI;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Single;

public class ProductListActivity extends AppCompatActivity implements ProductListPresentation, ProductListAdapter.OnItemClickListener {

    private static final int THRESHOLD_GET_MORE_RESULTS_SRP = 6;
    @Inject
    RedmartAPI redmartAPIServices;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.progressbar)
    ProgressBar mProgressBar;

    private ProductListPresenter mPresenter;
    private ProductListAdapter mProductListAdapter;
    private StaggeredGridLayoutManager mGridLayoutManager;
    private static final int COLOUMN_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);

        ((RedmartDemoApplication) getApplication()).getApiComponent().inject(this);
        configureViews();

        mPresenter = new ProductListPresenter(this);
        mPresenter.onCreate();
        mPresenter.fetchListPageInfo();
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
    public void onSuccess(ListPageInfo listPageInfo) {
        mProductListAdapter.addProducts(listPageInfo.getProducts());
        mProductListAdapter.notifyItemRangeInserted(mPresenter.getCurrentProductCount() - mPresenter.getPageSize(), mPresenter.getCurrentProductCount());
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public Single<ListPageInfo> getListPageinfo() {
        return redmartAPIServices.getListPageInfo(mPresenter.getPageCount(), mPresenter.getPageSize());
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(Product product, View itemView) {
        Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
        intent.putExtra("ITEM_ID", product.getId().toString());

        Pair<View, String> imageTransition = Pair.create(itemView.findViewById(R.id.item_default_image), "image");
        Pair<View, String> titleTransition = Pair.create(itemView.findViewById(R.id.title), "title");
        Pair<View, String> artistTransition = Pair.create(itemView.findViewById(R.id.price), "price");

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, imageTransition, titleTransition, artistTransition);
        startActivity(intent, options.toBundle());
    }


    private void configureViews() {
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mGridLayoutManager = new StaggeredGridLayoutManager(COLOUMN_COUNT, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.addOnScrollListener(scrollListener);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mProductListAdapter = new ProductListAdapter(this, getLayoutInflater());
        mRecyclerView.setAdapter(mProductListAdapter);
    }

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int[] into = new int[COLOUMN_COUNT];
            mGridLayoutManager.findLastCompletelyVisibleItemPositions(into);
            int totalCount = mGridLayoutManager.getItemCount();
            if (totalCount - into[1] < THRESHOLD_GET_MORE_RESULTS_SRP && mPresenter.getShouldFetchMore()) {
                mPresenter.fetchMoreProducts();
            }
        }
    };
}
