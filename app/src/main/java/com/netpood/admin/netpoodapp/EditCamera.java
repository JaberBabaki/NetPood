package com.netpood.admin.netpoodapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.netpood.admin.framework.activity.UAppCompatActivity;
import com.netpood.admin.framework.filter.IF1977Filter;
import com.netpood.admin.framework.filter.IFAmaroFilter;
import com.netpood.admin.framework.filter.IFBrannanFilter;
import com.netpood.admin.framework.filter.IFEarlybirdFilter;
import com.netpood.admin.framework.filter.IFHefeFilter;
import com.netpood.admin.framework.filter.IFHudsonFilter;
import com.netpood.admin.framework.filter.IFImageFilter;
import com.netpood.admin.framework.filter.IFInkwellFilter;
import com.netpood.admin.framework.filter.IFLomoFilter;
import com.netpood.admin.framework.filter.IFLordKelvinFilter;
import com.netpood.admin.framework.filter.IFNashvilleFilter;
import com.netpood.admin.framework.filter.IFRiseFilter;
import com.netpood.admin.framework.filter.IFSierraFilter;
import com.netpood.admin.framework.filter.IFSutroFilter;
import com.netpood.admin.framework.filter.IFToasterFilter;
import com.netpood.admin.framework.filter.IFValenciaFilter;
import com.netpood.admin.framework.filter.IFWaldenFilter;
import com.netpood.admin.framework.filter.IFXprollFilter;
import com.netpood.admin.framework.widget.ImageFilters;

import jp.co.cyberagent.android.gpuimage.GPUImage;

/**
 * Created by jaberALU on 01/06/2017.
 */


public class EditCamera extends UAppCompatActivity{


  private CameraHelper mImageSurfaceView;
  private Camera camera;

  private FrameLayout cameraPreviewLayout;
  private ImageView capturedImageHolder;
  private ImageView flipCamera;
  private ImageView flash;
  private boolean on = false;


  Bitmap src;
  String nameImage;
  int rotation;

  ImageView imgEditPic;
  ImageView imgSelectFilter;
  ImageView imgEdit;

  LinearLayout layFilter;
  LinearLayout layEdit;

  HorizontalScrollView scrollViewFilter;
  HorizontalScrollView scrollViewEdit;

  LinearLayout layBrightness;
  LinearLayout laySeekBar;
  LinearLayout layBackEffect;
  LinearLayout layNextEffect;
  LinearLayout layHeaderEffect;

  ImageView imgMain;

  ImageView captureImage;
  SurfaceView surfaceView;

  SeekBar sek;

  TextView txtHeaderEffect;
  TextView txtFilter;
  TextView txtEdit;


  ImageView imgNormal;
  ImageView imgAmaro;
  ImageView imgBrannan;
  ImageView imgEarlybird;
  ImageView imgHefe;
  ImageView imgHudson;
  ImageView imgLnkwell;
  ImageView imgLomo;
  ImageView imgLordKelvin;
  ImageView imgNashvill;
  ImageView imgRise;
  ImageView imgSierra;
  ImageView imgSutro;
  ImageView imgToaster;
  ImageView imgValencia;
  ImageView imgWalden;
  ImageView imgXproll;
  ImageView imgN1977;

  TextView txtNormal;
  TextView txtAmaro;
  TextView txtBrannan;
  TextView txtEarlybird;
  TextView txtHefe;
  TextView txtHudson;
  TextView txtLnkwell;
  TextView txtLomo;
  TextView txtLordKelvin;
  TextView txtNashvill;
  TextView txtRise;
  TextView txtSierra;
  TextView txtSutro;
  TextView txtToaster;
  TextView txtValencia;
  TextView txtWalden;
  TextView txtXproll;
  TextView txtN1977;

  LinearLayout layCameraa;
  LinearLayout layEditt;

