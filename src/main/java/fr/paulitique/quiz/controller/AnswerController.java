package fr.paulitique.quiz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.paulitique.quiz.dto.AnswerDTO;
import fr.paulitique.quiz.mapper.AnswerMapper;
import fr.paulitique.quiz.metier.AnswerService;
import fr.paulitique.quiz.metier.QuestionService;
import fr.paulitique.quiz.model.Answer;
import fr.paulitique.quiz.model.Question;

@RestController
@RequestMapping("/api/quiz/")
public class AnswerController {

	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private AnswerMapper answerMapper;
	
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("/{quizId}/question/{questionId}/answer")
	public AnswerDTO createAnswer(@PathVariable String questionId, @RequestBody AnswerDTO answerDTO) {
		Question question = questionService.getQuestion(Integer.parseInt(questionId));
		Answer answer = answerMapper.DTOToEntity(answerDTO);
		answer.setQuestion(question);
		Answer savedAnswer = answerService.createAnswer(answer);
		AnswerDTO savedAnswerDTO = answerMapper.entityToDTO(savedAnswer);
		return savedAnswerDTO;
	}
	
	@GetMapping("/{quizId}/question/{questionId}/answer/all")
	public List<AnswerDTO> getAllAnswers(@PathVariable String questionId) {
		Set<Answer> foundAnswerSet = questionService.getQuestion(Integer.parseInt(questionId)).getAnswers();
		List<AnswerDTO> foundAnswerDTOList = new ArrayList<>();
		foundAnswerSet.forEach(answer -> foundAnswerDTOList.add(answerMapper.entityToDTO(answer)));
		return foundAnswerDTOList;
	}
	
	@GetMapping("/question/answer/all")
	public List<AnswerDTO> getAllAnswers() {
		List<Answer> foundAnswerList = answerService.getAllAnswers();
		List<AnswerDTO> foundAnswerDTOList = new ArrayList<>();
		foundAnswerList.forEach(answer -> foundAnswerDTOList.add(answerMapper.entityToDTO(answer)));
		return foundAnswerDTOList;
	}
	
	@GetMapping("/question/answer/{answerId}/")
	public AnswerDTO getAnswer(@PathVariable String answerId) {
		Answer answer = answerService.getAnswer(Integer.parseInt(answerId));
		return answerMapper.entityToDTO(answer);
	}
	
	@PutMapping("/question/answer/")
	public AnswerDTO updateAnswer(@RequestBody AnswerDTO answerDTO) {
		Answer answer = answerMapper.DTOToEntity(answerDTO);
		Answer updatedAnswer = answerService.updateAnswer(answer);
		AnswerDTO updatedAnswerDTO = answerMapper.entityToDTO(updatedAnswer);
		
		return updatedAnswerDTO;
	}
	
	@DeleteMapping("/question/answer/{answerId}/")
	public void deleteAnswer(@PathVariable String answerId) {
		answerService.deleteAnswer(Integer.parseInt(answerId));
	}
}
