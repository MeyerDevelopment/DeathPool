package deathpool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import deathpool.beans.User;
import deathpool.repository.UserRepository;

@Controller
public class WebController {
	@Autowired
	UserRepository repo;
	//gets all users from the user database and displays them on results.html
	@GetMapping("/viewResults")
		public String viewAllContacts(Model model) {
		model.addAttribute("user", repo.findAll());
		return "results";
		}
	//used in index.html to call signup page when signup button is pressed
	@GetMapping("/inputUser")
		public String addNewUser(Model model) {
			User u = new User();
			model.addAttribute("newUser", u);
			return "signup";
		}
	
	@GetMapping("/login")
	public String voteNow(Model model) {
		
		return "login";
	}
	//enters new user from signup.html into user database and goes to results.html when submit button
	//on signup.html is pressed
	@PostMapping("/inputUser")
		public String addNewUser(@ModelAttribute User u, Model model){
			repo.save(u);
			model.addAttribute("user", repo.findAll());
			return "results";
		}
}