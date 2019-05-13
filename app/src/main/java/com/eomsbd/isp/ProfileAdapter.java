package com.eomsbd.isp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eomsbd.isp.model.User;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter  extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

Context context;

ArrayList<User>usersList;
    ArrayList<String> usersList1;


    public ProfileAdapter(Context context, ArrayList<String> usersList) {
        this.context = context;
        this.usersList1=usersList1;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from( context);
        View view = inflater.inflate(R.layout.profile_item, null);
        ProfileViewHolder profileViewHolder=new ProfileViewHolder( view );
        return profileViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder profileViewHolder, int i) {


        profileViewHolder.user.setText(usersList.get(i).getUsername());
        profileViewHolder.first.setText(usersList.get(i).getFirstname());
        profileViewHolder.last.setText(usersList.get(i).getLastname());
        profileViewHolder.email.setText(usersList.get(i).getEmail());
        profileViewHolder.phone.setText(usersList.get(i).getMobile());
        profileViewHolder.address.setText(usersList.get(i).getAddress());
        profileViewHolder.national.setText(usersList.get(i).getNationalid());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        TextView user,first,last,email,phone,address,national;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);

            user=itemView.findViewById(R.id.userName);
            first=itemView.findViewById(R.id.firstName);
            last=itemView.findViewById(R.id.lastName);
            email=itemView.findViewById(R.id.email);
            phone=itemView.findViewById(R.id.mobile);
            address=itemView.findViewById(R.id.address);
            national=itemView.findViewById(R.id.nationalId);
        }
    }
}
