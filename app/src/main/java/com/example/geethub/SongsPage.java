package com.example.geethub;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class SongsPage extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] FilmyHindiGeet= {"Aur Tanha.mp3",
            "Banjaara.mp3",
            "Chaar Kadam.mp3",
            "Chahun Main Ya Naa.mp3",
            "Chal Ghar Chalen.mp3",
            "Dil Ibaadat.mp3",
            "Humdard.mp3",
            "Khuda Aur Mohabbat.mp3",
            "Main Agar Kahoon.mp3",
            "Malang Sad Version.mp3",
            "Mere HumSafar.mp3",
            "Meri Aashiqui.mp3",
            "Salamat.mp3",
            "Shayad.mp3",
            "Sun Raha Hai Na Tu.mp3",
            "Suno Chanda.mp3",
            "Tere Hawaale.mp3",
            "Tu Hi Haqeeqat.mp3",
            "Tujh Mein Rab Dikhta Hai.mp3",
            "Tum Se Hi.mp3"};

    String[] HanumanGathaByKumarVishu= {
            "Hanuman Ji Ka Janam (Vol-1).mp3",
            "Mata Janki Ke Darshan (Vol-2).mp3"};

    String[] OdiaMovieSongs= {
            "Aa Lagei De Daga.mp3",
            "Are Re Re.mp3",
            "Feel Karuchhi.mp3",
            "Gala Pare Dhire Dhire.mp3",
            "Hole Hole Re.mp3",
            "Sie Mo Prathama Prema.mp3",
            "Sun Saheba Sun.mp3",
            "Tori Pain To Pain.mp3",
            "Tu Dhire Dhire Chal Re Samaya.mp3"};

    String[] Ringtones= {"Ashutosh Sashank Shekhar.mp3",
            "Bajrangbali Aur Main.mp3",
            "Har Har Sambhu.mp3",
            "Mata Kahan Tujhse Chhupi.mp3",
            "Namami Samisaan Nirvan Roopam.mp3",
            "Shree Hari Stotram.mp3"};

    String[] ShreeRamBhajan= {"Agar Nath Dekhoge Avgun Humare.mp3",
            "Bade Bhagya Se Ye Manuj Tan Mila Hai.mp3",
            "Data Ek Ram.mp3",
            "Raghunandan Raghav Ram Hare.mp3",
            "Raghunandan.mp3",
            "Sab Mere Ram Jante Hain.mp3",
            "Shri Ram Jaanki Baithe Hain .mp3",
            "Teri Mand Mand Muskaniya Pe.mp3",
            "Yeh Garv Bhara Mastak Mera.mp3"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songspage);
        Intent intent = getIntent();
        String currPlaylist= intent.getStringExtra(PlaylistAdapter.EXTRA_CLICKED_PLAYLIST);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        switch (currPlaylist.replaceAll(" ", "")) {
            case "FilmyHindiGeet":
                GeetAdapter ad = new GeetAdapter(FilmyHindiGeet,"Filmy%20Hindi%20Geet");
                recyclerView.setAdapter(ad);
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                break;
            case "HanumanGathaByKumarVishu":
                GeetAdapter ad1 = new GeetAdapter(HanumanGathaByKumarVishu,"Hanuman%20Gatha%20By%20Kumar%20Vishu");
                recyclerView.setAdapter(ad1);
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                break;
            case "OdiaMovieSongs":
                GeetAdapter ad2 = new GeetAdapter(OdiaMovieSongs,"Odia%20Movie%20Songs");
                recyclerView.setAdapter(ad2);
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                break;
            case "Ringtones":
                GeetAdapter ad3 = new GeetAdapter(Ringtones,"Ringtones");
                recyclerView.setAdapter(ad3);
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                break;
            case "ShreeRamBhajan":
                GeetAdapter ad4 = new GeetAdapter(ShreeRamBhajan,"Shree%20Ram%20Bhajan");
                recyclerView.setAdapter(ad4);
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                break;
        }
    }
}
