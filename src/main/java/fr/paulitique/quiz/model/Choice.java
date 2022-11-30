package fr.paulitique.quiz.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Choice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="CHOICE_ID")
	private Integer id;
	
	@Column(name="CHOICE_TEXT")
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CHOICE_F_QUESTION_ID", nullable = true)
	private ChoiceQuestion question;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "MULTIPLE_CHOICE_ANSWER_CHOICE",
			   joinColumns = @JoinColumn(name = "F_CHOICE_ID"),
			   inverseJoinColumns = @JoinColumn(name = "F_MULTIPLE_CHOICE_ANSWER_ID"))
	private List<MultipleChoiceAnswer> multipleChoiceAnswers;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "ANSWER_F_CHOICE_ID", nullable = true)
	private List<UniqueChoiceAnswer> uniqueChoiceAnswers;
}
