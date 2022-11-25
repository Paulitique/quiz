package fr.paulitique.quiz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultipleChoiceAnswerDTO extends AnswerDTO {
	
	private Iterable<Integer> idChoice;
	
	private Integer idQuestion;
}
