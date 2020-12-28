package com.space.service;

import com.space.model.Ship;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;

/**
 * @author Sergey Ponomarev on 23.12.2020
 * @project cosmoport/com.space.service
 */

public interface ShipService {
  List<Ship> getAllShipsList(Map<String, String> params);
  Optional<Ship> getShipById(Long id);
  Ship updateShipById(Long id,Map<String,String> params);
  public void deleteShipById(Long id);
}
