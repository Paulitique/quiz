package fr.paulitique.quiz.mapper;

import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dto.UniqueChoiceQuestionDTO;
import fr.paulitique.quiz.dto.QuizDTO;
import fr.paulitique.quiz.model.UniqueChoiceQuestion;
import fr.paulitique.quiz.model.Question;
import fr.paulitique.quiz.model.Quiz;

@Component
public class UniqueChoiceQuestionMapper {

	public UniqueChoiceQuestionDTO entityToDTO(UniqueChoiceQuestion question) {
		
		UniqueChoiceQuestionDTO questionDTO = new UniqueChoiceQuestionDTO();
		
		questionDTO.setId(question.getId());
		questionDTO.setText(question.getText());
		
		//don't manage quiz
		
		return questionDTO;
	}
	
	public Question DTOToEntity(UniqueChoiceQuestionDTO questionDTO) {
		Question question = new UniqueChoiceQuestion();
		//need to have a separate Mapper for each type of concrete question because of this line 
		
		question.setId(questionDTO.getId());
		question.setText(questionDTO.getText());
		
		return question;
	}
}
