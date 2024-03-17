package com.example.geethub;

import static com.example.geethub.GeetAdapter.EXTRA_CLICKED_GEETLIST;
import static com.example.geethub.GeetAdapter.EXTRA_CURRENT_PLAYLIST;
import static com.example.geethub.GeetAdapter.localDataSet;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MusicController extends AppCompatActivity {
    TextView playlist;
    TextView currGeet;
    TextView startDuration;
    TextView endDuration;
    ImageButton play;
    ImageButton previousBtn;
    ImageButton nextBtn;
    public static MediaPlayer mediaPlayer;
    SeekBar seekBar;

    String[] playlistStr= localDataSet;
    String nextGeet;
    String prevGeet;
    Thread updateSeek;

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_control);
        Intent intent = getIntent();
        final String geet = intent.getStringExtra(EXTRA_CLICKED_GEETLIST);
        String pl = intent.getStringExtra(EXTRA_CURRENT_PLAYLIST);

        playlist=findViewById(R.id.playlist);
        currGeet=findViewById(R.id.currGeet);
        startDuration=findViewById(R.id.startDuration);
        endDuration=findViewById(R.id.endDuration);
        play=findViewById(R.id.play);
        previousBtn=findViewById(R.id.previousBtn);
        nextBtn=findViewById(R.id.nextBtn);
        seekBar=findViewById(R.id.seekBar);

        playlist.setText(pl.replaceAll("%20"," "));
        currGeet.setText(geet.split(".mp3")[0].replaceAll("%20"," "));

        startMusic(pl, geet);

        int index = 0;

        for (int i = 0; i < playlistStr.length; i++) {
            if (playlistStr[i].equals(geet.replaceAll("%20"," "))) {
                index = i;
                break;
            }
        }

        final int[] finalIndex = {index};
        final int[] finalInd = {index};

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                int start = mediaPlayer.getCurrentPosition();
//                    Toast.makeText(MusicController.this, String.valueOf(start), Toast.LENGTH_SHORT).show();
                String st = timeFormat(start);
                startDuration.setText(st);

                if (mediaPlayer.getCurrentPosition() == mediaPlayer.getDuration()) {
                    play.setImageResource(R.drawable.play);

                    nextGeet = updateNextGeet(finalInd[0])[0];
                    startMusic(pl, nextGeet);
                    play.setImageResource(R.drawable.pause);
                    currGeet.setText(nextGeet.replaceAll("%20", " ").split(".mp3")[0]);
                    finalInd[0] = Integer.parseInt(updateNextGeet(finalInd[0])[1]);
                }

                if (!mediaPlayer.isPlaying()) play.setImageResource(R.drawable.play);
                // Schedule the next execution after 1000 milliseconds
                new Handler(Looper.getMainLooper()).postDelayed(this, 1000);
            }
        },1000);

//        final Handler handler=new Handler(Looper.getMainLooper());
//        handler.postDelayed(()->{
//            if (mediaPlayer.getCurrentPosition() == mediaPlayer.getDuration()) {
//                play.setImageResource(R.drawable.play);
//                int index = 0;
//                for (int i = 0; i < playlistStr.length; i++) {
//                    if (playlistStr[i].equals(geet.replaceAll("%20", " "))) {
//                        index = i;
//                        break;
//                    }
//                }
//                final int[] finalIndex = {index};
//                nextGeet = updateNextGeet(finalIndex[0])[0];
//                startMusic(pl, nextGeet);
//                play.setImageResource(R.drawable.pause);
//                currGeet.setText(nextGeet.replaceAll("%20", " ").split(".mp3")[0]);
//                finalIndex[0] = Integer.parseInt(updateNextGeet(finalIndex[0])[1]);
//            }
//        },1000);



        play.setOnClickListener(v -> {
            if (play.getTag() == null || play.getTag().equals("play")) {
                mediaPlayer.pause();
                play.setImageResource(R.drawable.play);
                play.setTag("pause");
            } else {
                mediaPlayer.start();
                play.setImageResource(R.drawable.pause);
                play.setTag("play");
            }
        });



        nextBtn.setOnClickListener(v -> {
//            Toast.makeText(this, String.valueOf(finalIndex[0]), Toast.LENGTH_SHORT).show();
            nextGeet= updateNextGeet(finalIndex[0])[0];
            startMusic(pl, nextGeet);
            currGeet.setText(nextGeet.replaceAll("%20"," ").split(".mp3")[0]);
            finalIndex[0]= Integer.parseInt(updateNextGeet(finalIndex[0])[1]);
        });

        previousBtn.setOnClickListener(v -> {
            prevGeet= updatePrevGeet(finalIndex[0])[0];
            startMusic(pl, prevGeet);
            currGeet.setText(prevGeet.replaceAll("%20"," ").split(".mp3")[0]);
            finalIndex[0]=Integer.parseInt(updatePrevGeet(finalIndex[0])[1]);
        });


    }

    protected void startMusic(String pl, String geet){
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer= new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://abhishek-abhinav.github.io/GeetHub/Songs/"+pl+"/"+geet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mediaPlayer.setOnPreparedListener(mp -> {
            mp.start();
            play.setImageResource(R.drawable.pause);

            int end=mp.getDuration();
            String ed= timeFormat(end);
            endDuration.setText(ed);

            updateSeek = new Thread(){
                @Override
                public void run() {
                    int currDur = 0;
                    try {
                        while (currDur < mediaPlayer.getDuration() && !nextBtn.isPressed() && !previousBtn.isPressed()) {
                            currDur = mediaPlayer.getCurrentPosition();
                            seekBar.setProgress(currDur);
                            //noinspection BusyWait
                            sleep(10);
                        }

                    }
                    catch(InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            };
            updateSeek.start();
            seekBar.setMax(mp.getDuration());
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser){
                        mp.seekTo(progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        });
        mediaPlayer.prepareAsync();
        mediaPlayer.start();
    }


    String[] updateNextGeet(int index) {
        if (index < playlistStr.length - 1) {
            nextGeet = playlistStr[index + 1].replaceAll(" ", "%20");
            index++;
        } else {
            nextGeet = playlistStr[0].replaceAll(" ", "%20");
            index=0;
        }
        return new String[]{nextGeet, String.valueOf(index)};
    }

    String[] updatePrevGeet(int index) {
        if (index > 0) {
            prevGeet = playlistStr[index - 1].replaceAll(" ", "%20");
            index--;
        } else {
            prevGeet = playlistStr[playlistStr.length-1].replaceAll(" ", "%20");
            index=playlistStr.length-1;
        }
        return new String[]{prevGeet, String.valueOf(index)};
    }

    String timeFormat(int dur){
        int time= dur/1000;
        String min= String.valueOf((time/60));
        String sec= String.valueOf((time%60));
        if(Integer.parseInt(min)<10){
            min="0"+min;
        }
        if( Integer.parseInt(sec)<10){
            sec="0"+sec;
        }
        return min+":"+sec;
    }
}
