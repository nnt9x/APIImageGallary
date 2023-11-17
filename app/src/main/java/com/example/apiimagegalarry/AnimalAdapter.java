package com.example.apiimagegalarry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AnimalAdapter extends BaseAdapter {

    private Context context;
    private List<String> urlAnimals;

    private LayoutInflater layoutInflater;

    public AnimalAdapter(Context context, List<String> urlAnimals) {
        this.context = context;
        this.urlAnimals = urlAnimals;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return urlAnimals.size();
    }

    @Override
    public Object getItem(int position) {
        return urlAnimals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.item_view, parent, false);
        // Bind id
        ImageView imageView = v.findViewById(R.id.imgAnimal);
        // Du lieu
        String animal = urlAnimals.get(position);
        // Do du lieu -> imageview -> hien thi
        Glide.with(v).load(animal).into(imageView);

        return v;
    }
}
