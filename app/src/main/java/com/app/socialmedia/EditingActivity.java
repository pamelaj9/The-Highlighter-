package com.app.socialmedia;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.app.socialmedia.utils.Utils;
import com.github.siyamed.shapeimageview.BubbleImageView;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.github.siyamed.shapeimageview.DiamondImageView;
import com.github.siyamed.shapeimageview.HeartImageView;
import com.github.siyamed.shapeimageview.HexagonImageView;
import com.github.siyamed.shapeimageview.OctogonImageView;
import com.github.siyamed.shapeimageview.PentagonImageView;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.github.siyamed.shapeimageview.ShapeImageView;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;


import de.hdodenhof.circleimageview.CircleImageView;

public class EditingActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSION_REQUEST_CODE = 001;
    private String copiedText;
    private RelativeLayout rootLayout , actionBarLayout , backgroundLayout , shareLayout,  bottomLayout , bottomBgLayout , bottomImageLayout;
    private LinearLayout textLayout;
    private FrameLayout  sharingSocialLayout , paintLayout ;
    private Boolean layoutSelected = false , textLayoutSelected = false;
    private FrameLayout flShapeOne;
    private FrameLayout flShapeTwo;
    private FrameLayout flShapeThree;
    private ImageView circularImage;
    private ImageView ivShapeOne;
    private ImageView ivShapeTwo;
    private ImageView ivShapeThree;
    private ImageView circularImage2;
    private ImageView circularImage3;
    private ImageView  layoutImage  , galleryImage ,tImage , paintImage  ,shareImg , backImage , tickImage , alignmentImage ,  underlineImage , boldImage;
    View rootView;
    TextView contentTextView;
    File filePath;
    int alignmentCount = 0 , styleCount = 0 , boldCount = 0 , italiCount = 0 , imageNumber = 0;
    private String sharePath="no";
    private FrameLayout mainFrameLayout;
    Bitmap bitmap;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private float xCoOrdinate, yCoOrdinate;
    private FrameLayout flLines;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_screen);

        if (getActionBar() != null)
        getActionBar().hide();

        declerationAndListener();

        ImageSpan is = new ImageSpan(getApplicationContext(), R.drawable.st_icon);
        ClipboardManager myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        ClipData clipData = myClipboard.getPrimaryClip();

        if (clipData != null && clipData.getItemAt(0) != null) {
            ClipData.Item item = clipData.getItemAt(0);
            copiedText = item.getText().toString();
        }

        SpannableString texts = new SpannableString( copiedText + " " );
        texts.setSpan(is, texts.length()-1, texts.length(),0);

        contentTextView.setText(texts);

        rootView = getWindow().getDecorView().findViewById(android.R.id.content);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());



