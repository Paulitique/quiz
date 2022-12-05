package fr.paulitique.quiz.model;

import java.util.List;

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
public class Quiz {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="QUIZ_ID")
	private Integer id;
	
	@Column(name="QUIZ_NAME")
	private String name;
	
	@Column(name="QUIZ_DESCRIPTION")
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTION_F_QUIZ_ID", nullable = true)
	private List<Question> questions;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUIZ_ANSWER_F_QUIZ_ID")
	private List<QuizAnswer> quizAnswers;
	//TODO: Add categories
	// private Iterable<Category> categories;
}
