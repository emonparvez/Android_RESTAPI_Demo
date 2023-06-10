package com.tanvir.tanvirmahmudkhan.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tanvir.tanvirmahmudkhan.model.Flower;

import java.util.List;

public class FlowerAdapter extends ArrayAdapter<Flower> {

    Context context;
    List<Flower> flowers;

    public FlowerAdapter(Context context, int resource, List<Flower> flowers){
        super(context,resource,flowers);
        this.context = context;
        this.flowers = flowers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.flower_layout,parent,false);

        Flower flower = flowers.get(position);
        TextView textView = (TextView) view.findViewById(R.id.flowerName);

        ImageView imageView = view.findViewById(R.id.flowerImage);

        if(flower!=null){
            imageView.setImageBitmap(flower.getBitmap());
            textView.setText(flower.getName());
        }


        return view;


    }
}



