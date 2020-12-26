package com.space.service.impl;

import com.space.model.Ship;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

/**
 * @author Sergey Ponomarev on 26.12.2020
 * @project cosmoport/com.space.service.impl
 */

public class ShipSpecification implements Specification<Ship> {

  private String shipFieldName;
  private String operation;
  private Object shipFieldValue;

  ShipSpecification(String shipFieldName, String operation, Object shipFieldValue) {
    this.shipFieldName = shipFieldName;
    this.operation = operation;
    this.shipFieldValue = shipFieldValue;
  }

  @Override
  public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

    Class<?> clazz = root.get(shipFieldName).getJavaType();

    switch (operation) {
      case "equal":
        if (clazz == String.class) {
          return criteriaBuilder.like(root.get(shipFieldName), "%" + shipFieldValue + "%");
        } else {
          return criteriaBuilder.equal(root.get(shipFieldName), shipFieldValue);
        }
      case "greater":
        if (clazz == Date.class) {
          return criteriaBuilder.greaterThanOrEqualTo(root.get(shipFieldName), (Date) shipFieldValue);
        } else {
          return criteriaBuilder.greaterThanOrEqualTo(root.get(shipFieldName), shipFieldValue.toString());
        }
      case "less":
        if (clazz == Date.class) {
          return criteriaBuilder.lessThanOrEqualTo(root.get(shipFieldName), (Date) shipFieldValue);
        } else {
          return criteriaBuilder.lessThanOrEqualTo(root.get(shipFieldName), shipFieldValue.toString());
        }
    }
    return null;
  }

}