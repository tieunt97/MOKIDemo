package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.CameraTrangChu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ThemSanPhamActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tieu_nt on 3/13/2018.
 */

public class CameraActivity extends AppCompatActivity implements View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback{
    private Camera mCamera = null;
    private CameraView mCameraView = null;
    private ImageButton imgClose, imgChupAnh, imgGallery, imgRotateCamera;
    private ImageView imgHinhA;
    private FrameLayout camera_view;
    private ToggleButton tgFlash;
    private int IMG_GALLERY_REQUEST = 1;
    private int cameraID;
    int position = -1, dem = 0;
    private byte[] byteImage;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private File pictureFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_trangchu_layout);
        anhXa();

        position = getIntent().getIntExtra("position", -1);

        cameraID = Camera.CameraInfo.CAMERA_FACING_BACK;
        mCamera = getCameraInstance(cameraID);

        if(mCamera != null) {
            mCameraView = new CameraView(this, mCamera);//create a SurfaceView to show camera data
            camera_view.addView(mCameraView);//add the SurfaceView to the layout
        }

        setActions();
    }

    private void anhXa(){
        camera_view = (FrameLayout) findViewById(R.id.camera_view);
        imgClose = (ImageButton) findViewById(R.id.imgClose);
        imgChupAnh = (ImageButton) findViewById(R.id.imgChupAnh);
        imgGallery = (ImageButton) findViewById(R.id.imgGallery);
        imgRotateCamera = (ImageButton) findViewById(R.id.imgRotateCamera);
        imgHinhA = (ImageView) findViewById(R.id.imgHinhA);
        tgFlash = (ToggleButton) findViewById(R.id.tgFlash);
    }

    private void setActions(){
        imgClose.setOnClickListener(this);
        imgChupAnh.setOnClickListener(this);
        imgGallery.setOnClickListener(this);
        imgRotateCamera.setOnClickListener(this);
        tgFlash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Camera.Parameters p = mCamera.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    mCamera.setParameters(p);
                    mCamera.startPreview();
                }else{
                    Camera.Parameters p = mCamera.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    mCamera.setParameters(p);
                    mCamera.startPreview();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgClose:
                finish();
                break;
            case R.id.imgChupAnh:
                mCamera.takePicture(null, null, mPicture);
                break;
            case R.id.imgGallery:
                onImageGalleryClicked();
                break;
            case R.id.imgRotateCamera:
                rotateCamera();
                break;
        }
    }

    private void rotateCamera(){
        //ẩn/hiện tgFlash khi đổi chiều camera
        if(tgFlash.getVisibility() == View.VISIBLE){
            if(tgFlash.isChecked())
                tgFlash.setChecked(false);
            tgFlash.setVisibility(View.GONE);
        }else{
            tgFlash.setVisibility(View.VISIBLE);
        }
//        if (inPreview) {
//            mCamera.stopPreview();
//        }
        //NB: if you don't release the current camera before switching, you app will crash
        mCamera.release();

        //swap the id of the camera to be used
        if(cameraID == Camera.CameraInfo.CAMERA_FACING_BACK){
            cameraID = Camera.CameraInfo.CAMERA_FACING_FRONT;
        }
        else {
            cameraID = Camera.CameraInfo.CAMERA_FACING_BACK;
        }
        mCamera = Camera.open(cameraID);

        setCameraDisplayOrientation(CameraActivity.this, cameraID, mCamera);
        try {
            mCamera.setPreviewDisplay(mCameraView.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.startPreview();
    }

    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    private void onImageGalleryClicked(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, IMG_GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            if(requestCode == IMG_GALLERY_REQUEST){
                Uri uri = data.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                        /*Nếu lấy quality quá lớn sẽ có một số ảnh dung lượng lớn gây tràn bộ nhớ*/
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bStream);
                    byteImage = bStream.toByteArray();
                    if(position == -1){
                        Intent iThemSanPham = new Intent(this, ThemSanPhamActivity.class);
                        iThemSanPham.putExtra("image", byteImage);
                        startActivity(iThemSanPham);
                    }else{
                        finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if (pictureFile == null){
                Log.d("MOKIDemo", "Error creating media file, check storage permissions: ");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
                setImage();
            } catch (FileNotFoundException e) {
                Log.d("MOKIDemo", "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d("MOKIDemo", "Error accessing file: " + e.getMessage());
            }
        }
    };



    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MOKIDemo");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MOKIDemo", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    private Camera getCameraInstance(int cameraID){
        Camera c = null;
        try {
            c = Camera.open(cameraID); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
            Log.d("ErrorCamera", "Fail to connect to camera:");
            e.printStackTrace();
        }
        return c; // returns null if camera is unavailable
    }

    @Override
    public void finish() {
        if (byteImage != null){
            Intent data = new Intent();
            data.putExtra("image", byteImage);
            setResult(RESULT_OK, data);
        }
        super.finish();
    }

    private void setImage(){
        Bitmap bitmap = BitmapFactory.decodeFile(pictureFile.getPath());
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        /*Nếu lấy quality = 100 sẽ có một số ảnh dung lượng lớn gây tràn bộ nhớ*/
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bStream);
        byteImage = bStream.toByteArray();
        if (byteImage != null){
            if(position == -1){
                Intent iThemSanPham = new Intent(this, ThemSanPhamActivity.class);
                iThemSanPham.putExtra("image", byteImage);
                startActivity(iThemSanPham);
            }else{
                finish();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    private void releaseCamera(){
        if (mCamera != null){
            mCamera.stopPreview();
            mCameraView.getHolder().removeCallback(mCameraView);
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }
}
