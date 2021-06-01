package com.example.spendingtracker_v2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> spend_id, spend_description, spend_value, spend_date;

    CustomAdapter(Context context,
                  ArrayList spend_id,
                  ArrayList spend_description,
                  ArrayList spend_value,
                  ArrayList spend_date){
            this.context = context;
            this.spend_id = spend_id;
            this.spend_description = spend_description;
            this.spend_value = spend_value;
            this.spend_date = spend_date;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.spending_id_text.setText(String.valueOf(spend_id.get(position)));
        holder.spending_description_txt.setText(String.valueOf(spend_description.get(position)));
        holder.spending_date_txt.setText(String.valueOf(spend_date.get(position)));
        holder.spending_value_txt.setText(String.valueOf(spend_value.get(position)));
    }

    @Override
    public int getItemCount() {
        return spend_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView spending_id_text, spending_description_txt, spending_date_txt, spending_value_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            spending_id_text = itemView.findViewById(R.id.spending_id_text);
            spending_description_txt = itemView.findViewById(R.id.spending_description_txt);
            spending_date_txt = itemView.findViewById(R.id.spending_date_txt);
            spending_value_txt = itemView.findViewById(R.id.spending_value_txt);
        }
    }
}
