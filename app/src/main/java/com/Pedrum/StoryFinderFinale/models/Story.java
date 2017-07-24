package com.Pedrum.StoryFinderFinale.models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created  by Pedrum Aghamir on 7/1/2017.
 */

public class Story implements Serializable {
    private UUID mUuid;
    private String mName;
    private String mDescription;
    private String  image;

    public Story(UUID mUuid, String mName, String mDescription, String image) {
        this.mUuid = mUuid;
        this.mName = mName;
        this.mDescription = mDescription;
        this.image = image;
    }

    public UUID getUuid() {
        return mUuid;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getImage() {
        return image;
    }
}
