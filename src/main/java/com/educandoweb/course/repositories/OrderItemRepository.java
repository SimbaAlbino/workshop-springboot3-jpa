package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.pk.OrderItemPK;

//por herdar do jparepository, é opicional colocar @Repository por já ser explícito no jpa
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}	
