package edu.cnm.deepdive.galleryservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.galleryservice.model.entity.Gallery;
import edu.cnm.deepdive.galleryservice.model.entity.User;
import edu.cnm.deepdive.galleryservice.service.GalleryService;
import edu.cnm.deepdive.galleryservice.service.UserService;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * A utility class to preload the database with some default galleries.
 */
@Component
@Profile("preload")
public class Preloader implements CommandLineRunner {

  private static final String PRELOADER_USERNAME = "system";
  private static final String PRELOADER_OAUTH_KEY = "";
  private static final String PRELOADER_DATA = "preload/galleries.json";

  private final UserService userService;
  private final GalleryService galleryService;

  @Autowired
  public Preloader(UserService userService, GalleryService galleryService) {
    this.userService = userService;
    this.galleryService = galleryService;
  }

  @Override
  public void run(String... args) throws Exception {
    User user = userService.getOrCreate(PRELOADER_OAUTH_KEY, PRELOADER_USERNAME);
    // use Spring to help us access the file
    ClassPathResource resource = new ClassPathResource(PRELOADER_DATA);
    try (InputStream input = resource.getInputStream()) {
      // have Jackson read the json for us
      ObjectMapper mapper = new ObjectMapper();
//      List<Gallery> galleries = new LinkedList<>();
//      for (Gallery gallery : mapper.readValue(input, Gallery[].class)) {
//        gallery.setCreator(user);
//        galleries.add(gallery);
//      }
//      galleryService.save(galleries);
      galleryService.save(
          Stream
              .of(mapper.readValue(input, Gallery[].class))
              .peek((gallery) -> gallery.setCreator(user))
              .collect(Collectors.toList())
      );
    }
  }

}
