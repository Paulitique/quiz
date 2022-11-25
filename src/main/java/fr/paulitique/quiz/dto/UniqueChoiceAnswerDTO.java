package fr.paulitique.quiz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UniqueChoiceAnswerDTO extends AnswerDTO {
	
	private Integer idChoice;
	
	private Integer idQuestion;
	
}
