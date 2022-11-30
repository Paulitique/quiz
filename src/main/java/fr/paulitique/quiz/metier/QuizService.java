package fr.paulitique.quiz.metier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dao.IQuizDAO;
import fr.paulitique.quiz.model.Quiz;

@Component
public class QuizService {
	
	@Autowired
	private IQuizDAO quizDAO;
	
	@Autowired
	@Lazy
	private QuestionService questionService;
	
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
		quiz.getQuestions().forEach(question -> questionService.deleteQuestion(question.getId()));
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
		
		Quiz storedQuiz = quizDAO.findQuizById(quiz.getId());
		
		storedQuiz.setName(quiz.getName());
		storedQuiz.setDescription(quiz.getDescription());
		
		Quiz savedQuiz = quizDAO.save(storedQuiz);
		
		return savedQuiz;
	}
}
