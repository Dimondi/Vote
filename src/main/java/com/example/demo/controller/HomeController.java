package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.IPollRepository;
import com.example.demo.repository.IUserRepository;
import org.hibernate.cfg.annotations.reflection.XMLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class HomeController {
	@Autowired
	private IUserRepository userRepository;

	@GetMapping("/mainPage")
	public String mainPage(){
		return "mainPage";
	}

	@GetMapping("/")
	public String page(){
		return "mainPage";
	}

	@GetMapping("/checker")
	public String home()
	{
		return "user";
	}

	@GetMapping("/addUser")
	public String addUser(@RequestParam String name, @RequestParam String surname)
	{
		User user = new User(name,surname);
		userRepository.save(user);
		return "user";
	}

	@GetMapping("/showUserById")
	public String showUserById(@RequestParam String id,Model model){
		User user = userRepository.findById(Long.valueOf(id)).orElse(new User());
		model.addAttribute(user);
		return "showUser";
	}

	@GetMapping("/showUserGreaterThanId")
	public String showUserGreaterThanId(@RequestParam String id,Model model){
		List<User> user = userRepository.findByIdGreaterThan(Long.valueOf(id));
		model.addAttribute("user",user);
		return "showUser";
	}

	@GetMapping("/showUserByName")
	public String showUserByName(@RequestParam String name,Model model){
		List<User> user = userRepository.findByFirstname(name);
		model.addAttribute("user",user);
		return "showUser";
	}

	@GetMapping("/showUserByNameSorted")
	public String showUserByNameSorted(@RequestParam String name,Model model){
		List<User> user = userRepository.findByFirstnameSorted(name);
		System.out.println(user);
		model.addAttribute("user",user);
		return "showUser";
	}

	@GetMapping("/users")
	@ResponseBody
	public List<User> showUserByIdNotView(){
		return (List<User>) userRepository.findAll();
	}

	@GetMapping("/user/{id}")
	@ResponseBody
	public Optional<User> showUser(@PathVariable("id") int id){
		return userRepository.findById((long) id);
	}

	@DeleteMapping("/user/{id}")
	@ResponseBody
	public String deleteUser(@PathVariable("id") long id){
		User user = userRepository.findById(id).orElse(new User());
		userRepository.delete(user);
		return user + " deleted";
	}
}
