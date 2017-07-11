package com.netpood.admin.netpoodapp;

import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

public class CameraHelper extends SurfaceView implements SurfaceHolder.Callback {

  private Camera camera;
  private SurfaceHolder surfaceHolder;
  private List<Camera.Size> mSupportedPreviewSizes;
  private Camera.Size mPreviewSize;

  private boolean flipCamera;
  private boolean flash;


  private int rotation;
  private int cameraId;


  public CameraHelper(Camera mcamera) {
    super(Base.getContext());

    camera = mcamera;
    Log.i("LLL", "ok2" + camera);
    // camera.release();
    //releaseCamera(CameraNew.CameraInfo.CAMERA_FACING_BACK);

    mSupportedPreviewSizes = camera.getParameters().getSupportedPreviewSizes();
    for (Camera.Size str : mSupportedPreviewSizes) {
      this.surfaceHolder = getHolder();
    }
    this.surfaceHolder.addCallback(this);

    //Log.i("LLL",""+Base.getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH));

    if (Camera.getNumberOfCameras() > 1) {
      flipCamera = true;
    } else {

    }

    if (Base.getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
      flash = true;
    }

  }


  public boolean getFlipCamera() {
    return flipCamera;
  }

  public boolean getFlash() {
    return flash;
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {

  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    if (surfaceHolder.getSurface() == null) {
      return;
    }
    try {
      camera.stopPreview();
    } catch (Exception e) {
    }
    try {
      setParamCamera(false);


    } catch (Exception e) {
      Log.i("LLL", "ok2" + e.toString());

    }


  }


  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    if(camera != null){
      camera.stopPreview();
      camera.setPreviewCallback(null);
      camera.release();
    }


  }


  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    final int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
    final int height = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);

    if (mSupportedPreviewSizes != null) {
      mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, width, height);
    }

    if (mPreviewSize != null) {
      float ratio;
      if (mPreviewSize.height >= mPreviewSize.width)
        ratio = (float) mPreviewSize.height / (float) mPreviewSize.width;
      else
        ratio = (float) mPreviewSize.width / (float) mPreviewSize.height;

      // One of these methods should be used, second method squishes preview slightly
      setMeasuredDimension(width, (int) (width * ratio));
      //        setMeasuredDimension((int) (width * ratio), height);
    }
  }

  private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
    final double ASPECT_TOLERANCE = 0.1;
    double targetRatio = (double) h / w;

    if (sizes == null)
      return null;

    Camera.Size optimalSize = null;
    double minDiff = Double.MAX_VALUE;

    int targetHeight = h;

    for (Camera.Size size : sizes) {
      double ratio = (double) size.height / size.width;
      if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
        continue;

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


  public void setParamCamera(boolean on) {
    try {
      Camera.Parameters parameters = camera.getParameters();
      parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);

      Base.w1 = mPreviewSize.width;
      Base.h1 = mPreviewSize.height;


      parameters.setPictureSize(mPreviewSize.width, mPreviewSize.height);
      //parameters.setRotation(90);
      parameters.setJpegQuality(100);
      parameters.setPictureFormat(ImageFormat.JPEG);
      if (on) {
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
      } else {
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
      }
      parameters.setFocusMode("continuous-picture");

      if (parameters.getSupportedFocusModes().contains("continuous-picture")) {
        parameters.setFocusMode("continuous-picture");
        camera.setParameters(parameters);
      } else {
      }

      camera.setParameters(parameters);
      camera.setDisplayOrientation(90);
      camera.setPreviewDisplay(surfaceHolder);
      camera.startPreview();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}



