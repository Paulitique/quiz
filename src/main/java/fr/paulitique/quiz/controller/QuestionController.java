package fr.paulitique.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.paulitique.quiz.dto.FreeQuestionDTO;
import fr.paulitique.quiz.dto.MultipleChoiceQuestionDTO;
import fr.paulitique.quiz.dto.NumericalQuestionDTO;
import fr.paulitique.quiz.dto.QuizDTO;
import fr.paulitique.quiz.dto.UniqueChoiceQuestionDTO;
import fr.paulitique.quiz.mapper.FreeQuestionMapper;
import fr.paulitique.quiz.mapper.MultipleChoiceQuestionMapper;
import fr.paulitique.quiz.mapper.NumericalQuestionMapper;
import fr.paulitique.quiz.mapper.QuizMapper;
import fr.paulitique.quiz.mapper.UniqueChoiceQuestionMapper;
import fr.paulitique.quiz.metier.QuestionService;
import fr.paulitique.quiz.metier.QuizService;
import fr.paulitique.quiz.model.FreeQuestion;
import fr.paulitique.quiz.model.MultipleChoiceQuestion;
import fr.paulitique.quiz.model.NumericalQuestion;
import fr.paulitique.quiz.model.Question;
import fr.paulitique.quiz.model.Quiz;
import fr.paulitique.quiz.model.UniqueChoiceQuestion;
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
		
		FreeQuestion createdQuestion = questionService.createQuestion(question);
		
		FreeQuestionDTO createdQuestionDTO = freeQuestionMapper.entityToDTO(createdQuestion);
		
		return createdQuestionDTO;
	}
	
	@PostMapping("/api/numericalQuestion")
	public NumericalQuestionDTO createNumericalQuestion(@RequestBody NumericalQuestionDTO numericalQuestionDTO) {
		
		NumericalQuestion question = numericalQuestionMapper.DTOToEntity(numericalQuestionDTO);
		
		NumericalQuestion createdQuestion = questionService.createQuestion(question);
		
		NumericalQuestionDTO createdQuestionDTO = numericalQuestionMapper.entityToDTO(createdQuestion);
		
		return createdQuestionDTO;
	}
	
	@PostMapping("/api/multipleChoiceQuestion")
	public MultipleChoiceQuestionDTO createMultipleChoiceQuestion(@RequestBody MultipleChoiceQuestionDTO multipleChoiceQuestionDTO) {
		
		MultipleChoiceQuestion question = multipleChoiceQuestionMapper.DTOToEntity(multipleChoiceQuestionDTO);
		
		MultipleChoiceQuestion createdQuestion = questionService.createQuestion(question);
		
		MultipleChoiceQuestionDTO createdQuestionDTO = multipleChoiceQuestionMapper.entityToDTO(createdQuestion);
		
		return createdQuestionDTO;
	}
	
	@PostMapping("/api/uniqueChoiceQuestion")
	public UniqueChoiceQuestionDTO createFreeQuestion(@RequestBody UniqueChoiceQuestionDTO uniqueChoiceQuestionDTO) {
		
		UniqueChoiceQuestion question = uniqueChoiceQuestionMapper.DTOToEntity(uniqueChoiceQuestionDTO);
		
		UniqueChoiceQuestion createdQuestion = questionService.createQuestion(question);
		
		UniqueChoiceQuestionDTO createdQuestionDTO = uniqueChoiceQuestionMapper.entityToDTO(createdQuestion);
		
		return createdQuestionDTO;
	}
	
	@DeleteMapping("/api/question/{questionId}")
	public void deleteQuestion(@PathVariable String questionId) {
		
		Integer id = Integer.parseInt(questionId);
		
		questionService.deleteQuestion(id);
		
	}
}
