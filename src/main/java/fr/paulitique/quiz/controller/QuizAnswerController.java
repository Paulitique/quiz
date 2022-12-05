package fr.paulitique.quiz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.paulitique.quiz.dto.QuizAnswerDTO;
import fr.paulitique.quiz.mapper.QuizAnswerMapper;
import fr.paulitique.quiz.metier.QuizAnswerService;
import fr.paulitique.quiz.metier.QuizService;
import fr.paulitique.quiz.model.QuizAnswer;

@RestController
@RequestMapping("/api/quiz")
public class QuizAnswerController {
	
	@Autowired
	private QuizAnswerService quizAnswerService;
	
	@Autowired
	private QuizAnswerMapper quizAnswerMapper;
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/{quizId}/answer")
	public QuizAnswerDTO createQuizAnswer(@PathVariable String quizId, @RequestBody QuizAnswerDTO quizAnswerDTO) {
		QuizAnswer quizAnswer = quizAnswerMapper.DTOToEntity(quizAnswerDTO);
		
		QuizAnswer savedQuizAnswer = quizAnswerService.createQuizAnswer(Integer.parseInt(quizId), quizAnswer);
		
		QuizAnswerDTO savedQuizAnswerDTO = quizAnswerMapper.entityToDTO(savedQuizAnswer);
		
		return savedQuizAnswerDTO;
	}
	
	@GetMapping("/answer/all")
	public List<QuizAnswerDTO> getAllQuizAnswer() {
		List<QuizAnswerDTO> quizAnswerDTOList = new ArrayList<>();
		quizAnswerService.getAllQuizAnswers().forEach(quizAnswer -> 
			quizAnswerDTOList.add(quizAnswerMapper.entityToDTO(quizAnswer)));
		
		return quizAnswerDTOList;
	}
	
	@GetMapping("/{quizId}/answer/all")
	public List<QuizAnswerDTO> getAllQuizAnswer(@PathVariable String quizId) {
		List<QuizAnswerDTO> quizAnswerDTOList = new ArrayList<>();
		quizService.getQuiz(Integer.parseInt(quizId)).getQuizAnswers().forEach(quizAnswer ->
			quizAnswerDTOList.add(quizAnswerMapper.entityToDTO(quizAnswer)));
		
		return quizAnswerDTOList;
	}
	
	@GetMapping("/answer/{answerId}")
	public QuizAnswerDTO getQuizAnswer(@PathVariable String answerId) {
		return quizAnswerMapper.entityToDTO(quizAnswerService.getQuizAnswer(Integer.parseInt(answerId)));
	}
	
	@PutMapping("/answer/")
	public QuizAnswerDTO updateQuizAnswer(@RequestBody QuizAnswerDTO quizAnswerDTO) {
		QuizAnswer quizAnswer = quizAnswerMapper.DTOToEntity(quizAnswerDTO);
		QuizAnswer updatedQuizAnswer = quizAnswerService.updateAnswer(quizAnswer);
		QuizAnswerDTO updatedQuizAnswerDTO = quizAnswerMapper.entityToDTO(updatedQuizAnswer);
		return updatedQuizAnswerDTO;
	}
	
	@DeleteMapping("/answer/{answerId}")
	public void deleteQuizAnswer(@PathVariable String answerId) {
		quizAnswerService.deleteAnswer(quizAnswerService.getQuizAnswer(Integer.parseInt(answerId)));
	}
}
