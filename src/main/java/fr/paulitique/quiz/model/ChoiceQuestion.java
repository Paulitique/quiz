package fr.paulitique.quiz.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public abstract class ChoiceQuestion extends Question {

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "CHOICE_QUESTION_ID", nullable = true)
	private List<Choice> choices;

}
