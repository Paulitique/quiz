package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.UniqueChoiceAnswer;

public interface IUniqueChoiceAnswerDAO extends JpaRepository<UniqueChoiceAnswer, Integer> {

	UniqueChoiceAnswer findAnswerById(Integer id);
	
}
