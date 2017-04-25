package br.com.jeancsanchez.photoviewslider;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhotosViewSlider extends LinearLayout implements View.OnClickListener, View.OnTouchListener {
    private TextView txtPhotoGalleryTitle;
    private TextView txtPhotoGalleryDescription;
    private PhotosViewAdapter photoAdapter;
    private RecyclerView recyclerView;
    private int currentPosition = 0;
    private float dx, dy, ux, uy;
    private final int MIN_DISTANCE = 100;
    private Dialog builder;
    private View viewDialog;
    private ImageButton btnShare;
    private TextView txtPhotosTotal;
    private TextView txtCurrentPosition;
    private TextView txtDescriptionGallery;
    private ImageView imgPhoto;
    private List<Photo> photos;
    private Techniques techniqueAnimation = Techniques.FadeIn;
    private int gridColumns = 3;


    public PhotosViewSlider(Context context){
        super(context);
        init();
    }

    public PhotosViewSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PhotosViewSlider(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void init(){
        inflate(getContext(), R.layout.photos_view, this);
        recyclerView = (RecyclerView) findViewById(R.id.photosList);
        photoAdapter = new PhotosViewAdapter(getContext());
    }


    public void initializePhotos(){
        try {
            if (photos == null)
                throw new PhotoListIsEmptyException();

        }catch (PhotoListIsEmptyException e){
            e.printStackTrace();
        }

        adapterSetup();
    }


    /**
     *
     * @param photos List of photos that will to be show on grid.
     */
    public void initializePhotos(List<Photo> photos) {
        if(this.photos == null)
            this.photos = photos;
        else
            this.photos.addAll(photos);

        adapterSetup();
    }


    /**
     *
     * @param photos List of photos that will to be show on grid.
     * @param gridColumns Number of columns that photos grid will have.
     */
    public void initializePhotos(List<Photo> photos, int gridColumns) {
        if(this.photos == null)
            this.photos = photos;
        else
            this.photos.addAll(photos);
        this.gridColumns = gridColumns;

        adapterSetup();
    }


    /**
     *
     * @param photos List of photos that will to be show on grid.
     * @param techniqueAnimation Type/Technique of animation on photos detail
     * @param gridColumns Number of columns that photos grid will have.
     */
    public void initializePhotos(List<Photo> photos, Techniques techniqueAnimation, int gridColumns) {
        if(this.photos == null)
            this.photos = photos;
        else
            this.photos.addAll(photos);

        this.techniqueAnimation = techniqueAnimation;
        this.gridColumns = gridColumns;
        adapterSetup();
    }


    /**
     *
     * @param photosUrls List of urls's photos that will to be show on grid.
     */
    public void initializePhotosUrls(ArrayList<String> photosUrls){
        if(photos == null)
            photos = new ArrayList<>();

        for(String photoUrl : photosUrls)
            photos.add(new Photo(photoUrl));

        adapterSetup();
    }

    /**
     *
     * @param photosFiles List of File of photos that will to be show on grid.
     */
    public void initializePhotosFiles(ArrayList<File> photosFiles){
        if(photos == null)
            photos = new ArrayList<>();

        for(File photoFile : photosFiles)
            photos.add(new Photo(photoFile));

        adapterSetup();
    }




    /**
     * Set image url to the list of photos
     * @param imageUrl set image url
     */
    public void setPhotoUrl(String imageUrl){
        if(photos == null)
            photos = new ArrayList<>();

        photos.add(new Photo(imageUrl));
    }

    /**
     * Add image File to the list of photos
     * @param imageFile the File Object representing the image
     */
    public void setPhotoFile(File imageFile) {
        if(photos == null)
            photos = new ArrayList<>();

        photos.add(new Photo(imageFile));
    }

    public void setPhotoFile(File imageFile, String description) {
        if(photos == null)
            photos = new ArrayList<>();

        photos.add(new Photo(imageFile, description));
    }

    /**
     * Set image url to the list of photos
     * @param imageUrl set image url
     * @param description set description to image
     */
    public void setPhotoUrl(String imageUrl, String description){
        if(photos == null)
            photos = new ArrayList<>();

        photos.add(new Photo(imageUrl, description));
    }


    /**
     * Set an effect transition on photos details
     * @param techniqueAnimation set image url
     */
    public void setTechniqueAnimation(Techniques techniqueAnimation){
        this.techniqueAnimation = techniqueAnimation;
    }


    private void adapterSetup() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(gridColumns, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(photoAdapter);
        photoAdapter.setupItems(photos);
    }


    private void generatePhotoDetail() {
        viewDialog = inflate(getContext(), R.layout.photo_detail, null);
        imgPhoto = (ImageView) viewDialog.findViewById(R.id.img_photo_gallery_detail);
        txtDescriptionGallery = (TextView) viewDialog.findViewById(R.id.txt_photo_gallery_description);
        txtCurrentPosition = (TextView) viewDialog.findViewById(R.id.txt_photo_current_position);
        txtPhotosTotal = (TextView) viewDialog.findViewById(R.id.txt_photo_total);
        btnShare = (ImageButton) viewDialog.findViewById(R.id.btn_share);

        builder = new Dialog(getContext());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    private void photoClicked(Photo photo) {
        generatePhotoDetail();

        for (int i = 0; i < photos.size(); i++)
            if (photos.get(i)  == photo)
                currentPosition = i;

        showImage(photo, currentPosition);
    }


    private void showImage(Photo photo, int currentPosition) {
        txtDescriptionGallery.setText(photo.getDescription());
        txtCurrentPosition.setText(String.valueOf(currentPosition+1));
        txtPhotosTotal.setText(String.valueOf(photos.size()));
        if(photo.getImageUrl() == null) {
            Picasso.with(getContext()).load(photo.getImageFile()).fit()
                    .placeholder(R.drawable.photodefault)
                    .into(imgPhoto);
        } else {
            Picasso.with(getContext()).load(photo.getImageUrl()).fit()
                    .placeholder(R.drawable.photodefault)
                    .into(imgPhoto);
        }
        btnShare.setOnClickListener(this);
        viewDialog.setOnTouchListener(this);

        builder.setContentView(viewDialog);
        builder.show();
        YoYo.with(techniqueAnimation)
                .duration(700)
                .playOn(imgPhoto);
    }


    private void preparePhotoForShare() {
        final Bitmap[] image = new Bitmap[1];

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    image[0] = Picasso.with(getContext())
                            .load(photos.get(currentPosition).getImageUrl())
                            .get();

                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    image[0].compress(Bitmap.CompressFormat.JPEG, 100, bytes);

                    File file = new File(Environment.getExternalStorageDirectory() + File.separator + "image_temp.jpg");
                    FileOutputStream fo = new FileOutputStream(file);
                    fo.write(bytes.toByteArray());
                    sendPhotoForShare();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void sendPhotoForShare() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(android.content.Intent.ACTION_SEND);
        shareIntent.setType("image/jpeg");

        shareIntent.putExtra(Intent.EXTRA_SUBJECT, photos.get(currentPosition).getDescription());
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory().toURI() + "image_temp.jpg"));
        getContext().startActivity(Intent.createChooser(shareIntent, "Share with..."));
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN){
                dx = event.getX();
                dy = event.getY();
                return true;

        }else if (action == MotionEvent.ACTION_UP) {
            ux = event.getX();
            uy = event.getY();

            float deltaX = dx - ux;
            float deltaY = dy - uy;

            if (Math.abs(deltaX) > Math.abs(deltaY))
                if (Math.abs(deltaX) > MIN_DISTANCE)
                        // Before
                    if (deltaX < 0) {
                        if(currentPosition == 0)
                            currentPosition = photos.size() -1;
                        else
                            currentPosition --;

                        Photo photo = photos.get(currentPosition);
                        showImage(photo, currentPosition);
                        return true;
                    }

                    // Next
                    if (deltaX > 0) {
                        if(currentPosition == photos.size()-1)
                            currentPosition = 0;
                        else
                            currentPosition ++;

                        Photo photo = photos.get(currentPosition);
                        showImage(photo, currentPosition);
                        return true;
                    }
            }
        return false;
    }


    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.btn_share)
            preparePhotoForShare();
    }


    private class PhotosViewAdapter extends RecyclerView.Adapter<PhotosViewAdapter.PhotosAdapterViewHolder> implements View.OnClickListener {
        private Context context;
        private List<Photo> photoList;

        public PhotosViewAdapter(Context context) {
            this.context = context;
        }


        @Override
        public PhotosAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(context).inflate(R.layout.photo_item, parent, false);
            PhotosAdapterViewHolder photosViewHolder = new PhotosAdapterViewHolder(view);
            photosViewHolder.itemView.setOnClickListener(this);
            return photosViewHolder;
        }

        @Override
        public void onBindViewHolder(final PhotosAdapterViewHolder holder, int position) {
            holder.itemView.setTag(photoList.get(position));
            if (photoList.get(position).getImageFile() != null) {
                Picasso.with(context).load(photoList.get(position).getImageFile())
                        .fit()
                        .placeholder(R.drawable.photodefault)
                        .into(holder.photo);
            } else {
                Picasso.with(context).load(photoList.get(position).getImageUrl())
                        .fit()
                        .placeholder(R.drawable.photodefault)
                        .into(holder.photo);
            }
        }

        @Override
        public int getItemCount() {
            return photoList == null ? 0 : photoList.size();
        }

        public void setupItems(List<Photo> photoList) {
            this.photoList = photoList;
            notifyDataSetChanged();
        }

        @Override
        public void onClick(View view) {
            Photo photo = (Photo) view.getTag();
            photoClicked(photo);
        }

        class PhotosAdapterViewHolder extends RecyclerView.ViewHolder{
            private ImageView photo;

            public PhotosAdapterViewHolder(View view){
                super(view);
                this.photo = (ImageView) view.findViewById(R.id.photo_item);
            }
        }
    }
}