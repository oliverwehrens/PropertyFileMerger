package com.maxheapsize.propertyfilemerger;

import java.io.File;
import java.io.FilenameFilter;

import static org.fest.assertions.Assertions.assertThat;

public class PropertyFileDirectoryMerger {

  private String existingDirectory;
  private String newDirectory;
  private String mergedDirectory;

  public PropertyFileDirectoryMerger existingPropertiesDirectory(String dirName) {
    this.existingDirectory = dirName;
    return this;
  }

  public PropertyFileDirectoryMerger newPropertiesDirectory(String dirName) {
    this.newDirectory = dirName;
    return this;
  }

  public PropertyFileDirectoryMerger mergedPropertiesDirectory(String dirName) {
    this.mergedDirectory = dirName;
    return this;
  }

  public void mergeValuesOfMatchingPropertyFiles() {

    final File[] files = getExistingPropertyFiles();
    File mergedPropertyFileDirectory = new File(mergedDirectory);
    assertThat(mergedPropertyFileDirectory.isDirectory()).describedAs("Directory for the merged PropertyFiles does not exists.").
        isTrue();

    for (File file : files) {
      File matchingNewPropertyFile = new File(newDirectory + File.separator + file.getName());
      if (matchingNewPropertyFile.exists()) {
        new PropertyValueFileMerger().existingPropertyFile(file.getAbsolutePath()).
            newPropertyFile(matchingNewPropertyFile.getAbsolutePath()).
            mergedPropertyFile(mergedPropertyFileDirectory.getAbsolutePath() + File.separator + file.getName()).mergeChangedPropertyValues();
      }
    }
  }

  private File[] getExistingPropertyFiles() {
    File existingProertyDirectory = new File(existingDirectory);
    assertThat(existingProertyDirectory.isDirectory()).isTrue();

    FilenameFilter select = new PropertyFileListFilter();
    final File[] files = existingProertyDirectory.listFiles(select);
    return files;
  }
}
