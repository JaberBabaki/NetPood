package com.netpood.admin.netpoodapp;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
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

import com.netpood.admin.framework.activity.UAppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CameraDemoActivity extends UAppCompatActivity implements Callback, OnClickListener {

  private SurfaceHolder surfaceHolder;
  private Camera camera;
  private int cameraId;
  private boolean flashmode = false;
  private int rotation;
  ImageView flipCamera;
  ImageView flash;
  ImageView captureImage;
  SurfaceView surfaceView;

  private Ui ui;

  public class Ui {

  }

  @Override
  protected void onPause()
  {
    super.onPause();
    if (camera != null) {
      camera.stopPreview();
      camera.release();
      camera = null;
    }
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


    flipCamera=(ImageView)findViewById(R.id.flipCamera);
    flash=(ImageView)findViewById(R.id.flash);
    captureImage=(ImageView)findViewById(R.id.captureImage);
    surfaceView=(SurfaceView)findViewById(R.id.surfaceView);


    // camera surface view created
    cameraId = CameraInfo.CAMERA_FACING_BACK;
    surfaceHolder = surfaceView.getHolder();
    surfaceHolder.addCallback(this);
    flipCamera.setOnClickListener(this);
    captureImage.setOnClickListener(this);
    flash.setOnClickListener(this);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    if (Camera.getNumberOfCameras() > 1) {
      flipCamera.setVisibility(View.VISIBLE);
    }

    if (!getBaseContext().getPackageManager().hasSystemFeature(
      PackageManager.FEATURE_CAMERA_FLASH)) {
      flash.setVisibility(View.GONE);
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
      Camera.Parameters params= camera.getParameters();
      params.setPreviewSize(100, 100);
      camera.setParameters(params);
      camera.setDisplayOrientation(90);
      camera.setPreviewDisplay(surfaceHolder);
      camera.startPreview();
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

     flash.setVisibility(showFlash ? View.VISIBLE
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

         /* Calendar calendar = Calendar.getInstance();
          String year = calendar.get(Calendar.YEAR)+"";
          String month = calendar.get(Calendar.MONTH)+"";
          String day = calendar.get(Calendar.DAY_OF_MONTH)+"";
          String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
          String min = calendar.get(Calendar.MINUTE)+"";
          String sec = calendar.get(Calendar.SECOND)+"";
          String nameImage=year+month+day+hour+min+sec+"netpood.png";*/

          BitmapFactory.Options options = new BitmapFactory.Options();
          options.inDither = false;
          options.inPreferredConfig = Bitmap.Config.RGB_565;
          Bitmap loadedImage = BitmapFactory.decodeByteArray(data, 0, data.length,options);
          Base.bit=loadedImage;
          Base.rotation=rotation;
         /* File image = new File(Base.getDIR_PICTURE(), nameImage);

          try {
            FileOutputStream outStream = new FileOutputStream(image);
            loadedImage.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
          }catch (FileNotFoundException e) {
               Log.i("FiL","no create");
          }catch (IOException e) {
               Log.i("FiL","no create");
          }

          /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
          loadedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);

             Base.getPic=stream;
             Base.rotation=rotation;*/

          Intent intent = new Intent(Base.getCurrentActivity(), EditCamera.class);
          //intent.putExtra("PIC",nameImage);
          //intent.putExtra("ROR",rotation);
          Base.getCurrentActivity().startActivity(intent);

        } catch (Exception e) {
          e.printStackTrace();
        }

      }
    });
  }

  private void flipCamera() {
    int id = (cameraId == CameraInfo.CAMERA_FACING_BACK ? CameraInfo.CAMERA_FACING_FRONT : CameraInfo.CAMERA_FACING_BACK);
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




  private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
    final double ASPECT_TOLERANCE = 0.1;
    double targetRatio=(double)h / w;

    if (sizes == null) return null;

    Camera.Size optimalSize = null;
    double minDiff = Double.MAX_VALUE;

    int targetHeight = h;

    for (Camera.Size size : sizes) {
      double ratio = (double) size.width / size.height;
      if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
      if (Math.abs(size.height - targetHeight) < minDiff) {
        optimalSize = size;
        minDiff = Math.abs(size.height - targetHeight);
      }
    }

    if (optimalSize == null) {
      minDiff = Double.MAX_VALUE;
      for (Camera.Size size : sizes) {
        if (Math.abs(size.height - targetHeight) < minDiff) {
          optimalSize = size;
          minDiff = Math.abs(size.height - targetHeight);
        }
      }
    }
    return optimalSize;
  }
}
