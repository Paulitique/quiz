package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.FreeQuestion;

public interface IFreeQuestionDAO extends JpaRepository<FreeQuestion, Integer>{

}
