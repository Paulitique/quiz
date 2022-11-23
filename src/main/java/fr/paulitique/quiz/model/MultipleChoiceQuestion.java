package fr.paulitique.quiz.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MultipleChoiceQuestion extends Question {

	@OneToMany
	private List<Choice> choices;

}
