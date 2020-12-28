package com.space.service.impl;

import com.space.controller.ShipOrder;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import com.space.model.Ship;
import com.space.service.ShipService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author Sergey Ponomarev on 23.12.2020
 * @project cosmoport/com.space.service.impl
 */


@Service
public class ShipServiceImpl implements ShipService {
  private final ShipRepository repository;

  @Autowired
  public ShipServiceImpl(ShipRepository repository) {
    this.repository = repository;
  }


  public List<Ship> getAllShipsList(Map<String, String> params){

    List<Ship> ships = repository.findAll();
    ships = getFilteredListWithParams(ships,params);
    return ships;
  }

 public Ship getShipById(Long id){
    return repository.findById(id);
 }

  public List<Ship>  getFilteredListWithParams(List<Ship> shipsList, Map<String, String> params){

    List<Ship> ships = new ArrayList<>(shipsList);

    if (params.containsKey("name")) {
      ships = ships.stream()
          .filter((elm)-> elm.getName().contains(params.get("name")))
          .collect(Collectors.toList());
    }

    if (params.containsKey("planet")) {
      ships = ships.stream()
          .filter((elm)-> elm.getPlanet().contains(params.get("planet")))
          .collect(Collectors.toList());
    }

    if (params.containsKey("shipType")) {
      ships = ships.stream()
          .filter((elm)-> elm.getShipType().equals(ShipType.valueOf(params.get("shipType"))))
          .collect(Collectors.toList());
    }

    if (params.containsKey("after")) {
      ships = ships.stream()
          .filter((elm)-> elm.getProdDate().after(new Date(Long.parseLong(params.get("after")))))
          .collect(Collectors.toList());
    }

    if (params.containsKey("before")) {
      ships = ships.stream()
          .filter((elm)-> elm.getProdDate().before(new Date(Long.parseLong(params.get("before")))))
          .collect(Collectors.toList());
    }

    if (params.containsKey("isUsed")) {
      ships = ships.stream()
          .filter((elm) -> elm.isUsed() == Boolean.parseBoolean(params.get("isUsed")))
          .collect(Collectors.toList());
    }

    if (params.containsKey("minSpeed")) {
      ships = ships.stream()
          .filter((elm) -> elm.getSpeed()>=Double.parseDouble(params.get("minSpeed")))
          .collect(Collectors.toList());
    }

    if (params.containsKey("maxSpeed")) {
      ships = ships.stream()
          .filter((elm) -> elm.getSpeed()<=Double.parseDouble(params.get("maxSpeed")))
          .collect(Collectors.toList());
    }

    if (params.containsKey("minCrewSize")) {
      ships = ships.stream()
          .filter((elm) -> elm.getCrewSize()>=Integer.parseInt(params.get("minCrewSize")))
          .collect(Collectors.toList());
    }

    if (params.containsKey("maxCrewSize")) {
      ships = ships.stream()
          .filter((elm) -> elm.getCrewSize()<=Integer.parseInt(params.get("maxCrewSize")))
          .collect(Collectors.toList());
    }

    if (params.containsKey("minRating")) {
      ships = ships.stream()
          .filter((elm) -> elm.getRating()>=Double.parseDouble(params.get("minRating")))
          .collect(Collectors.toList());
    }

    if (params.containsKey("maxRating")) {
      ships = ships.stream()
          .filter((elm) -> elm.getRating()<=Double.parseDouble(params.get("maxRating")))
          .collect(Collectors.toList());
    }

    ShipOrder order = params.containsKey("order") ? ShipOrder.valueOf(params.get("order")) : ShipOrder.ID;

    int pageSize = params.containsKey("pageSize") ? Integer.parseInt(params.get("pageSize")) : 3;
    int pageNumber = params.containsKey("pageNumber") ? Integer.parseInt(params.get("pageNumber")) : 0;

    switch (order) {
      case ID: {
        Comparator<Ship> compareById = Comparator.comparing(Ship::getId);
        ships = ships.stream()
            .sorted(compareById)
            .collect(Collectors.toList());
        break;
      }
      case SPEED: {
        Comparator<Ship> compareBySpeed = Comparator.comparing(Ship::getSpeed);
        ships = ships.stream()
            .sorted(compareBySpeed)
            .collect(Collectors.toList());
        break;
      }
      case DATE: {
        Comparator<Ship> compareByProdDate = Comparator.comparing(Ship::getProdDate);
        ships = ships.stream()
            .sorted(compareByProdDate)
            .collect(Collectors.toList());
        break;
      }
      case RATING: {
        Comparator<Ship> compareByRating = Comparator.comparing(Ship::getRating);
        ships = ships.stream()
            .sorted(compareByRating)
            .collect(Collectors.toList());
        break;
      }
    }

    ships = ships.stream()
        .skip(pageNumber*pageSize)
        .limit(pageSize)
        .collect(Collectors.toList());

    return ships;
  }





}
