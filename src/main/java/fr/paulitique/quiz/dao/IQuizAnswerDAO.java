package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.FreeAnswer;
import fr.paulitique.quiz.model.QuizAnswer;

public interface IQuizAnswerDAO extends JpaRepository<QuizAnswer, Integer> {

	QuizAnswer findQuizAnswerById(Integer id);
}
