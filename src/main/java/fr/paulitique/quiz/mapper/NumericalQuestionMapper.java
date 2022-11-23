package fr.paulitique.quiz.mapper;

import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dto.FreeQuestionDTO;
import fr.paulitique.quiz.dto.NumericalQuestionDTO;
import fr.paulitique.quiz.model.FreeQuestion;
import fr.paulitique.quiz.model.NumericalQuestion;
import fr.paulitique.quiz.model.Question;

@Component
public class NumericalQuestionMapper {
	public NumericalQuestionDTO entityToDTO(NumericalQuestion question) {
		
		NumericalQuestionDTO questionDTO = new NumericalQuestionDTO();
		
		questionDTO.setId(question.getId());
		questionDTO.setText(question.getText());
		
		//don't manage quiz
		
		return questionDTO;
	}
	
	public NumericalQuestion DTOToEntity(NumericalQuestionDTO questionDTO) {
		NumericalQuestion question = new NumericalQuestion();
		//need to have a separate Mapper for each type of concrete question because of this line 
		
		question.setId(questionDTO.getId());
		question.setText(questionDTO.getText());
		
		return question;
	}
}
