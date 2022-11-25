package fr.paulitique.quiz.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UniqueChoiceQuestionDTO extends QuestionDTO{

	private final QuestionType questionType = QuestionType.UniqueChoiceQuestion;
	
	List<ChoiceDTO> choices;
	
}
