package com.example.msdassignment;

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

    //variable declaration, imageID
    private OnItemClickListener listener;
    private String imageID;
    private Integer imageIDint;

    //
    PlanetRecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    //
    private static final DiffUtil.ItemCallback<PlanetModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<PlanetModal>() {
        @Override
        public boolean areItemsTheSame(PlanetModal oldItem, PlanetModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(PlanetModal oldItem, PlanetModal newItem) {
            //
            return oldItem.getPlanetName().equals(newItem.getPlanetName()) &&
                    oldItem.getPlanetsize().equals(newItem.getPlanetsize()) &&
                    oldItem.getPlanetDistancetoSun().equals(newItem.getPlanetDistancetoSun());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.planet_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //
        PlanetModal model = getPlanetAt(position);
        holder.planetNameTV.setText(model.getPlanetName());
        holder.planetsizeTV.setText(model.getPlanetsize());
        holder.planetDistancetoSunTV.setText(model.getPlanetDistancetoSun());

        //image setting
        imageID = model.getPlanetIMAGE();
        imageIDint = Integer.parseInt(imageID);

        //if statement to set list image to chosen image
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
    //
    public PlanetModal getPlanetAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //
        TextView planetNameTV, planetsizeTV, planetDistancetoSunTV;
        ImageView planetimgTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            //
            planetNameTV = itemView.findViewById(R.id.idPlanetname);
            planetsizeTV = itemView.findViewById(R.id.idPlanetsize);
            planetDistancetoSunTV = itemView.findViewById(R.id.idPlanetDistancetoSun);
            planetimgTV = itemView.findViewById(R.id.planetIMGlist);

            //setting
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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

