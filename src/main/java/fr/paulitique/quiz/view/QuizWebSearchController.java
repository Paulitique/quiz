package fr.paulitique.quiz.view;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import fr.paulitique.quiz.dto.QuizDTO;

@Controller
public class QuizWebSearchController {
	
	private static final String API_ENDPONIT = "http://localhost:8080/api";
	
	@GetMapping("/quiz/search")
	public String createQuiz(@RequestParam(name="query", required=false, defaultValue="") String query, Model model) {
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl(API_ENDPONIT)
				.path("/quiz/all")
				.build().encode();

		ResponseEntity<QuizDTO[]> res = new RestTemplate()
				.getForEntity(uri.toUriString(), QuizDTO[].class);
		QuizDTO[] q = res.getBody();
		
		ArrayList<Object> result = new ArrayList<Object>();
		
		for(int i=0; i<q.length; i++) {
			if(q[i].getName().contains(query))
				result.add(q[i]);
		}
		
		model.addAttribute("quizList", result);
		
		return "searchQuiz";
	}

}
