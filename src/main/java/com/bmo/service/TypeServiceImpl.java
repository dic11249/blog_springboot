package com.bmo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmo.NotFoundException;
import com.bmo.dao.TypeRepositiory;
import com.bmo.po.Type;

/**
 * @author Bmo  2019-12-12  
 *
 */
@Service
public class TypeServiceImpl implements TypeService{
	
	@Autowired TypeRepositiory typeRepositiory;

	@Transactional
	@Override
	public Type save(Type type) {
		return typeRepositiory.save(type);
	}

	@Transactional
	@Override
	public Type getType(Long id) {
		return typeRepositiory.findById(id).orElse(null);
	}
	
	@Override
	public Type getTypeByName(String name) {
		return typeRepositiory.findByName(name);
	}

	@Transactional
	@Override
	public Page<Type> listType(Pageable pageable) {
		return typeRepositiory.findAll(pageable);
	}

	@Transactional
	@Override
	public Type updateType(Long id, Type type) {
		Type t = typeRepositiory.findById(id).orElse(null);
		if(t == null) {
			throw new NotFoundException("該分類已不存在");
		}
		BeanUtils.copyProperties(type, t);
		return typeRepositiory.save(t);
	}

	@Transactional
	@Override
	public void deleteType(Long id) {
		typeRepositiory.deleteById(id);		
	}


}
