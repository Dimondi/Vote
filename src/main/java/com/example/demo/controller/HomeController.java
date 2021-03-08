package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.IPollRepository;
import com.example.demo.repository.IUserRepository;
import org.hibernate.cfg.annotations.reflection.XMLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Controller
public class HomeController {
	@Autowired
	private IUserRepository userRepository;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	ExecutorService threadExecutor = Executors.newCachedThreadPool();

	@GetMapping("/admin")
	public String adminPage(){
		List<User> users = userRepository.findAll();
		return "admin";
	}

	@GetMapping("/profile/edit")
	@ResponseBody
	public String profile(@RequestParam String firstname, @RequestParam String lastname,
						  @RequestParam String email, @RequestParam String phone,
						  @RequestParam String gender, @RequestParam String username,
						  Authentication authentication){
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		User user = userRepository.findByUsername(userPrincipal.getUsername());
		if(!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !username.isEmpty() && !gender.isEmpty()) {
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setEmail(email);
			user.setUsername(username);
			user.setGender(gender);
			user.setPhone(phone);
			userRepository.save(user);
			return "Updated";
		}
		else {
			return "All fields are required!";
		}
	}

	@GetMapping("/editPassword")
	@ResponseBody
	public String editPassword(@RequestParam String current, @RequestParam String newPassword,
							   @RequestParam String confirm,
							   Authentication authentication) throws ExecutionException, InterruptedException {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		User user = userRepository.findByUsername(userPrincipal.getUsername());
		boolean result = passwordEncoder.matches(current, user.getPassword());
		if (!result) {
			return "Invalid Password!";
		}
		if(newPassword.equals(confirm)) {
			Future<Boolean> futureCall = threadExecutor.submit(new UsernamePasswordChecker(null,newPassword));
			boolean isCorrect = futureCall.get();
			if(!isCorrect){
				return "Invalid Format of password!";
			}
			user.setPassword(passwordEncoder.encode(newPassword));
			userRepository.save(user);
			return "Updated";
		}
		else return "passwords don't match";
	}

	@GetMapping("/profile")
	public String profile(Authentication authentication, Model model){
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		User user = userRepository.findByUsername(userPrincipal.getUsername());
		model.addAttribute("user",user);
		return "profile_page";
	}

	@GetMapping("/mainPage")
	public String mainPage(Model model,Authentication authentication){
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		model.addAttribute("roles",userPrincipal.getAuthorities());
		return "mainPage";
	}

	@GetMapping("/")
	public String page(Model model,Authentication authentication){
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		model.addAttribute("roles",userPrincipal.getAuthorities());
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
