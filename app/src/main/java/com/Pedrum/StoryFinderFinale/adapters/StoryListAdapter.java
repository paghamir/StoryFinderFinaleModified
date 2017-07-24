package com.Pedrum.StoryFinderFinale.adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Pedrum.StoryFinderFinale.R;
import com.Pedrum.StoryFinderFinale.activities.StoryDetailActivity;
import com.Pedrum.StoryFinderFinale.models.Story;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Pedrum Aghamir on 7/1/2017.
 */

public class StoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private AppCompatActivity activity;
    private ArrayList<Story> stories = new ArrayList<>();

    public StoryListAdapter(AppCompatActivity activity) {
        this.activity = activity;
    }

    class ViewHolder extends RecyclerView.ViewHolder { // a class that holds view items

        TextView name;
        TextView description;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //returning views
        View view = LayoutInflater.from(activity).inflate(R.layout.book_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //setting data to views

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.name.setText(stories.get(position).getName());
        viewHolder.description.setText(stories.get(position).getDescription());

        InputStream bitmap = null;
        try {
            bitmap = activity.getAssets().open(stories.get(position).getImage());
            Bitmap bit = BitmapFactory.decodeStream(bitmap);
            viewHolder.image.setImageBitmap(bit);
        } catch (IOException e) {
            e.printStackTrace();
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // onclick -> should open details
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, StoryDetailActivity.class);
                intent.putExtra("story", stories.get(position));
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {  // returns number of items
        return stories.size();
    }

    public void update(String q) { // method to update data
        stories.clear(); // clearing previous data
//        stories.addAll(BookShelf.getInstance().getStories(q)); // getting new list based on search
        notifyDataSetChanged(); // tell adapter that data is changed
    }
}
