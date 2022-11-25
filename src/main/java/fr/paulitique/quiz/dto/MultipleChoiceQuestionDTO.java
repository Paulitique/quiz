package fr.paulitique.quiz.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultipleChoiceQuestionDTO extends QuestionDTO{

	private final QuestionType questionType = QuestionType.MultipleChoiceQuestion;
	
	private List<ChoiceDTO> choices;
	
}
