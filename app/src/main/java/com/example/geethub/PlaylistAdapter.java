package com.example.geethub;


import static android.content.Context.ACCESSIBILITY_SERVICE;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    private final String[] localDataSet;
    TextView playlist_name;
    ImageView playlistBanner;
    public static final String EXTRA_CLICKED_PLAYLIST="package com.example.geethub.EXTRA.PLAYLIST";
    public static String val="null";
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);

            // Define click listener for the ViewHolder's View
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView pn = v.findViewById(R.id.playlist_name);
                    val= pn.getText().toString();
//                    Log.d("this",val);
//
////                    try {
                        Intent intent=new Intent(v.getContext(),SongsPage.class);
                        intent.putExtra(EXTRA_CLICKED_PLAYLIST,val);
//                    Toast.makeText(v.getContext(), (CharSequence) intent, Toast.LENGTH_SHORT).show();
//                    Log.d("this",EXTRA_CLICKED_PLAYLIST);
                        v.getContext().startActivity(intent);
//                    } catch (IllegalAccessException e) {
//                        throw new RuntimeException(e);
//                    } catch (InstantiationException e) {
//                        throw new RuntimeException(e);
//                    }
                }
            });

        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public PlaylistAdapter(String[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.playlists, viewGroup, false);
        playlist_name= view.findViewById(R.id.playlist_name);
        playlistBanner= view.findViewById(R.id.playlistBanner);

        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        playlist_name.setText(localDataSet[position]);
        try {
            String url= "https://abhishek-abhinav.github.io/GeetHub/Songs/"+ localDataSet[position].replaceAll(" ","%20")+"/cover.png";
            Glide.with(playlistBanner).load(url).into(playlistBanner);
//            playlistBanner.setImageBitmap(bmp);  //filmyhindigeet
//            Log.d("this", String.valueOf(url));
        } catch (Exception e) {
            playlistBanner.setImageResource(R.drawable.logo);
            throw new RuntimeException(e);
        }


    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
