package com.maxheapsize.propertyfilemerger;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.fest.assertions.Assertions.assertThat;

public class PropertyValueFileMergerTest {

  private PropertyValueFileMerger unitUnderTest;

  @BeforeMethod
  public void setUp() {
    unitUnderTest = new PropertyValueFileMerger();
  }

  @Test
  public void testMergeTwoFiles() throws IOException {

    unitUnderTest.existingPropertyFile("src/test/resources/existingProperties.properties").
        newPropertyFile("src/test/resources/toBeMergedProperties.properties").
        mergedPropertyFile("target/mergedProperties.properties").
        mergeChangedPropertyValues();

    Properties mergedProperties = new Properties();
    FileInputStream fis = new FileInputStream("target/mergedProperties.properties");
    mergedProperties.load(fis);

    assertThat(mergedProperties.containsKey("Key10")).isFalse();
    assertThat(mergedProperties.getProperty("Key1")).isEqualTo("Value1Merged");
    assertThat(mergedProperties.containsKey("Key2"));
  }
}
