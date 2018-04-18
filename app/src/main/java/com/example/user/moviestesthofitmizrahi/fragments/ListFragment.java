package com.example.user.moviestesthofitmizrahi.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.moviestesthofitmizrahi.R;

public class ListFragment extends Fragment {


    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //setting txt adapter
        RecyclerAdapter Adapter = new RecyclerAdapter(getActivity());
        recyclerView.setAdapter(Adapter);

        return v;

    }
}
