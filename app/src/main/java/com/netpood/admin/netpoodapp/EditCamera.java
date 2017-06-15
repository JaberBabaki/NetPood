package com.netpood.admin.netpoodapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.netpood.admin.framework.activity.UAppCompatActivity;
import com.netpood.admin.framework.widget.ImageFilters;
import com.netpood.admin.framework.widget.StartPointSeekBar;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by jaberALU on 01/06/2017.
 */


public class EditCamera extends UAppCompatActivity {

  private Ui ui;
  private Bitmap  src;

  public class Ui {

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

    ImageView imgMain ;

    ImageView flash;
    ImageView captureImage;
    SurfaceView surfaceView;

    StartPointSeekBar sek;

    TextView txtHeaderEffect;
    TextView txtFilter;
    TextView txtEdit;
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

    Bundle extras = getIntent().getExtras();
    byte[] data1 = new byte[0];
    int rotation = 0;
    String nameImage = "";
    if (extras != null) {
      nameImage = extras.getString("PIC");
      rotation = extras.getInt("ROR");
    }

    Log.i("ELOG","jaber"+Base.getDIR_PICTURE()+"/"+nameImage);
    src =decodeUri(Base.getDIR_PICTURE()+"/"+nameImage);

    Matrix rotateMatrix = new Matrix();
    rotateMatrix.postRotate(rotation);
    src = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), rotateMatrix, false);
    ui.imgEditPic.setImageBitmap(src);

   ui.layFilter.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
       //Toast.makeText(Base.getCurrentActivity(),"Processing...",Toast.LENGTH_SHORT).show();
       int t=Color.parseColor("#000000");
       int t2=Color.parseColor("#ffffff");
       ui.imgSelectFilter.setBackgroundColor(t);;
       ui.imgEdit.setBackgroundColor(t2);
       ui.scrollViewEdit.setVisibility(View.GONE);
       ui.scrollViewFilter.setVisibility(View.VISIBLE);


     }
   });
   ui.layEdit.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
       //Toast.makeText(Base.getCurrentActivity(),"Processing...",Toast.LENGTH_SHORT).show();
       int t=Color.parseColor("#000000");
       int t2=Color.parseColor("#ffffff");
       ui.imgSelectFilter.setBackgroundColor(t2);
       ui.imgEdit.setBackgroundColor(t);
       ui.scrollViewFilter.setVisibility(View.GONE);
       ui.scrollViewEdit.setVisibility(View.VISIBLE);

     }
   });
      final ImageFilters imgFilter = new ImageFilters();

    ui.layBrightness.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        ui.sek.setProgress(0);

        ui.scrollViewEdit.setVisibility(View.GONE);
        ui.laySeekBar.setVisibility(View.VISIBLE);

        ui.layBackEffect.setVisibility(View.GONE);
        ui.layNextEffect.setVisibility(View.GONE);
        ui.layHeaderEffect.setVisibility(View.VISIBLE);

        ui.txtEdit.setText("Done");
        ui.txtFilter.setText("Cancel");
        ui.txtHeaderEffect.setText("Brigthness");

        int t2=Color.parseColor("#ffffff");
        ui.imgSelectFilter.setBackgroundColor(t2);
        ui.imgEdit.setBackgroundColor(t2);

        imgFilter.applyBrightnessEffect(src,100);

       }
     });
    Log.i("AAA","value");

     ui.sek.setOnSeekBarChangeListener(new StartPointSeekBar.OnSeekBarChangeListener() {
        @Override
        public void onOnSeekBarValueChange(StartPointSeekBar bar, double value) {

          Log.i("AAA",""+value);
          //imgFilter.applyBrightnessEffect(src,(int)value);
      }
     });





  }

  public void buttonClicked(View v){

    Toast.makeText(this,"Processing...",Toast.LENGTH_SHORT).show();
    ImageFilters imgFilter = new ImageFilters();

    if(v.getId() == R.id.effect_black)
      saveBitmap(imgFilter.applyBlackFilter(src),"effect_black");
    else if(v.getId() == R.id.effect_boost_1)
      saveBitmap(imgFilter.applyBoostEffect(src, 1, 40),"effect_boost_1");
    else if(v.getId() == R.id.effect_boost_2)
      saveBitmap(imgFilter.applyBoostEffect(src, 2, 30),"effect_boost_2");
    else if(v.getId() == R.id.effect_boost_3)
      saveBitmap(imgFilter.applyBoostEffect(src, 3, 67),"effect_boost_3");
    else if(v.getId() == R.id.effect_brightness)
      saveBitmap(imgFilter.applyBrightnessEffect(src, 80),"effect_brightness");
    else if(v.getId() == R.id.effect_color_red)
      saveBitmap(imgFilter.applyColorFilterEffect(src, 255, 0, 0),"effect_color_red");
    else if(v.getId() == R.id.effect_color_green)
      saveBitmap(imgFilter.applyColorFilterEffect(src, 0, 255, 0),"effect_color_green");
    else if(v.getId() == R.id.effect_color_blue)
      saveBitmap(imgFilter.applyColorFilterEffect(src, 0, 0, 255),"effect_color_blue");
    else if(v.getId() == R.id.effect_color_depth_64)
      saveBitmap(imgFilter.applyDecreaseColorDepthEffect(src, 64),"effect_color_depth_64");
    else if(v.getId() == R.id.effect_color_depth_32)
      saveBitmap(imgFilter.applyDecreaseColorDepthEffect(src, 32),"effect_color_depth_32");
    else if(v.getId() == R.id.effect_contrast)
      saveBitmap(imgFilter.applyContrastEffect(src, 70),"effect_contrast");
    else if(v.getId() == R.id.effect_emboss)
      saveBitmap(imgFilter.applyEmbossEffect(src),"effect_emboss");
    else if(v.getId() == R.id.effect_engrave)
      saveBitmap(imgFilter.applyEngraveEffect(src),"effect_engrave");
    else if(v.getId() == R.id.effect_flea)
      saveBitmap(imgFilter.applyFleaEffect(src),"effect_flea");
    else  if(v.getId() == R.id.effect_gaussian_blue)
      saveBitmap(imgFilter.applyGaussianBlurEffect(src),"effect_gaussian_blue");
    else if(v.getId() == R.id.effect_gamma)
      saveBitmap(imgFilter.applyGammaEffect(src, 1.8, 1.8, 1.8),"effect_gamma");
    else if(v.getId() == R.id.effect_grayscale)
      saveBitmap(imgFilter.applyGreyscaleEffect(src),"effect_grayscale");
    else  if(v.getId() == R.id.effect_hue)
      saveBitmap(imgFilter.applyHueFilter(src, 2),"effect_hue");
    else if(v.getId() == R.id.effect_invert)
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
      saveBitmap(imgFilter.applyWaterMarkEffect(src, "", 200, 200, Color.GREEN, 80, 24, false),"effect_watermark");

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


  private void saveBitmap(Bitmap bmp,String fileName){
    //try {
      ui.imgEditPic.setImageBitmap(bmp);
      /*File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName+".png");
      FileOutputStream fos = new FileOutputStream(f);
      bmp.compress(Bitmap.CompressFormat.PNG,90,fos);
    }
    catch(Exception ex){
      ex.printStackTrace();
    }*/
  }

 /* private Bitmap decodeFile(String g) {
    try {

      File f=new File(g);
      // Decode image size
      BitmapFactory.Options o = new BitmapFactory.Options();
      o.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(new FileInputStream(f), null, o);

      // The new size we want to scale to
      final int REQUIRED_SIZE=70;

      // Find the correct scale value. It should be the power of 2.
      int scale = 1;
      while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
        o.outHeight / scale / 2 >= REQUIRED_SIZE) {
        scale *= 2;
      }

      // Decode with inSampleSize
      BitmapFactory.Options o2 = new BitmapFactory.Options();
      o2.inSampleSize = scale;
      return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
    } catch (FileNotFoundException e) {}
    return null;
  }*/

}
