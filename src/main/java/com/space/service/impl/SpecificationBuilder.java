package com.space.service.impl;

import com.space.model.Ship;
import com.space.model.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Sergey Ponomarev on 26.12.2020
 * @project cosmoport/com.space.service.impl
 */

public class SpecificationBuilder {

  private final List<ShipSpecification> criteriaList;

  public SpecificationBuilder() {
    criteriaList = new ArrayList<>();
  }

  public void addNewCriteria(String fieldName, String operation, Object fieldValue){
    criteriaList.add(new ShipSpecification(fieldName, operation, fieldValue));
  }

  @SuppressWarnings("unchecked")
  public Specification<Ship> build() {
    if (criteriaList.size() == 0) {
      return null;
    }
    Specification allCriteria = criteriaList.get(0);
    for (int i = 1; i < criteriaList.size(); i++) {
      allCriteria = Specification.where(allCriteria).and(criteriaList.get(i));
    }
    return allCriteria;
  }



}