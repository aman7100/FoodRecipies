package com.example.foodapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Models.RecipieModel;
import com.example.foodapp.R;

import java.util.ArrayList;

public class RecipieAdapter extends RecyclerView.Adapter<RecipieAdapter.viewHolder>{

   ArrayList<RecipieModel> List;
   Context context;

    public RecipieAdapter(ArrayList<RecipieModel> list, Context context) {
        List = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(context).inflate(R.layout.layout_recyclerview,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
         RecipieModel model=List.get(position);
         holder.imageView.setImageResource(model.getPic());
         holder.textView.setText(model.getText());
         switch(position)
         {
             case 0:
                 holder.imageView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {

                     }
                 });holder.textView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {

                 }
             });
                 break;

             case 1:
                 holder.imageView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                     }
                 });holder.textView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {

                 }
             });
                 break;

         }

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
       ImageView imageView;
       TextView textView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView6);
        }
    }





}
