package WRONG_PACKAGE.utils;

public class SpringError extends Exception {

  public String timestamp;
  public int status;
  public String error;
  public String message;
  public String path;

  @Override
  public String getMessage() {
    return message;
  }
}
