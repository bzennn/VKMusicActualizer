package xyz.bzennn.vkmusicactualizer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<AudioFile> playlist;

    public PlaylistAdapter(Context context, List<AudioFile> playlist) {
        this.inflater = LayoutInflater.from(context);
        this.playlist = playlist;
    }

    @NonNull
    @Override
    public PlaylistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.audio_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.ViewHolder viewHolder, int position) {
        AudioFile audioFile = playlist.get(position);

        viewHolder.nameView.setText(audioFile.getName());
        viewHolder.authorView.setText(audioFile.getAuthor());
        viewHolder.lengthView.setText(audioFile.getLength());
    }

    @Override
    public int getItemCount() {
        return playlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        final TextView authorView;
        final TextView lengthView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameView = (TextView) itemView.findViewById(R.id.audioName);
            authorView = (TextView) itemView.findViewById(R.id.audioAuthor);
            lengthView = (TextView) itemView.findViewById(R.id.audioLength);
        }
    }
}
