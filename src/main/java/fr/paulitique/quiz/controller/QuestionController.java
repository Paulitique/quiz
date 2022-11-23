package fr.paulitique.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.paulitique.quiz.dto.FreeQuestionDTO;
import fr.paulitique.quiz.dto.QuizDTO;
import fr.paulitique.quiz.mapper.FreeQuestionMapper;
import fr.paulitique.quiz.mapper.MultipleChoiceQuestionMapper;
import fr.paulitique.quiz.mapper.NumericalQuestionMapper;
import fr.paulitique.quiz.mapper.QuizMapper;
import fr.paulitique.quiz.mapper.UniqueChoiceQuestionMapper;
import fr.paulitique.quiz.metier.QuestionService;
import fr.paulitique.quiz.metier.QuizService;
import fr.paulitique.quiz.model.FreeQuestion;
import fr.paulitique.quiz.model.Question;
import fr.paulitique.quiz.model.Quiz;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private FreeQuestionMapper freeQuestionMapper;
	
	@Autowired
	private NumericalQuestionMapper numericalQuestionMapper;
	
	@Autowired
	private MultipleChoiceQuestionMapper multipleChoiceQuestionMapper;
	
	@Autowired
	private UniqueChoiceQuestionMapper uniqueChoiceQuestionMapper;
	
	@PostMapping("/api/freeQuestion")
	public FreeQuestionDTO createFreeQuestion(@RequestBody FreeQuestionDTO freeQuestionDTO) {
		
		FreeQuestion question = freeQuestionMapper.DTOToEntity(freeQuestionDTO);
		
		FreeQuestion createdQuestion = questionService.createFreeQuestion(question);
		
		FreeQuestionDTO createdQuestionDTO = freeQuestionMapper.entityToDTO(createdQuestion);
		
		return createdQuestionDTO;
	}
}
