package fr.paulitique.quiz.dto;

import java.util.List;

import fr.paulitique.quiz.model.Choice;
import fr.paulitique.quiz.model.Quiz;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultipleChoiceQuestionDTO {
	private Integer id;
	
	private String text;
	
	private QuizDTO quiz;
	
	private List<Choice> choices;
}
