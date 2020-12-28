package com.space.controller;

import com.space.model.Ship;
import com.space.service.ShipService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
  @ResponseBody
  public ResponseEntity<?> getAllShipsList(@RequestParam Map<String, String> params) {
    List<Ship> result = shipService.getAllShipsList(params);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/ships/count")
  @ResponseBody
  public ResponseEntity<?> getAllShipsListCount(@RequestParam Map<String, String> params) {
    params.put("pageNumber","0");
    params.put("pageSize",Integer.toString(Integer.MAX_VALUE));
    int result = shipService.getAllShipsList(params).size();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/ships/{id}")
  @ResponseBody
  public ResponseEntity<?> getShipsById(@PathVariable String id){
    Long value;
    if ( (id != null) && id.matches("-?\\d++") ) {
       value = Long.parseLong(id);
       if (value == 0) {
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    Optional<Ship> ship = shipService.getShipById(value);

    if ( ship.isPresent()) {
      return new ResponseEntity<>(ship.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/ships/{id}")
  @ResponseBody
  public ResponseEntity<?> deleteShipsById(@PathVariable String id){
    Long value;
    if ( (id != null) && id.matches("-?\\d++") ) {
      value = Long.parseLong(id);
      if (value == 0) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Optional<Ship> ship = shipService.getShipById(value);

    if ( ship.isPresent()) {
      shipService.deleteShipById(value);
      return new ResponseEntity<>( HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/ships/{id}")
  @ResponseBody
  public ResponseEntity<?> updateShipsById(@PathVariable String id, @RequestBody Map<String,String> params){

    Long value;

    if ( (id != null) && id.matches("-?\\d++") ) {
      value = Long.parseLong(id);
      if (value == 0) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    Optional<Ship> shipTest = shipService.getShipById(value);

    if (shipTest.isPresent()) {
        if (params.size()>0) {
          Ship updateShip = shipService.updateShipById(value, params);
          return new ResponseEntity<>(updateShip, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(shipTest.get(), HttpStatus.OK);
        }

    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

}