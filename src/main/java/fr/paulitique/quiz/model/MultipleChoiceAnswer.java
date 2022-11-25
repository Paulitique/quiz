package fr.paulitique.quiz.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MultipleChoiceAnswer extends Answer{
	
	@ManyToMany
	private Iterable<Choice> choices;
	
	@ManyToOne
	private Question question;
}
