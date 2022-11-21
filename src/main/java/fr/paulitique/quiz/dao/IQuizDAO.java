package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.Quiz;

public interface IQuizDAO extends JpaRepository<Quiz, Integer>{

}
