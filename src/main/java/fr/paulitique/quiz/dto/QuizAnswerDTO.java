package fr.paulitique.quiz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizAnswerDTO extends AnswerDTO {
	
	private Integer idQuiz;
	
	private Iterable<Integer> idAnswerList;
	
}
