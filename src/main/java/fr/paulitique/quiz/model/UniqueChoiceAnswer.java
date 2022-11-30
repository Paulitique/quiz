package fr.paulitique.quiz.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UniqueChoiceAnswer extends Answer {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ANSWER_F_CHOICE_ID", nullable = true)
	private Choice choice;
	
}
