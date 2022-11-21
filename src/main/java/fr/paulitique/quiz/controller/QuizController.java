package fr.paulitique.quiz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import fr.paulitique.quiz.dto.QuizDTO;
import fr.paulitique.quiz.mapper.QuizMapper;
import fr.paulitique.quiz.metier.QuizService;
import fr.paulitique.quiz.model.Quiz;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	@Autowired
	private QuizMapper quizMapper;
	
	@PostMapping("/quiz")
	public QuizDTO createQuiz(@RequestBody QuizDTO quizDTO) {
		
		Quiz quiz = quizMapper.DTOToEntity(quizDTO);
		
		Quiz createdQuiz = quizService.createQuiz(quiz);
		
		QuizDTO createdQuizDTO = quizMapper.entityToDTO(createdQuiz);
		
		return createdQuizDTO;
	}
	
	@DeleteMapping("/quiz/{quizId}/delete")
	public void deleteQuiz(@PathVariable String quizId) {
		
		Integer id = Integer.parseInt(quizId);
		
		quizService.deleteQuiz(id);
		
	}
	
	@GetMapping("/quiz/{quizId}/get")
	public QuizDTO getQuiz(@PathVariable String quizId) {
		
		Integer id = Integer.parseInt(quizId);
		
		Quiz consultedQuiz = quizService.getQuiz(id);
		
		QuizDTO createdQuizDTO = quizMapper.entityToDTO(consultedQuiz);
		
		return createdQuizDTO;
		
	}
	
}
