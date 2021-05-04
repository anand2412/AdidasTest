package com.adidas.adidasproduct.ui.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.adidas.adidasproduct.data.api.ApiHelper;
import com.adidas.adidasproduct.data.model.ProductResponse;
import com.adidas.adidasproduct.ui.adapter.ProductListAdapter;
import com.adidas.adidasproduct.ui.viewmodel.ProductResponseViewModel;
import com.adidas.adidasproduct.ui.viewmodel.ViewModelFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adidas.adidasproduct.R;

import java.util.ArrayList;

import static com.adidas.adidasproduct.util.Constants.KEY_PRODUCTS;

public class ProductListActivity extends AppCompatActivity {

    private static final String TAG ="ProductListActivity";
    private RecyclerView mRvProducts;
    private EditText mEditSearch;
    private ProductListAdapter mAdapter;
    private ProductResponseViewModel mProductViewModel;
    private ArrayList<ProductResponse> mProductList = new ArrayList<>();
    private ProgressBar mProgressBar;
    private TextView mTvErrorText;
    private LinearLayout mLytErrorView;
    private HandlerThread searchHT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        searchHT = new HandlerThread("search");
        searchHT.start();
        setupUI();
        setupObservers();
    }

    private void setupObservers() {
        mProductViewModel = new ViewModelProvider(this, new ViewModelFactory(ApiHelper.getInstance())).get(ProductResponseViewModel.class);
        mProgressBar.setVisibility(View.VISIBLE);
        mProductViewModel.getAllProducts();
        mProductViewModel.productResponse.observe(this, productResponseList -> {
            mProgressBar.setVisibility(View.GONE);
            if(productResponseList != null) {
                mRvProducts.setVisibility(View.VISIBLE);
                mProductList.addAll(productResponseList);
                mAdapter.notifyDataSetChanged();
            } else {
                mLytErrorView.setVisibility(View.VISIBLE);
            }
        });
        mProductViewModel.error.observe(this, message -> {
            mProgressBar.setVisibility(View.GONE);
            mLytErrorView.setVisibility(View.VISIBLE);
            mTvErrorText.setText(message.message());
        });
    }

    private void setupUI() {
        mRvProducts = findViewById(R.id.rv_products);
        mEditSearch = findViewById(R.id.editSearch);
        mEditSearch.addTextChangedListener(textWatcher);
        mProgressBar = findViewById(R.id.progressBar);
        mTvErrorText = findViewById(R.id.tv_errorText);
        mLytErrorView = findViewById(R.id.lyt_errorView);
        mRvProducts.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ProductListAdapter(this, mProductList, item -> {
            Intent intent = new Intent(ProductListActivity.this, ProductReviewsActivity.class);
            intent.putExtra(KEY_PRODUCTS, item);
            startActivity(intent);
        });
        mRvProducts.setHasFixedSize(true);
        mRvProducts.setAdapter(mAdapter);
        Log.d(TAG,"UI initialization completed...");
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            //search could be heavy in case of search is performed from database
            Handler handler = new Handler(searchHT.getLooper());
            handler.post(() -> filter(s.toString()));
        }
    };

    /**
     * @param text
     */
    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<ProductResponse> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (ProductResponse s : mProductList) {
            //if the existing elements contains the search input
            if (s.getName().toLowerCase().contains(text.toLowerCase()) || s.getDescription().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        runOnUiThread(() -> mAdapter.filterList(filterdNames));

    }
}