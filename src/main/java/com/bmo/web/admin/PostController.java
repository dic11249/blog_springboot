package com.bmo.web.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bmo.po.Post;
import com.bmo.po.User;
import com.bmo.service.PostService;
import com.bmo.service.TypeService;
import com.bmo.vo.PostQuery;



/**
 * @author Bmo  2019-12-11  
 *
 */
@Controller
@RequestMapping("/admin")
public class PostController {
	
	@Autowired 
	PostService postService;
	
	@Autowired
	TypeService typeService;
	
	//Post列表
	@GetMapping("/posts")
	public String posts(@PageableDefault(size = 5, sort= {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
						PostQuery post, Model model) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("page", postService.listPost(pageable, post));
		return "admin/posts";
	}
	
	//Post搜尋
	@PostMapping("/posts/search")
	public String search(@PageableDefault(size = 5, sort= {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
						PostQuery post, Model model) {
		System.out.println("title : "+post.getTitle());
		System.out.println("tyep : "+post.getTypeId());
		System.out.println("recommend : "+post.isRecommend());
		System.out.println("pageNumber "+pageable.getPageNumber() + pageable.hasPrevious() );
		
		model.addAttribute("page", postService.listPost(pageable, post));
		return "admin/posts :: postList";
	}
	
	//Post新增頁面
	@GetMapping("/posts/input")
	public String input(Model model) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("post", new Post());
		return "admin/posts-input";
	}
	
	//Post編輯頁面
	@GetMapping("/posts/{id}/input")
	public String editInput(@PathVariable Long id, Model model) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("post", postService.getPost(id));
		return "admin/posts-input";
	}
	
	//Post儲存
	@PostMapping("/posts")
	public String post(Post post, RedirectAttributes attributes, HttpSession session) {
		post.setUser((User)session.getAttribute("user"));
		post.setType(typeService.getType(post.getType().getId()));
		Post p;
		if(post.getId() == null) {
			p = postService.savePost(post);
		} else {
			p = postService.updatePost(post.getId(), post);
		}
		if(p == null) {
			attributes.addFlashAttribute("message", "操作失敗");
		} else {
			attributes.addFlashAttribute("message", "操作成功");
		}
		
		return "redirect:/admin/posts";
	}
	
	//Post刪除
	@GetMapping("/posts/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		postService.deletePost(id);
		attributes.addFlashAttribute("message", "刪除成功");
		return "redirect:/admin/posts";
	}

}