  CameraHardWare cameraCustom;
  Bitmap gpu;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.edit_picture);

  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    cameraCustom = new CameraHardWare.Builder()
      .setDirectory("pics")
      .setName("ali_" + System.currentTimeMillis())
      .setImageFormat(CameraHardWare.IMAGE_JPEG)
      .setCompression(75)
      .setImageHeight(1000)
      .build(this);
    try {
      cameraCustom.takePicture();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == CameraHardWare.REQUEST_TAKE_PHOTO) {
      src = cameraCustom.getCameraBitmap();
      if (src != null) {

        imgEditPic = (ImageView) findViewById(R.id.imgEditPic);
        imgEditPic.setImageBitmap(src);
        Base.bit = ((BitmapDrawable) imgEditPic.getDrawable()).getBitmap();

        startEdit();
      } else {
        Toast.makeText(this.getApplicationContext(), "Picture not taken!", Toast.LENGTH_SHORT).show();
      }
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    cameraCustom.deleteImage();
  }


  public void startEdit() {
    // imgEditPic=(ImageView) findViewById(R.id.imgEditPic);
    imgSelectFilter = (ImageView) findViewById(R.id.imgSelectFilter);
    imgEdit = (ImageView) findViewById(R.id.imgEdit);

    layFilter = (LinearLayout) findViewById(R.id.layFilter);
    layEdit = (LinearLayout) findViewById(R.id.layEdit);

    scrollViewFilter = (HorizontalScrollView) findViewById(R.id.scrollViewFilter);
    scrollViewEdit = (HorizontalScrollView) findViewById(R.id.scrollViewEdit);

    laySeekBar = (LinearLayout) findViewById(R.id.laySeekBar);
    layBackEffect = (LinearLayout) findViewById(R.id.layBackEffect);
    layBrightness = (LinearLayout) findViewById(R.id.lay_brightness);
    layNextEffect = (LinearLayout) findViewById(R.id.layNextEffect);
    layHeaderEffect = (LinearLayout) findViewById(R.id.layHeaderEffect);

    sek = (SeekBar) findViewById(R.id.sek);

    txtHeaderEffect = (TextView) findViewById(R.id.txtHeaderEffect);
    txtFilter = (TextView) findViewById(R.id.txtFilter);
    txtEdit = (TextView) findViewById(R.id.txtEdit);

    imgNormal = (ImageView) findViewById(R.id.normal);
    imgAmaro = (ImageView) findViewById(R.id.amaro);
    imgBrannan = (ImageView) findViewById(R.id.brannan);
    imgEarlybird = (ImageView) findViewById(R.id.earlybird);
    imgHefe = (ImageView) findViewById(R.id.hefe);
    imgHudson = (ImageView) findViewById(R.id.hudson);
    imgLnkwell = (ImageView) findViewById(R.id.lnkwell);
    imgLomo = (ImageView) findViewById(R.id.lomo);
    imgLordKelvin = (ImageView) findViewById(R.id.lordKelvin);
    imgNashvill = (ImageView) findViewById(R.id.nashvill);
    imgRise = (ImageView) findViewById(R.id.rise);
    imgSierra = (ImageView) findViewById(R.id.sierra);
    imgSutro = (ImageView) findViewById(R.id.sutro);
    imgToaster = (ImageView) findViewById(R.id.toaster);
    imgValencia = (ImageView) findViewById(R.id.valencia);
    imgWalden = (ImageView) findViewById(R.id.walden);
    imgXproll = (ImageView) findViewById(R.id.xproll);
    imgN1977 = (ImageView) findViewById(R.id.n1977);


    txtNormal = (TextView) findViewById(R.id.txt_normal);
    txtAmaro = (TextView) findViewById(R.id.txt_amaro);
    txtBrannan = (TextView) findViewById(R.id.txt_brannam);
    txtEarlybird = (TextView) findViewById(R.id.txt_earybird);
    txtHefe = (TextView) findViewById(R.id.txt_hefe);
    txtHudson = (TextView) findViewById(R.id.txt_hudson);
    txtLnkwell = (TextView) findViewById(R.id.txt_lnkwell);
    txtLomo = (TextView) findViewById(R.id.txt_lomo);
    txtLordKelvin = (TextView) findViewById(R.id.txt_lordKelvin);
    txtNashvill = (TextView) findViewById(R.id.txt_nashvill);
    txtRise = (TextView) findViewById(R.id.txt_rise);
    txtSierra = (TextView) findViewById(R.id.txt_sierra);
    txtSutro = (TextView) findViewById(R.id.txt_sutro);
    txtToaster = (TextView) findViewById(R.id.txt_toaster);
    txtValencia = (TextView) findViewById(R.id.txt_valencia);
    txtWalden = (TextView) findViewById(R.id.txt_walden);
    txtXproll = (TextView) findViewById(R.id.txt_xproll);
    txtN1977 = (TextView) findViewById(R.id.txt_n1977);


    Log.i("ELOG", "jaber" + Base.getDIR_PICTURE() + "/" + nameImage);


    int nh = (int) (src.getHeight() * (512.0 / src.getWidth()));
    Bitmap scaled = Bitmap.createScaledBitmap(src, 512, nh, true);

    imgNormal.setImageBitmap(scaled);

    GPUImage mGPUImage = new GPUImage(this);
    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFAmaroFilter(this));
    Bitmap bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgAmaro.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFBrannanFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgBrannan.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFEarlybirdFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgEarlybird.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFHefeFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgHefe.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFHudsonFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgHudson.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFInkwellFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgLnkwell.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFLomoFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgLomo.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFLordKelvinFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgLordKelvin.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFNashvilleFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgNashvill.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFRiseFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgRise.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFSierraFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgSierra.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFSutroFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgSutro.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFToasterFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgToaster.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFValenciaFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgValencia.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFWaldenFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgWalden.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFXprollFilter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgXproll.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IF1977Filter(this));
    bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgN1977.setImageBitmap(bp);


    layFilter.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (txtFilter.getText().equals("Cancel")) {
          cancelSeekEffect();
        } else {
          selectFilter();
        }


      }
    });
    layEdit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (txtEdit.getText().equals("Done")) {
          cancelSeekEffect();
        } else {
          selectEdit();
        }


      }
    });


    final ImageFilters imgFilter = new ImageFilters();
    layBrightness.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        sek.setProgress(0);

        scrollViewEdit.setVisibility(View.GONE);
        laySeekBar.setVisibility(View.VISIBLE);

        layBackEffect.setVisibility(View.GONE);
        layNextEffect.setVisibility(View.GONE);
        layHeaderEffect.setVisibility(View.VISIBLE);

        txtEdit.setText("Done");
        txtFilter.setText("Cancel");
        txtHeaderEffect.setText("Brigthness");

        int t2 = Color.parseColor("#ffffff");
        imgSelectFilter.setBackgroundColor(t2);
        imgEdit.setBackgroundColor(t2);

        imgFilter.applyBrightnessEffect(src, 100);

      }
    });

