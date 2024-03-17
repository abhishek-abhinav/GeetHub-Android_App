package com.example.geethub;

import static com.example.geethub.MusicController.mediaPlayer;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class GeetAdapter extends RecyclerView.Adapter<GeetAdapter.ViewHolder> {
    public static String[] localDataSet;
    public int plSize;
    private final String currPlay;
    TextView geet_name;

    public static String EXTRA_CLICKED_GEETLIST="package com.example.geethub.EXTRA.GEETLIST";
    public static final String EXTRA_CURRENT_PLAYLIST="package com.example.geethub.EXTRA.CURRENT";


    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */

    public GeetAdapter(String[] dataSet, String playlist) {
        localDataSet = dataSet;
        currPlay =playlist;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {

            super(view);

            // Define click listener for the ViewHolder's View
            view.setOnClickListener(v -> {

                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                TextView gn = v.findViewById(R.id.geet_name);
                String val = gn.getText().toString().replaceAll(" ","%20").concat(".mp3");

                Intent intent = new Intent(v.getContext(), MusicController.class);
                intent.putExtra(EXTRA_CLICKED_GEETLIST,val);
                intent.putExtra(EXTRA_CURRENT_PLAYLIST,currPlay);
                v.getContext().startActivity(intent);
            });

        }
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.geetlist, viewGroup, false);
        geet_name= view.findViewById(R.id.geet_name);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        geet_name.setText(localDataSet[position].split(".mp3")[0]);
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
