package com.rigor.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.rigor.model.User;
import com.rigor.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService service;

	// -------------------Login authentication-------------
	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, @ModelAttribute(value = "user") User user,
			HttpSession session) {

		User au_user = new User();
		au_user.setEmail(email);
		au_user.setPassword(password);
		user = service.authenticate(au_user);
		if (user == null) {
			session.setAttribute("error", "Email or Password is wrong!!!");
			return new ModelAndView("index");
		}
		
		if(user.getStatus()==2 ){
			session.setAttribute("user_fresh", user);
			return new ModelAndView("fresh_login");	
		}
		session.setAttribute("user", user);
		return new ModelAndView("dashboard");
	}

	// -------------------Log out-------------
	@RequestMapping(value = "/logout/", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {

		session.removeAttribute("user");
		session.invalidate();

		return new ModelAndView("index");
	}

	// -------------------Create a model-------------
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody User model, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Deaprtment " + model);

		User model_new = service.save(model);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(model_new.getUser_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// -------------------Retrieve All Data-----------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllData() {
		List<User> list = service.getAllData();
		if (list.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	// -------------------Retrieve Single Model-----------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getModeldata(@PathVariable("id") String id) {
		System.out.println("Fetching Dept with id " + id);
		User model = service.findById(id);
		if (model == null) {
			System.out.println("id= " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(model, HttpStatus.OK);
	}

	// ------------------- Update a Model ----------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> update(@PathVariable("id") String id, @RequestBody User model) {
		System.out.println("Updating User " + id);

		User currentModel = service.findById(id);

		if (currentModel == null) {
			System.out.println("id = " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentModel.setName(model.getName());
		currentModel.setEmail(model.getEmail());
		currentModel.setRole(model.getRole());
		currentModel.setEmp_no(model.getEmp_no());

		service.updateDept(currentModel);
		return new ResponseEntity<User>(currentModel, HttpStatus.OK);
	}

	// -------------------Deactivate Model-------------------------

	@RequestMapping(value = "/user_deactivate/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deactivate(@PathVariable("id") String id) {

		service.deactivate(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// -------------------Activate Model-----------------------------
	@RequestMapping(value = "/user_activate/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> activate(@PathVariable("id") String id) {

		service.activate(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	// ------------------- Update Password----------------------

	@RequestMapping(value = "/user_password/", method = RequestMethod.POST)
	public ModelAndView updatePassword(@RequestParam(value = "user_id") String id,@RequestParam(value = "password") String password,HttpSession session) {
		System.out.println("Updating User " + id );

		User currentModel = service.findById(id);

		if (currentModel == null) {
			System.out.println("id = " + id + " not found");
			
			return new ModelAndView("dashboard");
		}
		System.out.println("Current User " + currentModel);
		currentModel.setPassword(password);
		currentModel.setStatus(1);

		service.updateDept(currentModel);
		session.setAttribute("user", currentModel);
		return new ModelAndView("dashboard");
	}

}
