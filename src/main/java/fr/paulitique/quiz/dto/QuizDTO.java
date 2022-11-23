package fr.paulitique.quiz.dto;

import java.util.Set;

import java.util.List;

import fr.paulitique.quiz.model.Question;
import fr.paulitique.quiz.model.Quiz;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizDTO {

	private Integer id;
	private String name;
	private String description;
	private List<Question> questions;
	//this won't let me set the type of Question to QuestionDTO:
	//when I do, QuizMapper has an error
	
}
