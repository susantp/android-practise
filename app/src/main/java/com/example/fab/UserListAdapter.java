package com.example.fab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListAdapter extends ArrayAdapter<UserInfo> {
    Context context;

    public UserListAdapter(Context context, ArrayList<UserInfo> list) {
        super(context, 0, list);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position%2 == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
            TextView name = view.findViewById(R.id.name);
            TextView address = view.findViewById(R.id.address);
            ImageView imageView = view.findViewById(R.id.image);
            final UserInfo info = getItem(position);
            name.setText(info.getUsername());
            address.setText(info.getAddress());
            if (info.getImage() != null)
                imageView.setImageBitmap(GeneralUtil.getBitmap(info.getImage()));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UserDetailActivity.class);
                    intent.putExtra("id", info.getId());
                    context.startActivity(intent);
                }
            });
            view.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("id", Integer.parseInt(info.getId()));
                    context.startActivity(intent);
                }
            });
            return view;
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.activity_user_list, null);
            TextView name = view.findViewById(R.id.view);
            TextView address = view.findViewById(R.id.view1);
            ImageView imageView = view.findViewById(R.id.image);
            ImageView imageView1 = view.findViewById(R.id.image1);
            final UserInfo info = getItem(position);
            name.setText(info.getUsername());
            address.setText(info.getAddress());
            if (info.getImage() != null)
                imageView.setImageBitmap(GeneralUtil.getBitmap(info.getImage()));
                imageView1.setImageBitmap(GeneralUtil.getBitmap(info.getImage()));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, UserDetailActivity.class);
                        intent.putExtra("id", info.getId());
                        context.startActivity(intent);
                    }
                });
                view.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("id", Integer.parseInt(info.getId()));
                        context.startActivity(intent);
                    }
                });
            return view;
        }
    }
}