//        circularImage.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                switch (event.getActionMasked()) {
//                    case MotionEvent.ACTION_DOWN:
//                        xCoOrdinate = view.getX() - event.getRawX();
//                        yCoOrdinate = view.getY() - event.getRawY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
//                        break;
//                    default:
//                        return false;
//                }
//                return true;
//            }
//        });
//        circularImage2.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                switch (event.getActionMasked()) {
//                    case MotionEvent.ACTION_DOWN:
//                        xCoOrdinate = view.getX() - event.getRawX();
//                        yCoOrdinate = view.getY() - event.getRawY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
//                        break;
//                    default:
//                        return false;
//                }
//                return true;
//            }
//        });
//        circularImage3.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                switch (event.getActionMasked()) {
//                    case MotionEvent.ACTION_DOWN:
//                        xCoOrdinate = view.getX() - event.getRawX();
//                        yCoOrdinate = view.getY() - event.getRawY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
//                        break;
//                    default:
//                        return false;
//                }
//                return true;
//            }
//        });

        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }


    // this redirects all touch events in the activity to the gesture detector
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mScaleGestureDetector.onTouchEvent(event);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        // when a scale gesture is detected, use it to resize the image
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            circularImage.setScaleX(mScaleFactor);
            circularImage.setScaleY(mScaleFactor);

            circularImage2.setScaleX(mScaleFactor);
            circularImage2.setScaleY(mScaleFactor);

            circularImage3.setScaleX(mScaleFactor);
            circularImage3.setScaleY(mScaleFactor);
            return true;
        }
    }

    private void declerationAndListener(){
        contentTextView = findViewById(R.id.copied_text);
        backImage = findViewById(R.id.back_image);

        backgroundLayout = findViewById(R.id.scrollview);
        flLines = findViewById(R.id.flLines);
        circularImage = findViewById(R.id.circularImage);
        ivShapeOne = findViewById(R.id.ivShapeOne);
        ivShapeTwo = findViewById(R.id.ivShapeTwo);
        ivShapeThree = findViewById(R.id.ivShapeThree);
        flShapeOne = findViewById(R.id.flShapeOne);
        circularImage2 = findViewById(R.id.circularImage2);
        flShapeTwo = findViewById(R.id.flShapeTwo);
        circularImage3 = findViewById(R.id.circularImage3);
        flShapeThree = findViewById(R.id.flShapeThree);

        sharingSocialLayout = findViewById(R.id.sharing_layout);
        paintLayout = findViewById(R.id.paint_layout);
        textLayout = findViewById(R.id.text_layout);
        mainFrameLayout = findViewById(R.id.mainFrameLayout);


        layoutImage = findViewById(R.id.layout_selection_img);
        galleryImage = findViewById(R.id.gallery_selection_img);

        tImage = findViewById(R.id.t_selection_img);
        paintImage = findViewById(R.id.paint_img);
        tickImage = findViewById(R.id.tick_image);

        ImageView firstImage = findViewById(R.id.first_img);
        ImageView secondImage = findViewById(R.id.second_img);
        ImageView thirdImage = findViewById(R.id.third_img);

        alignmentImage = findViewById(R.id.img_text_alignment);
        underlineImage = findViewById(R.id.img_text_underline);
        boldImage = findViewById(R.id.text_bold);

        ImageView firstImageBg = findViewById(R.id.first_image_bg);
        ImageView secondImageBg = findViewById(R.id.second_image_bg);
        ImageView italicIcon = findViewById(R.id.text_italic);


        ImageView colourOne = findViewById(R.id.colour_one);
        ImageView colour2 = findViewById(R.id.colour_two);
        ImageView colour3 = findViewById(R.id.colour_three);
        ImageView colour4 = findViewById(R.id.colour_four);
        ImageView colour5 = findViewById(R.id.colour_five);
        ImageView colour6 = findViewById(R.id.colour_six);
        ImageView colour7 = findViewById(R.id.colour_seven);
        ImageView colour8 = findViewById(R.id.colour_eight);
        ImageView colour9 = findViewById(R.id.color_nine);
        ImageView colour10 = findViewById(R.id.colour_ten);

        Button insta = findViewById(R.id.insta_btn);
        Button facebook = findViewById(R.id.facebook_btn);
        Button telegram = findViewById(R.id.telegram_btn);
        Button camera = findViewById(R.id.save_camera_btn);


        shareLayout = findViewById(R.id.share_layout);
        bottomLayout = findViewById(R.id.bottom_layout);
        bottomBgLayout = findViewById(R.id.bottom_bg_layout);
        bottomImageLayout = findViewById(R.id.image_bg_layout);
        actionBarLayout = findViewById(R.id.action_bar_layout);
        rootLayout = findViewById(R.id.root_layout);


        shareImg = findViewById(R.id.share_img);


        backImage.setOnClickListener(this);
        shareLayout.setOnClickListener(this);
        layoutImage.setOnClickListener(this);
        galleryImage.setOnClickListener(this);
        tImage.setOnClickListener(this);
        paintImage.setOnClickListener(this);
        tickImage.setOnClickListener(this);

        firstImage.setOnClickListener(this);
        secondImage.setOnClickListener(this);
        thirdImage.setOnClickListener(this);

        firstImageBg.setOnClickListener(this);
        secondImageBg.setOnClickListener(this);

        insta.setOnClickListener(this);
        facebook.setOnClickListener(this);
        telegram.setOnClickListener(this);
        camera.setOnClickListener(this);
        italicIcon.setOnClickListener(this);

        colourOne.setOnClickListener(this);
        colour2.setOnClickListener(this);
        colour3.setOnClickListener(this);
        colour4.setOnClickListener(this);
        colour5.setOnClickListener(this);
        colour6.setOnClickListener(this);
        colour7.setOnClickListener(this);
        colour8.setOnClickListener(this);
        colour9.setOnClickListener(this);
        colour10.setOnClickListener(this);

        alignmentImage.setOnClickListener(this);
        underlineImage.setOnClickListener(this);
        boldImage.setOnClickListener(this);
    }

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = getExternalCacheDir() + "/" + now + ".jpeg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);


            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            //setting screenshot in imageview
            String filePath = imageFile.getPath();

            sharePath = filePath;

        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    private void shareInsta(String sharePath) {

        File file = new File(sharePath);
        Uri photoURI = FileProvider.getUriForFile(this, this.getPackageName() + ".provider", file);

        Intent intent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        if (intent != null) {
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("image/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM, photoURI);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            shareIntent.setPackage("com.instagram.android"); //Instagram App package
            startActivity(Intent.createChooser(shareIntent, "Share.."));
        } else {
            Toast.makeText(this, "Instagram have not been installed.", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_image:
                onBackPressed();
                break;

            case R.id.tick_image:
                bottomBgLayout.setVisibility(View.GONE);
                bottomImageLayout.setVisibility(View.GONE);
                shareLayout.setVisibility(View.VISIBLE);
                bottomLayout.setVisibility(View.VISIBLE);
                paintLayout.setVisibility(View.GONE);
                shareImg.setVisibility(View.VISIBLE);
                textLayout.setVisibility(View.GONE);
                break;

            case R.id.share_layout:
                bottomBgLayout.setVisibility(View.GONE);
                bottomImageLayout.setVisibility(View.GONE);
                shareImg.setVisibility(View.INVISIBLE);
//                shareLayout.setVisibility(View.GONE);
                bottomLayout.setVisibility(View.VISIBLE);
                actionBarLayout.setVisibility(View.VISIBLE);
                tickImage.setVisibility(View.INVISIBLE);
                backImage.setVisibility(View.INVISIBLE);

                layoutImage.setVisibility(View.GONE);
                galleryImage.setVisibility(View.GONE);
                tImage.setVisibility(View.GONE);
                paintImage.setVisibility(View.GONE);

                int color = ((ColorDrawable) shareLayout.getBackground()).getColor();
                bottomLayout.setBackgroundColor(color);

                //Need to take screenshot here and make visible of social sharing layout
                takeScreenshot();

                sharingSocialLayout.setVisibility(View.VISIBLE);
                break;

            //LayoutSelection
            case R.id.layout_selection_img:
                bottomLayout.setVisibility(View.INVISIBLE);
                shareLayout.setVisibility(View.INVISIBLE);
                bottomBgLayout.setVisibility(View.VISIBLE);
                break;

                case R.id.gallery_selection_img:
                    if (!layoutSelected){
                        Toast.makeText(this, "Please select layout first", Toast.LENGTH_SHORT).show();
                    }else {
                        bottomLayout.setVisibility(View.INVISIBLE);
                        shareLayout.setVisibility(View.INVISIBLE);
                        bottomBgLayout.setVisibility(View.INVISIBLE);
                        bottomImageLayout.setVisibility(View.VISIBLE);
                    }
                break;

                case R.id.t_selection_img:
                    textLayout.setVisibility(View.VISIBLE);
                    bottomLayout.setVisibility(View.INVISIBLE);
                    shareLayout.setVisibility(View.VISIBLE);
                    shareImg.setVisibility(View.GONE);
                    bottomBgLayout.setVisibility(View.INVISIBLE);
                    bottomImageLayout.setVisibility(View.GONE);
                    paintLayout.setVisibility(View.VISIBLE);
                    textLayoutSelected = true;
                    break;

                case R.id.paint_img:
                    textLayoutSelected = false;
                    bottomLayout.setVisibility(View.INVISIBLE);
                    shareLayout.setVisibility(View.VISIBLE);
                    shareImg.setVisibility(View.GONE);
                    bottomBgLayout.setVisibility(View.INVISIBLE);
                    bottomImageLayout.setVisibility(View.GONE);
                    paintLayout.setVisibility(View.VISIBLE);
                break;

                case R.id.first_image_bg:

                    if (imageNumber == 1){
                        circularImage.setVisibility(View.VISIBLE);
                        flShapeOne.setVisibility(View.VISIBLE);
                        circularImage2.setVisibility(View.GONE);
                        flShapeTwo.setVisibility(View.GONE);
                        circularImage3.setVisibility(View.GONE);
                        flShapeThree.setVisibility(View.GONE);
                        circularImage.setImageResource(R.drawable.bg_img_one);
                    }else if (imageNumber == 2){
                        circularImage.setVisibility(View.INVISIBLE);
                        flShapeOne.setVisibility(View.INVISIBLE);
                        circularImage3.setVisibility(View.GONE);
                        flShapeThree.setVisibility(View.GONE);
                        circularImage2.setVisibility(View.VISIBLE);
                        flShapeTwo.setVisibility(View.VISIBLE);
                        circularImage2.setImageResource(R.drawable.bg_img_one);
                    }else if (imageNumber ==3){
                        circularImage.setVisibility(View.INVISIBLE);
                        flShapeOne.setVisibility(View.INVISIBLE);
                        circularImage2.setVisibility(View.GONE);
                        flShapeTwo.setVisibility(View.GONE);
                        circularImage3.setVisibility(View.VISIBLE);
                        flShapeThree.setVisibility(View.VISIBLE);
                        circularImage3.setImageResource(R.drawable.bg_img_one);
                    }
                break;

                case R.id.second_image_bg:
                    if (imageNumber == 1){
                        circularImage.setVisibility(View.VISIBLE);
                        flShapeOne.setVisibility(View.VISIBLE);
                        circularImage2.setVisibility(View.GONE);
                        flShapeTwo.setVisibility(View.GONE);
                        circularImage3.setVisibility(View.GONE);
                        flShapeThree.setVisibility(View.GONE);
                        circularImage.setImageResource(R.drawable.bg_img_two);
                    }else if (imageNumber == 2){
                        circularImage.setVisibility(View.INVISIBLE);
                        flShapeOne.setVisibility(View.INVISIBLE);
                        circularImage3.setVisibility(View.GONE);
                        flShapeThree.setVisibility(View.GONE);
                        circularImage2.setVisibility(View.VISIBLE);
                        flShapeTwo.setVisibility(View.VISIBLE);
                        circularImage2.setImageResource(R.drawable.bg_img_two);
                    }else if (imageNumber ==3){
                        circularImage.setVisibility(View.INVISIBLE);
                        flShapeOne.setVisibility(View.INVISIBLE);
                        circularImage3.setVisibility(View.VISIBLE);
                        flShapeThree.setVisibility(View.VISIBLE);
                        circularImage3.setImageResource(R.drawable.bg_img_two);
                    }
                break;

            //BackgroundImageSelection
                case R.id.first_img:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        backgroundLayout.setBackgroundResource(R.drawable.top_eye_bg);
                        //flLines.setBackgroundResource(R.drawable.top_eye_bg);
                    }
                    circularImage.setVisibility(View.VISIBLE);
                    flShapeOne.setVisibility(View.VISIBLE);
                    circularImage2.setVisibility(View.GONE);
                    flShapeTwo.setVisibility(View.GONE);
                    circularImage3.setVisibility(View.GONE);
                    flShapeThree.setVisibility(View.GONE);
                    circularImage.setImageResource(R.drawable.bg_img_two);
                    imageNumber = 1;
                    layoutSelected = true;
                    break;

                case R.id.second_img:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        backgroundLayout.setBackgroundResource(R.drawable.bottom_eye_bg);
                        //flLines.setBackgroundResource(R.drawable.bottom_eye_bg);
                    }
                    circularImage.setVisibility(View.INVISIBLE);
                    flShapeOne.setVisibility(View.INVISIBLE);
                    circularImage3.setVisibility(View.GONE);
                    flShapeThree.setVisibility(View.GONE);
                    circularImage2.setVisibility(View.VISIBLE);
                    flShapeTwo.setVisibility(View.VISIBLE);
                    circularImage2.setImageResource(R.drawable.bg_img_two);
                    layoutSelected = true;
                    imageNumber = 2;
                    break;

                case R.id.third_img:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        backgroundLayout.setBackgroundResource(R.drawable.bottom_right_eye_bg);
                        //flLines.setBackgroundResource(R.drawable.bottom_right_eye_bg);
                    }
                    circularImage.setVisibility(View.INVISIBLE);
                    flShapeOne.setVisibility(View.INVISIBLE);
                    circularImage2.setVisibility(View.GONE);
                    flShapeTwo.setVisibility(View.GONE);
                    circularImage3.setVisibility(View.VISIBLE);
                    flShapeThree.setVisibility(View.VISIBLE);
                    circularImage3.setImageResource(R.drawable.bg_img_two);
                    layoutSelected = true;
                    imageNumber = 3;
                    break;

            case R.id.insta_btn:
                Toast.makeText(this, "Insta", Toast.LENGTH_SHORT).show();

                //redirect to instagram story with image
                shareInsta(sharePath);
                break;

            case R.id.facebook_btn:
                File file = new File(sharePath);
                Uri photoURI = FileProvider.getUriForFile(this, this.getPackageName() + ".provider", file);

                Intent intent = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
                if (intent != null) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/*");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, photoURI);
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                    shareIntent.setPackage("com.facebook.katana"); //Instagram App package
                    startActivity(Intent.createChooser(shareIntent, "Share.."));
                } else {
                    Toast.makeText(this, "Facebook have not been installed.", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.telegram_btn:
                //redirect to telegram  with image
                File files = new File(sharePath);
                Uri photoURIs = FileProvider.getUriForFile(this, this.getPackageName() + ".provider", files);

                Intent intentTypes = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
                if (intentTypes != null) {
                    Intent intentType = new Intent(Intent.ACTION_SEND);
                    intentType.setType("image/*");
                    intentType.putExtra(Intent.EXTRA_STREAM, photoURIs);
                    intentType.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intentType.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                    intentType.setPackage("org.telegram.messenger"); //Instagram App package
                    startActivity(Intent.createChooser(intentType, "Share.."));
                }else {
                    Toast.makeText(this, "Facebook have not been installed.", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.save_camera_btn:
                //Save in gallery
                if (Build.VERSION.SDK_INT >= 23)
                {
                    if (checkPermission())
                    {
                        // Code for above or equal 23 API Oriented Device
                        // Your Permission granted already .Do next code
                        Date now = new Date();
                        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

                        String ImagePath = MediaStore.Images.Media.insertImage(
                                getContentResolver(),
                                bitmap,
                                "social_"+ now,
                                "ImageGallery"
                        );

                        Toast.makeText(EditingActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();

                    } else {
                        requestPermission(); // Code for permission
                    }
                }
                else
                {
                    // Code for Below 23 API Oriented Device
                    // Do next code
                    Date now = new Date();
                    android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

                    String ImagePath = MediaStore.Images.Media.insertImage(
                            getContentResolver(),
                            bitmap,
                            "social_"+ now,
                            "ImageGallery"
                    );

                    Toast.makeText(EditingActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;

            case R.id.colour_one:
                if (textLayoutSelected){
                    contentTextView.setTextColor(getResources().getColor(R.color.colour_one));
                }else{
                    actionBarLayout.setBackgroundColor(getResources().getColor(R.color.colour_one));
                    mainFrameLayout.setBackgroundColor(getResources().getColor(R.color.colour_one));
                    shareLayout.setVisibility(View.VISIBLE);
                    shareImg.setVisibility(View.GONE);
                    shareLayout.setBackgroundColor(getResources().getColor(R.color.colour_one));
                    paintLayout.setBackgroundColor(getResources().getColor(R.color.colour_one));

                    if (ivShapeOne.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_one,getResources().getColor(R.color.colour_one));
                        ivShapeOne.setImageBitmap(bitmap);
                    }
                    if (ivShapeTwo.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_two,getResources().getColor(R.color.colour_one));
                        ivShapeTwo.setImageBitmap(bitmap);
                    }
                    if (ivShapeThree.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_three,getResources().getColor(R.color.colour_one));
                        ivShapeThree.setImageBitmap(bitmap);
                    }
                }
                break;

            case R.id.colour_two:
                if (textLayoutSelected){
                    contentTextView.setTextColor(getResources().getColor(R.color.colour_two));
                }else {
                    actionBarLayout.setBackgroundColor(getResources().getColor(R.color.colour_two));
                    mainFrameLayout.setBackgroundColor(getResources().getColor(R.color.colour_two));
                    shareLayout.setVisibility(View.VISIBLE);
                    shareImg.setVisibility(View.GONE);
                    shareLayout.setBackgroundColor(getResources().getColor(R.color.colour_two));
                    paintLayout.setBackgroundColor(getResources().getColor(R.color.colour_two));

                    if (ivShapeOne.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_one,getResources().getColor(R.color.colour_two));
                        ivShapeOne.setImageBitmap(bitmap);
                    }
                    if (ivShapeTwo.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_two,getResources().getColor(R.color.colour_two));
                        ivShapeTwo.setImageBitmap(bitmap);
                    }
                    if (ivShapeThree.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_three,getResources().getColor(R.color.colour_two));
                        ivShapeThree.setImageBitmap(bitmap);
                    }
                }
                    break;

            case R.id.colour_three:
                if (textLayoutSelected){
                    contentTextView.setTextColor(getResources().getColor(R.color.colour_three));
                }else {
                    actionBarLayout.setBackgroundColor(getResources().getColor(R.color.colour_three));
                    mainFrameLayout.setBackgroundColor(getResources().getColor(R.color.colour_three));
                    shareLayout.setVisibility(View.VISIBLE);
                    shareImg.setVisibility(View.GONE);
                    shareLayout.setBackgroundColor(getResources().getColor(R.color.colour_three));
                    paintLayout.setBackgroundColor(getResources().getColor(R.color.colour_three));

                    if (ivShapeOne.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_one,getResources().getColor(R.color.colour_three));
                        ivShapeOne.setImageBitmap(bitmap);
                    }
                    if (ivShapeTwo.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_two,getResources().getColor(R.color.colour_three));
                        ivShapeTwo.setImageBitmap(bitmap);
                    }
                    if (ivShapeThree.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_three,getResources().getColor(R.color.colour_three));
                        ivShapeThree.setImageBitmap(bitmap);
                    }
                }
                break;

            case R.id.colour_four:
                if (textLayoutSelected){
                    contentTextView.setTextColor(getResources().getColor(R.color.colour_four));
                }else {
                    actionBarLayout.setBackgroundColor(getResources().getColor(R.color.colour_four));
                    mainFrameLayout.setBackgroundColor(getResources().getColor(R.color.colour_four));
                    shareLayout.setVisibility(View.VISIBLE);
                    shareImg.setVisibility(View.GONE);
                    shareLayout.setBackgroundColor(getResources().getColor(R.color.colour_four));
                    paintLayout.setBackgroundColor(getResources().getColor(R.color.colour_four));

                    if (ivShapeOne.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_one,getResources().getColor(R.color.colour_four));
                        ivShapeOne.setImageBitmap(bitmap);
                    }
                    if (ivShapeTwo.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_two,getResources().getColor(R.color.colour_four));
                        ivShapeTwo.setImageBitmap(bitmap);
                    }
                    if (ivShapeThree.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_three,getResources().getColor(R.color.colour_four));
                        ivShapeThree.setImageBitmap(bitmap);
                    }
                }
                break;

            case R.id.colour_five:
                if (textLayoutSelected){
                    contentTextView.setTextColor(getResources().getColor(R.color.colour_five));
                }else {
                    actionBarLayout.setBackgroundColor(getResources().getColor(R.color.colour_five));
                    mainFrameLayout.setBackgroundColor(getResources().getColor(R.color.colour_five));
                    shareLayout.setVisibility(View.VISIBLE);
                    shareImg.setVisibility(View.GONE);
                    shareLayout.setBackgroundColor(getResources().getColor(R.color.colour_five));
                    paintLayout.setBackgroundColor(getResources().getColor(R.color.colour_five));

                    if (ivShapeOne.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_one,getResources().getColor(R.color.colour_five));
                        ivShapeOne.setImageBitmap(bitmap);
                    }
                    if (ivShapeTwo.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_two,getResources().getColor(R.color.colour_five));
                        ivShapeTwo.setImageBitmap(bitmap);
                    }
                    if (ivShapeThree.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_three,getResources().getColor(R.color.colour_five));
                        ivShapeThree.setImageBitmap(bitmap);
                    }
                }
                break;

            case R.id.colour_six:
                if (textLayoutSelected){
                    contentTextView.setTextColor(getResources().getColor(R.color.colour_six));
                }else {
                    actionBarLayout.setBackgroundColor(getResources().getColor(R.color.colour_six));
                    mainFrameLayout.setBackgroundColor(getResources().getColor(R.color.colour_six));
                    shareLayout.setVisibility(View.VISIBLE);
                    shareImg.setVisibility(View.GONE);
                    shareLayout.setBackgroundColor(getResources().getColor(R.color.colour_six));
                    paintLayout.setBackgroundColor(getResources().getColor(R.color.colour_six));

                    if (ivShapeOne.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_one,getResources().getColor(R.color.colour_six));
                        ivShapeOne.setImageBitmap(bitmap);
                    }
                    if (ivShapeTwo.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_two,getResources().getColor(R.color.colour_six));
                        ivShapeTwo.setImageBitmap(bitmap);
                    }
                    if (ivShapeThree.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_three,getResources().getColor(R.color.colour_six));
                        ivShapeThree.setImageBitmap(bitmap);
                    }
                }
                break;

            case R.id.colour_seven:
                if (textLayoutSelected){
                    contentTextView.setTextColor(getResources().getColor(R.color.colour_seven));
                }else {
                    actionBarLayout.setBackgroundColor(getResources().getColor(R.color.colour_seven));
                    mainFrameLayout.setBackgroundColor(getResources().getColor(R.color.colour_seven));
                    shareLayout.setVisibility(View.VISIBLE);
                    shareImg.setVisibility(View.GONE);
                    shareLayout.setBackgroundColor(getResources().getColor(R.color.colour_seven));
                    paintLayout.setBackgroundColor(getResources().getColor(R.color.colour_seven));

                    if (ivShapeOne.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_one,getResources().getColor(R.color.colour_seven));
                        ivShapeOne.setImageBitmap(bitmap);
                    }
                    if (ivShapeTwo.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_two,getResources().getColor(R.color.colour_seven));
                        ivShapeTwo.setImageBitmap(bitmap);
                    }
                    if (ivShapeThree.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_three,getResources().getColor(R.color.colour_seven));
                        ivShapeThree.setImageBitmap(bitmap);
                    }
                }
                break;

                case R.id.colour_eight:
                    if (textLayoutSelected){
                        contentTextView.setTextColor(getResources().getColor(R.color.colour_eight));
                    }else {
                        actionBarLayout.setBackgroundColor(getResources().getColor(R.color.colour_eight));
                        mainFrameLayout.setBackgroundColor(getResources().getColor(R.color.colour_eight));
                        shareLayout.setVisibility(View.VISIBLE);
                        shareImg.setVisibility(View.GONE);
                        shareLayout.setBackgroundColor(getResources().getColor(R.color.colour_eight));
                        paintLayout.setBackgroundColor(getResources().getColor(R.color.colour_eight));

                        if (ivShapeOne.getVisibility() == View.VISIBLE) {
                            bitmap = Utils.bitmap(this,R.drawable.shape_one,getResources().getColor(R.color.colour_eight));
                            ivShapeOne.setImageBitmap(bitmap);
                        }
                        if (ivShapeTwo.getVisibility() == View.VISIBLE) {
                            bitmap = Utils.bitmap(this,R.drawable.shape_two,getResources().getColor(R.color.colour_eight));
                            ivShapeTwo.setImageBitmap(bitmap);
                        }
                        if (ivShapeThree.getVisibility() == View.VISIBLE) {
                            bitmap = Utils.bitmap(this,R.drawable.shape_three,getResources().getColor(R.color.colour_eight));
                            ivShapeThree.setImageBitmap(bitmap);
                        }
                    }
                break;

            case R.id.color_nine:
                if (textLayoutSelected){
                    contentTextView.setTextColor(getResources().getColor(R.color.colour_nine));
                }else {
                    actionBarLayout.setBackgroundColor(getResources().getColor(R.color.colour_nine));
                    mainFrameLayout.setBackgroundColor(getResources().getColor(R.color.colour_nine));
                    shareLayout.setVisibility(View.VISIBLE);
                    shareImg.setVisibility(View.GONE);
                    shareLayout.setBackgroundColor(getResources().getColor(R.color.colour_nine));
                    paintLayout.setBackgroundColor(getResources().getColor(R.color.colour_nine));

                    if (ivShapeOne.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_one,getResources().getColor(R.color.colour_nine));
                        ivShapeOne.setImageBitmap(bitmap);
                    }
                    if (ivShapeTwo.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_two,getResources().getColor(R.color.colour_nine));
                        ivShapeTwo.setImageBitmap(bitmap);
                    }
                    if (ivShapeThree.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_three,getResources().getColor(R.color.colour_nine));
                        ivShapeThree.setImageBitmap(bitmap);
                    }
                }
                break;

            case R.id.colour_ten:

                if (textLayoutSelected){
                    contentTextView.setTextColor(getResources().getColor(R.color.colour_nine));
                }else {
                    actionBarLayout.setBackgroundColor(getResources().getColor(R.color.colour_ten));
                    mainFrameLayout.setBackgroundColor(getResources().getColor(R.color.colour_ten));
                    shareLayout.setVisibility(View.VISIBLE);
                    shareImg.setVisibility(View.GONE);
                    shareLayout.setBackgroundColor(getResources().getColor(R.color.colour_ten));
                    paintLayout.setBackgroundColor(getResources().getColor(R.color.colour_ten));

                    if (ivShapeOne.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_one,getResources().getColor(R.color.colour_ten));
                        ivShapeOne.setImageBitmap(bitmap);
                    }
                    if (ivShapeTwo.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_two,getResources().getColor(R.color.colour_ten));
                        ivShapeTwo.setImageBitmap(bitmap);
                    }
                    if (ivShapeThree.getVisibility() == View.VISIBLE) {
                        bitmap = Utils.bitmap(this,R.drawable.shape_three,getResources().getColor(R.color.colour_ten));
                        ivShapeThree.setImageBitmap(bitmap);
                    }
                }
                break;

            case R.id.img_text_alignment:
                   //Three types of text alignment
                    if (alignmentCount == 0){
                        contentTextView.setGravity(Gravity.RIGHT | Gravity.END);
                        alignmentImage.setBackgroundResource(R.drawable.text_right);
                        alignmentCount = 1;
                    }else  if (alignmentCount == 1){
                        contentTextView.setGravity(Gravity.CENTER);
                        alignmentImage.setBackgroundResource(R.drawable.text_center);
                        alignmentCount = 2;
                    }else {
                        contentTextView.setGravity(Gravity.LEFT | Gravity.START);
                        alignmentImage.setBackgroundResource(R.drawable.text_left);
                        alignmentCount = 0;
                    }
                break;

                case R.id.img_text_underline:
                    //Add underline and remove underline
                    if (styleCount == 0){
                        contentTextView.setPaintFlags(contentTextView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
                        styleCount = 1;
                    }else {
                        styleCount = 0;
                        contentTextView.setPaintFlags(0);
                    }
                    break;

                case R.id.text_bold:
                    //Bold text
                    if (boldCount == 0){
                        if (italiCount == 1){
                            contentTextView.setTypeface(null, Typeface.BOLD_ITALIC);
                        }else {
                            contentTextView.setTypeface(null, Typeface.BOLD);
                        }
                        boldCount = 1;
                    }else {
                        boldCount = 0;
                        if (italiCount == 1){
                            contentTextView.setTypeface(null, Typeface.ITALIC);
                            contentTextView.setTypeface(null, Typeface.NORMAL);
                        }else {
                            contentTextView.setTypeface(null, Typeface.NORMAL);
                        }
                    }
                    break;

                    case R.id.text_italic:
                    //Italic text
                    if (italiCount == 0){
                        if (boldCount == 1){
                            contentTextView.setTypeface(null, Typeface.BOLD_ITALIC);
                        }else {
                            contentTextView.setTypeface(null, Typeface.ITALIC);
                        }
                        italiCount = 1;
                    }else {
                        if (boldCount == 1){
                            contentTextView.setTypeface(null, Typeface.BOLD);
                        }else {
                            contentTextView.setTypeface(null, Typeface.NORMAL);
                        }
                        italiCount = 0;
                    }
                break;

        }
    }
}
