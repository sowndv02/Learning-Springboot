package sowndv02.learning.shop.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import sowndv02.learning.shop.model.Category;
import sowndv02.learning.shop.repository.CategoryRepository;

@Controller
@RequestMapping("categories")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@GetMapping("delete/{id}")
	public String delete(RedirectAttributes attributes, @PathVariable("id") Long id) {
		categoryRepository.deleteById(id);
		attributes.addAttribute("message", "Delete successful!");
		return "redirect:/categories";
	}
	
	@GetMapping
	public String list(ModelMap model, @RequestParam Optional<String> message) {
		Iterable<Category> list = categoryRepository.findAll();
		
		if(message.isPresent()) {
			model.addAttribute("message", message.get());
		}
		
		model.addAttribute("categories", list);
		return "categories/list";
	}
	
	@GetMapping("sort")
	public String sort(ModelMap model, @RequestParam Optional<String> message, @SortDefault(sort = "name", direction = Direction.ASC) Sort sort) {
		Iterable<Category> list = categoryRepository.findAll(sort);
		
		if(message.isPresent()) {
			model.addAttribute("message", message.get());
		}
		
		model.addAttribute("categories", list);
		return "categories/sort";
	}
	
	@GetMapping("paginate")
	public String pagingnate(ModelMap model, @RequestParam Optional<String> message, 
			@PageableDefault(size = 5, sort="name", direction = Direction.ASC) Pageable pageable) {
		Page<Category> pages = categoryRepository.findAll(pageable);
		
		if(message.isPresent()) {
			model.addAttribute("message", message.get());
		}
		List<Sort.Order> sortOrder = pages.getSort().stream().collect(Collectors.toList());
		if(sortOrder.size() > 0) {
			Sort.Order order = sortOrder.get(0);
			model.addAttribute("sort", order.getProperty() + "," + (order.getDirection()==Sort.Direction.ASC?"asc":"desc"));
		}else {
			model.addAttribute("sort", "name");
		}
		model.addAttribute("pages", pages);
		return "categories/paginate";
	}
	
	
	@GetMapping(value= {"newOrEdit", "newOrEdit/{id}"})
	public String newOrEdit(ModelMap model, @PathVariable(name = "id", required = false) Optional<Long> id) {
		
		Category category;
		if(id.isPresent()) {
			Optional<Category> existedCategory = categoryRepository.findById(id.get());
			category = existedCategory.isPresent()? existedCategory.get(): new Category();
			
		}else{
			category = new Category();
		}
		model.addAttribute("category", category);
		
		return "categories/newOrEdit";
	}
	
	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(RedirectAttributes attribute,@Valid Category item, BindingResult result, ModelMap model ) {
		
		if(result.hasErrors()) {
			model.addAttribute("category", item);
			return "categories/newOrEdit";
		}
		
		categoryRepository.save(item); // Nếu tồn tại tự động cập nhật nếu chưa tồn tại thì tự add
		
		attribute.addAttribute("message", "New category is saved!");
		
		return "redirect:/categories";
	}
	
	
	
}
