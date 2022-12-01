package fr.paulitique.quiz.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
			Model model) {
		
		UriComponents uri = UriComponentsBuilder
				.newInstance()
				.scheme("http")
				.host("localhost")
				.port("8080")
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
	
}
