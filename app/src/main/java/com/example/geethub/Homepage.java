package com.example.geethub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Homepage extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] album= {"Filmy Hindi Geet",
            "Hanuman Gatha By Kumar Vishu",
            "Odia Movie Songs",
            "Ringtones",
            "Shree Ram Bhajan"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        Intent intent = getIntent();

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PlaylistAdapter ad = new PlaylistAdapter(album);
        recyclerView.setAdapter(ad);

//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                return true;
//            }
//
//            @Override
//            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                Intent intent = new Intent(Homepage.this, SongsPage.class);
//                Log.d("this", PlaylistAdapter.val);
//                intent.putExtra(PlaylistAdapter.EXTRA_CLICKED_PLAYLIST,PlaylistAdapter.val);
//                Toast.makeText(Homepage.this, PlaylistAdapter.val, Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
    }
}