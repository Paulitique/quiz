package fr.paulitique.quiz.metier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dao.IAnswerDAO;
import fr.paulitique.quiz.dao.IQuizDAO;
import fr.paulitique.quiz.model.Quiz;

@Component
public class AnswerService {
 // TODO
	
	@Autowired
	private IAnswerDAO answerDAO;
	
	
	
}
