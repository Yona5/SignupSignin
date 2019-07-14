package com.example.signupsignin;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.signupsignin.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    private Context context;
    private List<User> users = new ArrayList<>();

    public UserAdapter(@NonNull Context context, ArrayList<User> users){
        super(context, 0 ,users);
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @Nullable ViewGroup parent){

        View listItem = view;
        if (listItem == null)
            listItem = LayoutInflater.from(this.context).inflate(R.layout.list_layout, parent, false);

        User currentUser = users.get(position);

        TextView userName = listItem.findViewById(R.id.userName);
        userName.setText(currentUser.getFname());

        return listItem;

    }
}
