package sowndv02.learning.shop.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import sowndv02.learning.shop.model.Category;
import sowndv02.learning.shop.model.InventoryReport;
import sowndv02.learning.shop.model.Product;
import sowndv02.learning.shop.repository.CategoryRepository;
import sowndv02.learning.shop.repository.ProductRepository;

@Controller
@RequestMapping("products")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryRepository.findAll();
	}
	
	@GetMapping("reportInventory")
	public String reportInventory(ModelMap model, 
			@RequestParam Optional<String> message,
			@PageableDefault(size = 5, page = 0) Pageable pageable) {
		
		Page<InventoryReport> pages = productRepository.reportInventory(pageable);
		if(message.isPresent()) {
			model.addAttribute("message", message.get());
		}
		model.addAttribute("pages",pages);
		return "products/reportInventory";
	}
	
	@GetMapping("searchByName")
	public String searchByName(ModelMap model, 
			@RequestParam Optional<String> message, 
			@RequestParam Optional<String> name, 
			@PageableDefault(size = 5, sort = "name", direction = Direction.ASC) Pageable pageable) {
		
		Page<Product> pages = productRepository.searchByName("%" + name.orElse("")+ "%",pageable);
		
		if(message.isPresent()) {
			model.addAttribute("message", message.get());
		}
		
		List<Sort.Order> sortOrders = pages.getSort().stream().collect(Collectors.toList());
		
		if(sortOrders.size() > 0) {
			Sort.Order order = sortOrders.get(0);
			model.addAttribute("sort", order.getProperty() + "," + 
			(order.getDirection() == Sort.Direction.ASC ? "asc" : "desc"));
		}else {
			model.addAttribute("sort", "name,asc");
		}
		model.addAttribute("pages", pages);
		
		return "products/searchByName";
	}
	
	@GetMapping("findByPrice")
	public String findByPrice(ModelMap model, 
			@RequestParam Optional<String> message, 
			@RequestParam Optional<Double> min, 
			@RequestParam Optional<Double> max,
			@PageableDefault(size = 5, sort = "name", direction = Direction.ASC) Pageable pageable) {
		
		Page<Product> pages = productRepository.findByPriceBetween(min.orElse(0d), max.orElse(Double.MAX_VALUE),pageable);
		
		if(message.isPresent()) {
			model.addAttribute("message", message.get());
		}
		
		List<Sort.Order> sortOrders = pages.getSort().stream().collect(Collectors.toList());
		
		if(sortOrders.size() > 0) {
			Sort.Order order = sortOrders.get(0);
			model.addAttribute("sort", order.getProperty() + "," + 
			(order.getDirection() == Sort.Direction.ASC ? "asc" : "desc"));
		}else {
			model.addAttribute("sort", "name,asc");
		}
		model.addAttribute("pages", pages);
		
		return "products/findByPrice";
	}
	
	
	@GetMapping("searchByPrice")
	public String searchByPrice(ModelMap model, 
			@RequestParam Optional<String> message, 
			@RequestParam Optional<Double> min, 
			@RequestParam Optional<Double> max,
			@PageableDefault(size = 5, sort = "name", direction = Direction.ASC) Pageable pageable) {
		
		Page<Product> pages = productRepository.searchByPrice(min.orElse(0d), max.orElse(Double.MAX_VALUE),pageable);
		
		if(message.isPresent()) {
			model.addAttribute("message", message.get());
		}
		
		List<Sort.Order> sortOrders = pages.getSort().stream().collect(Collectors.toList());
		
		if(sortOrders.size() > 0) {
			Sort.Order order = sortOrders.get(0);
			model.addAttribute("sort", order.getProperty() + "," + 
			(order.getDirection() == Sort.Direction.ASC ? "asc" : "desc"));
		}else {
			model.addAttribute("sort", "name,asc");
		}
		model.addAttribute("pages", pages);
		
		return "products/searchByPrice";
	}
	
	
	@GetMapping("delete/{id}")
	public String delete(RedirectAttributes attributes, @PathVariable("id") Long id) {
		productRepository.deleteById(id);
		attributes.addAttribute("message", "Delete product successful");
		return "redirect:/products";
		
	}
	
	@GetMapping
	public String list(ModelMap model, @RequestParam Optional<String> message, 
			@PageableDefault(size = 5, sort = "name", direction = Direction.ASC) Pageable pageable) {
		Page<Product> pages = productRepository.findAll(pageable);
		
		if(message.isPresent()) {
			model.addAttribute("message", message.get());
		}
		
		List<Sort.Order> sortOrders = pages.getSort().stream().collect(Collectors.toList());
		
		if(sortOrders.size() > 0) {
			Sort.Order order = sortOrders.get(0);
			model.addAttribute("sort", order.getProperty() + "," + 
			(order.getDirection() == Sort.Direction.ASC ? "asc" : "desc"));
		}else {
			model.addAttribute("sort", "name,asc");
		}
		model.addAttribute("pages", pages);
		
		return "products/list";
	}
	
	@GetMapping(value = {"newOrEdit", "newOrEdit/{id}"})
	public String newOrEdit(ModelMap model, @PathVariable(name = "id", required = false) Optional<Long> id) {
		Product product;
		if(id.isPresent()) {
			Optional<Product> existed = productRepository.findById(id.get());
			product = existed.isPresent() ? existed.get() : new Product();
		}else{
			product = new Product();
		}
		
		model.addAttribute("product", product);
		return "products/newOrEdit";
	}
	
	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(ModelMap model, RedirectAttributes attributies, @Valid Product product, BindingResult result) {
		
		if(result.hasErrors()) {
			model.addAttribute("product", product);
			
			return "products/newOrEdit";
		}
		
		productRepository.save(product);
		
		
		attributies.addAttribute("message", "New product is saved!");
		return "redirect:/products";
	}
}
