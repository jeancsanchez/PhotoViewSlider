package br.com.jeancsanchez.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.jeancsanchez.photoviewslider.Photo;
import br.com.jeancsanchez.photoviewslider.PhotosViewSlider;

public class MainActivity extends AppCompatActivity {

    private PhotosViewSlider photoViewSlider;
    private List<Photo> photoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photoViewSlider = (PhotosViewSlider) findViewById(R.id.photosViewSlider);
        photoList = new ArrayList<>();

        generatePhotos();
        photoViewSlider.initializePhotos(photoList, 3);
    }

    public void generatePhotos() {
        Photo photo1 = new Photo();
        photo1.setId(1);
        photo1.setImage("http://modmyi.com/attachments/forums/iphone-4-4s-new-skins-themes-launches/555329d1322802429-ice-cream-sandwich-android-4-0-a-android_ice_cream_sandwich_electronic_bytes.png");
        photo1.setDescription("Android  Ice Cream Sandwich");

        Photo photo2 = new Photo();
        photo2.setId(2);
        photo2.setImage("http://cdn.gigjets.com/wp-content/uploads/2012/10/Android-Jelly-Bean-Logo-Sort-Of.jpg");
        photo2.setDescription("Android Jelly Bean");

        Photo photo3 = new Photo();
        photo3.setId(3);
        photo3.setImage("http://cdn.ndtv.com/tech/images/gadgets/android-4.4-teaser-big.jpg");
        photo3.setDescription("Android Kitkat");

        Photo photo4 = new Photo();
        photo4.setId(4);
        photo4.setImage("http://thenextweb.com/wp-content/blogs.dir/1/files/2015/08/android-marshmallow-mascot.jpg");
        photo4.setDescription("Android Marshmallow");

        Photo photo5 = new Photo();
        photo5.setId(5);
        photo5.setImage("http://static.trustedreviews.com/94/000039bf9/f93f_orh370w630/n.jpg");
        photo5.setDescription("Android Nougat");

        photoList.add(photo1);
        photoList.add(photo2);
        photoList.add(photo3);
        photoList.add(photo4);
        photoList.add(photo5);
    }
}
