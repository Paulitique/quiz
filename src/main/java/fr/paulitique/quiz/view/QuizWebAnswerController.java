package fr.paulitique.quiz.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.paulitique.quiz.dto.QuestionDTO;
import fr.paulitique.quiz.model.Question;
import fr.paulitique.quiz.dao.*;


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
	public String answerQuiz(
			@RequestParam(name="idQuiz", required=true) String idQuiz,
			@RequestParam(name="numQuestion", required=false, defaultValue="") String numQuestion,
			@RequestParam(name="move", required=false, defaultValue="") String move,
			@RequestParam(name="idQestion", required=false, defaultValue="") String idQuestion,
			@RequestParam(name="answer", required=false, defaultValue="") String answer,
			@RequestParam(name="status", required=false) String status,
			Model model) {
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl(API_ENDPONIT)
				.path("/quiz/")
				.path(idQuiz)
				.build().encode();
		
		Object res = new RestTemplate().getForObject(uri.toUriString(), Object.class);
		model.addAttribute("quiz", res);
		
		
		UriComponents questionsUri = UriComponentsBuilder
				.fromHttpUrl(API_ENDPONIT)
				.path("/quiz/")
				.path(idQuiz)
				.path("/question/all")
				.build().encode();
		
		ResponseEntity<QuestionDTO[]> questions = new RestTemplate().getForEntity(questionsUri.toUriString(), QuestionDTO[].class);
		
		ArrayList<QuestionDTO> questionList = new ArrayList<QuestionDTO>(Arrays.asList(questions.getBody()));		
		
		QuestionDTO currentQuestion = null;
		String numCurrentQuestion = numQuestion;
		
		if(numCurrentQuestion.equals("-1")) {
			
			if(questionList.size() <= 0) {				
				model.addAttribute("status", "Quiz empty");
			}else {
				model.addAttribute("status", "Quiz finished");
			}			
			
		}else if(numQuestion.equals("")) {
			if(questionList.size()>0) {
				currentQuestion = questionList.get(0);
				numCurrentQuestion = "1";
				
				// save the answer
			}else {
				return "redirect:/answer/quiz?idQuiz="+idQuiz+"&numQuestion=-1";
			}
		} else {
			int num = Integer.parseInt(numCurrentQuestion);
			
			if(move.equals("backward") && num >= 0 && num < questionList.size()) {
				numCurrentQuestion = (num-1)+"";
				currentQuestion = questionList.get(num);
			}
			
			if(move.equals("forward") && num >= 0 && num < questionList.size()) {
				numCurrentQuestion = (num+1)+"";
				currentQuestion = questionList.get(num);
			}else {
				if (!answer.equals("")) {
					UriComponents postUri = UriComponentsBuilder.fromHttpUrl(API_ENDPONIT).path("/quiz/").path(idQuiz)
							.path("/question/").path(idQuestion).path("/answer").build().encode();
					String jsonQuestion = "";
					Map<String, Object> jsonMap = new HashMap<String, Object>();
					jsonMap.put("answerType", "FreeAnswer");
					jsonMap.put("text", answer);
					try {
						jsonQuestion = new ObjectMapper().writeValueAsString(jsonMap);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
					HttpHeaders h = new HttpHeaders();
					h.setContentType(MediaType.APPLICATION_JSON);
					new RestTemplate().postForObject(postUri.toUriString(), new HttpEntity<String>(jsonQuestion, h),
							String.class);

				}
				return "redirect:/answer/quiz?idQuiz="+idQuiz+"&numQuestion=-1";
			}

		}
		
		if(!answer.equals("")) {
						
			UriComponents postUri = UriComponentsBuilder
					.fromHttpUrl(API_ENDPONIT)
					.path("/quiz/")
					.path(idQuiz)
					.path("/question/")
					.path(idQuestion)
					.path("/answer")
					.build().encode();

			String jsonQuestion = "";
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("answerType", "FreeAnswer");
			jsonMap.put("text", answer);
			try {
				jsonQuestion = new ObjectMapper().writeValueAsString(jsonMap);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			HttpHeaders h = new HttpHeaders();
			h.setContentType(MediaType.APPLICATION_JSON);
			new RestTemplate().postForObject(postUri.toUriString(), new HttpEntity<String>(jsonQuestion, h), String.class);
		}
		
		
		// display the current question
		model.addAttribute("currentQuestion", currentQuestion);
		model.addAttribute("numCurrentQuestion", numCurrentQuestion);
		
		return "answerQuiz";
	}

}