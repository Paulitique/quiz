package fr.paulitique.quiz.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UniqueChoiceAnswer extends Answer {
	
	@ManyToOne
	private Choice choice;
	
	@ManyToOne
	private Question question;
}
