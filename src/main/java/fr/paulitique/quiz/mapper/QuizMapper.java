package fr.paulitique.quiz.mapper;

import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dto.QuizDTO;
import fr.paulitique.quiz.model.Quiz;

@Component
public class QuizMapper {

	public QuizDTO entityToDTO(Quiz quiz) {
		
		QuizDTO quizDTO = new QuizDTO();
		
		quizDTO.setId(quiz.getId());
		quizDTO.setName(quiz.getName());
		quizDTO.setDescription(quiz.getDescription());
		
		return quizDTO;
	}
	
	public Quiz DTOToEntity(QuizDTO quizDTO) {
		Quiz quiz = new Quiz();
		
		quiz.setId(quizDTO.getId());
		quiz.setName(quizDTO.getName());
		quiz.setDescription(quizDTO.getDescription());
		
		return quiz;
	}
}
