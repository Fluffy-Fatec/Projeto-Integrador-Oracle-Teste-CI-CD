package com.fluffy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fluffy.backend.entity.Command;
import com.fluffy.backend.entity.Product;
import com.fluffy.backend.entity.ProductCommand;

@Repository
public interface ProductCommandRepository extends JpaRepository<ProductCommand, Integer> {

	ProductCommand findByProductAndCommand(Product product, Command command);

}
