package com.bmo.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bmo.po.Post;
import com.bmo.service.PostService;



/**
 * @author Bmo  2019-12-11  
 *
 */
@Controller
@RequestMapping("/admin")
public class PostController {
	
	@Autowired PostService postService;

	@GetMapping("/posts")
	public String posts(@PageableDefault(size = 5, sort= {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Post post, Model model) {
		model.addAttribute("page", postService.listPost(pageable, post));
		return "admin/post";
	}
}
