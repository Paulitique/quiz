package fr.paulitique.quiz.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class QuizAnswer extends Answer {
	
	@ManyToOne
	private Quiz quiz;
	
	@ManyToOne
	private Iterable<Answer> answer;
}
