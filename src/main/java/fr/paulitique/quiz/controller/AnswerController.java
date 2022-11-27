package fr.paulitique.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.paulitique.quiz.dto.AnswerDTO;
import fr.paulitique.quiz.mapper.AnswerMapper;
import fr.paulitique.quiz.metier.AnswerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private AnswerMapper answerMapper;
	
	@PostMapping("/api/answer")
	public AnswerDTO createAnswer(@RequestBody AnswerDTO answerDTO) {
		
		Answer answer = answerMapper.DTOToEntity(answerDTO);
		
		Quiz createdAnswer = answerService.createAnswer(answer);
		
		QuizDTO createdAnswerDTO = answerMapper.entityToDTO(createdAnswer);
		
		return createdAnswerDTO;
	}
}
