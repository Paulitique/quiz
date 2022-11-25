package fr.paulitique.quiz.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Choice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CHOICE_QUESTION_ID", nullable = true)
	private ChoiceQuestion question;
}
