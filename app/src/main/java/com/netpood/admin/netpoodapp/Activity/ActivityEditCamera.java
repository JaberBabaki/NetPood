package com.netpood.admin.netpoodapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Camera;
import android.net.Uri;
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

import com.netpood.admin.framework.activity.UAppCompatActivity;
import com.netpood.admin.framework.filter.IF1977Filter;
import com.netpood.admin.framework.filter.IFAmaroFilter;
import com.netpood.admin.framework.filter.IFBrannanFilter;
import com.netpood.admin.framework.filter.IFEarlybirdFilter;
import com.netpood.admin.framework.filter.IFHefeFilter;
import com.netpood.admin.framework.filter.IFHudsonFilter;
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
import com.netpood.admin.framework.widget.StartPointSeekBar;
import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.classs.CameraHardWare;
import com.netpood.admin.netpoodapp.classs.CameraHelper;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

/**
 * Created by jaberALU on 01/06/2017.
 */


public class ActivityEditCamera extends UAppCompatActivity {

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

  GPUImageView imgEditPic;
  ImageView imgSelectFilter;
  ImageView imgEdit;

  LinearLayout layFilter;
  LinearLayout layEdit;

  HorizontalScrollView scrollViewFilter;
  LinearLayout scrollViewEdit;

  LinearLayout layBrightness;
  LinearLayout laySeekBar;
  LinearLayout layBackEffect;
  LinearLayout layNextEffect;
  LinearLayout layHeaderEffect;

  ImageView imgMain;

  ImageView captureImage;
  SurfaceView surfaceView;

  StartPointSeekBar sek;
  SeekBar seekbar;

  TextView txtHeaderEffect;
  TextView txtFilter;
  TextView txtEdit;
  TextView txtShowValue;

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

  TextView txtSat;
  TextView txtBri;
  TextView txtCon;

  LinearLayout layCameraa;
  LinearLayout layEditt;
  CameraHardWare cameraCustom;
  Bitmap gpu;
  GPUImage mGPUImage1 = new GPUImage(Base.getContext());
  Bitmap bitNormal;
  Bitmap bitSec;

  SeekBar sekbarBri;
  SeekBar sekbarContrast;
  SeekBar sekbarVig;

  int Bri;
  int Con;
  int Sat;
  int Vig;

