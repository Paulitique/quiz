package fr.paulitique.quiz.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultipleChoiceAnswerDTO extends AnswerDTO {

	private final AnswerType answerType = AnswerType.MultipleChoiceAnswer;
	private List<ChoiceDTO> choices;
	
}
