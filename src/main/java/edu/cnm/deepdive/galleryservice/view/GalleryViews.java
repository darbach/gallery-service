package edu.cnm.deepdive.galleryservice.view;

/**
 * View definitions to control serialization of {@link edu.cnm.deepdive.galleryservice.model.entity.Gallery}
 * instances.
 */
public class GalleryViews {

  /**
   * Serialization without referenced entity instances.
   */
  public static class Flat {}

  /**
   * Serialization with referenced entity instances.
   */
  public static class Hierarchical extends Flat {}

}
