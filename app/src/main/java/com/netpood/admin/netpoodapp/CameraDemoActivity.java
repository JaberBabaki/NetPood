package com.netpood.admin.netpoodapp;

import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.netpood.admin.framework.activity.UAppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class CameraDemoActivity extends UAppCompatActivity implements Callback, OnClickListener {

  private SurfaceHolder surfaceHolder;
  private Camera camera;
  private int cameraId;
  private boolean flashmode = false;
  private int rotation;


  private Ui ui;

  public class Ui {
    ImageView flipCamera;
    ImageView flash;
    ImageView captureImage;
    SurfaceView surfaceView;
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ui = new Ui();
    new UAppCompatActivity.Founder(this)
      .requestFeatures()
      .noTitlebar()
      .noActionbar()
      .statusBar()
      .contentView(R.layout.camerademo_activity)
      .extractUi(ui)
      .build();


    // camera surface view created
    cameraId = CameraInfo.CAMERA_FACING_BACK;
    surfaceHolder = ui.surfaceView.getHolder();
    surfaceHolder.addCallback(this);
    ui.flipCamera.setOnClickListener(this);
    ui.captureImage.setOnClickListener(this);
    ui.flash.setOnClickListener(this);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    if (Camera.getNumberOfCameras() > 1) {
      ui.flipCamera.setVisibility(View.VISIBLE);
    }

    if (!getBaseContext().getPackageManager().hasSystemFeature(
      PackageManager.FEATURE_CAMERA_FLASH)) {
      ui.flash.setVisibility(View.GONE);
    }


  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    if (!openCamera(CameraInfo.CAMERA_FACING_BACK)) {
      alertCameraDialog();
    }

  }

  private boolean openCamera(int id) {
    boolean result = false;
    cameraId = id;
    releaseCamera();
    try {
      camera = Camera.open(cameraId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (camera != null) {
      try {
        setUpCamera(camera);
        camera.setErrorCallback(new ErrorCallback() {

          @Override
          public void onError(int error, Camera camera) {

          }
        });
        camera.setPreviewDisplay(surfaceHolder);
        camera.startPreview();
        result = true;
      } catch (IOException e) {
        e.printStackTrace();
        result = false;
        releaseCamera();
      }
    }
    return result;
  }

  private void setUpCamera(Camera c) {
    CameraInfo info = new CameraInfo();
    Camera.getCameraInfo(cameraId, info);
    rotation = getWindowManager().getDefaultDisplay().getRotation();
    int degree = 0;
    switch (rotation) {
      case Surface.ROTATION_0:
        degree = 0;
        break;
      case Surface.ROTATION_90:
        degree = 90;
        break;
      case Surface.ROTATION_180:
        degree = 180;
        break;
      case Surface.ROTATION_270:
        degree = 270;
        break;

      default:
        break;
    }

    if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
      // frontFacing
      rotation = (info.orientation + degree) % 330;
      rotation = (360 - rotation) % 360;
    } else {
      // Back-facing
      rotation = (info.orientation - degree + 360) % 360;
    }
    c.setDisplayOrientation(rotation);
    Parameters params = c.getParameters();

    showFlashButton(params);

    List<String> focusModes = params.getSupportedFlashModes();
    if (focusModes != null) {
      if (focusModes
        .contains(Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
        params.setFlashMode(Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
      }
    }

    params.setRotation(rotation);
  }

  private void showFlashButton(Parameters params) {
    boolean showFlash = (getPackageManager().hasSystemFeature(
      PackageManager.FEATURE_CAMERA_FLASH) && params.getFlashMode() != null)
      && params.getSupportedFlashModes() != null
      && params.getSupportedFocusModes().size() > 1;

    ui.flash.setVisibility(showFlash ? View.VISIBLE
      : View.INVISIBLE);

  }

  private void releaseCamera() {
    try {
      if (camera != null) {
        camera.setPreviewCallback(null);
        camera.setErrorCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;
      }
    } catch (Exception e) {
      e.printStackTrace();
      Log.e("error", e.toString());
      camera = null;
    }
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width,
                             int height) {

  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.flash:
        flashOnButton();
        break;
      case R.id.flipCamera:
        flipCamera();
        break;
      case R.id.captureImage:
        takeImage();
        break;

      default:
        break;
    }
  }

  private void takeImage() {
    camera.takePicture(null, null, new PictureCallback() {

      private File imageFile;

      @Override
      public void onPictureTaken(byte[] data, Camera camera) {
        try {
          // convert byte array into bitmap
          Bitmap loadedImage = null;
          Bitmap rotatedBitmap = null;
          loadedImage = BitmapFactory.decodeByteArray(data, 0,data.length);

          // rotate Image
          Matrix rotateMatrix = new Matrix();
          rotateMatrix.postRotate(rotation);
          rotatedBitmap = Bitmap.createBitmap(loadedImage, 0, 0, loadedImage.getWidth(), loadedImage.getHeight(), rotateMatrix, false);
          String state = Environment.getExternalStorageState();
          File folder = null;
          if (state.contains(Environment.MEDIA_MOUNTED)) {
            folder = new File(Environment
              .getExternalStorageDirectory() + "/Demo");
          } else {
            folder = new File(Environment
              .getExternalStorageDirectory() + "/Demo");
          }

          boolean success = true;
          if (!folder.exists()) {
            success = folder.mkdirs();
          }
          if (success) {
            java.util.Date date = new java.util.Date();
            imageFile = new File(folder.getAbsolutePath()
              + File.separator
              + new Timestamp(date.getTime()).toString()
              + "Image.jpg");

            imageFile.createNewFile();
          } else {
            Toast.makeText(getBaseContext(), "Image Not saved",
              Toast.LENGTH_SHORT).show();
            return;
          }

          ByteArrayOutputStream ostream = new ByteArrayOutputStream();

          // save image into gallery
          rotatedBitmap.compress(CompressFormat.JPEG, 100, ostream);

          FileOutputStream fout = new FileOutputStream(imageFile);
          fout.write(ostream.toByteArray());
          fout.close();
          ContentValues values = new ContentValues();

          values.put(Images.Media.DATE_TAKEN,
            System.currentTimeMillis());
          values.put(Images.Media.MIME_TYPE, "image/jpeg");
          values.put(MediaStore.MediaColumns.DATA,
            imageFile.getAbsolutePath());

          CameraDemoActivity.this.getContentResolver().insert(
            Images.Media.EXTERNAL_CONTENT_URI, values);

        } catch (Exception e) {
          e.printStackTrace();
        }

      }
    });
  }

  private void flipCamera() {
    int id = (cameraId == CameraInfo.CAMERA_FACING_BACK ? CameraInfo.CAMERA_FACING_FRONT
      : CameraInfo.CAMERA_FACING_BACK);
    if (!openCamera(id)) {
      alertCameraDialog();
    }
  }

  private void alertCameraDialog() {
    Builder dialog = createAlert(CameraDemoActivity.this,
      "Camera info", "error to open camera");
    dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();

      }
    });

    dialog.show();
  }

  private Builder createAlert(Context context, String title, String message) {

    Builder dialog = new Builder(
      new ContextThemeWrapper(context,
        android.R.style.Theme_Holo_Light_Dialog));
    dialog.setIcon(R.drawable.btn_flash);
    if (title != null)
      dialog.setTitle(title);
    else
      dialog.setTitle("Information");
    dialog.setMessage(message);
    dialog.setCancelable(false);
    return dialog;

  }

  private void flashOnButton() {
    if (camera != null) {
      try {
        Parameters param = camera.getParameters();
        param.setFlashMode(!flashmode ? Parameters.FLASH_MODE_TORCH
          : Parameters.FLASH_MODE_OFF);
        camera.setParameters(param);
        flashmode = !flashmode;
      } catch (Exception e) {
        // TODO: handle exception
      }

    }
  }
}
