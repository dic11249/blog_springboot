package com.bmo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javassist.NotFoundException;

/**
 * @author Bmo  2019-12-08  
 *
 */
@Controller
public class IndexController {

	@GetMapping("/{id}/{name}")
	public String index(@PathVariable Integer id, @PathVariable String name) {
		System.out.println("-----index-----");
		return "admin/index";
	}
}
