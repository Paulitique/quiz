package fr.paulitique.quiz.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizAnswerDTO {
	private Integer id;
	private List<AnswerDTO> answerList;
}
