package fr.paulitique.quiz.view;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
public class QuizWebAnswerController {
	
	private static final String API_ENDPONIT = "http://localhost:8080/api";
	
	@GetMapping("/answer")
	public String answerListQuiz(@RequestParam(name="status", required=false) String status, Model model) {
		model.addAttribute("status", status);
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl(API_ENDPONIT)
				.path("/quiz/all")
				.build().encode();

		ResponseEntity<Object[]> res = new RestTemplate()
				.getForEntity(uri.toUriString(), Object[].class);
		Object[] q = res.getBody();
		
		model.addAttribute("quizList", q);
		
		return "answerListQuiz";
	}
	
	@GetMapping("/answer/quiz")
	public String answerQuiz(@RequestParam(name="id", required=true) String id, Model model) {
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl(API_ENDPONIT)
				.path("/quiz/")
				.path(id)
				.build().encode();
		
		Object res = new RestTemplate().getForObject(uri.toUriString(), Object.class);
		model.addAttribute("quiz", res);
		
		
		UriComponents questionsUri = UriComponentsBuilder
				.fromHttpUrl(API_ENDPONIT)
				.path("/quiz/")
				.path(id)
				.path("/question/all")
				.build().encode();
		
		Object questionList = new RestTemplate().getForObject(questionsUri.toUriString(), Object.class);
		model.addAttribute("questionList", questionList);
		
		return "answerQuiz";
	}

}
