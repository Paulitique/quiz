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
import fr.paulitique.quiz.model.Question;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuestionMapper questionMapper;
	
	@PostMapping("/")
	public QuestionDTO createQuestion(@RequestBody QuestionDTO questionDTO) {
		
		return questionMapper.entityToDTO(questionService.createQuestion(questionMapper.DTOToEntity(questionDTO))); 
	}
	
	@GetMapping("/all")
	public List<QuestionDTO> getAllQuestion() {
		
		List<QuestionDTO> questionDTOList = new ArrayList<>();
		questionService.getAllQuestions().forEach(question -> questionDTOList.add(questionMapper.entityToDTO(question)));
		
		return questionDTOList;
	}
	
	@GetMapping("/{questionId}")
	public QuestionDTO getQuestion(@PathVariable String questionId) {
		Integer id = Integer.parseInt(questionId);
		
		Question question = questionService.getQuestion(id);
		
		QuestionDTO questionDTO = questionMapper.entityToDTO(question);
		
		return questionDTO;
	}
	
	@DeleteMapping("/{questionId}")
	public void deleteQuestion(@PathVariable String questionId) {
		Integer id = Integer.parseInt(questionId);
		
		questionService.deleteQuestion(id);
	}
	
	@PutMapping("/")
	public QuestionDTO updateQuestion(@RequestBody QuestionDTO questionDTO) {
		Question question = questionMapper.DTOToEntity(questionDTO);
		Question savedQuestion = questionService.updateQuestion(question);
		QuestionDTO savedQuestionDTO = questionMapper.entityToDTO(savedQuestion);
		return savedQuestionDTO;
	}
	
}
