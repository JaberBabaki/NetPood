package com.netpood.admin.framework.widget;


public class Preconditions {
  public static void checkNotNull(Object object, String message) {
    if (object == null) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void checkIfPositive(int number, String message) {
    if (number <= 0) {
      throw new IllegalArgumentException(message);
    }
  }
}