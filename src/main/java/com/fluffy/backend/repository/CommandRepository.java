package com.fluffy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fluffy.backend.entity.Command;

@Repository
public interface CommandRepository extends JpaRepository<Command, Integer> {

	Command findByCommandNumber(int i);

}
