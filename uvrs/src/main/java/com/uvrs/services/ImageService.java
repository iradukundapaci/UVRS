package com.uvrs.services;

import com.uvrs.models.Image;
import com.uvrs.models.Vehicle;
import com.uvrs.repositories.ImageRepository;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

  @Autowired
  private ImageRepository imageInterface;

  @Value("${image.upload.directory}")
  private String IMAGE_FOLDER;

  public boolean uploadImage(MultipartFile file, Vehicle vehicle)
    throws IOException {
    Image img = new Image();
    img.setVehicle(vehicle);

    img = imageInterface.save(img);

    if (img != null) {
      String originalFileName = file.getOriginalFilename();
      String fileName = img.getImageId() + "_" + originalFileName;

      img.setImage(fileName);
      imageInterface.save(img);
      file.transferTo(new File(IMAGE_FOLDER, fileName));

      return true;
    } else {
      return false;
    }
  }

  public Resource downloadImage(String imageUrl) throws MalformedURLException {
    Path filePath = Paths.get(IMAGE_FOLDER).resolve(imageUrl).normalize();
    Resource resource = new UrlResource(filePath.toUri());

    if (resource.exists()) {
      return resource;
    } else {
      throw new RuntimeException("Image not found: " + imageUrl);
    }
  }
  // private void deleteImageFile(String imageUrl, String imageFolder) {
  //   try {
  //     Path imagePath = Paths.get(imageFolder, imageUrl);
  //     Files.deleteIfExists(imagePath);
  //   } catch (IOException e) {
  //     e.printStackTrace();
  //   }
  // }
}
