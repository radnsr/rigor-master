package com.rigor.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rigor.model.Category;
import com.rigor.model.Feedback;
import com.rigor.service.FeedbackService;

@RestController
public class FeedbackController {
	@Autowired
	FeedbackService service;

	// -------------------Create a model-------------
	@RequestMapping(value = "/feedback/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody String str, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Feedback " + str);

		Feedback feedback = new Feedback();
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(str);

			feedback = mapper.convertValue(node.get("fb"), Feedback.class);
			Category category = mapper.convertValue(node.get("cat"), Category.class);
			//User user = mapper.convertValue(node.get("user"), User.class);
			feedback.setCategory(category);
			//feedback.set(user);
			//System.out.println("user:>" + user);
			System.out.println("model_new:>" + feedback);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Feedback model_new = service.save(feedback);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(model_new.getFeedback_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// -------------------Retrieve All Data-----------------------

	@RequestMapping(value = "/feedback/", method = RequestMethod.GET)
	public ResponseEntity<List<Feedback>> listAllData() {
		List<Feedback> list = service.getAllData();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Feedback>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Feedback>>(list, HttpStatus.OK);
	}
	// -------------------Retrieve All Data-----------------------
	
	@RequestMapping(value = "/feedback/{dept_id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Feedback>> listDataByDept_id(@PathVariable("dept_id") String dept_id) {
		List<Feedback> list = service.getDataByDeptId(dept_id);
		if (list.isEmpty()) {
			return new ResponseEntity<List<Feedback>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Feedback>>(list, HttpStatus.OK);
	}


	// -------------------Retrieve Category List-----------------

	@RequestMapping(value = "/category_list/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> getCategoryList(@PathVariable("id") String dept_id) {
		System.out.println("Fetching List with id " + dept_id);
		List<Category> list = service.categoryList(dept_id);
		if (list.isEmpty()) {
			return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
	}



	// -------------------Deactivate Model-------------------------

	@RequestMapping(value = "/feedback_deactivate/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deactivate(@PathVariable("id") String id) {

		service.deactivate(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// -------------------Activate Model-----------------------------
	@RequestMapping(value = "/feedback_activate/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> activate(@PathVariable("id") String id) {

		service.activate(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
