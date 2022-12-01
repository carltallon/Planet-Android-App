package com.example.msdassignment;

import static androidx.core.graphics.drawable.IconCompat.*;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class PlanetRecyclerViewAdapter extends ListAdapter<PlanetModal, PlanetRecyclerViewAdapter.ViewHolder> {

    // creating a variable for on item click listener.
    private OnItemClickListener listener;
    private String imageID;
    private Integer imageIDint;

    // creating a constructor class for our adapter class.
    PlanetRecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<PlanetModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<PlanetModal>() {
        @Override
        public boolean areItemsTheSame(PlanetModal oldItem, PlanetModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(PlanetModal oldItem, PlanetModal newItem) {
            // below line is to check the course name, description and course duration.
            return oldItem.getPlanetName().equals(newItem.getPlanetName()) &&
                    oldItem.getPlanetsize().equals(newItem.getPlanetsize()) &&
                    oldItem.getPlanetDistancetoSun().equals(newItem.getPlanetDistancetoSun());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is use to inflate our layout
        // file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.planet_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // below line of code is use to set data to
        // each item of our recycler view.
        PlanetModal model = getPlanetAt(position);
        holder.planetNameTV.setText(model.getPlanetName());
        holder.planetsizeTV.setText(model.getPlanetsize());
        holder.planetDistancetoSunTV.setText(model.getPlanetDistancetoSun());

        //image setting
        imageID = model.getPlanetIMAGE();
        imageIDint = Integer.parseInt(imageID);

        if (imageIDint == 1) {
            holder.planetimgTV.setImageResource(R.drawable.planet1);
        } else if (imageIDint == 2) {
            holder.planetimgTV.setImageResource(R.drawable.planet2);
        } else if (imageIDint == 3) {
            holder.planetimgTV.setImageResource(R.drawable.planet3);
        } else if (imageIDint == 4) {
            holder.planetimgTV.setImageResource(R.drawable.planet4);
        } else if (imageIDint == 5) {
            holder.planetimgTV.setImageResource(R.drawable.planet5);
        } else if (imageIDint == 6) {
            holder.planetimgTV.setImageResource(R.drawable.planet6);
        } else {
            holder.planetimgTV.setImageResource(R.drawable.ic_launcher_background);
        }

    }
    // creating a method to get course modal for a specific position.
    public PlanetModal getPlanetAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // view holder class to create a variable for each view.
        TextView planetNameTV, planetsizeTV, planetDistancetoSunTV;
        ImageView planetimgTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing each view of our recycler view.
            planetNameTV = itemView.findViewById(R.id.idPlanetname);
            planetsizeTV = itemView.findViewById(R.id.idPlanetsize);
            planetDistancetoSunTV = itemView.findViewById(R.id.idPlanetDistancetoSun);
            planetimgTV = itemView.findViewById(R.id.planetIMGlist);

            // adding on click listener for each item of recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // inside on click listener we are passing
                    // position to our item of recycler view.
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PlanetModal model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

