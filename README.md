# PhotoViewSlider
A simple slider view of images for Android applications.

[ ![jCenter](https://api.bintray.com/packages/drummer-aidan/maven/easy-video-player/images/download.svg) ](https://bintray.com/jeancsanchez/maven/PhotoViewSlider)



##Gradle Dependency
```Gradle
dependencies {
    // ... other dependencies
    compile 'io.github.jeancsanchez.photoviewslider:photoviewslider:1.0.0'
}
```


##Getting Started
You only need  a PhotosViewSlider on your Layout Activity.
```xml
<br.com.jeancsanchez.photoviewslider.PhotosViewSlider
        android:id="@+id/photosViewSlider"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

##Code Setup
Create a list of Photo objects and set the values to it.
```Java
photoViewSlider = (PhotosViewSlider) findViewById(R.id.photosViewSlider);
photoList = new ArrayList<>();

Photo photo1 = new Photo();
photo1.setId(1);
photo1.setImage("http://modmyi.com/attachments/forums/iphone-4-4s-new-skins-themes-launches/555329d1322802429-ice-cream-sandwich-android-4-0-a-android_ice_cream_sandwich_electronic_bytes.png");
photo1.setDescription("Android  Ice Cream Sandwich");

Photo photo2 = new Photo();
photo2.setId(2);
photo2.setImage("http://cdn.gigjets.com/wp-content/uploads/2012/10/Android-Jelly-Bean-Logo-Sort-Of.jpg");
photo2.setDescription("Android Jelly Bean");

photoList.add(photo1);
photoList.add(photo2);
```

###Init the photos
```java
photoViewSlider.initializePhotos(photoList, 3);
```

###Change transition animation anytime
```java
photoViewSlider.setTechniqueAnimation(Techniques.BounceIn);
```

##Thanks
* [Picasso](https://github.com/square/picasso)
* [Android View Animations] (https://github.com/daimajia/AndroidViewAnimations)
