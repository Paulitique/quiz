package fr.paulitique.quiz.mapper;

import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dto.FreeQuestionDTO;
import fr.paulitique.quiz.dto.QuizDTO;
import fr.paulitique.quiz.model.FreeQuestion;
import fr.paulitique.quiz.model.Question;
import fr.paulitique.quiz.model.Quiz;

@Component
public class FreeQuestionMapper {

	public FreeQuestionDTO entityToDTO(FreeQuestion question) {
		
		FreeQuestionDTO questionDTO = new FreeQuestionDTO();
		
		questionDTO.setId(question.getId());
		questionDTO.setText(question.getText());
		
		//don't manage quiz
		
		return questionDTO;
	}
	
	public FreeQuestion DTOToEntity(FreeQuestionDTO questionDTO) {
		FreeQuestion question = new FreeQuestion();
		//need to have a separate Mapper for each type of concrete question because of this line 
		
		question.setId(questionDTO.getId());
		question.setText(questionDTO.getText());
		
		return question;
	}
}
