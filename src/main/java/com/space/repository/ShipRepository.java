package com.space.repository;

import com.space.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sergey Ponomarev on 23.12.2020
 * @project cosmoport/com.space.repository
 */

@Repository
public interface ShipRepository extends CrudRepository<Ship, Long> {

}
