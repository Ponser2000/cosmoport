package com.space.controller;

import com.space.model.Ship;
import com.space.service.ShipService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
  public ResponseEntity<?> getAllShips(@RequestParam Map<String, String> params){

    if (!params.containsKey("order")) {
      params.put("order","id");
    }
    if(!params.containsKey("pageNumber")) {
      params.put("pageNumber","0");
    }
    if(!params.containsKey("pageSize")){
      params.put("pageSize","3");
    }

    if(params.containsKey("isUsed")){
      params.put("isUsed","true");
    } else {
      params.put("isUsed","false");
    }

    Page<Ship> result = shipService.getAllShips(params);
    return new ResponseEntity<>(result.getContent(), HttpStatus.OK);
  }
}
