package com.Pedrum.StoryFinderFinale.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.Pedrum.StoryFinderFinale.R;
import com.Pedrum.StoryFinderFinale.models.Event;

public class StoryDetailActivity extends AppCompatActivity {

    private Event story;

    TextView name, description;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        story = (Event) getIntent().getSerializableExtra("story");
        name = (TextView) findViewById(R.id.name);
        description = (TextView) findViewById(R.id.description);
        image = (ImageView) findViewById(R.id.image);

        if (story != null) {
            name.setText(story.getName());
            description.setText(Html.fromHtml(story.getDescription()));
/*            InputStream bitmap = null;
            try {
                bitmap = getAssets().open(story.getImage());
                Bitmap bit = BitmapFactory.decodeStream(bitmap);
                image.setImageBitmap(bit);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }
}
