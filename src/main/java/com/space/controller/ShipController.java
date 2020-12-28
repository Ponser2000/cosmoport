package com.space.controller;

import com.space.model.Ship;
import com.space.service.ShipService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<?> getAllShipsList(@RequestParam Map<String, String> params) {
    List<Ship> result = shipService.getAllShipsList(params);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/ships/count")
  public ResponseEntity<?> getAllShipsListCount(@RequestParam Map<String, String> params) {
    params.put("pageNumber","0");
    params.put("pageSize",Integer.toString(Integer.MAX_VALUE));
    int result = shipService.getAllShipsList(params).size();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/ships/{id}")
  public ResponseEntity<?> getShipsById(@PathVariable String id){
    Long value;
    if ( (id != null) && id.matches("-?\\d++") ) {
       value = Long.parseLong(id);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    Ship ship = shipService.getShipById(value);

    if ( ship != null) {
      return new ResponseEntity<>(ship, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}