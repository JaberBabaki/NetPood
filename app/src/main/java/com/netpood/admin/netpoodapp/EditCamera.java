package com.netpood.admin.netpoodapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.netpood.admin.framework.activity.UAppCompatActivity;

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

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;

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
    if (extras != null) {
      data1 = extras.getByteArray("PIC");
      rotation = extras.getInt("ROR");
    }

    Bitmap loadedImage = null;
    loadedImage = BitmapFactory.decodeByteArray(data1, 0, data1.length);

    // rotate Image
    Matrix rotateMatrix = new Matrix();
    rotateMatrix.postRotate(rotation);
    final Bitmap  rotatedBitmap = Bitmap.createBitmap(loadedImage, 0, 0, loadedImage.getWidth(), loadedImage.getHeight(), rotateMatrix, false);

    ui.imgEditPic.setImageBitmap(rotatedBitmap);

    //ui.img2.setImageBitmap( BitmapProcessing.grayscale(rotatedBitmap));
    //ui.img1.setImageBitmap( BitmapProcessing.emboss(rotatedBitmap));
    // ui.img3.setImageBitmap( BitmapProcessing.grayscale(rotatedBitmap));
   //ui.img4.setImageBitmap( BitmapProcessing.sepia(rotatedBitmap));




  }


 /* private Bitmap setImage(int rotation, byte[] data1) {



  }*/
}
