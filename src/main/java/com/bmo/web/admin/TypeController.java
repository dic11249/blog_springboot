package com.bmo.web.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bmo.po.Type;
import com.bmo.service.TypeService;

/**
 * @author Bmo  2019-12-12  
 *
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
	
	@Autowired TypeService typeSvc;
	
	@GetMapping("/types")
	public String types(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
						Pageable pageable, Model model) {
		model.addAttribute("page", typeSvc.listType(pageable));		
		return "admin/types";
	}
	
	@GetMapping("/types/input")
	public String input(Model model) {
		model.addAttribute("type", new Type());
		return "admin/type-input";
	}
	
	@GetMapping("/types/{id}/input")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("type", typeSvc.getType(id));
		return "admin/type-input";
	}
	
	@PostMapping("/types")
	public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
		Type type1 = typeSvc.getTypeByName(type.getName());
		if(type1 != null) {
			result.rejectValue("name", "nameError", "該分類已存在");
			return "admin/type-input";
		}
		
		if(result.hasErrors()) {
			return "admin/type-input";
		}
		Type t = typeSvc.save(type);
		if (t == null) {
			attributes.addFlashAttribute("message", "新增失敗");
		} else {
			attributes.addFlashAttribute("message", "新增成功");
		}
		return "redirect:/admin/types";
	}
	
	@PutMapping("/types")
	public String editPost(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
		Type type1 = typeSvc.getTypeByName(type.getName());
		if(type1 != null) {
			result.rejectValue("name", "nameError", "該分類已存在");
			return "admin/type-input";
		}
		
		if(result.hasErrors()) {
			return "admin/type-input";
		}
		Type t = typeSvc.updateType(type.getId(), type);
		if (t == null) {
			attributes.addFlashAttribute("message", "更新失敗");
		} else {
			attributes.addFlashAttribute("message", "更新成功");
		}
		return "redirect:/admin/types";
	}
	
	@GetMapping("/types/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		typeSvc.deleteType(id);
		attributes.addFlashAttribute("message", "刪除成功");
		return "redirect:/admin/types";
	}

}
