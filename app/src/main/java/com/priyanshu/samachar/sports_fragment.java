package com.priyanshu.samachar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sports_fragment extends Fragment {
    String api ="1b72f4ef67b54ce1b25b4b51f08fdbd1";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country="in";
    private RecyclerView recyclerViewofsports;
    private String category="sports";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sports_fragments, null);

        recyclerViewofsports = v.findViewById(R.id.sports_recyclerview);
        modelClassArrayList = new ArrayList<>();
        recyclerViewofsports.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelClassArrayList);
        recyclerViewofsports.setAdapter(adapter);

        findNews();


        return v;
    }




    private void findNews() {
        ApiUtilities.getApiInterface().getCategoryNews(country,category, 25,api).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse( Call<MainNews> call, Response<MainNews> response) {
                if(response. isSuccessful())
                {


                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure( Call<MainNews> call, Throwable t) {

            }
        });
    }
}
