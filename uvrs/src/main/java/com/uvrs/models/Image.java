package com.uvrs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "image_id")
  private int imageId;

  @Column(name = "image")
  private String image;

  @JsonIgnore
  @OneToOne(fetch = FetchType.EAGER)
  private Vehicle vehicle;

  public Image() {}

  public Image(int imageId, String image, Vehicle vehicle) {
    this.imageId = imageId;
    this.image = image;
    this.vehicle = vehicle;
  }

  public int getImageId() {
    return imageId;
  }

  public void setImageId(int imageId) {
    this.imageId = imageId;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }
}
