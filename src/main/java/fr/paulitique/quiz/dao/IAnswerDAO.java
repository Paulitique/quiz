package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.Answer;

public interface IAnswerDAO extends JpaRepository<Answer, Integer> {
	
	Answer findAnswerById( Integer id );

}
