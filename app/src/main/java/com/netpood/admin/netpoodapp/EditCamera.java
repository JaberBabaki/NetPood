package com.netpood.admin.netpoodapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHighlightShadowFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

/**
 * Created by jaberALU on 01/06/2017.
 */


public class EditCamera extends UAppCompatActivity {


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
  HorizontalScrollView scrollViewEdit;

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

  LinearLayout layCameraa;
  LinearLayout layEditt;

  CameraHardWare cameraCustom;
  Bitmap gpu;
  GPUImage mGPUImage1 = new GPUImage(Base.getContext());

  Bitmap bitNormal;
  Bitmap bitSec;


  int Bri;
  int Con;
  int Sat;
  int Vig;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.edit_picture);
    setUpHeader();

    layBackEffect.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
        Intent intent = new Intent(Base.getCurrentActivity(), EditCamera.class);
        Base.getCurrentActivity().startActivity(intent);
      }
    });
    layNextEffect.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(Base.getCurrentActivity(), SendPost.class);
        Base.bit=imgEditPic.getGPUImage().getBitmapWithFilterApplied();
        Base.getCurrentActivity().startActivity(intent);
      }
    });
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
      Base.bit = src;
      if (src != null) {
        imgEditPic = (GPUImageView) findViewById(R.id.imgEditPic);
        imgEditPic.setScaleType(GPUImage.ScaleType.CENTER_INSIDE);
        imgEditPic.setImage(src);
        //Base.bit = ((BitmapDrawable) imgEditPic.getDrawable()).getBitmap();
        startEdit();
      } else {
        finish();
      }
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    cameraCustom.deleteImage();
  }


  public void startEdit() {
    setUpBoxEdit();

    filterControl();
    setImageThum();


    sek.setProgress(0);
    sek.setOnSeekBarChangeListener(new StartPointSeekBar.OnSeekBarChangeListener() {
      @Override
      public void onOnSeekBarValueChange(StartPointSeekBar bar, double value) {


        if (txtHeaderEffect.getText().equals("Brightness")) {
          runFilterBrightness((int) value, 0);
        } else if (txtHeaderEffect.getText().equals("Contrast")) {
          runFilterContrast((int) value, 0);
        } else if (txtHeaderEffect.getText().equals("Saturation")) {
          runFilterSaturation((int) value, 0);
        } else if (txtHeaderEffect.getText().equals("Vignette")) {
          runFilterVignette((int) value, 0);
        }

        txtShowValue.setText("" + (int) value);
        Log.i("PPP", "" + value);
      }
    });


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
          doneSeelectEffect();
        } else {
          selectEdit();
        }
      }
    });

  }

  public void runFilterBrightness(int i, int set) {
    float valueOfBrightness = (float) (i * 0.01);
    Log.i("BRI", "" + valueOfBrightness);
    Log.i("BRI", "" + valueOfBrightness);
    imgEditPic.setFilter(new GPUImageBrightnessFilter(valueOfBrightness));
  }

  public void runFilterContrast(int i, int set) {
    float valueOfContrastPlus = (float) (i * 0.04);
    float valueOfContrastMines = (float) (i * 0.01) + 1;
    Log.i("CON", "" + valueOfContrastPlus);
    if (set == 0) {
      if (valueOfContrastPlus > 1) {
        imgEditPic.setFilter(new GPUImageContrastFilter(valueOfContrastPlus));
      } else if (i < 0) {
        imgEditPic.setFilter(new GPUImageContrastFilter(valueOfContrastMines));
      }
    }
  }

  public void runFilterSaturation(int i, int set) {
    float valueSaturationFilter = (float) (i * 0.02);
    Log.i("SAT", "" + valueSaturationFilter);
    imgEditPic.setFilter(new GPUImageSaturationFilter(valueSaturationFilter));
  }
  public void runFilterVignette(int i, int set) {
    float valueVignetteFilter = (float) (i * 0.02);
    Log.i("SAT", "" + valueVignetteFilter);
    mGPUImage1.setImage(src);
    mGPUImage1.setFilter(new GPUImageHighlightShadowFilter());
    Bitmap mFilteredBitmap = mGPUImage1.getBitmapWithFilterApplied(src);
    imgEditPic.setImage(mFilteredBitmap);
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

    Log.i("CANS", "" + src.equals(bitNormal));
    Log.i("CANS", "" + src.equals(bitSec));

    //imgEditPic.setImage(bitNormal);

  }

  public void doneSeelectEffect() {

    if (txtHeaderEffect.getText().equals("Brightness")) {
      Log.i("DBR", "" + Bri);
      Bri = Integer.parseInt(txtShowValue.getText().toString());// (int) (valueOfBrightness * 100);
    } else if (txtHeaderEffect.getText().equals("Contrast")) {
      Log.i("DBR", "" + Con);
      Con = Integer.parseInt(txtShowValue.getText().toString());
    } else if (txtHeaderEffect.getText().equals("Saturation")) {
      Log.i("DBR", "" + Sat);
      Sat = Integer.parseInt(txtShowValue.getText().toString());
    } else if (txtHeaderEffect.getText().equals("Vignette")) {
      Log.i("DBR", "" + Vig);
      Vig = Integer.parseInt(txtShowValue.getText().toString());
    }
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

    //Base.bit= imgEditPic.getGPUImage().getBitmapWithFilterApplied();
    //imgEditPic.setImage(Base.bit);

    src=imgEditPic.getGPUImage().getBitmapWithFilterApplied();
    imgEditPic.setFilter(new  GPUImageFilter());
    imgEditPic.setImage(src);

  }

  public void readyToSeekEffect(View v) {

    // vaghti ke roy yeki az edit ha clik shod v bayad seekbar load besheh
    //imgEditPic.setImage(src);

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
    if (txtHeaderEffect.getText().equals("Brightness")) {
      Log.i("SEE", "B  " + Bri);
      txtShowValue.setText(Bri + "");
      sek.setProgress(Bri);
    } else if (txtHeaderEffect.getText().equals("Contrast")) {
      txtShowValue.setText(Con + "");
      sek.setProgress(Con);
    } else if (txtHeaderEffect.getText().equals("Saturation")) {
      txtShowValue.setText(Sat + "");
      sek.setProgress(Sat);
    }
    bitSec = Bitmap.createBitmap(src);

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

    /*
    GPUImage gpuImage=new GPUImage(Base.getContext());
    gpuImage.setImage(src);
    gpuImage.setFilter(imgFilter);
    gpuImage.requestRender();
    imgEditPic.setImage(gpuImage.getBitmapWithFilterApplied());
    gpuImage.deleteImage();
    */

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


  public void filterControl() {
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
  }

  public void setImageThum() {

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

  public void setUpHeader() {
    laySeekBar = (LinearLayout) findViewById(R.id.laySeekBar);
    layBackEffect = (LinearLayout) findViewById(R.id.layBackEffect);
    layBrightness = (LinearLayout) findViewById(R.id.lay_brightness);
    layNextEffect = (LinearLayout) findViewById(R.id.layNextEffect);
    layHeaderEffect = (LinearLayout) findViewById(R.id.layHeaderEffect);
    txtHeaderEffect = (TextView) findViewById(R.id.txtHeaderEffect);
  }

  public void setUpBoxEdit() {
    imgSelectFilter = (ImageView) findViewById(R.id.imgSelectFilter);
    imgEdit = (ImageView) findViewById(R.id.imgEdit);
    layFilter = (LinearLayout) findViewById(R.id.layFilter);
    layEdit = (LinearLayout) findViewById(R.id.layEdit);
    scrollViewFilter = (HorizontalScrollView) findViewById(R.id.scrollViewFilter);
    scrollViewEdit = (HorizontalScrollView) findViewById(R.id.scrollViewEdit);
    txtFilter = (TextView) findViewById(R.id.txtFilter);
    txtEdit = (TextView) findViewById(R.id.txtEdit);
    sek = (StartPointSeekBar) findViewById(R.id.seek_midle);
    seekbar = (SeekBar) findViewById(R.id.sek);
    txtShowValue = (TextView) findViewById(R.id.txt_seek_value);

  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    Base.title=null;
    Base.tag=null;
    Base.matn=null;
  }
}
