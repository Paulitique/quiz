package fr.paulitique.quiz.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dao.IQuizDAO;
import fr.paulitique.quiz.model.Quiz;

@Component
public class QuizService {
	
	@Autowired
	private IQuizDAO quizDAO;
	
	public Quiz createQuiz(Quiz quiz) {
		
		//TODO: Add validation ?
		
		quiz.setId(null);
		Quiz savedQuiz = quizDAO.save(quiz);
		
		
		return savedQuiz;
	}
	
	public List<Quiz> findQuizList() {
		
		return quizDAO.findAll();
	}
}
