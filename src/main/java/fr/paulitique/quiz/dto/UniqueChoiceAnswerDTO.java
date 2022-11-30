package fr.paulitique.quiz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UniqueChoiceAnswerDTO extends AnswerDTO {

	private final AnswerType answerType = AnswerType.UniqueChoiceAnswer;
	private ChoiceDTO choice;
	
}
