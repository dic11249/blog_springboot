package com.bmo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmo.po.Type;

public interface TypeRepositiory extends JpaRepository<Type, Long>{

	Type findByName(String name);
}
