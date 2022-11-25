package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.FreeQuestion;
import fr.paulitique.quiz.model.NumericalQuestion;

public interface IFreeQuestionDAO extends JpaRepository<FreeQuestion, Integer>{

	FreeQuestion findQuestionById(Integer id);
	
}
