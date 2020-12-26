package com.space.service.impl;

import com.space.model.Ship;
import com.space.model.ShipType;
import java.util.Date;
import java.util.Map;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Sergey Ponomarev on 26.12.2020
 * @project cosmoport/com.space.service.impl
 */

public class SpecificationUtils {

  private SpecificationBuilder builderCriteria;

  public Specification<Ship> createSpecification(Map<String, String> params) {

    builderCriteria = new SpecificationBuilder();

    if (params.containsKey("name")) {
      builderCriteria.addNewCriteria("name", "equal", params.get("name"));
    }

    if (params.containsKey("planet")) {
      builderCriteria.addNewCriteria("planet", "equal", params.get("planet"));
    }
    if (params.containsKey("shipType")) {
      builderCriteria.addNewCriteria("shipType", "equal", ShipType.valueOf(params.get("shipType")));
    }

    if (params.containsKey("isUsed")) {
      builderCriteria.addNewCriteria("isUsed", "equal", Boolean.valueOf(params.get("isUsed")));
    }

    if (params.containsKey("minSpeed")) {
      builderCriteria.addNewCriteria("speed", "greater", Double.parseDouble(params.get("minSpeed")));
    }

    if (params.containsKey("maxSpeed")) {
      builderCriteria.addNewCriteria("speed", "less", Double.parseDouble(params.get("maxSpeed")));
    }

    if (params.containsKey("minCrewSize")) {
      builderCriteria.addNewCriteria("crewSize", "greater", Integer.parseInt(params.get("minCrewSize")));
    }

    if (params.containsKey("maxCrewSize")) {
      builderCriteria.addNewCriteria("crewSize", "less", Integer.parseInt(params.get("maxCrewSize")));
    }

    if (params.containsKey("minRating")) {
      builderCriteria.addNewCriteria("rating", "greater", Double.parseDouble(params.get("minRating")));
    }

    if (params.containsKey("maxRating")) {
      builderCriteria.addNewCriteria("rating", "less", Double.parseDouble(params.get("maxRating")));
    }

    if (params.containsKey("after")) {
      builderCriteria.addNewCriteria("prodDate", "greater", new Date(params.get("after")));
    }

    if (params.containsKey("beforer")) {
      builderCriteria.addNewCriteria("prodDate", "less", new Date(params.get("before")));
    }

    return builderCriteria.build();
  }
}
