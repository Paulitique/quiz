package fr.paulitique.quiz.mapper;

import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dto.MultipleChoiceQuestionDTO;
import fr.paulitique.quiz.dto.QuizDTO;
import fr.paulitique.quiz.model.MultipleChoiceQuestion;
import fr.paulitique.quiz.model.Question;
import fr.paulitique.quiz.model.Quiz;

@Component
public class MultipleChoiceQuestionMapper {

	public MultipleChoiceQuestionDTO entityToDTO(MultipleChoiceQuestion question) {
		
		MultipleChoiceQuestionDTO questionDTO = new MultipleChoiceQuestionDTO();
		
		questionDTO.setId(question.getId());
		questionDTO.setText(question.getText());
		
		//don't manage quiz
		
		return questionDTO;
	}
	
	public MultipleChoiceQuestion DTOToEntity(MultipleChoiceQuestionDTO questionDTO) {
		MultipleChoiceQuestion question = new MultipleChoiceQuestion();
		//need to have a separate Mapper for each type of concrete question because of this line 
		
		question.setId(questionDTO.getId());
		question.setText(questionDTO.getText());
		
		return question;
	}
}
