package fr.paulitique.quiz.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Quiz {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	private String name;
	
	private String description;
	
	@OneToMany
	private List<Question> questions;
	
	//TODO: Add categories
	// private Iterable<Category> categories;
}
