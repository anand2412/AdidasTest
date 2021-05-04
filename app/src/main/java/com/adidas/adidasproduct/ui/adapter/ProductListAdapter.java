package com.adidas.adidasproduct.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adidas.adidasproduct.R;
import com.adidas.adidasproduct.data.model.ProductResponse;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ProductResponse> mProductList;
    private List<ProductResponse> productListFiltered;
    private final OnItemClickListener listener;

    public ProductListAdapter(Context context, ArrayList<ProductResponse> productResponseList, OnItemClickListener listener){
        mProductList = productResponseList;
        mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mProductList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    /**
     * @param filterdNames
     */
    public void filterList(ArrayList<ProductResponse> filterdNames) {
        this.mProductList = filterdNames;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(ProductResponse item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgViewCover;
        private final TextView tvTitle;
        private final TextView tvDescription;
        private final TextView tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewCover= itemView.findViewById(R.id.ivProductImage);
            tvTitle= itemView.findViewById(R.id.tvTitle);
            tvPrice= itemView.findViewById(R.id.tvPrice);
            tvDescription= itemView.findViewById(R.id.tvDescription);
        }

        public void bind(final ProductResponse response, final OnItemClickListener listener) {
            tvTitle.setText(response.getName());
            tvDescription.setText(response.getDescription());
            tvPrice.setText(response.getCurrency());
            Glide.with(mContext).load(response.getImgUrl())
                    .placeholder(R.drawable.ic_noimg)
                    .error(R.drawable.ic_error_image)
                    .fallback(R.drawable.ic_noimg)
                    .into(imgViewCover);
            itemView.setOnClickListener(v -> listener.onItemClick(response));
        }
    }
}