/*
    imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0));
    sek.setProgress(50);
    sek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
        if (i >= 50) {
          if (i == 50) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0));
          }
          if (i > 50 && i <= 55) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.1));
          }
          if (i > 55 && i <= 60) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.2));
          }
          if (i > 60 && i <= 65) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.3));
          }
          if (i > 65 && i <= 70) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.4));
          }
          if (i > 70 && i <= 75) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.5));
          }
          if (i > 75 && i <= 80) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.6));
          }
          if (i > 80 && i >= 85) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.7));
          }
          if (i > 85 && i >= 90) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.8));
          }
          if (i > 90 && i >= 95) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.9));
          }
          if (i > 95 && i >= 100) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 1));
          }

        } else {
          if (i == 50) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0));
          }
          if (i < 50 && i >= 45) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.1));
          }
          if (i < 45 && i >= 40) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.2));
          }
          if (i < 40 && i >= 35) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.3));
          }
          if (i < 35 && i >= 30) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.4));
          }
          if (i < 30 && i >= 25) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.5));
          }
          if (i < 25 && i >= 20) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.6));
          }
          if (i < 20 && i >= 15) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.7));
          }
          if (i < 15 && i >= 10) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.8));
          }
          if (i < 10 && i >= 5) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.9));
          }
          if (i < 5 && i >= 0) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -1));
          }

        }

        Log.i("TAG", "" + i);
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
*/
  }

  public void editClicked(View v) {
    readyToSeekEffect(v);
  }


  public void selectEdit() {

    // vaghti ke edit entekhab shod clik

    int t = Color.parseColor("#000000");
    int t2 = Color.parseColor("#ffffff");
    imgSelectFilter.setBackgroundColor(t2);
    imgEdit.setBackgroundColor(t);
    scrollViewFilter.setVisibility(View.GONE);
    scrollViewEdit.setVisibility(View.VISIBLE);

  }

  public void selectFilter() {

    // vaghti ke filter entekhab shod clik

    int t = Color.parseColor("#000000");
    int t2 = Color.parseColor("#ffffff");
    imgSelectFilter.setBackgroundColor(t);
    imgEdit.setBackgroundColor(t2);
    scrollViewEdit.setVisibility(View.GONE);
    scrollViewFilter.setVisibility(View.VISIBLE);

  }
  public void cancelSeekEffect() {

    // vaghti ke cancel clik shod v bargardim be EDIT

    scrollViewEdit.setVisibility(View.VISIBLE);
    laySeekBar.setVisibility(View.GONE);

    txtEdit.setText("EDIT");
    txtFilter.setText("FILTER");

    layBackEffect.setVisibility(View.VISIBLE);
    layNextEffect.setVisibility(View.VISIBLE);
    layHeaderEffect.setVisibility(View.GONE);

    int t2 = Color.parseColor("#ffffff");
    int t1 = Color.parseColor("#000000");
    imgSelectFilter.setBackgroundColor(t2);
    imgEdit.setBackgroundColor(t1);

  }

  public void readyToSeekEffect(View v) {

    // vaghti ke roy yeki az edit ha clik shod v bayad seekbar load besheh

    scrollViewEdit.setVisibility(View.GONE);
    laySeekBar.setVisibility(View.VISIBLE);

    txtEdit.setText("Done");
    txtFilter.setText("Cancel");

    layBackEffect.setVisibility(View.GONE);
    layNextEffect.setVisibility(View.GONE);
    layHeaderEffect.setVisibility(View.VISIBLE);

    int t2 = Color.parseColor("#ffffff");
    imgSelectFilter.setBackgroundColor(t2);
    imgEdit.setBackgroundColor(t2);

    TextView b = null;
    LinearLayout ll = (LinearLayout) v;
    final int childCount = ll.getChildCount();
    for (int i = 0; i < childCount; i++) {
      View ch = ll.getChildAt(i);
      if (ch instanceof TextView) {
        b = (TextView) ch;
      }
    }
    txtHeaderEffect.setText(b.getText());

  }

  public void buttonClicked(View v) {

    if (v.getId() == R.id.normal) {
      txtNormal.setTextColor(Color.parseColor("#000000"));
      imgEditPic.setImageBitmap(Base.bit);
    } else if (v.getId() == R.id.amaro) {
      saveBitmap(new IFAmaroFilter(Base.getContext()));
      txtAmaro.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.brannan) {
      saveBitmap(new IFBrannanFilter(Base.getContext()));
      txtBrannan.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.earlybird) {
      saveBitmap(new IFEarlybirdFilter(Base.getContext()));
      txtEarlybird.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.hefe) {
      saveBitmap(new IFHefeFilter(Base.getContext()));
      txtHefe.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.hudson) {
      saveBitmap(new IFHudsonFilter(Base.getContext()));
      txtHudson.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.lnkwell) {
      saveBitmap(new IFInkwellFilter(Base.getContext()));
      txtLnkwell.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.lomo) {
      saveBitmap(new IFLomoFilter(Base.getContext()));
      txtLomo.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.lordKelvin) {
      saveBitmap(new IFLordKelvinFilter(Base.getContext()));
      txtLordKelvin.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.nashvill) {
      saveBitmap(new IFNashvilleFilter(Base.getContext()));
      txtNashvill.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.rise) {
      saveBitmap(new IFRiseFilter(Base.getContext()));
      txtRise.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.sierra) {
      saveBitmap(new IFSierraFilter(Base.getContext()));
      txtSierra.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.sutro)
      saveBitmap(new IFSutroFilter(Base.getContext()));
    else if (v.getId() == R.id.toaster) {
      saveBitmap(new IFToasterFilter(Base.getContext()));
      txtToaster.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.valencia) {
      saveBitmap(new IFValenciaFilter(Base.getContext()));
      txtValencia.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.walden) {
      saveBitmap(new IFWaldenFilter(Base.getContext()));
      txtWalden.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.xproll) {
      saveBitmap(new IFXprollFilter(Base.getContext()));
      txtXproll.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.n1977) {
      saveBitmap(new IF1977Filter(Base.getContext()));
      txtN1977.setTextColor(Color.parseColor("#000000"));
    }


  }


  private void saveBitmap(IFImageFilter imgFilter) {
    GPUImage mGPUImage = new GPUImage(this);
    mGPUImage.deleteImage();
    mGPUImage.setFilter(imgFilter);
    Bitmap bp = mGPUImage.getBitmapWithFilterApplied(src);
    // imgAmaro.setImageBitmap(bp);
    imgEditPic.setImageBitmap(bp);
    txtColorNoSelect();
  }

  public void txtColorNoSelect() {

    txtNormal.setTextColor(Color.parseColor("#ADADAD"));
    txtAmaro.setTextColor(Color.parseColor("#ADADAD"));
    txtBrannan.setTextColor(Color.parseColor("#ADADAD"));
    txtEarlybird.setTextColor(Color.parseColor("#ADADAD"));
    txtHefe.setTextColor(Color.parseColor("#ADADAD"));
    txtHudson.setTextColor(Color.parseColor("#ADADAD"));
    txtLnkwell.setTextColor(Color.parseColor("#ADADAD"));
    txtLomo.setTextColor(Color.parseColor("#ADADAD"));
    txtLordKelvin.setTextColor(Color.parseColor("#ADADAD"));
    txtNashvill.setTextColor(Color.parseColor("#ADADAD"));
    txtRise.setTextColor(Color.parseColor("#ADADAD"));
    txtSierra.setTextColor(Color.parseColor("#ADADAD"));
    txtSutro.setTextColor(Color.parseColor("#ADADAD"));
    txtToaster.setTextColor(Color.parseColor("#ADADAD"));
    txtValencia.setTextColor(Color.parseColor("#ADADAD"));
    txtWalden.setTextColor(Color.parseColor("#ADADAD"));
    txtXproll.setTextColor(Color.parseColor("#ADADAD"));
    txtN1977.setTextColor(Color.parseColor("#ADADAD"));

  }

}
