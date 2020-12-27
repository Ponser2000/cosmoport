package com.space.service;

import com.space.model.Ship;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 * @author Sergey Ponomarev on 23.12.2020
 * @project cosmoport/com.space.service
 */

public interface ShipService {
  Page<Ship> getAllShips(Map<String, String> params);
  List<Ship> getAllShipsList(Map<String, String> params);
}
