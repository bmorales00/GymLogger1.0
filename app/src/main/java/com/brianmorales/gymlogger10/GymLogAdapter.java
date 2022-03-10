package com.brianmorales.gymlogger10;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.brianmorales.gymlogger10.models.GymLog;

public class GymLogAdapter extends RecyclerView.Adapter<GymLogAdapter.ViewHolder>{

    private final int SET_START_COUNT = 1;

    Context context;
    private GymLog logger;
    private int sets = SET_START_COUNT;

    public GymLogAdapter(Context context, GymLog logger) {
        this.context = context;
        this.logger = logger;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.logger_card_items, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvCardPounds.setText(logger.getWeightAt(i) + " lbs");
        viewHolder.tvCardReps.setText(logger.getRepsAt(i) + " reps");
        viewHolder.tvCardSets.setText(Integer.toString(sets++));
    }

    @Override
    public int getItemCount() {
        return logger.getSetSize();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvCardSets;
        TextView tvCardPounds;
        TextView tvCardReps;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCardSets = itemView.findViewById(R.id.tvCardSets);
            tvCardPounds = itemView.findViewById(R.id.tvCardPounds);
            tvCardReps = itemView.findViewById(R.id.tvCardReps);

        }
    }

}
