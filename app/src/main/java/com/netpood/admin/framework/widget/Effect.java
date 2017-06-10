package com.netpood.admin.framework.widget;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;

/**
 * Created by jaberALU on 07/06/2017.
 */
public class Effect {

  public  Bitmap doInvert(Bitmap src) {
    // create new bitmap with the same settings as source bitmap
    Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
    // color info
    int A, R, G, B;
    int pixelColor;
    // image size
    int height = src.getHeight();
    int width = src.getWidth();

    // scan through every pixel
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        // get one pixel
        pixelColor = src.getPixel(x, y);
        // saving alpha channel
        A = Color.alpha(pixelColor);
        // inverting byte for each R/G/B channel
        R = 255 - Color.red(pixelColor);
        G = 255 - Color.green(pixelColor);
        B = 255 - Color.blue(pixelColor);
        // set newly-inverted pixel to output image
        bmOut.setPixel(x, y, Color.argb(A, R, G, B));
      }
    }

    // return final bitmap
    return bmOut;
  }
  public Bitmap doHighlightImage(Bitmap src) {
    // create new bitmap, which will be painted and becomes result image
    Bitmap bmOut = Bitmap.createBitmap(src.getWidth() + 96, src.getHeight() + 96, Bitmap.Config.ARGB_8888);
    // setup canvas for painting
    Canvas canvas = new Canvas(bmOut);
    // setup default color
    canvas.drawColor(0, PorterDuff.Mode.CLEAR);

    // create a blur paint for capturing alpha
    Paint ptBlur = new Paint();
    ptBlur.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.NORMAL));
    int[] offsetXY = new int[2];
    // capture alpha into a bitmap
    Bitmap bmAlpha = src.extractAlpha(ptBlur, offsetXY);
    // create a color paint
    Paint ptAlphaColor = new Paint();
    ptAlphaColor.setColor(0xFFFFFFFF);
    // paint color for captured alpha region (bitmap)
    canvas.drawBitmap(bmAlpha, offsetXY[0], offsetXY[1], ptAlphaColor);
    // free memory
    bmAlpha.recycle();

    // paint the image source
    canvas.drawBitmap(src, 0, 0, null);

    // return out final image
    return bmOut;


  }

  public static Bitmap doGreyscale(Bitmap src) {
    // constant factors
    final double GS_RED = 0.299;
    final double GS_GREEN = 0.587;
    final double GS_BLUE = 0.114;

    // create output bitmap
    Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
    // pixel information
    int A, R, G, B;
    int pixel;

    // get image size
    int width = src.getWidth();
    int height = src.getHeight();

    // scan through every single pixel
    for(int x = 0; x < width; ++x) {
      for(int y = 0; y < height; ++y) {
        // get one pixel color
        pixel = src.getPixel(x, y);
        // retrieve color of all channels
        A = Color.alpha(pixel);
        R = Color.red(pixel);
        G = Color.green(pixel);
        B = Color.blue(pixel);
        // take conversion up to one single value
        R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
        // set new pixel color to output bitmap
        bmOut.setPixel(x, y, Color.argb(A, R, G, B));
      }
    }

    // return final image
    return bmOut;
  }

  public static Bitmap doGamma(Bitmap src, double red, double green, double blue) {
    // create output image
    Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
    // get image size
    int width = src.getWidth();
    int height = src.getHeight();
    // color information
    int A, R, G, B;
    int pixel;
    // constant value curve
    final int    MAX_SIZE = 256;
    final double MAX_VALUE_DBL = 255.0;
    final int    MAX_VALUE_INT = 255;
    final double REVERSE = 1.0;

    // gamma arrays
    int[] gammaR = new int[MAX_SIZE];
    int[] gammaG = new int[MAX_SIZE];
    int[] gammaB = new int[MAX_SIZE];

    // setting values for every gamma channels
    for(int i = 0; i < MAX_SIZE; ++i) {
      gammaR[i] = (int)Math.min(MAX_VALUE_INT,
        (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / red)) + 0.5));
      gammaG[i] = (int)Math.min(MAX_VALUE_INT,
        (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / green)) + 0.5));
      gammaB[i] = (int)Math.min(MAX_VALUE_INT,
        (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / blue)) + 0.5));
    }

    // apply gamma table
    for(int x = 0; x < width; ++x) {
      for(int y = 0; y < height; ++y) {
        // get pixel color
        pixel = src.getPixel(x, y);
        A = Color.alpha(pixel);
        // look up gamma
        R = gammaR[Color.red(pixel)];
        G = gammaG[Color.green(pixel)];
        B = gammaB[Color.blue(pixel)];
        // set new color to output bitmap
        bmOut.setPixel(x, y, Color.argb(A, R, G, B));
      }
    }

    // return final image
    return bmOut;
  }

  public static Bitmap doColorFilter(Bitmap src, double red, double green, double blue) {
    // image size
    int width = src.getWidth();
    int height = src.getHeight();
    // create output bitmap
    Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
    // color information
    int A, R, G, B;
    int pixel;

    // scan through all pixels
    for(int x = 0; x < width; ++x) {
      for(int y = 0; y < height; ++y) {
        // get pixel color
        pixel = src.getPixel(x, y);
        // apply filtering on each channel R, G, B
        A = Color.alpha(pixel);
        R = (int)(Color.red(pixel) * red);
        G = (int)(Color.green(pixel) * green);
        B = (int)(Color.blue(pixel) * blue);
        // set new color pixel to output bitmap
        bmOut.setPixel(x, y, Color.argb(A, R, G, B));
      }
    }

    // return final image
    return bmOut;
  }

  public static Bitmap createSepiaToningEffect(Bitmap src, int depth, double red, double green, double blue) {
    // image size
    int width = src.getWidth();
    int height = src.getHeight();
    // create output bitmap
    Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
    // constant grayscale
    final double GS_RED = 0.3;
    final double GS_GREEN = 0.59;
    final double GS_BLUE = 0.11;
    // color information
    int A, R, G, B;
    int pixel;

    // scan through all pixels
    for(int x = 0; x < width; ++x) {
      for(int y = 0; y < height; ++y) {
        // get pixel color
        pixel = src.getPixel(x, y);
        // get color on each channel
        A = Color.alpha(pixel);
        R = Color.red(pixel);
        G = Color.green(pixel);
        B = Color.blue(pixel);
        // apply grayscale sample
        B = G = R = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);

        // apply intensity level for sepid-toning on each channel
        R += (depth * red);
        if(R > 255) { R = 255; }

        G += (depth * green);
        if(G > 255) { G = 255; }

        B += (depth * blue);
        if(B > 255) { B = 255; }

        // set new pixel color to output image
        bmOut.setPixel(x, y, Color.argb(A, R, G, B));
      }
    }

    // return final image
    return bmOut;
  }

  public static Bitmap sharpen(Bitmap src, double weight) {
    double[][] SharpConfig = new double[][] {
      { 0 , -2    , 0  },
      { -2, weight, -2 },
      { 0 , -2    , 0  }
    };
    ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
    convMatrix.applyConfig(SharpConfig);
    convMatrix.Factor = weight - 8;
    return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
  }

  public static Bitmap addEffect(Bitmap src, int depth, double red, double green, double blue) {
    int width = src.getWidth();
    int height = src.getHeight();
    Bitmap finalBitmap = Bitmap.createBitmap(width, height, src.getConfig());
    final double grayScale_Red = 0.3;
    final double grayScale_Green = 0.59;
    final double grayScale_Blue = 0.11;
    int channel_aplha, channel_red, channel_green, channel_blue;
    int pixel;
    for(int x = 0; x < width; ++x) {
      for(int y = 0; y < height; ++y) {
        pixel = src.getPixel(x, y);
        channel_aplha = Color.alpha(pixel);
        channel_red = Color.red(pixel);
        channel_green = Color.green(pixel);
        channel_blue = Color.blue(pixel);
        channel_blue = channel_green = channel_red = (int)(grayScale_Red * channel_red + grayScale_Green * channel_green + grayScale_Blue * channel_blue);
        channel_red += (depth * red);
        if(channel_red > 255)
        {
          channel_red = 255;
        }
        channel_green += (depth * green);
        if(channel_green > 255)
        {
          channel_green = 255;
        }
        channel_blue += (depth * blue);
        if(channel_blue > 255)
        {
          channel_blue = 255;
        }
        finalBitmap.setPixel(x, y, Color.argb(channel_aplha, channel_red, channel_green, channel_blue));

      }
    }
    return finalBitmap;

  }


}