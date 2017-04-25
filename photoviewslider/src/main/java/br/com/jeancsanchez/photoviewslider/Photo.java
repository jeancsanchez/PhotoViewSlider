package br.com.jeancsanchez.photoviewslider;

import java.io.File;
import java.io.Serializable;

/**
 * Created by jean on 19/07/16.
 */

public class Photo implements Serializable {
    private String description = "";
    private String imageUrl;
    private File imageFile;


    public Photo(){}

    public Photo(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public Photo(String imageUrl, String description){
        this.imageUrl = imageUrl;
        this.description = description;
        imageFile = null;
    }

    public Photo(File f, String description) {
        this.imageFile = f;
        this.description = description;
    }

    public Photo(File f) {
        this.imageFile = f;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