  final int CAMERA_CAPTURE = 1;
  final int CROP_PIC = 2;
  private Uri picUri;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.edit_picture);
    tarefControl();



    layBackEffect.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
        Intent intent = new Intent(Base.getCurrentActivity(), ActivityEditCamera.class);
        Base.getCurrentActivity().startActivity(intent);
      }
    });
    layNextEffect.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(Base.getCurrentActivity(), ActivitySendPost.class);
        Base.bit = imgEditPic.getGPUImage().getBitmapWithFilterApplied();
        Base.getCurrentActivity().startActivity(intent);

      }
    });
  }

  public void tarefControl() {
    //tartef header activity
    layBackEffect = (LinearLayout) findViewById(R.id.layBackEffect);
    layNextEffect = (LinearLayout) findViewById(R.id.layNextEffect);
    layHeaderEffect = (LinearLayout) findViewById(R.id.layHeaderEffect);
    txtHeaderEffect = (TextView) findViewById(R.id.txtHeaderEffect);

    // taref kol box payin
    imgSelectFilter = (ImageView) findViewById(R.id.imgSelectFilter);
    imgEdit = (ImageView) findViewById(R.id.imgEdit);
    layFilter = (LinearLayout) findViewById(R.id.layFilter);
    layEdit = (LinearLayout) findViewById(R.id.layEdit);
    scrollViewFilter = (HorizontalScrollView) findViewById(R.id.scrollViewFilter);
    scrollViewEdit = (LinearLayout) findViewById(R.id.lay_edit_ad);
    txtFilter = (TextView) findViewById(R.id.txtFilter);
    txtEdit = (TextView) findViewById(R.id.txtEdit);

    //taref image view filter amadeh
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

//taref textView filter amadeh
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

    sekbarBri = (SeekBar) findViewById(R.id.seekbar_brigthness);
    sekbarContrast = (SeekBar) findViewById(R.id.seekbar_contrast);
    sekbarVig = (SeekBar) findViewById(R.id.seekbar_vignette);

    txtSat = (TextView) findViewById(R.id.txt_satu);
    txtCon = (TextView) findViewById(R.id.txt_con);
    txtBri = (TextView) findViewById(R.id.txt_bri);

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
      Log.i("DAT",""+data);
      if (src != null) {
        imgEditPic = (GPUImageView) findViewById(R.id.imgEditPic);
        imgEditPic.setScaleType(GPUImage.ScaleType.CENTER_INSIDE);
        imgEditPic.setImage(src);


        startEdit();
      }  else {
        finish();
      }

    }
  }
  public void startEdit() {
    setFilterImagethumbnail();
    layFilter.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          selectFilter();

      }
    });
    layEdit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          selectEdit();

      }
    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    cameraCustom.deleteImage();
  }

  public void runFilterBrightness(int i) {
    float valueOfBrightness = (float) (i * 0.005);
    Log.i("BRI", "" + valueOfBrightness);
    Log.i("BRI", "" + valueOfBrightness);
    imgEditPic.setFilter(new GPUImageBrightnessFilter(valueOfBrightness));
}

  public void runFilterContrast(int i) {
    if (i > 50) {
      if (i > 50 && i<=52) {
        imgEditPic.setFilter(new GPUImageContrastFilter(1.1f));
      } else if (i > 52 && i<=55) {
        imgEditPic.setFilter(new GPUImageContrastFilter(1.15f));
      } else if (i > 55 && i<=57) {
        imgEditPic.setFilter(new GPUImageContrastFilter(1.2f));
      } else if (i > 57 && i<=60) {
        imgEditPic.setFilter(new GPUImageContrastFilter(1.25f));
      } else if (i > 60 && i<=62) {
        imgEditPic.setFilter(new GPUImageContrastFilter(1.3f));
      } else if (i > 62 && i<=65) {
        imgEditPic.setFilter(new GPUImageContrastFilter(1.35f));
      } else if (i > 65 && i<=67) {
        imgEditPic.setFilter(new GPUImageContrastFilter(1.4f));
      } else if (i > 67&& i<=70) {
        imgEditPic.setFilter(new GPUImageContrastFilter(1.45f));
      } else if (i > 70 && i<=73) {
        imgEditPic.setFilter(new GPUImageContrastFilter(1.5f));
      } else if (i > 73 && i<=75) {
        imgEditPic.setFilter(new GPUImageContrastFilter(1.55f));
      } else if (i > 75 && i<=80) {
        imgEditPic.setFilter(new GPUImageContrastFilter(1.6f));
      } else if (i > 80 && i<=100) {
        imgEditPic.setFilter(new GPUImageContrastFilter(1.65f));
      }
    } else if (i < 50) {
      if (i < 50 && i>=42) {
        imgEditPic.setFilter(new GPUImageContrastFilter(0.9f));
      } else if (i < 42 && i>=40) {
        imgEditPic.setFilter(new GPUImageContrastFilter(0.85f));
      } else if (i < 40 && i>=37) {
        imgEditPic.setFilter(new GPUImageContrastFilter(0.8f));
      } else if (i < 37 && i>=35) {
        imgEditPic.setFilter(new GPUImageContrastFilter(0.75f));
      } else if (i < 35 && i>=32) {
        imgEditPic.setFilter(new GPUImageContrastFilter(0.7f));
      } else if (i < 32 && i>=30) {
        imgEditPic.setFilter(new GPUImageContrastFilter(0.65f));
      } else if (i < 30 && i>=20) {
        imgEditPic.setFilter(new GPUImageContrastFilter(0.6f));
      } else if (i < 20 && i>=10) {
        imgEditPic.setFilter(new GPUImageContrastFilter(0.55f));
      }
    } else if (i == 50) {
      imgEditPic.setFilter(new GPUImageContrastFilter(1f));
    }
  }
  public void runFilterSaturation(int i) {
    if (i > 50) {
      if (i > 50 && i<=52) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(1.1f));
      } else if (i > 52 && i<=55) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(1.15f));
      } else if (i > 55 && i<=57) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(1.2f));
      } else if (i > 57 && i<=60) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(1.25f));
      } else if (i > 60 && i<=62) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(1.3f));
      } else if (i > 62 && i<=65) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(1.35f));
      } else if (i > 65 && i<=67) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(1.4f));
      } else if (i > 67&& i<=70) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(1.45f));
      } else if (i > 70 && i<=73) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(1.5f));
      } else if (i > 73 && i<=75) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(1.55f));
      } else if (i > 75 && i<=80) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(1.6f));
      } else if (i > 80 && i<=100) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(1.65f));
      }
    } else if (i < 50) {
      if (i < 50 && i>=42) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(0.9f));
      } else if (i < 42 && i>=40) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(0.85f));
      } else if (i < 40 && i>=37) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(0.8f));
      } else if (i < 37 && i>=35) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(0.75f));
      } else if (i < 35 && i>=32) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(0.7f));
      } else if (i < 32 && i>=30) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(0.65f));
      } else if (i < 30 && i>=20) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(0.6f));
      } else if (i < 20 && i>=10) {
        imgEditPic.setFilter(new GPUImageSaturationFilter(0.55f));
      }
    } else if (i == 50) {
      imgEditPic.setFilter(new GPUImageSaturationFilter(1f));
    }
  }

  public void selectEdit() {

    // vaghti ke edit entekhab shod clik
    int t = Color.parseColor("#000000");
    int t2 = Color.parseColor("#ffffff");
    imgSelectFilter.setBackgroundColor(t2);
    imgEdit.setBackgroundColor(t);
    scrollViewFilter.setVisibility(View.GONE);
    scrollViewEdit.setVisibility(View.VISIBLE);
    sekbarBri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Log.i("FIL", "" + i);
        txtBri.setText(""+i);
        runFilterBrightness(i);
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });

    sekbarContrast.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Log.i("FIL", "" + i);
        txtCon.setText(""+i);
          runFilterContrast(i);
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
    sekbarVig.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Log.i("FIL", "" + i);
        txtSat.setText(""+i);
        runFilterSaturation(i);
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });

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

  public void buttonClicked(View v) {

    if (v.getId() == R.id.normal) {
      txtColorNoSelect();
      txtNormal.setTextColor(Color.parseColor("#000000"));
      imgEditPic.setImage(src);
      Log.i("BBB", "B  " + src.equals(Base.bit));
      Log.i("BBB", "b  " + src.equals(bitNormal));
      //imgEditPic.setFilter(new GPUImageFilter());

    } else if (v.getId() == R.id.amaro) {
      //imgEditPic.setFilter(new IFAmaroFilter(Base.getContext()));
      saveBitmap(new IFAmaroFilter(Base.getContext()), 1);
      txtAmaro.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.brannan) {
      //imgEditPic.setFilter(new IFBrannanFilter(Base.getContext()));
      saveBitmap(new IFBrannanFilter(Base.getContext()), 1);
      txtBrannan.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.earlybird) {
      //imgEditPic.setFilter(new IFEarlybirdFilter(Base.getContext()));
      saveBitmap(new IFEarlybirdFilter(Base.getContext()), 1);
      txtEarlybird.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.hefe) {
      saveBitmap(new IFHefeFilter(Base.getContext()), 1);
      txtHefe.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.hudson) {
      saveBitmap(new IFHudsonFilter(Base.getContext()), 1);
      txtHudson.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.lnkwell) {
      saveBitmap(new IFInkwellFilter(Base.getContext()), 1);
      txtLnkwell.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.lomo) {
      saveBitmap(new IFLomoFilter(Base.getContext()), 1);
      txtLomo.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.lordKelvin) {
      saveBitmap(new IFLordKelvinFilter(Base.getContext()), 1);
      txtLordKelvin.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.nashvill) {
      saveBitmap(new IFNashvilleFilter(Base.getContext()), 1);
      txtNashvill.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.rise) {
      saveBitmap(new IFRiseFilter(Base.getContext()), 1);
      txtRise.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.sierra) {
      saveBitmap(new IFSierraFilter(Base.getContext()), 1);
      txtSierra.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.sutro)
      saveBitmap(new IFSutroFilter(Base.getContext()), 1);
    else if (v.getId() == R.id.toaster) {
      saveBitmap(new IFToasterFilter(Base.getContext()), 1);
      txtToaster.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.valencia) {
      saveBitmap(new IFValenciaFilter(Base.getContext()), 1);
      txtValencia.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.walden) {
      saveBitmap(new IFWaldenFilter(Base.getContext()), 1);
      txtWalden.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.xproll) {
      saveBitmap(new IFXprollFilter(Base.getContext()), 1);
      txtXproll.setTextColor(Color.parseColor("#000000"));
    } else if (v.getId() == R.id.n1977) {
      saveBitmap(new IF1977Filter(Base.getContext()), 1);
      txtN1977.setTextColor(Color.parseColor("#000000"));
    }


  }


  private void saveBitmap(GPUImageFilter imgFilter, int i) {


    mGPUImage1.deleteImage();
    mGPUImage1.setImage(src);
    mGPUImage1.setFilter(imgFilter);
    Bitmap mFilteredBitmap = mGPUImage1.getBitmapWithFilterApplied();
    imgEditPic.setImage(mFilteredBitmap);

    //imgEditPic.setFilter(imgFilter);
    if (i == 1) {
      txtColorNoSelect();
    }

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


  public void setFilterImagethumbnail() {

    int nh = (int) (src.getHeight() * (512.0 / src.getWidth()));
    Bitmap scaled = Bitmap.createScaledBitmap(src, 512, nh, true);
    bitNormal = Bitmap.createScaledBitmap(src, src.getWidth(), src.getHeight(), true);
    bitSec = src.copy(src.getConfig(), true);

    imgNormal.setImageBitmap(scaled);

    GPUImage mGPUImage = new GPUImage(this);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFAmaroFilter(this));
    Bitmap bp = mGPUImage.getBitmapWithFilterApplied(scaled);
    imgAmaro.setImageBitmap(bp);
    // imgAmaro.setImageResource(R.drawable.a1);

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
  }


}
