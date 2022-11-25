package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.UniqueChoiceQuestion;

public interface IUniqueChoiceQuestionDAO extends JpaRepository<UniqueChoiceQuestion, Integer>{

	
	UniqueChoiceQuestion findQuestionById(Integer id);
	
}
