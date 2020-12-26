package com.space.service.impl;

import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import com.space.model.Ship;
import com.space.service.ShipService;
import java.util.List;
import java.util.Map;
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





}
