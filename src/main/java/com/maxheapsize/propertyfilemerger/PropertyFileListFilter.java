package com.maxheapsize.propertyfilemerger;

import java.io.File;
import java.io.FilenameFilter;

public class PropertyFileListFilter implements FilenameFilter {

  public boolean accept(File directory, String filename) {
    boolean fileOK = true;
    fileOK = filename.endsWith(".properties");
    return fileOK;
  }
}
