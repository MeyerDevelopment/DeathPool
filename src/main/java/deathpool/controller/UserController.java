package deathpool.controller;

import java.util.Iterator;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import deathpool.beans.Role;
import deathpool.beans.User;
import deathpool.repository.UserRepository;
import deathpool.service.UserService;

@Controller
public class UserController {

 @Autowired
 private UserService userService;
 
 @Autowired
	UserRepository repo;
 
 @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
 public ModelAndView login() {
  ModelAndView model = new ModelAndView();
  
  model.setViewName("user/login");
  return model;
 }
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
 public ModelAndView signup() {
  ModelAndView model = new ModelAndView();
  User user = new User();
  model.addObject("user", user);
  model.setViewName("user/signup");
  
  return model;
 }
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
 public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
  ModelAndView model = new ModelAndView();
  User userExists = userService.findUserByEmail(user.getEmail());
  
  if(userExists != null) {
   bindingResult.rejectValue("email", "error.user", "This email already exists!");
  }
  if(bindingResult.hasErrors()) {
   model.setViewName("user/signup");
  } else {
   userService.saveUser(user);
   model.addObject("msg", "User has been registered successfully!");
   model.addObject("user", new User());
   model.setViewName("user/signup");
  }
  
  return model;
 }
 
 @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
 public ModelAndView home() {
  ModelAndView model = new ModelAndView();
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  User user = userService.findUserByEmail(auth.getName());
  Set<Role> roles = user.getRoles();
  Boolean isAdmin = false;
  for (Iterator<Role> i = roles.iterator(); i.hasNext();) {
	  Role role = i.next();
	  System.out.println(role.getRole());
	  if (role.getRole().equals("ADMIN")) {
		  isAdmin = true;
	  }
  }
  
  model.addObject("userName", user.getFirstname() + " " + user.getLastname());
  model.addObject("isAdmin", isAdmin);
  model.setViewName("home/home");
  return model;
 }
 
 @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
 public ModelAndView accessDenied() {
  ModelAndView model = new ModelAndView();
  model.setViewName("errors/access_denied");
  return model;
 }

	//gets all users from the user database and displays them on results.html
	@GetMapping("/viewResults")
		public String viewAllContacts(Model model) {
		model.addAttribute("user", repo.findAll());
		return "results";
		}
	
	@GetMapping("/viewUsers")
	public String viewAllUsers(Model model) {
	model.addAttribute("user", repo.findAll());
	return "allUsers";
	}

	
}