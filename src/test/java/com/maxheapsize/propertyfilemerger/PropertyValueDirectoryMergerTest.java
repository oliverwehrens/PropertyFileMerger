package com.maxheapsize.propertyfilemerger;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

public class PropertyValueDirectoryMergerTest {

  private PropertyFileDirectoryMerger unitUnderTest;
  private final String targetDirectory = "target/mergedDirectory";
  private final String expectedFileName = "directoryMergeTest.properties";

  @BeforeMethod
  public void setUp() {
    unitUnderTest = new PropertyFileDirectoryMerger();
    File targetDir = new File(targetDirectory);
    targetDir.mkdirs();

    File expectedFile = new File(expectedFileName);
    if (expectedFile.exists()) {
      expectedFile.delete();
    }
  }

  @Test
  public void testMergeDirectories() {

    unitUnderTest.existingPropertiesDirectory("src/test/resources/existing").
        newPropertiesDirectory("src/test/resources/toBeMerged").
        mergedPropertiesDirectory(targetDirectory).
        mergeValuesOfMatchingPropertyFiles();

    File mergedDirectory = new File(targetDirectory);
    assertThat(mergedDirectory.isDirectory()).isTrue();

    final File[] propertyFiles = mergedDirectory.listFiles();
    Set<String> filenames = new HashSet<String>();
    for (File propertyFile : propertyFiles) {
      filenames.add(propertyFile.getName());
    }

    assertThat(filenames).contains(expectedFileName);
  }
}
