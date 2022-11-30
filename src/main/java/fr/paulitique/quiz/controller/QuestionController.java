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

import fr.paulitique.quiz.dto.QuestionDTO;
import fr.paulitique.quiz.mapper.QuestionMapper;
import fr.paulitique.quiz.metier.QuestionService;
import fr.paulitique.quiz.metier.QuizService;
import fr.paulitique.quiz.model.Question;
import fr.paulitique.quiz.model.Quiz;

@RestController
@RequestMapping("/api/quiz")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/{quizId}/question")
	public QuestionDTO createQuestion(@PathVariable String quizId, @RequestBody QuestionDTO questionDTO) {
		
		return questionMapper.entityToDTO(questionService.createQuestion(Integer.parseInt(quizId), questionMapper.DTOToEntity(questionDTO))); 
	}
	
	@GetMapping("/question/all")
	public List<QuestionDTO> getAllQuestion() {
		
		List<QuestionDTO> questionDTOList = new ArrayList<>();
		questionService.getAllQuestions().forEach(question -> questionDTOList.add(questionMapper.entityToDTO(question)));
		
		return questionDTOList;
	}
	
	@GetMapping("/{quizId}/question/all")
	public List<QuestionDTO> getAllQuestion(@PathVariable String quizId) {
		Integer id = Integer.parseInt(quizId);
		Quiz quiz = quizService.getQuiz(id);
		List<QuestionDTO> questionDTOList = new ArrayList<>();
		quiz.getQuestions().forEach(question -> questionDTOList.add(questionMapper.entityToDTO(question)));
		
		return questionDTOList;
	}
	
	@GetMapping("/question/{questionId}")
	public QuestionDTO getQuestion(@PathVariable String questionId) {
		Integer id = Integer.parseInt(questionId);
		
		Question question = questionService.getQuestion(id);
		
		QuestionDTO questionDTO = questionMapper.entityToDTO(question);
		
		return questionDTO;
	}
	
	@DeleteMapping("/question/{questionId}")
	public void deleteQuestion(@PathVariable String questionId) {
		Integer id = Integer.parseInt(questionId);
		
		questionService.deleteQuestion(id);
	}
	
	@PutMapping("/question/")
	public QuestionDTO updateQuestion(@RequestBody QuestionDTO questionDTO) {
		Question question = questionMapper.DTOToEntity(questionDTO);
		Question savedQuestion = questionService.updateQuestion(question);
		QuestionDTO savedQuestionDTO = questionMapper.entityToDTO(savedQuestion);
		return savedQuestionDTO;
	}
	
}
