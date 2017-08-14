package com.netpood.admin.netpoodapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class CameraCustom extends Activity {


  private CameraHelper mImageSurfaceView;
  private Camera mCamera;

  private FrameLayout cameraPreviewLayout;
  private ImageView capturedImageHolder;
  private ImageView flipCamera;
  private ImageView flash;
  private boolean on = false;

  LinearLayout layTest;
  LinearLayout layok;
  private ImageView imgSet;

  private Ui ui;

  public class Ui {

  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.camerademo_activity);


    layTest = (LinearLayout) findViewById(R.id.lay_testi);
    layok = (LinearLayout) findViewById(R.id.lay_ok);

    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    cameraPreviewLayout = (FrameLayout) findViewById(R.id.camera_preview);
    capturedImageHolder = (ImageView) findViewById(R.id.captured_image);
    flipCamera = (ImageView) findViewById(R.id.flip_camera);
    flash = (ImageView) findViewById(R.id.flash);

    cameraPreviewLayout.post(new Runnable() {
      @Override
      public void run() {
        //maybe also works height = ll.getLayoutParams().height;

       // Base.h = cameraPreviewLayout.getHeight();
       // Base.w = cameraPreviewLayout.getWidth();

      }
    });


    // Base.w=cameraPreviewLayout.getMeasuredWidth();
    // Base.h=cameraPreviewLayout.getMeasuredHeight();
    Log.e("LLL", "jasem " + cameraPreviewLayout.getWidth());
    Log.e("LLL", "jasem " + cameraPreviewLayout.getHeight());

    checkDeviceCamera();
    mImageSurfaceView = new CameraHelper(mCamera);
    cameraPreviewLayout.addView(mImageSurfaceView);

    capturedImageHolder.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mCamera.takePicture(null, null, pictureCallback);
      }
    });


    if (mImageSurfaceView.getFlipCamera()) {
      flipCamera.setVisibility(View.VISIBLE);
    }
    if (mImageSurfaceView.getFlash()) {
      flash.setVisibility(View.VISIBLE);
    }

    flash.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        mImageSurfaceView.setParamCamera(on);
        on = !on;

      }
    });

  }

  private void releaseCamera() {
    try {
      if (mCamera != null) {
        mCamera.setPreviewCallback(null);
        mCamera.setErrorCallback(null);
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
      }
    } catch (Exception e) {
      e.printStackTrace();
      Log.e("error", e.toString());
      mCamera = null;
    }
  }

  private void checkDeviceCamera() {
    releaseCamera();
    try {
      Log.i("LLL", "ok1");
      mCamera = Camera.open();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {

      Bitmap loadedImage = BitmapFactory.decodeByteArray(data, 0, data.length);
      Base.bit = loadedImage;
      Base.rotation = 90;
      mCamera.setPreviewCallback(null);
      mCamera.setErrorCallback(null);
      mCamera.stopPreview();
      mCamera.release();
      mCamera = null;
     // mCamera.set
      layTest.setVisibility(View.GONE);
      layok.setVisibility(View.VISIBLE);


    }
  };

  private Bitmap scaleDownBitmapImage(Bitmap bitmap, int newWidth, int newHeight) {
    Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
    return resizedBitmap;
  }


}
