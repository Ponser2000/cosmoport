package com.space.service.impl;

import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import com.space.model.Ship;
import com.space.service.ShipService;
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
  private final SpecificationUtils specificationUtils;

  @Autowired
  public ShipServiceImpl(ShipRepository repository) {
    this.repository = repository;
    specificationUtils = new SpecificationUtils();
  }

  public Page<Ship> getAllShips(Map<String, String> params){
    Specification specification = specificationUtils.createSpecification(params);
    int pageNumber = Integer.parseInt(params.get("pageNumber"));
    int pageSize = Integer.parseInt(params.get("pageSize"));
    String order = params.get("order");
    Page<Ship> result = repository.findAll(specification, PageRequest.of(pageNumber,pageSize, Sort.by(order)));

     return result;
  }

  public List<Ship> getAllShipsList(Map<String, String> params){

    List<Ship> ships = repository.findAll();
    if (params.containsKey("name")) {
      ships = ships.stream()
          .filter((elm)-> elm.getName().contains(params.get("name")))
          .sorted((o1,o2) -> o1.getName().compareTo(o2.getName()))
          .collect(Collectors.toList());
    }

    if (params.containsKey("planet")) {
      ships = ships.stream()
          .filter((elm)-> elm.getPlanet().contains(params.get("planet")))
          .sorted((o1,o2) -> o1.getName().compareTo(o2.getName()))
          .collect(Collectors.toList());
    }


    return ships;
  }





}
