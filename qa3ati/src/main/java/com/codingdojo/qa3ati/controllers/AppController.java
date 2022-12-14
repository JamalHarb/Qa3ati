package com.codingdojo.qa3ati.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.qa3ati.models.Hall;
import com.codingdojo.qa3ati.models.LoginUser;
import com.codingdojo.qa3ati.models.ReserveDate;
import com.codingdojo.qa3ati.models.User;
import com.codingdojo.qa3ati.services.CityService;
import com.codingdojo.qa3ati.services.EmailSender;
import com.codingdojo.qa3ati.services.HallService;
import com.codingdojo.qa3ati.services.ReserveDateService;
import com.codingdojo.qa3ati.services.UserService;

@Controller
public class AppController {
	private final CityService cityService;
	private final EmailSender emailSender;
	private final HallService hallService;
	private final ReserveDateService reserveDateService;
	private final UserService userService;
	
	public AppController(
			CityService cityService,
			EmailSender emailSender,
			HallService hallService,
			ReserveDateService reserveDateService,
			UserService userService) {
		this.cityService = cityService;
		this.emailSender = emailSender;
		this.hallService = hallService;
		this.reserveDateService = reserveDateService;
		this.userService = userService;
	}
	
	@GetMapping("")
	public String index(
			@ModelAttribute("newUser") User newUser,
			@ModelAttribute("newLogin") LoginUser loginUser) {
        return "index.jsp";
    }
	
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result,
			Model model,
			HttpSession session) {
		userService.register(newUser, result);
		if(result.hasErrors()) { 
            model.addAttribute("newLogin", new LoginUser()); 
            return "index.jsp";
        }
		session.setAttribute("user", newUser);
		return "redirect:/halls";
	}
	
	@PostMapping("/login")
    public String login(
    		@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result,
            Model model,
            HttpSession session) {
        User user = userService.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("user", user);
        return "redirect:/halls";
    }
	
	@GetMapping("/logout")
    public String logout(HttpSession session) { 
	    session.invalidate();
	    return "redirect:/";
    }
	
	@GetMapping("/halls")
	public String halls(Model model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/";
		}
		model.addAttribute("halls", hallService.allHalls());
		return "all_halls.jsp";
	}
	
	@GetMapping("/halls/new")
	public String newHall(Model model, @ModelAttribute("hall") Hall hall, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/";
		}
		model.addAttribute("cities", cityService.allCities());
		return "new_hall.jsp";
	}
	
	@PostMapping("/halls/create")
	public String createHall(
			@Valid @ModelAttribute("hall") Hall hall,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			model.addAttribute("cities", cityService.allCities());
			return "new_hall";
		}
		else {
			hallService.createHall(hall);
			return "redirect:/halls";
		}
	}
	
	@GetMapping("/halls/{id}")
	public String displayHall(
			@PathVariable("id") Long id,
			Model model,
			@ModelAttribute("reserveDate") ReserveDate reserveDate,
			HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/";
		}
		model.addAttribute("hall", hallService.findHallById(id));
		return "display_hall.jsp";
	}
	
	@PostMapping("/halls/{hallId}/add_availability")
	public String addAvailability(
			@PathVariable("hallId") Long hallId,
			@ModelAttribute("reserveDate") ReserveDate reserveDate,
			HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/";
		}
		System.out.println("method entered");
		ReserveDate date = reserveDateService.createReserveDate(reserveDate);
		System.out.println("date created");
		System.out.println("id" + date.getId());
		Hall hall = hallService.findHallById(hallId);
		hall.getReserveDates().add(date);
		hallService.updateHall(hall);
		return "redirect:/halls/{hallId}";
	}
	
	@GetMapping("/halls/{id}/delete")
	public String deleteHall(@PathVariable("id") Long id, HttpSession session) {
		Hall hall = hallService.findHallById(id);
		User user = (User) session.getAttribute("user");
		if(session.getAttribute("user") == null || hall.getCreator().getId() != user.getId()) {
			return "redirect:/";
		}
		hallService.deleteHall(id);
		return "redirect:/halls";
	}
	
	@GetMapping("/halls/hall{hallId}/date{dateId}/book")
	public String bookHall(
			@PathVariable("hallId") Long hallId,
			@PathVariable("dateId") Long dateId,
			HttpSession session) {
		ReserveDate date = reserveDateService.findReserveDateById(dateId);
		Hall hall = hallService.findHallById(hallId);
		User booker = (User) session.getAttribute("user");
		date.setAvailable(false);
		date.setBooker(booker);
		hall.getBookers().add(booker);
		hallService.updateHall(hall);
		String msg = "Hello " + hall.getCreator().getUserName() + ". " + booker.getUserName() + " reserved " + hall.getName() + " on " + date.getDate().toString() + ", from " + date.getFromHour().toString() + " to " + date.getToHour().toString();
		emailSender.sendEmail(hall.getCreator().getEmail(), "Your Hall Has Been Reserved", msg);
		return "redirect:/halls/{hallId}";
	}
	
	@GetMapping("/halls/hall{hallId}/date{dateId}/unbook")
	public String unbookHall(
			@PathVariable("hallId") Long hallId,
			@PathVariable("dateId") Long dateId,
			HttpSession session) {
		ReserveDate date = reserveDateService.findReserveDateById(dateId);
		Hall hall = hallService.findHallById(hallId);
		User booker = (User) session.getAttribute("user");
		date.setAvailable(true);
		date.setBooker(null);
		hall.getBookers().remove(booker);
		hallService.updateHall(hall);
		String msg = "Hello " + hall.getCreator().getUserName() + ". " + booker.getUserName() + " cancelled reservation for " + hall.getName() + " on " + date.getDate().toString() + ", from " + date.getFromHour().toString() + " to " + date.getToHour().toString();
		emailSender.sendEmail(hall.getCreator().getEmail(), "Your Hall Reservation Has Been Cancelled", msg);
		return "redirect:/halls/{hallId}";
	}
}
