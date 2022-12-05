package fr.paulitique.quiz.metier;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dao.IQuizAnswerDAO;
import fr.paulitique.quiz.model.Answer;
import fr.paulitique.quiz.model.QuizAnswer;

@Component
public class QuizAnswerService {

	@Autowired
	private IQuizAnswerDAO quizAnswerDAO;
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private AnswerService answerService;
	
	public QuizAnswer createQuizAnswer(Integer quizId, QuizAnswer quizAnswer) {
		quizAnswer.setQuiz(quizService.getQuiz(quizId));
		return createQuizAnswer(quizAnswer);
	}
	
	public QuizAnswer createQuizAnswer(QuizAnswer quizAnswer) {
		quizAnswer.setId(0);
		
		List<Answer> answers = new ArrayList<>();
		quizAnswer.getAnswers().forEach(answer -> answers.add(answerService.getAnswer(answer.getId())));
		
		quizAnswer.setAnswers(answers);
		
		if (quizAnswer.getQuiz()!=null) {
			quizAnswer.setQuiz(quizService.getQuiz(quizAnswer.getQuiz().getId()));
		}
		
		QuizAnswer savedQuizAnswer = quizAnswerDAO.save(quizAnswer);
		return savedQuizAnswer;
	}
	
	public QuizAnswer getQuizAnswer(Integer id) {
		return quizAnswerDAO.findQuizAnswerById(id);
	}
	
	public List<QuizAnswer> getAllQuizAnswers() {
		return quizAnswerDAO.findAll();
	}
	
	public QuizAnswer updateAnswer(QuizAnswer quizAnswer) {
		QuizAnswer foundQuizAnswer = quizAnswerDAO.findQuizAnswerById(quizAnswer.getId());
		
		List<Answer> answers = new ArrayList<>();
		quizAnswer.getAnswers().forEach(answer -> answers.add(answerService.getAnswer(answer.getId())));
		
		foundQuizAnswer.setAnswers(answers);
		
		QuizAnswer savedQuizAnswer = quizAnswerDAO.save(foundQuizAnswer);
		return savedQuizAnswer;
	}

	public void deleteAnswer(QuizAnswer quizAnswer) {
		QuizAnswer foundQuizAnswer = quizAnswerDAO.findQuizAnswerById(quizAnswer.getId());
		quizAnswerDAO.delete(foundQuizAnswer);
	}
}