package com.space.controller;

import com.space.model.Ship;
import com.space.service.ShipService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sergey Ponomarev on 23.12.2020
 * @project cosmoport/com.space.controller
 */

@Controller
@RequestMapping("/rest")
public class ShipController {
  private final ShipService shipService;

  @Autowired
  public ShipController(ShipService shipService) {
    this.shipService = shipService;
  }

  @GetMapping("/ships")
  public ResponseEntity<List<Ship>> getAllShips(){
    List<Ship> result = shipService.getAllShips();
    return ResponseEntity.ok().body(result);
  }
}
