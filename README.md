# PhotoViewSlider
![Version](https://img.shields.io/badge/version-1.2.0-green.svg)
[![Version](https://img.shields.io/badge/Bintray-jeancsanchez-blue.svg)](https://bintray.com/jeancsanchez/maven/PhotoViewSlider)
<br/>
A simple photo browser for Android applications.

![alt tag]
(https://raw.githubusercontent.com/jeancsanchez/PhotoViewSlider/master/PhotoViewSlider.gif)


##Gradle Dependency
```Gradle
dependencies {
    // ... other dependencies
    compile 'io.github.jeancsanchez.photoviewslider:photoviewslider:1.2.0'
}
```

##Maven
```xml
<dependency>
  <groupId>io.github.jeancsanchez.photoviewslider</groupId>
  <artifactId>photoviewslider</artifactId>
  <version>1.2.0</version>
  <type>pom</type>
</dependency>
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
###Setup the view on your Activity
```Java
photoViewSlider = (PhotosViewSlider) findViewById(R.id.photosViewSlider);
```

###Option 1: Set the urls on demand and initialize the photo view.
```Java
photoViewSlider.setPhotoUrl("http://awesomeimg.com.br", "any description");
photoViewSlider.setPhotoUrl("http://awesomeimg.com.br");
photoViewSlider.initializePhotos();
```

###Option 2: Create a list of String urls, so initialize the photo view.
```Java
stringsList = new ArrayList<>();

stringsList.add("http://awesomeimg.com.br");
stringsList.add("http://awesomeimg.com.br");
photoViewSlider.initializePhotosUrls(stringsList);
```

###Option 3: Create a list of Photo objects and set the values to it, so initialize the photo view .
```Java
photoList = new ArrayList<>();

Photo photo1 = new Photo();
photo1.setImage("http://modmyi.com/attachments/forums/iphone-4-4s-new-skins-themes-launches/555329d1322802429-ice-cream-sandwich-android-4-0-a-android_ice_cream_sandwich_electronic_bytes.png");
photo1.setDescription("Android  Ice Cream Sandwich");

Photo photo2 = new Photo();
photo2.setImage("http://cdn.gigjets.com/wp-content/uploads/2012/10/Android-Jelly-Bean-Logo-Sort-Of.jpg");
photo2.setDescription("Android Jelly Bean");

photoList.add(photo1);
photoList.add(photo2);

photoViewSlider.initializePhotos(photosList);
```

###Change transition animation (Optional)
```java
photoViewSlider.setTechniqueAnimation(Techniques.BounceIn);
```


##TO DO LIST
- [ ] Make the recycler view "responsive" 

##Thanks
* [Picasso](https://github.com/square/picasso)
* [Android View Animations] (https://github.com/daimajia/AndroidViewAnimations)
