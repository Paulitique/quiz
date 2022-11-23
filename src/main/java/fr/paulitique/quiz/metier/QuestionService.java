package fr.paulitique.quiz.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dao.IQuestionDAO;
import fr.paulitique.quiz.dao.IQuizDAO;
import fr.paulitique.quiz.model.FreeQuestion;
import fr.paulitique.quiz.model.Question;
import fr.paulitique.quiz.model.Quiz;

@Component
public class QuestionService {
	@Autowired
	private IQuestionDAO questionDAO;
	
	public FreeQuestion createFreeQuestion(FreeQuestion question) {
		
		//TODO: Add validation ?
		
		question.setId(null);
		FreeQuestion savedQuestion = questionDAO.save(question);
		
		return savedQuestion;
	}
}
