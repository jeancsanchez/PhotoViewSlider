package br.com.jeancsanchez.photoviewslider;

import java.io.Serializable;

/**
 * Created by jean on 19/07/16.
 */

public class Photo implements Serializable {
    private int id;
    private String description;
    private String image;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
