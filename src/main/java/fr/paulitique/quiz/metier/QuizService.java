package fr.paulitique.quiz.metier;

import java.util.List;
import java.util.Optional;

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
	
	public void deleteQuiz(Integer id) {
		
		//TODO: Add confirmation step ?
		Quiz quiz = quizDAO.findQuizById(id);
		quizDAO.delete(quiz);

	}
	
	public Quiz getQuiz(Integer id) {
		
		Quiz consultedQuiz = quizDAO.findQuizById(id);
		
		return consultedQuiz;
		
	}
	
	public List<Quiz> getAllQuiz() {
		
		return quizDAO.findAll();
		
	}
	
	public Quiz modifyQuiz(Quiz quiz) {
		
		Quiz savedQuiz = quizDAO.findQuizById( quiz.getId() );
		
		savedQuiz.setName( quiz.getName() );
		savedQuiz.setDescription( quiz.getDescription() );
		
		savedQuiz = quizDAO.save(savedQuiz);
		
		return savedQuiz;
	}
}
