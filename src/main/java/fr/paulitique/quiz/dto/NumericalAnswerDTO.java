package fr.paulitique.quiz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumericalAnswerDTO extends AnswerDTO {

	private Long num;
	
	private Integer idQuestion;
	
}
