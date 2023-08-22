package com.example.todo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo.app.model.ToDo;
import com.example.todo.app.service.ToDoService;
import com.example.todo.app.service.UserService;

@Controller
public class ToDoController {

	@Autowired
	private ToDoService service;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public String loginPage(){
		return "login";
	}
	
	@PostMapping({"/", "login"})
	public String welcomePage(ModelMap model,@RequestParam String userName,	@RequestParam String password){
		
		User user = userService.getUserByUserName(userName);
		
		if(user.getUserName().isEmpty() && user.getPassword().isEmpty()) {
			return "redirect:/login";
		}
		
		if(user.getPassword().equals(password)) {
			model.put("userName", userName);
			return "redirect:/viewToDoList";
		}
		
		model.put("errorMsg", "Please provide the correct username and password"); 
		return "login";
	}
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user, ModelMap model) {
		int count = userService.createNewUser(user);
		
		if(count !=1) {
			model.put("errorMsg", "Some issue occured with registration");
			return "register";
		}
		
		model.put("successMsg", "User created successfully");
		return "login";
		
	}
	
	
	@GetMapping({"/viewToDoList"})
	public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("list", service.getAllToDoItems());
		model.addAttribute("message", message);
		
		return "ViewToDoList";
	}
	
	
	@GetMapping("/updateToDoStatus/{id}")
	public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (service.updateStatus(id)) {
			redirectAttributes.addFlashAttribute("message", "Update Success");
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Update Failure");
		return "redirect:/viewToDoList";
	}
	
	@GetMapping("/addToDoItem")
	public String addToDoItem(Model model) {
		model.addAttribute("todo", new ToDo());
		
		return "AddToDoItem";
	}
	
	@PostMapping("/saveToDoItem")
	public String saveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
		if(service.saveOrUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Save Success");
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Save Failure");
		return "redirect:/addToDoItem";
	}
	
	@GetMapping("/editToDoItem/{id}")
	public String editToDoItem(@PathVariable Long id, Model model) {
		model.addAttribute("todo", service.getToDoItemById(id));
		
		return "EditToDoItem";
	}
	
	@PostMapping("/editSaveToDoItem")
	public String editSaveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
		if(service.saveOrUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Edit Success");
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Edit Failure");
		return "redirect:/editToDoItem/" + todo.getId();
	}
	
	@GetMapping("/deleteToDoItem/{id}")
	public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (service.deleteToDoItem(id)) {
			redirectAttributes.addFlashAttribute("message", "Delete Success");
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Delete Failure");
		return "redirect:/viewToDoList";
	}  
	
}
