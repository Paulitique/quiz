package fr.paulitique.quiz.view;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import fr.paulitique.quiz.model.Quiz;

@Controller
public class QuizWebController {
	
	@GetMapping("/quiz/new")
	public String createQuiz(@RequestParam(name="status", required=false) String status, Model model) {
		model.addAttribute("status", status);
		return "createQuiz";
	}
	
	@GetMapping("/quiz/new/create")
	public String createNewQuiz(
			@RequestParam(name="name", required=true) String name,
			@RequestParam(name="description", required=true) String description,
			@RequestHeader(name="Host", required=false) String host,
			Model model) {
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl("http://"+host)
				.path("/api/quiz/")
				.queryParam("name", name)
				.queryParam("description", description)
				.build().encode();
				
		String res = new RestTemplate()
				.postForObject(uri.toUriString(), 
				new HttpEntity<String>("", new HttpHeaders()), String.class);
		
		String message = res.contains("id")?"worked":"failed";
		return "redirect:/quiz/new?status="+message+"#create";
	}
	
	@GetMapping("/quiz/all")
	public String listQuiz(
			@RequestParam(name="status", required=false) String status,
			@RequestHeader(name="Host", required=false) String host, 
			Model model) {
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl("http://"+host)
				.path("/api/quiz/all")
				.build().encode();

		ResponseEntity<Quiz[]> res = new RestTemplate()
				.getForEntity(uri.toUriString(), Quiz[].class);
		Quiz[] q = res.getBody();
		
		model.addAttribute("status", status);
		model.addAttribute("quizList", q);
		
		return "listQuiz";
	}
	
	@GetMapping("/quiz/delete")
	public String deleteQuiz(
			@RequestParam(name="id", required=false) String id,
			@RequestHeader(name="Host", required=false) String host, 
			Model model) {
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl("http://"+host)
				.path("/api/quiz/"+id)
				.build().encode();
		
		new RestTemplate().delete(uri.toUriString());
		
		return "redirect:/quiz/all?status=Suppression%20quiz%20ok#list";
	}
}
