package fr.paulitique.quiz.metier;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dao.IChoiceDAO;
import fr.paulitique.quiz.model.Choice;

@Component
public class ChoiceService {

	@Autowired
	private IChoiceDAO choiceDAO;
	
	public Choice createChoice(Choice choice) {
		choice.setId(null);
		Choice savedChoice = choiceDAO.save(choice);
		return savedChoice;
	}
	
	public void deleteChoice(Choice choice) {
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
