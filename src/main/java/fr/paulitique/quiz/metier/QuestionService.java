package fr.paulitique.quiz.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dao.IQuestionDAO;
import fr.paulitique.quiz.dao.IQuizDAO;
import fr.paulitique.quiz.model.FreeQuestion;
import fr.paulitique.quiz.model.MultipleChoiceQuestion;
import fr.paulitique.quiz.model.NumericalQuestion;
import fr.paulitique.quiz.model.Question;
import fr.paulitique.quiz.model.Quiz;
import fr.paulitique.quiz.model.UniqueChoiceQuestion;

@Component
public class QuestionService {
	@Autowired
	private IQuestionDAO questionDAO;
	
	public FreeQuestion createQuestion(FreeQuestion question) {
		
		//TODO: Add validation ?
		
		question.setId(null);
		FreeQuestion savedQuestion = questionDAO.save(question);
		
		return savedQuestion;
	}
	
	
	public NumericalQuestion createQuestion(NumericalQuestion question) {
		
		//TODO: Add validation ?
		
		question.setId(null);
		NumericalQuestion savedQuestion = questionDAO.save(question);
		
		return savedQuestion;
	}
	
	
	public MultipleChoiceQuestion createQuestion(MultipleChoiceQuestion question) {
		
		//TODO: Add validation ?
		
		question.setId(null);
		MultipleChoiceQuestion savedQuestion = questionDAO.save(question);
		
		return savedQuestion;
	}
	
	
	public UniqueChoiceQuestion createQuestion(UniqueChoiceQuestion question) {
		
		//TODO: Add validation ?
		
		question.setId(null);
		UniqueChoiceQuestion savedQuestion = questionDAO.save(question);
		
		return savedQuestion;
	}
	
	
	
	public void deleteQuestion(Integer id) {
		
		//TODO: Add confirmation step ?
		Question question = questionDAO.findQuestionById(id);
		questionDAO.delete(question);

	}
}
