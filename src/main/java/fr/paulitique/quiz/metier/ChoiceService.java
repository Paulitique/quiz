package fr.paulitique.quiz.metier;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dao.IChoiceDAO;
import fr.paulitique.quiz.model.Choice;

@Component
public class ChoiceService {

	@Autowired
	private IChoiceDAO choiceDAO;
	
	@Autowired
	@Lazy
	private AnswerService answerService;
	
	public Choice createChoice(Choice choice) {
		choice.setId(null);
		Choice savedChoice = choiceDAO.save(choice);
		return savedChoice;
	}
	
	public void deleteChoice(Choice choice) {
		choice.getMultipleChoiceAnswers().forEach(answer -> {
			List<Choice> choices = answer.getChoices();
			List<Choice> newChoices = new ArrayList<>();
			choices.forEach(currentChoice -> {
				if (!currentChoice.getId().equals(choice.getId())) {
					newChoices.add(currentChoice);
				}
			});
			answer.setChoices(newChoices);
			answerService.updateAnswer(answer);
		});
		
		choice.getUniqueChoiceAnswers().forEach(answer -> {
			answerService.deleteAnswer(answer);
		});
		
		choiceDAO.delete(choice);
	}
	
	
	public Choice updateChoice(Choice choice) {
		Choice foundChoice = choiceDAO.findChoiceById(choice.getId());
		foundChoice.setText(choice.getText());
		
		Choice savedChoice = choiceDAO.save(foundChoice);
		return savedChoice;
	}
	
	public Choice findChoice(Integer id) {
		return choiceDAO.findChoiceById(id);
	}
	
}
