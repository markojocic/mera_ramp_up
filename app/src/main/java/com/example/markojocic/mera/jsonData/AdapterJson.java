package com.example.markojocic.mera.jsonData;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;


import com.example.markojocic.mera.R;


public class AdapterJson extends RecyclerView.Adapter<AdapterJson.ViewHolder> {
    private ArrayList<Items> values;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtName;
        public TextView txtOwner;
        public TextView txtSizeLine;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtName =  v.findViewById(R.id.firstLine);
            txtOwner =  v.findViewById(R.id.secondLine);
            txtSizeLine = v.findViewById(R.id.sizeLine);
        }
    }
/*
    public void add(int position, String item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }*/

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterJson() {


    }

    public void SetAdapterJson(ArrayList<Items> Item){
        values = Item;


    }



    // Create new views (invoked by the layout manager)
    @Override
    public AdapterJson.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        Log.e("ON CREATE VIEW HOLDER","----------->\n");
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.layout_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.e("ON BIND VIEW HOLDER","--------------->\n");
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = values.get(position).getName();
        holder.txtName.setText("Name: " + name);


        /*holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });*/
        final String owner = values.get(position).getOwner().getLogin();
        holder.txtOwner.setText("Owner: " + owner);

        final Integer size = values.get(position).getSize();
        holder.txtSizeLine.setText("Size: " + size);


        final Boolean has_wiki = values.get(position).getHas_wiki();
        Log.e("has wiki", has_wiki.toString());


        if (values.get(position).getHas_wiki() == true){
            holder.itemView.setBackgroundColor(Color.parseColor("#4169E1"));
        }

    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

            if (values == null){
                return 0;
            }
            else{
                return values.size();
            }

    }

}
