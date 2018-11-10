package com.example.consultants.week3day3.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.consultants.week3day3.R;
import com.example.consultants.week3day3.model.Person;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<Person> personList;

    public MyAdapter(List<Person> personList)
    {
        this.personList = personList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivImage;
        public TextView tvPersonName;
        public TextView tvPersonAge;
        public TextView tvPersonGender;

        public ViewHolder(@NonNull View v) {
            super(v);
            ivImage = v.findViewById(R.id.ivImage);
            tvPersonName = v.findViewById(R.id.tvPersonName);
            tvPersonAge = v.findViewById(R.id.tvPersonAge);
            tvPersonGender = v.findViewById(R.id.tvPersonGender);
        }
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int i) {

        Person person = personList.get(i);

        viewHolder.ivImage.setImageResource(person.getImage());
        viewHolder.tvPersonName.setText((person.getName()));
        viewHolder.tvPersonAge.setText(person.getAge());
        viewHolder.tvPersonGender.setText(person.getGender());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

}
