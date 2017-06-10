package com.netpood.admin.netpoodapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.netpood.admin.framework.activity.UAppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by jaberALU on 01/06/2017.
 */


public class EditCamera extends UAppCompatActivity {

  private Ui ui;

  public class Ui {
    ImageView imgEditPic;

    LinearLayout lay1;
    LinearLayout lay2;
    LinearLayout lay3;
    LinearLayout lay4;
    LinearLayout lay5;
    LinearLayout lay6;
    LinearLayout lay7;
    LinearLayout lay8;

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    ImageView img7;
    ImageView img8;

    ImageView flash;
    ImageView captureImage;
    SurfaceView surfaceView;
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

    /*Bitmap loadedImage = null;
    loadedImage = BitmapFactory.decodeByteArray( Base.getPic.toByteArray(), 0,  Base.getPic.toByteArray().length);

    // rotate Image
    Matrix rotateMatrix = new Matrix();
    rotateMatrix.postRotate(Base.rotation);
    final Bitmap  rotatedBitmap = Bitmap.createBitmap(loadedImage, 0, 0, loadedImage.getWidth(), loadedImage.getHeight(), rotateMatrix, false);
**/



   /* final Effect ef=new Effect();
    Bitmap bitmapImg= decodeFile(Base.getDIR_PICTURE()+"/"+nameImage);
    Matrix rotateMatrix = new Matrix();
    rotateMatrix.postRotate(rotation);
    final Bitmap  rotatedBitmap = Bitmap.createBitmap(bitmapImg, 0, 0, bitmapImg.getWidth(), bitmapImg.getHeight(), rotateMatrix, false);



    ui.imgEditPic.setImageBitmap(bitmapImg);
  //  ui.img1.setImageBitmap(ef.doGreyscale(bitmapImg));
   // ui.img2.setImageBitmap(ef.addEffect(bitmapImg,5,5.0,6.0,0.0));
   // ui.img3.setImageBitmap(ef.addEffect(bitmapImg,5,5.0,0.0,10.0));
    //ui.img4.setImageBitmap(ef.addEffect(bitmapImg,5,5.0,0.10,0.0));
    //ui.img5.setImageBitmap(ef.addEffect(bitmapImg,15,5.0,0.0,10.0));


   // ui.img2.setImageBitmap(ef.doInvert(rotatedBitmap));
    //ui.img3.setImageBitmap(ef.doGamma(rotatedBitmap,0.6, 0.6, 0.6));
    //ui.img4.setImageBitmap(ef.doHighlightImage(rotatedBitmap));
    //ui.img5.setImageBitmap(ef.doColorFilter(rotatedBitmap,0.25,0,0));
    //ui.img6.setImageBitmap(ef.doColorFilter(rotatedBitmap,0,0.25,0));
   // ui.img7.setImageBitmap(ef.createSepiaToningEffect(rotatedBitmap,0,50,30,0));
  //  ui.img8.setImageBitmap(ef.sharpen(rotatedBitmap,10));

   /* AsyncTask.execute(new Runnable() {
      @Override
      public void run() {

      }
    });*/











  }


  private Bitmap decodeFile(String g) {
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
  }

}
