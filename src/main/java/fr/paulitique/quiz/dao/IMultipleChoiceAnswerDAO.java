package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.Answer;
import fr.paulitique.quiz.model.MultipleChoiceAnswer;

public interface IMultipleChoiceAnswerDAO extends JpaRepository<MultipleChoiceAnswer, Integer> {

	MultipleChoiceAnswer findAnswerById(Integer id);
	
}
