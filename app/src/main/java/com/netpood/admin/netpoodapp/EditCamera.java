package com.netpood.admin.netpoodapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
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

import java.io.File;
import java.io.FileInputStream;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

/**
 * Created by jaberALU on 01/06/2017.
 */


public class EditCamera extends UAppCompatActivity {

  private Ui ui;
  private Bitmap  src;
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

  ImageView imgMain ;

  ImageView flash;
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
  public class Ui {

  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ui = new Ui();
    new Founder(this)
      .requestFeatures()
      .noTitlebar()
      .noActionbar()
      .statusBar()
      .contentView(R.layout.edit_picture)
      .extractUi(ui)
      .build();

   /* Bundle extras = getIntent().getExtras();
    byte[] data1 = new byte[0];
    rotation = 0;
    nameImage = "";
    if (extras != null) {
      nameImage = extras.getString("PIC");
      rotation = extras.getInt("ROR");
    }*/

    imgEditPic=(GPUImageView)findViewById(R.id.imgEditPic);
    // imgEditPic=(ImageView) findViewById(R.id.imgEditPic);
    imgSelectFilter=(ImageView ) findViewById(R.id.imgSelectFilter);
    imgEdit=(ImageView )findViewById(R.id.imgEdit);

    layFilter=(LinearLayout ) findViewById(R.id.layFilter);
    layEdit=(LinearLayout ) findViewById(R.id.layEdit);

    scrollViewFilter=(HorizontalScrollView) findViewById(R.id.scrollViewFilter);
    scrollViewEdit=(HorizontalScrollView) findViewById(R.id.scrollViewEdit);

    laySeekBar=(LinearLayout) findViewById(R.id.laySeekBar);
    layBackEffect=(LinearLayout) findViewById(R.id.layBackEffect);
    layBrightness=(LinearLayout)findViewById(R.id.layBrightness);
    layNextEffect=(LinearLayout) findViewById(R.id.layNextEffect);
    layHeaderEffect=(LinearLayout) findViewById(R.id.layHeaderEffect);

    //imgMain =(ImageView) findViewById(R.id.imgMain);

    flash=(ImageView) findViewById(R.id.flash);
    captureImage=(ImageView) findViewById(R.id.captureImage);
    surfaceView=(SurfaceView) findViewById(R.id.surfaceView);

    sek=(SeekBar) findViewById(R.id.sek);

    txtHeaderEffect=(TextView)findViewById(R.id.txtHeaderEffect);
    txtFilter=(TextView) findViewById(R.id.txtFilter);
    txtEdit=(TextView) findViewById(R.id.txtEdit);

    imgNormal=(ImageView) findViewById(R.id.normal);
    imgAmaro=(ImageView) findViewById(R.id.amaro);
    imgBrannan=(ImageView) findViewById(R.id.brannan);
    imgEarlybird=(ImageView) findViewById(R.id.earlybird);
    imgHefe=(ImageView) findViewById(R.id.hefe);
    imgHudson=(ImageView) findViewById(R.id.hudson);
    imgLnkwell=(ImageView) findViewById(R.id.lnkwell);
    imgLomo=(ImageView) findViewById(R.id.lomo);
    imgLordKelvin=(ImageView) findViewById(R.id.lordKelvin);
    imgNashvill=(ImageView) findViewById(R.id.nashvill);
    imgRise=(ImageView) findViewById(R.id.rise);
    imgSierra=(ImageView) findViewById(R.id.sierra);
    imgSutro=(ImageView) findViewById(R.id.sutro);
    imgToaster=(ImageView) findViewById(R.id.toaster);
    imgValencia=(ImageView) findViewById(R.id.valencia );
    imgWalden=(ImageView) findViewById(R.id.walden);
    imgXproll=(ImageView) findViewById(R.id.xproll);
    imgN1977=(ImageView) findViewById(R.id.n1977);



    Log.i("ELOG","jaber"+Base.getDIR_PICTURE()+"/"+nameImage);
    src =Base.bit;
    rotation =Base.rotation;
    //decodeUri(Base.getDIR_PICTURE()+"/"+nameImage);

