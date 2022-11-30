package fr.paulitique.quiz.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public abstract class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "QUESTION_ID")
	private Integer id;
	
	@Column(name = "QUESTION_TEXT")
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTION_F_QUIZ_ID", nullable = true)
	private Quiz quiz;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "ANSWER_F_QUESTION_ID", nullable = true)
	private Set<Answer> answers;	
	
}
