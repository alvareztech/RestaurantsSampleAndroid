package tech.alvarez.restaurantssample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import tech.alvarez.restaurantssample.R;
import tech.alvarez.restaurantssample.models.Restaurant;

/**
 * Created on 11/6/16.
 *
 * @author Daniel Alvarez
 */

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder> {

    private ArrayList<Restaurant> dataset;
    private OnItemClickListener onItemClickListener;

    public RestaurantsAdapter(OnItemClickListener onItemClickListener) {
        this.dataset = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Restaurant r = dataset.get(position);
        holder.nameTextView.setText(r.getName());
        holder.addressTextView.setText(r.getStreet_name());
        holder.ratingBar.setRating(Float.parseFloat(r.getRate()));

        holder.setOnItemClickListener(r, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView addressTextView;
        RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            addressTextView = (TextView) itemView.findViewById(R.id.addressTextView);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        }

        public void setOnItemClickListener(final Restaurant r, final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(r);
                }
            });
        }
    }

    public void setDataset(ArrayList<Restaurant> dataset) {
        this.dataset = dataset;
        notifyDataSetChanged();
    }
}
