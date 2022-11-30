package fr.paulitique.quiz.metier;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dao.IFreeQuestionDAO;
import fr.paulitique.quiz.dao.IMultipleChoiceQuestionDAO;
import fr.paulitique.quiz.dao.INumericalQuestionDAO;
import fr.paulitique.quiz.dao.IQuestionDAO;
import fr.paulitique.quiz.dao.IUniqueChoiceQuestionDAO;
import fr.paulitique.quiz.model.Choice;
import fr.paulitique.quiz.model.ChoiceQuestion;
import fr.paulitique.quiz.model.FreeQuestion;
import fr.paulitique.quiz.model.MultipleChoiceQuestion;
import fr.paulitique.quiz.model.NumericalQuestion;
import fr.paulitique.quiz.model.Question;
import fr.paulitique.quiz.model.Quiz;
import fr.paulitique.quiz.model.UniqueChoiceQuestion;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class QuestionService {

	@Autowired
	private IQuestionDAO questionDAO;
	
	@Autowired
	private IFreeQuestionDAO freeQuestionDAO;
	
	@Autowired
	private IMultipleChoiceQuestionDAO multipleChoiceQuestionDAO;
	
	@Autowired
	private IUniqueChoiceQuestionDAO uniqueChoiceQuestionDAO;
	
	@Autowired
	private INumericalQuestionDAO numericalQuestionDAO;
	
	@Autowired
	private ChoiceService choiceService;
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private AnswerService answerService;
	
	
	public Question createQuestion(Integer quizId, Question question) {
		Quiz quiz = quizService.getQuiz(quizId);
		question.setQuiz(quiz);
		
		return createQuestion(question);
	}
	
	public Question createQuestion(Question question) {
		if (question instanceof FreeQuestion) return createQuestion((FreeQuestion) question);
		if (question instanceof MultipleChoiceQuestion) return createQuestion((MultipleChoiceQuestion) question);
		if (question instanceof UniqueChoiceQuestion) return createQuestion((UniqueChoiceQuestion) question);
		if (question instanceof NumericalQuestion) return createQuestion((NumericalQuestion) question);
		return null;
	}
	
	public FreeQuestion createQuestion(FreeQuestion question) {
		
		question.setId(null);
		FreeQuestion savedQuestion = freeQuestionDAO.save(question);
		return savedQuestion;
		
	}
	
	public MultipleChoiceQuestion createQuestion(MultipleChoiceQuestion question) {
		
		question.setId(null);
		
		List<Choice> savedChoices = new ArrayList<>();
		question.getChoices().forEach(choice -> savedChoices.add(choiceService.createChoice(choice)));
		question.setChoices(savedChoices);
		
		MultipleChoiceQuestion savedQuestion = multipleChoiceQuestionDAO.save(question);
		return savedQuestion;
		
	}
	
	public UniqueChoiceQuestion createQuestion(UniqueChoiceQuestion question) {
		question.setId(null);
		
		List<Choice> savedChoices = new ArrayList<>();
		question.getChoices().forEach(choice -> savedChoices.add(choiceService.createChoice(choice)));
		question.setChoices(savedChoices);
		
		UniqueChoiceQuestion savedQuestion = uniqueChoiceQuestionDAO.save(question);
		return savedQuestion;
	}
	
	public NumericalQuestion createQuestion(NumericalQuestion question) {
		question.setId(null);
		NumericalQuestion savedQuestion = numericalQuestionDAO.save(question);
		return savedQuestion;
	}
	
	public List<Question> getAllQuestions() {
		return questionDAO.findAll();
	}
	
	public Question getQuestion(Integer id) {
		return questionDAO.findQuestionById(id);
	}
	
	public void deleteQuestion(Integer id) {
		Question question = questionDAO.findQuestionById(id);
		
		question.getAnswers().forEach(answerService::deleteAnswer);
		
		if (question instanceof ChoiceQuestion) {
			((ChoiceQuestion) question).getChoices().forEach(choiceService::deleteChoice);
		}
		
		questionDAO.delete(question);
	}
	
	public Question updateQuestion(Question question) {
		if (question instanceof FreeQuestion) return updateQuestion((FreeQuestion) question);
		if (question instanceof MultipleChoiceQuestion) return updateQuestion((MultipleChoiceQuestion) question);
		if (question instanceof UniqueChoiceQuestion) return updateQuestion((UniqueChoiceQuestion) question);
		if (question instanceof NumericalQuestion) return updateQuestion((NumericalQuestion) question);
		return null;
	}
	
	public FreeQuestion updateQuestion(FreeQuestion question) {
		FreeQuestion foundQuestion = freeQuestionDAO.findQuestionById(question.getId());
		
		foundQuestion.setText(question.getText());
		
		FreeQuestion savedQuestion = freeQuestionDAO.save(foundQuestion);
		
		return savedQuestion;
	}

	public void updateQuestionChoices(ChoiceQuestion question, ChoiceQuestion foundQuestion) {
		
		List<Choice> foundChoices = new ArrayList<>();
		List<Choice> newChoices = new ArrayList<>();
		List<Choice> missingChoices = new ArrayList<>();
		
		question.getChoices().forEach(choice -> {
			Choice foundChoice = choiceService.findChoice(choice.getId());
			
			if (foundChoice == null) {
				newChoices.add(choice);
			} else {
				if (!foundChoice.getQuestion().getId().equals(foundQuestion.getId())) {
					newChoices.add(choice);
				} else {
					foundChoices.add(choice);
				}
			}
		});
		
		foundQuestion.getChoices().forEach(choice -> {
			for (Choice otherChoice: foundChoices) {
				if (choice.getId().equals(otherChoice.getId())) {
					return;
				}
			}
			missingChoices.add(choice);
		});
		
		List<Choice> updatedChoices = new ArrayList<>();
		
		foundChoices.forEach(choice -> updatedChoices.add(choiceService.updateChoice(choice)));
		newChoices.forEach(choice -> updatedChoices.add(choiceService.createChoice(choice)));
		missingChoices.forEach(choice -> choiceService.deleteChoice(choice));
		
		foundQuestion.setChoices(updatedChoices);
		
	}
	
	public MultipleChoiceQuestion updateQuestion(MultipleChoiceQuestion question) {
		MultipleChoiceQuestion foundQuestion = multipleChoiceQuestionDAO.findQuestionById(question.getId());
		
		foundQuestion.setText(question.getText());
		
		updateQuestionChoices(question, foundQuestion);
		
		MultipleChoiceQuestion savedQuestion = multipleChoiceQuestionDAO.save(foundQuestion);
		
		return savedQuestion;
	}
	
	public UniqueChoiceQuestion updateQuestion(UniqueChoiceQuestion question) {
		UniqueChoiceQuestion foundQuestion = uniqueChoiceQuestionDAO.findQuestionById(question.getId());
		
		foundQuestion.setText(question.getText());
		
		updateQuestionChoices(question, foundQuestion);
		
		UniqueChoiceQuestion savedQuestion = uniqueChoiceQuestionDAO.save(foundQuestion);
		
		return savedQuestion;
	}
	
	public NumericalQuestion updateQuestion(NumericalQuestion question) {
		
		NumericalQuestion foundQuestion = numericalQuestionDAO.findQuestionById(question.getId());
		
		foundQuestion.setText(question.getText());
		
		NumericalQuestion savedQuestion = numericalQuestionDAO.save(foundQuestion);
		
		return savedQuestion;
	}
}
