package edu.cnm.deepdive.galleryservice.service;

import edu.cnm.deepdive.galleryservice.model.dao.GalleryRepository;
import edu.cnm.deepdive.galleryservice.model.entity.Gallery;
import edu.cnm.deepdive.galleryservice.model.entity.User;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {

  private final GalleryRepository galleryRepository;

  public GalleryService(GalleryRepository galleryRepository) {
    this.galleryRepository = galleryRepository;
  }

  public Gallery newGallery(Gallery gallery, User creator) {
    gallery.setCreator(creator);
    return galleryRepository.save(gallery);
  }

  public Gallery save(@NonNull Gallery gallery) {
    return galleryRepository.save(gallery);
  }

  public Iterable<Gallery> save(@NonNull Iterable<Gallery> galleries) {
    return galleryRepository.saveAll(galleries);
  }


  public Optional<Gallery> get(UUID id) {
    return galleryRepository.findById(id);
  }

  public Iterable<Gallery> getAll() {
    return galleryRepository.getAllByOrderByTitleAsc();
  }
}
