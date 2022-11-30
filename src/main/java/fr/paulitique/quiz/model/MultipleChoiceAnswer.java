package fr.paulitique.quiz.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MultipleChoiceAnswer extends Answer {

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "MULTIPLE_CHOICE_ANSWER_CHOICE",
			   joinColumns = @JoinColumn(name = "F_MULTIPLE_CHOICE_ANSWER_ID"),
			   inverseJoinColumns = @JoinColumn(name = "F_CHOICE_ID"))
	private List<Choice> choices;
	
}
