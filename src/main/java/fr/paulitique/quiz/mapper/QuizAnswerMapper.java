package fr.paulitique.quiz.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dto.AnswerDTO;
import fr.paulitique.quiz.dto.QuizAnswerDTO;
import fr.paulitique.quiz.model.Answer;
import fr.paulitique.quiz.model.QuizAnswer;

@Component
public class QuizAnswerMapper {
	
	@Autowired
	private AnswerMapper answerMapper;
	
	public QuizAnswerDTO entityToDTO(QuizAnswer quizAnswer) {
		QuizAnswerDTO quizAnswerDTO = new QuizAnswerDTO();
		
		quizAnswerDTO.setId(quizAnswer.getId());
		
		List<AnswerDTO> answerDTOList = new ArrayList<>();
		quizAnswer.getAnswers().forEach(answer -> 
			answerDTOList.add(answerMapper.entityToDTO(answer)));
		
		quizAnswerDTO.setAnswerList(answerDTOList);
		
		return quizAnswerDTO;
	}
	
	public QuizAnswer DTOToEntity(QuizAnswerDTO quizAnswerDTO) {
		QuizAnswer quizAnswer = new QuizAnswer();
		
		quizAnswer.setId(quizAnswerDTO.getId());
		
		List<Answer> answerList = new ArrayList<>();
		quizAnswerDTO.getAnswerList().forEach(answerDTO -> 
			answerList.add(answerMapper.DTOToEntity(answerDTO)));
		
		quizAnswer.setAnswers(answerList);
		
		return quizAnswer;
	}
}
