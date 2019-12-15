package com.bmo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javassist.NotFoundException;

/**
 * @author Bmo  2019-12-08  
 *
 */
@Controller
@RequestMapping("/admin")
public class IndexController {

	@GetMapping("/index")
	public String index() {
		return "admin/post";
	}
}
