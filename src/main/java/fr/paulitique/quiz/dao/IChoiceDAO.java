package fr.paulitique.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.paulitique.quiz.model.Choice;

public interface IChoiceDAO extends JpaRepository<Choice, Integer>{

	Choice findChoiceById( Integer id );
	
}
