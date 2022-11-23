package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.NumericalQuestion;

public interface INumericalQuestionDAO extends JpaRepository<NumericalQuestion, Integer>{

}