    Matrix rotateMatrix = new Matrix();
    rotateMatrix.postRotate(rotation);
    src = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), rotateMatrix, false);
    imgEditPic.setImage(src);

    int targetWidth = 100;
    int targetHeight = 100;
    RectF rectf = new RectF(0, 0, 100, 100);//was missing before update
    Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, targetHeight,Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(targetBitmap);
    Path path = new Path();
    path.addRect(rectf, Path.Direction.CW);
    canvas.clipPath(path);
    canvas.drawBitmap(src, new Rect(0, 0, src.getWidth(), src.getHeight()), new Rect(0, 0, targetWidth, targetHeight), null);

    imgNormal.setImageBitmap(targetBitmap);

    GPUImage mGPUImage = new GPUImage(this);
    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFAmaroFilter(this));
    Bitmap bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgAmaro.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFBrannanFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgBrannan.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFEarlybirdFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgEarlybird.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFHefeFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgHefe.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFHudsonFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgHudson.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFInkwellFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgLnkwell.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFLomoFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgLomo.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFLordKelvinFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgLordKelvin.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFNashvilleFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgNashvill.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFRiseFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgRise.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFSierraFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgSierra.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFSutroFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgSutro.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFToasterFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgToaster.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFValenciaFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgValencia.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFWaldenFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgWalden.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IFXprollFilter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgXproll.setImageBitmap(bp);

    mGPUImage.deleteImage();
    mGPUImage.setFilter(new IF1977Filter(this));
    bp=mGPUImage.getBitmapWithFilterApplied(targetBitmap);
    imgN1977.setImageBitmap(bp);


    layFilter.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //Toast.makeText(Base.getCurrentActivity(),"Processing...",Toast.LENGTH_SHORT).show();
        int t=Color.parseColor("#000000");
        int t2=Color.parseColor("#ffffff");
        imgSelectFilter.setBackgroundColor(t);;
        imgEdit.setBackgroundColor(t2);
        scrollViewEdit.setVisibility(View.GONE);
        scrollViewFilter.setVisibility(View.VISIBLE);


      }
    });
    layEdit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //Toast.makeText(Base.getCurrentActivity(),"Processing...",Toast.LENGTH_SHORT).show();
        int t=Color.parseColor("#000000");
        int t2=Color.parseColor("#ffffff");
        imgSelectFilter.setBackgroundColor(t2);
        imgEdit.setBackgroundColor(t);
        scrollViewFilter.setVisibility(View.GONE);
        scrollViewEdit.setVisibility(View.VISIBLE);

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

        int t2=Color.parseColor("#ffffff");
        imgSelectFilter.setBackgroundColor(t2);
        imgEdit.setBackgroundColor(t2);

        imgFilter.applyBrightnessEffect(src,100);

      }
    });


    imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0));
    sek.setProgress(50);
    sek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
        if(i>=50){
          if(i==50) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0));
          }
          if(i>50&&i<=55) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.1));
          }
          if(i>55&&i<=60) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.2));
          }
          if(i>60&&i<=65) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.3));
          }
          if(i>65&&i<=70) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.4));
          }
          if(i>70&&i<=75) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.5));
          }
          if(i>75&&i<=80) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.6));
          }
          if(i>80&&i>=85) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.7));
          }
          if(i>85&&i>=90) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.8));
          }
          if(i>90&&i>=95) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0.9));
          }
          if(i>95&&i>=100) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 1));
          }

        }else{
          if(i==50) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) 0));
          }
          if(i<50&&i>=45) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.1));
          }
          if(i<45&&i>=40) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.2));
          }
          if(i<40&&i>=35) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.3));
          }
          if(i<35&&i>=30) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.4));
          }
          if(i<30&&i>=25) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.5));
          }
          if(i<25&&i>=20) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.6));
          }
          if(i<20&&i>=15) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.7));
          }
          if(i<15&&i>=10) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.8));
          }
          if(i<10&&i>=5) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -0.9));
          }
          if(i<5&&i>=0) {
            imgEditPic.setFilter(new GPUImageBrightnessFilter((float) -1));
          }

        }

        Log.i("TAG",""+i);
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });


  }

  public void buttonClicked(View v){

    if(v.getId() == R.id.normal) {

      GPUImage mGPUImage = new GPUImage(this);
      mGPUImage.requestRender();
      mGPUImage.deleteImage();
      mGPUImage.setImage(src);


      // Toast.makeText(this,"Processing...",Toast.LENGTH_SHORT).show();
      Bitmap srce =decodeUri(Base.getDIR_PICTURE()+"/"+nameImage);
      Matrix rotateMatrix = new Matrix();
      rotateMatrix.postRotate(rotation);
      srce = Bitmap.createBitmap(srce, 0, 0, srce.getWidth(), srce.getHeight(), rotateMatrix, false);
      imgEditPic.setImage(srce);

    } else if(v.getId() == R.id.amaro)
      saveBitmap(new IFAmaroFilter(Base.getContext()));
    else if(v.getId() == R.id.brannan)
      saveBitmap(new IFBrannanFilter(Base.getContext()));
    else if(v.getId() == R.id.earlybird)
      saveBitmap(new IFEarlybirdFilter(Base.getContext()));
    else if(v.getId() == R.id.hefe)
      saveBitmap(new IFHefeFilter(Base.getContext()));
    else if(v.getId() == R.id.hudson)
      saveBitmap(new IFHudsonFilter(Base.getContext()));
    else if(v.getId() == R.id.lnkwell)
      saveBitmap(new IFInkwellFilter(Base.getContext()));
    else if(v.getId() == R.id.lomo)
      saveBitmap(new IFLomoFilter(Base.getContext()));
    else if(v.getId() == R.id.lordKelvin)
      saveBitmap(new IFLordKelvinFilter(Base.getContext()));
    else if(v.getId() == R.id.nashvill)
      saveBitmap(new IFNashvilleFilter(Base.getContext()));
    else if(v.getId() == R.id.rise)
      saveBitmap(new IFRiseFilter(Base.getContext()));
    else if(v.getId() == R.id.sierra)
      saveBitmap(new IFSierraFilter(Base.getContext()));
    else if(v.getId() == R.id.sutro)
      saveBitmap(new IFSutroFilter(Base.getContext()));
    else if(v.getId() == R.id.toaster)
      saveBitmap(new IFToasterFilter(Base.getContext()));
    else  if(v.getId() == R.id.valencia)
      saveBitmap(new IFValenciaFilter(Base.getContext()));
    else if(v.getId() == R.id.walden)
      saveBitmap(new IFWaldenFilter(Base.getContext()));
    else if(v.getId() == R.id.xproll)
      saveBitmap(new IFXprollFilter(Base.getContext()));
    else  if(v.getId() == R.id.n1977)
      saveBitmap(new IF1977Filter(Base.getContext()));
    /*else if(v.getId() == R.id.effect_invert)
      saveBitmap(imgFilter.applyInvertEffect(src),"effect_invert");
    else if(v.getId() == R.id.effect_mean_remove)
      saveBitmap(imgFilter.applyMeanRemovalEffect(src),"effect_mean_remove");
    else if(v.getId() == R.id.effect_round_corner)
      saveBitmap(imgFilter.applyRoundCornerEffect(src, 45),"effect_round_corner");
    else if(v.getId() == R.id.effect_saturation)
      saveBitmap(imgFilter.applySaturationFilter(src, 1),"effect_saturation");
    else if(v.getId() == R.id.effect_sepia)
      saveBitmap(imgFilter.applySepiaToningEffect(src, 10, 1.5, 0.6, 0.12),"effect_sepia");
    else if(v.getId() == R.id.effect_sepia_green)
      saveBitmap(imgFilter.applySepiaToningEffect(src, 10, 0.88, 2.45, 1.43),"effect_sepia_green");
    else if(v.getId() == R.id.effect_sepia_blue)
      saveBitmap(imgFilter.applySepiaToningEffect(src, 10, 1.2, 0.87, 2.1),"effect_sepia_blue");
    else if(v.getId() == R.id.effect_smooth)
      saveBitmap(imgFilter.applySmoothEffect(src, 100),"effect_smooth");
    else if(v.getId() == R.id.effect_sheding_cyan)
      saveBitmap(imgFilter.applyShadingFilter(src, Color.CYAN),"effect_sheding_cyan");
    else if(v.getId() == R.id.effect_sheding_yellow)
      saveBitmap(imgFilter.applyShadingFilter(src, Color.YELLOW),"effect_sheding_yellow");
    else if(v.getId() == R.id.effect_sheding_green)
      saveBitmap(imgFilter.applyShadingFilter(src, Color.GREEN),"effect_sheding_green");
    else if(v.getId() == R.id.effect_tint)
      saveBitmap(imgFilter.applyTintEffect(src, 100),"effect_tint");
    else if(v.getId() == R.id.effect_watermark)
      saveBitmap(imgFilter.applyWaterMarkEffect(src, "", 200, 200, Color.GREEN, 80, 24, false),"effect_watermark");*/

  }

  private Bitmap decodeUri(String g)  {

    try {

      File f=new File(g);
      // Decode image size
      BitmapFactory.Options o = new BitmapFactory.Options();
      o.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(new FileInputStream(f), null, o);

      // The new size we want to scale to
      final int REQUIRED_SIZE = 400;

      // Find the correct scale value. It should be the power of 2.
      int width_tmp = o.outWidth, height_tmp = o.outHeight;
      int scale = 1;
      while (true) {
        if (width_tmp / 2 < REQUIRED_SIZE
          || height_tmp / 2 < REQUIRED_SIZE) {
          break;
        }
        width_tmp /= 2;
        height_tmp /= 2;
        scale *= 2;
      }

      // Decode with inSampleSize
      BitmapFactory.Options o2 = new BitmapFactory.Options();
      o2.inSampleSize = scale;
      return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }


  private void saveBitmap(IFImageFilter imgFilter){
    imgEditPic.setFilter(imgFilter);
  }


}
