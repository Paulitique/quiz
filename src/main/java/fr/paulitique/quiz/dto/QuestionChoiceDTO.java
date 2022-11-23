package fr.paulitique.quiz.dto;

import java.util.List;

import fr.paulitique.quiz.model.Choice;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionChoiceDTO {
	private Integer id;
	
	private String text;
	
	private QuizDTO quiz;
	
	private List<ChoiceDTO> choices;
}
