package com.adidas.adidasproduct.ui.view;

import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.adidas.adidasproduct.data.model.ProductResponse;

import androidx.appcompat.app.AppCompatActivity;

import com.adidas.adidasproduct.R;
import com.adidas.adidasproduct.data.model.Reviews;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.adidas.adidasproduct.util.Constants.KEY_PRODUCTS;

public class ProductReviewsActivity extends AppCompatActivity {

    private static final String TAG = "ProductReviewsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_product_reviews);
        ProductResponse productDetail = getIntent().getParcelableExtra(KEY_PRODUCTS);
        setUpActionBar(productDetail);
        setUpReviews(productDetail);
    }

    /**
     * @param response
     * reviews is not being fetched from review api as giving empty data
     * or else it can be performed adding product id as a path in review url
     */
    private void setUpReviews(ProductResponse response) {
        ListView lvReviews = findViewById(R.id.lvReviews);
        ArrayList<String> reviewsList = new ArrayList<>();
        for(Reviews review: response.getReviews()) {
            reviewsList.add(review.getText());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                reviewsList );
        lvReviews.setAdapter(arrayAdapter);
    }

    private void setUpActionBar(ProductResponse productDetail) {
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ImageView ivProductImage = findViewById(R.id.iv_product_thumb);
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvDescription = findViewById(R.id.tv_description);
        TextView tvPrice = findViewById(R.id.tv_price);
        Glide.with(this).load(productDetail.getImgUrl())
                .placeholder(R.drawable.ic_noimg)
                .error(R.drawable.ic_error_image)
                .fallback(R.drawable.ic_noimg)
                .into(ivProductImage);
        tvTitle.setText(productDetail.getName());
        tvDescription.setText(productDetail.getDescription());
        tvPrice.setText(productDetail.getCurrency());
    }
}