package com.maxheapsize.propertyfilemerger;

import java.util.Properties;

import static org.fest.assertions.Assertions.assertThat;

public class PropertyValueMerger {

  private Properties existingProperties = new Properties();
  private Properties toBeMergeInProperties = new Properties();
  private Properties resultProperties = new Properties();

  public PropertyValueMerger existingProperties(Properties existingProperties) {
    this.existingProperties = existingProperties;
    return this;
  }

  public PropertyValueMerger newProperties(Properties toBeMergeInProperties) {
    this.toBeMergeInProperties = toBeMergeInProperties;
    return this;
  }

  public Properties mergeChangedPropertyValues() {
    assertThat(existingProperties).describedAs("Existing Properties must not be null.").isNotNull();
    assertThat(toBeMergeInProperties).describedAs("Properties which should be merged must not be null.").isNotNull();

    for (String propertyName : existingProperties.stringPropertyNames()) {
      resultProperties.setProperty(propertyName, existingProperties.getProperty(propertyName));
    }

    for (String propertyKey : existingProperties.stringPropertyNames()) {
      if (toBeMergeInProperties.containsKey(propertyKey)) {
        resultProperties.setProperty(propertyKey, toBeMergeInProperties.getProperty(propertyKey));
      }
    }

    return resultProperties;
  }
}
