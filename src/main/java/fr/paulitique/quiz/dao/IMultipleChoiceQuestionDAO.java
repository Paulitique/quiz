package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.FreeQuestion;
import fr.paulitique.quiz.model.MultipleChoiceQuestion;
import fr.paulitique.quiz.model.NumericalQuestion;

public interface IMultipleChoiceQuestionDAO extends JpaRepository<MultipleChoiceQuestion, Integer>{

	MultipleChoiceQuestion findQuestionById(Integer id);
	
}
