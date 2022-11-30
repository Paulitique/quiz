package fr.paulitique.quiz.metier;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dao.IAnswerDAO;
import fr.paulitique.quiz.dao.IFreeAnswerDAO;
import fr.paulitique.quiz.dao.IMultipleChoiceAnswerDAO;
import fr.paulitique.quiz.dao.INumericalAnswerDAO;
import fr.paulitique.quiz.dao.IUniqueChoiceAnswerDAO;
import fr.paulitique.quiz.model.Answer;
import fr.paulitique.quiz.model.Choice;
import fr.paulitique.quiz.model.FreeAnswer;
import fr.paulitique.quiz.model.MultipleChoiceAnswer;
import fr.paulitique.quiz.model.NumericalAnswer;
import fr.paulitique.quiz.model.UniqueChoiceAnswer;


@Component
public class AnswerService {
	@Autowired
	private IAnswerDAO answerDAO;
	
	@Autowired
	private IFreeAnswerDAO freeAnswerDAO;
	
	@Autowired
	private IMultipleChoiceAnswerDAO multipleChoiceAnswerDAO;
	
	@Autowired
	private IUniqueChoiceAnswerDAO uniqueChoiceAnswerDAO;
	
	@Autowired
	private INumericalAnswerDAO numericalAnswerDAO;
	
	@Autowired
	private ChoiceService choiceService;
	
	public Answer createAnswer(Answer answer) {
		if (answer instanceof FreeAnswer) return createAnswer((FreeAnswer) answer);
		if (answer instanceof MultipleChoiceAnswer) return createAnswer((MultipleChoiceAnswer) answer);
		if (answer instanceof UniqueChoiceAnswer) return createAnswer((UniqueChoiceAnswer) answer);
		if (answer instanceof NumericalAnswer) return createAnswer((NumericalAnswer) answer);
		return null;
	}
	
	public FreeAnswer createAnswer(FreeAnswer answer) {
		answer.setId(null);
		FreeAnswer savedAnswer = freeAnswerDAO.save(answer);
		return savedAnswer;
	}
	
	public MultipleChoiceAnswer createAnswer(MultipleChoiceAnswer answer) {
		answer.setId(null);
		
		List<Choice> choices = new ArrayList<>();
		answer.getChoices().forEach(choice -> {
			Choice foundChoice = choiceService.findChoice(choice.getId());
			foundChoice.getId();
			choices.add(foundChoice);
			});
		
		answer.setChoices(choices);
		MultipleChoiceAnswer savedAnswer = multipleChoiceAnswerDAO.save(answer);
		return savedAnswer;
	}
	
	public UniqueChoiceAnswer createAnswer(UniqueChoiceAnswer answer) {
		answer.setId(null);
		
		Choice choice = choiceService.findChoice(answer.getChoice().getId());
		answer.setChoice(choice);
		
		UniqueChoiceAnswer savedAnswer = uniqueChoiceAnswerDAO.save(answer);
		return savedAnswer;
	}
	
	public NumericalAnswer createAnswer(NumericalAnswer answer) {
		answer.setId(null);
		
		NumericalAnswer savedAnswer = numericalAnswerDAO.save(answer);
		return savedAnswer;
	}
	
	public List<Answer> getAllAnswers() {
		return answerDAO.findAll();
	}
	
	public Answer getAnswer(Integer answerId) {
		return answerDAO.findAnswerById(answerId);
	}
	
	public Answer updateAnswer(Answer answer) {
		if (answer instanceof FreeAnswer) return updateAnswer((FreeAnswer) answer);
		if (answer instanceof MultipleChoiceAnswer) return updateAnswer((MultipleChoiceAnswer) answer);
		if (answer instanceof UniqueChoiceAnswer) return updateAnswer((UniqueChoiceAnswer) answer);
		if (answer instanceof NumericalAnswer) return updateAnswer((NumericalAnswer) answer);
		return null;
	}
	
	public FreeAnswer updateAnswer(FreeAnswer answer) {
		FreeAnswer foundAnswer = freeAnswerDAO.findAnswerById(answer.getId());
		foundAnswer.setText(answer.getText());
		FreeAnswer savedAnswer = freeAnswerDAO.save(foundAnswer);
		return savedAnswer;
	}
	
	public MultipleChoiceAnswer updateAnswer(MultipleChoiceAnswer answer) {
		MultipleChoiceAnswer foundAnswer = multipleChoiceAnswerDAO.findAnswerById(answer.getId());
		List<Choice> choices = new ArrayList<>();
		answer.getChoices().forEach(choice -> choices.add(choiceService.findChoice(choice.getId())));
		foundAnswer.setChoices(choices);
		MultipleChoiceAnswer savedAnswer = multipleChoiceAnswerDAO.save(foundAnswer);
		return savedAnswer;
	}
	
	public UniqueChoiceAnswer updateAnswer(UniqueChoiceAnswer answer) {
		UniqueChoiceAnswer foundAnswer = uniqueChoiceAnswerDAO.findAnswerById(answer.getId());
		Choice choice = choiceService.findChoice(answer.getChoice().getId());
		foundAnswer.setChoice(choice);
		UniqueChoiceAnswer savedAnswer = uniqueChoiceAnswerDAO.save(foundAnswer);
		return savedAnswer;
	}
	
	public NumericalAnswer updateAnswer(NumericalAnswer answer) {
		NumericalAnswer foundAnswer = numericalAnswerDAO.findAnswerById(answer.getId());
		foundAnswer.setValue(answer.getValue());
		NumericalAnswer savedAnswer = numericalAnswerDAO.save(foundAnswer);
		return savedAnswer;
	}
	
	public void deleteAnswer(Answer answer) {
		Answer foundAnswer = answerDAO.findAnswerById(answer.getId());
		answerDAO.delete(foundAnswer);
	}
	
	public void deleteAnswer(Integer answerId) {
		deleteAnswer(answerDAO.findAnswerById(answerId));
	}
}