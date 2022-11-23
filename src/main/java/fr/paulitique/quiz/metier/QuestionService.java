package fr.paulitique.quiz.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dao.IQuestionDAO;
import fr.paulitique.quiz.dao.IQuizDAO;

@Component
public class QuestionService {
	@Autowired
	private IQuestionDAO questionDAO;
	
	
}
