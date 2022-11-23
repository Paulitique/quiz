package fr.paulitique.quiz.dto;

import fr.paulitique.quiz.model.Quiz;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeQuestionDTO {
	private Integer id;
	
	private String text;
	
	private QuizDTO quiz;
}
