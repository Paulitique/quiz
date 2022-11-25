package fr.paulitique.quiz.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NumericalAnswer extends Answer{
	
	private Long num;
	
	@ManyToOne
	private Question question;
	
}
