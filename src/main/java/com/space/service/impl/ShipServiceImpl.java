package com.space.service.impl;

import com.space.repository.ShipRepository;
import com.space.model.Ship;
import com.space.service.ShipService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

  public List<Ship> getAllShips(){
    List<Ship> result = repository.findAll();
    return result;
  }
}
