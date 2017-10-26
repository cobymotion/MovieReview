package edu.tequila.tecmm.exampleitny;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Copyright 2017 TecMM Tequila
 * Created by luiscobian on 10/23/17.
 * Edit by ---- on 10/23/17
 */

public class AdapterRecyclerSimple extends RecyclerView.Adapter<AdapterRecyclerSimple.ViewHolderRecycler>
implements ItemClickListener{

    private final Context context;
    private List<DataMovie> items;

    public AdapterRecyclerSimple(Context context, List<DataMovie> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolderRecycler onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.recycler_item_movie,parent,false);
        return new ViewHolderRecycler(v,this);
    }

    @Override
    public void onBindViewHolder(ViewHolderRecycler holder, int position) {
        DataMovie current = items.get(position);
        holder.name.setText(current.getName());
        if(current.checkImage())
            holder.avatar.setImageDrawable(ContextCompat.getDrawable
                (context,current.getIdDrawable()));
        else
            Picasso.with(context).load(RetroInterfaceWs.url + current.getPathImg())
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onItemClick(View view, int position) {
        DescriptionMovie.createInstance((Activity) context, items.get(position));
    }


    // Inner Class
    public static class ViewHolderRecycler extends RecyclerView.ViewHolder
    implements View.OnClickListener {

        @BindView(R.id.movieName)
        public TextView name;
        @BindView(R.id.thumbnail)
        public ImageView avatar;

        public ItemClickListener listener;




        public ViewHolderRecycler(View v, ItemClickListener listener)
        {
            super(v);
            ButterKnife.bind(this,v);
            this.listener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(view, getAdapterPosition());
        }
    }

}
