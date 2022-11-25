package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.NumericalQuestion;
import fr.paulitique.quiz.model.Question;

public interface INumericalQuestionDAO extends JpaRepository<NumericalQuestion, Integer>{

	NumericalQuestion findQuestionById(Integer id);
	
}
