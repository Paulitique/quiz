package fr.paulitique.quiz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeQuestionDTO extends QuestionDTO{

	private final QuestionType questionType = QuestionType.FreeQuestion;
	
	
}
