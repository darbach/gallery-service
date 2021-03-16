package edu.cnm.deepdive.galleryservice.configuration;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "upload")
public class UploadConfiguration {

  private boolean applicationHome = true;
  private String directory = "uploads";
  private Pattern subdirectoryPattern = Pattern.compile("^(.{4})(.{2})(.{2}).*$");
  private Set<String> whitelist = new LinkedHashSet<>(Set.of(
          "image/bmp",
          "image/gif",
          "image/jpeg",
          "image/pjpeg",
          "image/png",
          "image/tiff"
      )
  );

  private FilenameProperties filename;

  /**
   * Returns a flag indicating whether the upload directory should be interpreted relative to the
   * application home directory.
   */
  public boolean isApplicationHome() {
    return applicationHome;
  }

  /**
   * Specifies whether the upload directory should be interpreted relative to the application
   * home directory.
   */
  public void setApplicationHome(boolean applicationHome) {
    this.applicationHome = applicationHome;
  }

  /**
   * Returns the directory where files are uploaded.
   */
  public String getDirectory() {
    return directory;
  }

  /**
   * Set the directory where files are uploaded.
   */
  public void setDirectory(String directory) {
    this.directory = directory;
  }

  /**
   * Returns the subdirectory pattern for creating subdirectory names into which files are
   * automatically separated.
   */
  public Pattern getSubdirectoryPattern() {
    return subdirectoryPattern;
  }

  /**
   * Set the subdirectory pattern for creating subdirectory names into which files are
   * automatically separated.
   */
  public void setSubdirectoryPattern(Pattern subdirectoryPattern) {
    this.subdirectoryPattern = subdirectoryPattern;
  }

  /**
   * Returns the whitelist of acceptable upload file types.
   */
  public Set<String> getWhitelist() {
    return whitelist;
  }

  /**
   * Set the whitelist of acceptable upload file types.
   */
  public void setWhitelist(Set<String> whitelist) {
    this.whitelist = whitelist;
  }

  /**
   * Returns the Filename class used to automatically generate unique names for uploaded files.
   */
  public FilenameProperties getFilename() {
    return filename;
  }

  /**
   * Set the Filename class used to automatically generate unique names for uploaded files.
   */
  public void setFilename(FilenameProperties filename) {
    this.filename = filename;
  }

  public static class FilenameProperties {

    private String format = "%1$s-%2$d.%3$s";
    private int randomizerLimit = 1_000_000;
    private Timestamp timestamp;

    public String getFormat() {
      return format;
    }

    public void setFormat(String format) {
      this.format = format;
    }

    public int getRandomizerLimit() {
      return randomizerLimit;
    }

    public void setRandomizerLimit(int randomizerLimit) {
      this.randomizerLimit = randomizerLimit;
    }

    public Timestamp getTimestamp() {
      return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
      this.timestamp = timestamp;
    }

    public static class Timestamp {

      private String format = "yyyyMMddHHmmssSSS";
      private TimeZone timeZone = TimeZone.getTimeZone("UTC");

      public String getFormat() {
        return format;
      }

      public void setFormat(String format) {
        this.format = format;
      }

      public TimeZone getTimeZone() {
        return timeZone;
      }

      public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
      }
    }
  }
}
