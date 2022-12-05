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
public class QuizAnswer {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="QUIZ_ANSWER_ID")
	private Integer id;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "ANSWER_F_QUIZ_ANSWER_ID", nullable = true)
	private List<Answer> answers;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUIZ_ANSWER_F_QUIZ_ID")
	private Quiz quiz;
	
}
