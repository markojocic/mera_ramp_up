package com.example.markojocic.mera.contactView;

import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.markojocic.mera.R;

import java.util.ArrayList;

public class AdapterContact extends  RecyclerView.Adapter<AdapterContact.ViewHolder> {

    private ArrayList<String> contactname;
    private ArrayList<String> contactphone;
    private ArrayList<String> contactphoto;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtName;
        public TextView txtPhone;
        public ImageView imgPhoto;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;

            txtName =  v.findViewById(R.id.contactName);
            txtPhone =  v.findViewById(R.id.contactPhone);
            imgPhoto = v.findViewById(R.id.contactPhoto);
        }
    }




    public AdapterContact(ArrayList<String> contactName, ArrayList<String> contactPhone, ArrayList<String> contactPhoto, Context context){

      contactname = contactName;
      contactphone = contactPhone;
      contactphoto = contactPhoto;

      this.context = context;
    }



    @Override
    public AdapterContact.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        Log.e("ON CREATE VIEW HOLDER","----------->\n");
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.contact_layout_items, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        final String name = contactname.get(position);
        holder.txtName.setText(name);

        final  String phone = contactphone.get(position);
        holder.txtPhone.setText(phone);

       final String photo = contactphoto.get(position);


        if (photo != null){
            holder.imgPhoto.setImageURI(Uri.parse(photo));

        }


        holder.txtPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //remove(position);
                Log.e("MSG", "" + phone);

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phone));

                context.startActivity(intent);


            }
        });

    }


    @Override
    public int getItemCount() {

        if (contactphone == null){
            return 0;
        }
        else{
            return contactphone.size();
        }

    }

}
