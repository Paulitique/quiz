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
	
	@PostMapping("/api/quiz")
	public QuizDTO createQuiz(@RequestBody QuizDTO quizDTO) {
		
		Quiz quiz = quizMapper.DTOToEntity(quizDTO);
		
		Quiz createdQuiz = quizService.createQuiz(quiz);
		
		QuizDTO createdQuizDTO = quizMapper.entityToDTO(createdQuiz);
		
		return createdQuizDTO;
	}

	@DeleteMapping("/api/quiz/{quizId}")
	public void deleteQuiz(@PathVariable String quizId) {
		
		Integer id = Integer.parseInt(quizId);
		
		quizService.deleteQuiz(id);
		
	}
	
	@GetMapping("/api/quiz/{quizId}")
	public QuizDTO getQuiz(@PathVariable String quizId) {
		
		Integer id = Integer.parseInt(quizId);
		
		Quiz quiz = quizService.getQuiz(id);
		
		QuizDTO quizDTO = quizMapper.entityToDTO(quiz);
		
		return quizDTO;
		
	}
	
	@GetMapping("/api/quiz/getAll")
	public List<QuizDTO> getAllQuiz() {
		
		List<QuizDTO> quizDTOList = new ArrayList<QuizDTO>();
		
		List<Quiz> quizList = quizService.getAllQuiz();
		
		quizList.forEach(quiz -> quizDTOList.add(quizMapper.entityToDTO(quiz)));
		
		return quizDTOList;
		
	}
	@PutMapping("/quiz")
	public QuizDTO modifyQuiz(@RequestBody QuizDTO quizDTO ) {
		
		Quiz quiz = quizMapper.DTOToEntity(quizDTO);
		
		Quiz modifiedQuiz = quizService.modifyQuiz(quiz);
		
		QuizDTO modifiedQuizDTO = quizMapper.entityToDTO(modifiedQuiz);
		
		return modifiedQuizDTO;
	}
	
}
