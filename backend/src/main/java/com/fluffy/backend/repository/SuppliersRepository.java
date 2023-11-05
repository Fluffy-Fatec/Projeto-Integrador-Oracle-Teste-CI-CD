package com.fluffy.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fluffy.backend.entity.Suppliers;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Long> {

	public List<Suppliers> findByName(String name);

	@Query("SELECT s FROM Suppliers s WHERE s.name = :name")
	Suppliers findBySupplierName(@Param("name") String name);

}
