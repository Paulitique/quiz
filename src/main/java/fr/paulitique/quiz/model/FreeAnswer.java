package fr.paulitique.quiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FreeAnswer extends Answer {

	@Column(name = "FREE_ANSWER_TEXT")
	private String text;
	
}
