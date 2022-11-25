package fr.paulitique.quiz.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FreeAnswer extends Answer{
	
	private String content;
	
	@ManyToOne
	private Question question;
	
}
