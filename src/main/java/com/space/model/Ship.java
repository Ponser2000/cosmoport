package com.space.model;

import java.util.Objects;
import javax.persistence.*;
import java.util.Date;


/**
 * @author Sergey Ponomarev on 23.12.2020
 * @project cosmoport/com.space.repository.dto
 */


@Entity
@Table(name = "ship")
public class Ship {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String planet;
  @Enumerated(EnumType.STRING)
  private ShipType shipType;
  private Date prodDate;
  private boolean isUsed;
  private double speed;
  private int crewSize;
  private double rating;

  public Ship() {
  }

  public Ship(String name, String planet, ShipType shipType, Date prodDate, boolean isUsed,
      double speed, int crewSize, double rating) {
    this.name = name;
    this.planet = planet;
    this.shipType = shipType;
    this.prodDate = prodDate;
    this.isUsed = isUsed;
    this.speed = speed;
    this.crewSize = crewSize;
    this.rating = rating;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPlanet() {
    return planet;
  }

  public void setPlanet(String planet) {
    this.planet = planet;
  }

  public ShipType getShipType() {
    return shipType;
  }

  public void setShipType(ShipType shipType) {
    this.shipType = shipType;
  }

  public Date getProdDate() {
    return prodDate;
  }

  public void setProdDate(Date prodDate) {
    this.prodDate = prodDate;
  }

  public boolean isUsed() {
    return isUsed;
  }

  public void setUsed(boolean used) {
    isUsed = used;
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public int getCrewSize() {
    return crewSize;
  }

  public void setCrewSize(int crewSize) {
    this.crewSize = crewSize;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ship ship = (Ship) o;
    return Objects.equals(id, ship.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }


}
