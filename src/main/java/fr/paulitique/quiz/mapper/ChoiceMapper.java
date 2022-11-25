package fr.paulitique.quiz.mapper;

import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dto.ChoiceDTO;
import fr.paulitique.quiz.model.Choice;

@Component
public class ChoiceMapper {

	public ChoiceDTO entityToDTO(Choice choice) {
		
		ChoiceDTO choiceDTO = new ChoiceDTO();
		
		choiceDTO.setId(choice.getId());
		choiceDTO.setText(choice.getText());
		
		return choiceDTO;
	}
	
	public Choice DTOToEntity(ChoiceDTO choiceDTO) {
		
		Choice choice = new Choice();
		
		choice.setId(choiceDTO.getId());
		choice.setText(choiceDTO.getText());
		
		return choice;
	}
	
}
