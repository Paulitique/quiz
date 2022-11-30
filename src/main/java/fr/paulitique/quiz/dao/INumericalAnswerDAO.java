package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.NumericalAnswer;

public interface INumericalAnswerDAO extends JpaRepository<NumericalAnswer, Integer> {

	NumericalAnswer findAnswerById(Integer id);
	
}
