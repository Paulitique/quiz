package fr.paulitique.quiz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeAnswerDTO extends AnswerDTO {

	private final AnswerType answerType = AnswerType.FreeAnswer;
	private String text;
	
}
