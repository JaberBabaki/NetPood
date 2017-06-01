package com.netpood.admin.netpoodapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.netpood.admin.framework.activity.UAppCompatActivity;
import com.netpood.admin.framework.widget.ColorHalftoneFilter;

/**
 * Created by jaberALU on 01/06/2017.
 */


public class EditCamera extends UAppCompatActivity {

  private Ui ui;
  /**
   * ATTENTION: This was auto-generated to implement the App Indexing API.
   * See https://g.co/AppIndexing/AndroidStudio for more information.
   */
  private GoogleApiClient client;

  @Override
  public void onStart() {
    super.onStart();

    // ATTENTION: This was auto-generated to implement the App Indexing API.
    // See https://g.co/AppIndexing/AndroidStudio for more information.
    client.connect();
    Action viewAction = Action.newAction(
      Action.TYPE_VIEW, // TODO: choose an action type.
      "EditCamera Page", // TODO: Define a title for the content shown.
      // TODO: If you have web page content that matches this app activity's content,
      // make sure this auto-generated web page URL is correct.
      // Otherwise, set the URL to null.
      Uri.parse("http://host/path"),
      // TODO: Make sure this auto-generated app URL is correct.
      Uri.parse("android-app://com.netpood.admin.netpoodapp/http/host/path")
    );
    AppIndex.AppIndexApi.start(client, viewAction);
  }

  @Override
  public void onStop() {
    super.onStop();

    // ATTENTION: This was auto-generated to implement the App Indexing API.
    // See https://g.co/AppIndexing/AndroidStudio for more information.
    Action viewAction = Action.newAction(
      Action.TYPE_VIEW, // TODO: choose an action type.
      "EditCamera Page", // TODO: Define a title for the content shown.
      // TODO: If you have web page content that matches this app activity's content,
      // make sure this auto-generated web page URL is correct.
      // Otherwise, set the URL to null.
      Uri.parse("http://host/path"),
      // TODO: Make sure this auto-generated app URL is correct.
      Uri.parse("android-app://com.netpood.admin.netpoodapp/http/host/path")
    );
    AppIndex.AppIndexApi.end(client, viewAction);
    client.disconnect();
  }

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

    ui.imgEditPic.setImageBitmap(setImage(rotation, data1));
    ColorHalftoneFilter b = new ColorHalftoneFilter();
    // ATTENTION: This was auto-generated to implement the App Indexing API.
    // See https://g.co/AppIndexing/AndroidStudio for more information.
    client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
  }


  private Bitmap setImage(int rotation, byte[] data1) {
    Bitmap loadedImage = null;
    Bitmap rotatedBitmap = null;
    loadedImage = BitmapFactory.decodeByteArray(data1, 0, data1.length);

    // rotate Image
    Matrix rotateMatrix = new Matrix();
    rotateMatrix.postRotate(rotation);
    return rotatedBitmap = Bitmap.createBitmap(loadedImage, 0, 0, loadedImage.getWidth(), loadedImage.getHeight(), rotateMatrix, false);





  }
}
