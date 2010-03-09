package com.maxheapsize.propertyfilemerger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static org.fest.assertions.Assertions.assertThat;

public class PropertyValueFileMerger {

  private String existingPropertyFile;
  private String newPropertyFile;
  private String mergedPropertyFile;

  public PropertyValueFileMerger existingPropertyFile(String filename) {
    this.existingPropertyFile = filename;
    return this;
  }

  public PropertyValueFileMerger newPropertyFile(String filename) {
    this.newPropertyFile = filename;
    return this;
  }

  public PropertyValueFileMerger mergedPropertyFile(String filename) {
    this.mergedPropertyFile = filename;
    return this;
  }

  public void mergeChangedPropertyValues() {

    Properties existingProperties = loadProperties(existingPropertyFile);
    Properties tobeMergedProperties = loadProperties(newPropertyFile);

    Properties mergedProperties = new PropertyValueMerger().existingProperties(existingProperties).newProperties(tobeMergedProperties).mergeChangedPropertyValues();

    storeMergedProperties(mergedProperties, mergedPropertyFile);
  }

  private void storeMergedProperties(Properties mergedProperties, String mergedPropertyFile) {
    try {
      mergedProperties.store(new FileOutputStream(mergedPropertyFile), "Merged automatically by PropertyValueMerger");
    }
    catch (IOException e) {
      System.out.println("Could not store Properties to File " + mergedPropertyFile);
      System.exit(1);
    }
  }

  private Properties loadProperties(String existingPropertyFile) {
    Properties result = new Properties();
    File propertyFile = new File(existingPropertyFile);
    assertThat(propertyFile.exists()).describedAs("File " + existingPropertyFile + " must exist.").isTrue();
    try {
      FileInputStream fis = new FileInputStream(propertyFile);
      result.load(fis);
    }
    catch (IOException e) {
      System.out.println("Could not load Properties from File " + existingPropertyFile);
      System.exit(1);
    }
    return result;
  }
}
