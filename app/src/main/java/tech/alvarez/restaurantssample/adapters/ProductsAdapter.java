package tech.alvarez.restaurantssample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import tech.alvarez.restaurantssample.R;
import tech.alvarez.restaurantssample.models.Product;

/**
 * Created on 11/6/16.
 *
 * @author Daniel Alvarez
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private ArrayList<Product> dataset;

    public ProductsAdapter() {
        this.dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product r = dataset.get(position);
        holder.nameTextView.setText(r.getName());
        holder.descriptionTextView.setText(r.getDescription());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
        }
    }

    public void setDataset(ArrayList<Product> dataset) {
        this.dataset = dataset;
        notifyDataSetChanged();
    }
}
