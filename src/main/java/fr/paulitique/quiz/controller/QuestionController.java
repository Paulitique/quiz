package fr.paulitique.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fr.paulitique.quiz.mapper.FreeQuestionMapper;
import fr.paulitique.quiz.mapper.MultipleChoiceQuestionMapper;
import fr.paulitique.quiz.mapper.NumericalQuestionMapper;
import fr.paulitique.quiz.mapper.QuizMapper;
import fr.paulitique.quiz.mapper.UniqueChoiceQuestionMapper;
import fr.paulitique.quiz.metier.QuestionService;
import fr.paulitique.quiz.metier.QuizService;

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
}
