package br.com.jeancsanchez.photoviewslider;

/**
 * Created by jean on 24/08/16.
 */

public class PhotoListIsEmptyException extends NullPointerException{
    public PhotoListIsEmptyException(){
        super("Impossible init the photos without some photo added!");
    }
}
