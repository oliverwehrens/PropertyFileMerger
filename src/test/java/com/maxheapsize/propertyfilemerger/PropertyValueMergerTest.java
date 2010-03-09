package com.maxheapsize.propertyfilemerger;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

import static org.fest.assertions.Assertions.assertThat;

public class PropertyValueMergerTest {

  private PropertyValueMerger unitUnderTest;

  @BeforeMethod
  public void setUp() {
    unitUnderTest = new PropertyValueMerger();
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testAssertExistingPropertiesAreNotNull() {
    unitUnderTest.existingProperties(null).newProperties(new Properties()).mergeChangedPropertyValues();
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testAssertToBeMergedPropertiesAreNotNull() {
    unitUnderTest.existingProperties(new Properties()).newProperties(null).mergeChangedPropertyValues();
  }

  @Test
  public void testMergeReplaceExistingPropertyValue() {

    Properties existingProperties = new Properties();
    existingProperties.setProperty("Key1", "Value1");
    Properties toBeMergedIn = new Properties();
    toBeMergedIn.setProperty("Key1", "Value2");

    Properties mergedProperties = unitUnderTest.existingProperties(existingProperties).newProperties(toBeMergedIn).mergeChangedPropertyValues();

    assertThat(mergedProperties.getProperty("Key1")).isEqualTo("Value2");
  }

  @Test
  public void testDoNotMergeObsoleteProperties() {
    final String obsoleteKey = "OldAndObsolete";
    
    Properties existingProperties = new Properties();
    existingProperties.setProperty("Key1", "Value1");
    Properties toBeMergedIn = new Properties();
    toBeMergedIn.setProperty("Key1", "Value2");
    toBeMergedIn.setProperty(obsoleteKey, "Value3");

    Properties mergedProperties = unitUnderTest.existingProperties(existingProperties).newProperties(toBeMergedIn).mergeChangedPropertyValues();

    assertThat(mergedProperties.containsKey(obsoleteKey)).isFalse();
  }
}
