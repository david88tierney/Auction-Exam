package com.codingdojo.wednesday.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.codingdojo.wednesday.models.Product;
import com.codingdojo.wednesday.models.User;
import com.codingdojo.wednesday.services.ProductService;
import com.codingdojo.wednesday.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	public UserService uS;
	public ProductService pS;
	
	public UserController(UserService uS, ProductService pS) {
		this.uS = uS;
		this.pS = pS;
	}
	
	@GetMapping("")
	public String showIndex(@ModelAttribute("user") User user) {
		return "index";
	}
	
	@PostMapping("")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult res, Model model) {
		if(res.hasErrors()) {
			return "index";
		} else {
			if(!user.getPassword().equals(user.getConfirm())) {
				model.addAttribute("userError", "Passwords dont match");
				return "index";
			} else {
				String pw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
				user.setPassword(pw);
				
				User exists= uS.findByEmail(user.getEmail());
				if(exists != null) {
					model.addAttribute("userError", "A user with this email already exists");
					return "index";
				} else {
					uS.create(user);
					return "redirect:/users";
				}
			}
		}
	}
		
	
	@PostMapping("/login")
	public String login( @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		if(email.length() < 1) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			return "index";
		}
		if(password.length() < 8) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			return "index";
		}
		
		User u = uS.findByEmail(email);
		
		if(u == null) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			return "index";
		} else {
			boolean matches = BCrypt.checkpw(password, u.getPassword());
			
			if(matches) {
				session.setAttribute("user", u.getId());
//				session.setAttribute("user", u.getName());
				return "redirect:/users/dashboard";
				} else {
					model.addAttribute("loginError", "Invalid Credentials");
					model.addAttribute("user", new User());
					return "index";
				}
			}
		}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("user");
		
		if(id == null) {
			return "redirect:/users";
		} else {
			User u = uS.findById(id);
			model.addAttribute("user", u);
			
			model.addAttribute("products", pS.findAll());		
			
			return "dashboard";
		}
	}
	
	@GetMapping("/add")
	public String addRoute(@ModelAttribute("product") Product product) {
		return "new";
	}
	
	
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("product")Product product, BindingResult res, Model model, HttpSession session) {

		
		if(res.hasErrors()) return "new";

		product.setUser(uS.findById((Long)session.getAttribute("user")));
		
		
		
		pS.create(product);
		return "redirect:/users/dashboard";
	}
	

	
	@GetMapping("products/{product_id}" )
	public String displayProduct(@PathVariable("product_id") Long product_id, @ModelAttribute("product") Product product, Model model, HttpSession session){
		
		Product viewProduct = pS.findById(product_id);
		
		model.addAttribute("viewProduct", viewProduct );
		
		return "view";
	}
	
	@PostMapping("{id}/update")
	public String edit(@PathVariable("id") Long id, @RequestParam("bid") int bid) {
		Product pro = pS.findById(id);
		pro.setBid(bid);
		pS.update(pro);
		return "redirect:/users/dashboard";
		
	}
	
	
	
}
	
