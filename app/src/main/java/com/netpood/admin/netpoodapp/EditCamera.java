package com.netpood.admin.netpoodapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ImageView;

import com.netpood.admin.framework.activity.UAppCompatActivity;

/**
 * Created by jaberALU on 01/06/2017.
 */



public class EditCamera extends UAppCompatActivity {

  private Ui ui;

  public class Ui {
    ImageView imgEditPic;
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

    ui.imgEditPic.setImageBitmap(setImage(rotation,data1));

  }


  private Bitmap setImage(int rotation,byte[] data1){
    Bitmap loadedImage = null;
    Bitmap rotatedBitmap = null;
    loadedImage = BitmapFactory.decodeByteArray(data1, 0,data1.length);

    // rotate Image
    Matrix rotateMatrix = new Matrix();
    rotateMatrix.postRotate(rotation);
    return  rotatedBitmap = Bitmap.createBitmap(loadedImage, 0, 0, loadedImage.getWidth(), loadedImage.getHeight(), rotateMatrix, false);

  }
}
