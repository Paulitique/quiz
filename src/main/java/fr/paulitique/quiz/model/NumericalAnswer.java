package fr.paulitique.quiz.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NumericalAnswer extends Answer {

	@Column(name = "NUMERICAL_ANSWER_VALUE")
	private BigDecimal value;
	
}
