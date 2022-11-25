package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.FreeAnswer;

public interface IFreeAnswerDAO extends JpaRepository<FreeAnswer, Integer>{

}
