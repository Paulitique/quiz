package fr.paulitique.quiz.model;

import javax.persistence.Column;
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
public abstract class Answer {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ANSWER_TEXT")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ANSWER_F_QUESTION_ID", nullable = true)
	private Question question;
	
}
