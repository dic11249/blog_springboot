package com.bmo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bmo.po.Type;

/**
 * @author Bmo  2019-12-12  
 *
 */
public interface TypeService {

	Type save(Type type);
	
	Type getType(Long id);
	
	Type getTypeByName(String name);
	
	Page<Type> listType(Pageable pageable);
	
	List<Type> listType();
	
	List<Type> listTypeTop(Integer size);
	
	Type updateType(Long id, Type type);
	
	void deleteType(Long id);
}
