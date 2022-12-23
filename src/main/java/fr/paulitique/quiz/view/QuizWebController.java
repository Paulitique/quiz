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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
				.build().encode();
				

		String jsonQuiz = "";
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("name", name);
		jsonMap.put("description", description);
		try {
			jsonQuiz = new ObjectMapper().writeValueAsString(jsonMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		HttpHeaders h = new HttpHeaders();
		h.setContentType(MediaType.APPLICATION_JSON);
		String res = new RestTemplate()
				.postForObject(uri.toUriString(), 
				new HttpEntity<String>(jsonQuiz, h),
				String.class);
		
		String message = res.contains("id")?"ok":"ko";
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

		ResponseEntity<Object[]> res = new RestTemplate()
				.getForEntity(uri.toUriString(), Object[].class);
		Object[] q = res.getBody();
		
		model.addAttribute("status", status);
		model.addAttribute("quizList", q);
		
		return "listQuiz";
	}
	
	@GetMapping("/quiz/delete")
	public String deleteQuiz(
			@RequestParam(name="id", required=true) String id,
			@RequestHeader(name="Host", required=true) String host, 
			Model model) {
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl("http://"+host)
				.path("/api/quiz/"+id)
				.build().encode();
		
		new RestTemplate().delete(uri.toUriString());
		
		return "redirect:/quiz/all?status=Suppression%20quiz%20ok#list";
	}
	
	@GetMapping("/quiz/update")
	public String updateQuizForm(
			@RequestParam(name="id", required=true) String id,
			@RequestHeader(name="Host", required=true) String host,
			Model model) {
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl("http://"+host)
				.path("/api/quiz/")
				.path(id)
				.build().encode();
		
		Object res = new RestTemplate().getForObject(uri.toUriString(), Object.class);
		
		model.addAttribute("quiz", res);
		
		return "updateQuiz";
	}
	
	@GetMapping("/quiz/putUpdate")
	public String updateQuiz(
			@RequestParam(name="id", required=true) String id,
			@RequestParam(name="name", required=true) String name,
			@RequestParam(name="description", required=true) String description,
			@RequestHeader(name="Host", required=true) String host,
			Model model) {
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl("http://"+host)
				.path("/api/quiz/")
				.build().encode();
		
		String jsonQuiz = "";
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("id", id);
		jsonMap.put("name", name);
		jsonMap.put("description", description);
		try {
			jsonQuiz = new ObjectMapper().writeValueAsString(jsonMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		HttpHeaders h = new HttpHeaders();
		h.setContentType(MediaType.APPLICATION_JSON);
		new RestTemplate().put(uri.toUriString(), new HttpEntity<String>(jsonQuiz, h));		
		
		return "redirect:/quiz/all?status=Modification%20quiz%20ok#list";
	}
	
	@GetMapping("/quiz/compose")
	public String composeQuiz(
			@RequestParam(name="id", required=true) String id,
			@RequestParam(name="status", required=false) String status,
			@RequestHeader(name="Host", required=true) String host,
			Model model) {
		
		model.addAttribute("status", status);
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl("http://"+host)
				.path("/api/quiz/")
				.path(id)
				.build().encode();
		
		Object res = new RestTemplate().getForObject(uri.toUriString(), Object.class);
		model.addAttribute("quiz", res);
		
		
		UriComponents questionsUri = UriComponentsBuilder
				.fromHttpUrl("http://"+host)
				.path("/api/quiz/")
				.path(id)
				.path("/question/all")
				.build().encode();
		
		Object questionList = new RestTemplate().getForObject(questionsUri.toUriString(), Object.class);
		model.addAttribute("questionList", questionList);
		
		
		return "composeQuiz";
	}
	
	@GetMapping("/quiz/compose/new")
	public String composeQuizNewQuestion(
			@RequestParam(name="quizId", required=true) String quizId,
			@RequestParam(name="questionType", required=true) String questionType,
			@RequestParam(name="question", required=true) String question,
			@RequestHeader(name="Host", required=true) String host,
			Model model) {
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl("http://"+host)
				.path("/api/quiz/")
				.path(quizId)
				.path("/question")
				.build().encode();
		
		String jsonQuestion = "";
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("questionType", questionType);
		jsonMap.put("text", question);
		try {
			jsonQuestion = new ObjectMapper().writeValueAsString(jsonMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		HttpHeaders h = new HttpHeaders();
		
		h.setContentType(MediaType.APPLICATION_JSON);
		
		
		
		String res = new RestTemplate()
				.postForObject(uri.toUriString(), 
				new HttpEntity<String>(
						jsonQuestion,
						h
						),
				String.class);
		
		return "redirect:/quiz/compose?id="+quizId+"&status=Ajout%20question%20ok#compose";
	}
	
	@GetMapping("/quiz/question/update")
	public String updateQuestionForm(
			@RequestParam(name="id", required=true) String id,
			@RequestParam(name="status", required=false) String status,
			@RequestHeader(name="Host", required=true) String host,
			Model model) {
		
		model.addAttribute("status", status);
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl("http://"+host)
				.path("/api/quiz/question/")
				.path(id)
				.build().encode();
		
		Object res = new RestTemplate().getForObject(uri.toUriString(), Object.class);
		
		model.addAttribute("question", res);
		
		return "updateQuestion";
	}
	
	@GetMapping("/quiz/question/putUpdate")
	public String updateQuestion(
			@RequestParam(name="id", required=true) String id,
			@RequestParam(name="questionType", required=true) String questionType,
			@RequestParam(name="question", required=true) String question,
			@RequestHeader(name="Host", required=true) String host,
			Model model) {
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl("http://"+host)
				.path("/api/quiz/question/")
				.build().encode();
		
		String jsonQuestion = "";
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("id", id);
		jsonMap.put("questionType", questionType);
		jsonMap.put("text", question);
		try {
			jsonQuestion = new ObjectMapper().writeValueAsString(jsonMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		HttpHeaders h = new HttpHeaders();
		h.setContentType(MediaType.APPLICATION_JSON);
		new RestTemplate()
				.put(uri.toUriString(), new HttpEntity<String>(jsonQuestion, h),
				String.class);
		
		return "redirect:/quiz/question/update?id="+id+"&status=Modification%20question%20ok#update";
	}
	
	@GetMapping("/quiz/question/delete")
	public String deleteQuestion(
			@RequestParam(name="id", required=true) String id,
			@RequestHeader(name="Host", required=true) String host, 
			Model model) {
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl("http://"+host)
				.path("/api/quiz/question/")
				.path(id)
				.build().encode();
		
		new RestTemplate().delete(uri.toUriString());
		
		return "redirect:/quiz/all?status=Suppression%20question%20ok#list";
	}
	
	@GetMapping("/quiz/view")
	public String viewQuiz(
			@RequestParam(name="id", required=true) String id,
			@RequestHeader(name="Host", required=true) String host,
			Model model) {
		
		UriComponents uri = UriComponentsBuilder
				.fromHttpUrl("http://"+host)
				.path("/api/quiz/")
				.path(id)
				.build().encode();
		
		Object res = new RestTemplate().getForObject(uri.toUriString(), Object.class);
		model.addAttribute("quiz", res);
		
		
		UriComponents questionsUri = UriComponentsBuilder
				.fromHttpUrl("http://"+host)
				.path("/api/quiz/")
				.path(id)
				.path("/question/all")
				.build().encode();
		
		Object questionList = new RestTemplate().getForObject(questionsUri.toUriString(), Object.class);
		model.addAttribute("questionList", questionList);
		
		return "viewQuiz";
	}
}
