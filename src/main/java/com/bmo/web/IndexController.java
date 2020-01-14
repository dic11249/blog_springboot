package com.bmo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bmo.service.CommentService;
import com.bmo.service.PostService;
import com.bmo.service.TypeService;


/**
 * @author Bmo  2019-12-08  
 *
 */
@Controller
public class IndexController {
	
	@Autowired 
	PostService postService;
	
	@Autowired 
	TypeService typeService;
	
	@Autowired
	CommentService commentService;

	@GetMapping("/")
	public String index(@PageableDefault(size = 5, sort= {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
						Model model) {
		
		model.addAttribute("page", postService.listPost(pageable));
		model.addAttribute("types", typeService.listTypeTop(6));
		model.addAttribute("recommends", postService.listRecommendPostTop(6));
		return "blog/index";
	}
	
	@GetMapping("/posts/{id}")
	public String post(@PathVariable Long id, Model model) {
		model.addAttribute("post", postService.getPost(id));
		model.addAttribute("comments", commentService.listByPostId(id));
		System.out.println(commentService.listByPostId(id).size());
		return "blog/post";
	}
}
