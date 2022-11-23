package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.Question;
import fr.paulitique.quiz.model.Quiz;

public interface IQuestionDAO extends JpaRepository<Question, Integer>{
	
	Question findQuestionById(Integer id);

}
