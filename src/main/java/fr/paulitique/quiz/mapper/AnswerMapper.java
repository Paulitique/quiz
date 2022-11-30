package fr.paulitique.quiz.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dto.AnswerDTO;
import fr.paulitique.quiz.dto.ChoiceDTO;
import fr.paulitique.quiz.dto.FreeAnswerDTO;
import fr.paulitique.quiz.dto.MultipleChoiceAnswerDTO;
import fr.paulitique.quiz.dto.NumericalAnswerDTO;
import fr.paulitique.quiz.dto.UniqueChoiceAnswerDTO;
import fr.paulitique.quiz.model.Answer;
import fr.paulitique.quiz.model.Choice;
import fr.paulitique.quiz.model.FreeAnswer;
import fr.paulitique.quiz.model.MultipleChoiceAnswer;
import fr.paulitique.quiz.model.NumericalAnswer;
import fr.paulitique.quiz.model.UniqueChoiceAnswer;

@Component
public class AnswerMapper {

	@Autowired
	private ChoiceMapper choiceMapper;
	
	public AnswerDTO entityToDTO(Answer answer) {
		
		if (answer instanceof FreeAnswer) return entityToDTO((FreeAnswer) answer);
		if (answer instanceof MultipleChoiceAnswer) return entityToDTO((MultipleChoiceAnswer) answer);
		if (answer instanceof UniqueChoiceAnswer) return entityToDTO((UniqueChoiceAnswer) answer);
		if (answer instanceof NumericalAnswer) return entityToDTO((NumericalAnswer) answer);
		return null;
	}
	
	public FreeAnswerDTO entityToDTO(FreeAnswer answer) {
		FreeAnswerDTO freeAnswerDTO = new FreeAnswerDTO();
		
		freeAnswerDTO.setId(answer.getId());
		freeAnswerDTO.setText(answer.getText());
		return freeAnswerDTO;
	}
	
	public MultipleChoiceAnswerDTO entityToDTO(MultipleChoiceAnswer answer) {
		MultipleChoiceAnswerDTO multipleChoiceAnswerDTO = new MultipleChoiceAnswerDTO();
		
		List<ChoiceDTO> choiceDTOList = new ArrayList<>();
		
		answer.getChoices().forEach(choice -> choiceDTOList.add(choiceMapper.entityToDTO(choice)));
		
		multipleChoiceAnswerDTO.setId(answer.getId());
		multipleChoiceAnswerDTO.setChoices(choiceDTOList);
		return multipleChoiceAnswerDTO;
	}
	
	public UniqueChoiceAnswerDTO entityToDTO(UniqueChoiceAnswer answer) {
		UniqueChoiceAnswerDTO uniqueChoiceAnswerDTO = new UniqueChoiceAnswerDTO();
		
		uniqueChoiceAnswerDTO.setId(answer.getId());
		uniqueChoiceAnswerDTO.setChoice(choiceMapper.entityToDTO(answer.getChoice()));
		
		return uniqueChoiceAnswerDTO;
	}
	
	public NumericalAnswerDTO entityToDTO(NumericalAnswer answer) {
		NumericalAnswerDTO numericalAnswerDTO = new NumericalAnswerDTO();
		
		numericalAnswerDTO.setId(answer.getId());
		numericalAnswerDTO.setValue(answer.getValue().toPlainString());
		return numericalAnswerDTO;
	}
	
	
	
	
	public Answer DTOToEntity(AnswerDTO answerDTO) {
		
		switch (answerDTO.getAnswerType()) {
		case FreeAnswer:
			if (answerDTO instanceof FreeAnswerDTO) return DTOToEntity((FreeAnswerDTO) answerDTO);
			break;

		case MultipleChoiceAnswer:
			if (answerDTO instanceof MultipleChoiceAnswerDTO) return DTOToEntity((MultipleChoiceAnswerDTO) answerDTO);
			break;
			
		case UniqueChoiceAnswer:
			if (answerDTO instanceof UniqueChoiceAnswerDTO) return DTOToEntity((UniqueChoiceAnswerDTO) answerDTO);
			break;
		
		case NumericalAnswer:
			if (answerDTO instanceof NumericalAnswerDTO) return DTOToEntity((NumericalAnswerDTO) answerDTO);
			break;
		}
		
		return null;
	}
	
	public FreeAnswer DTOToEntity(FreeAnswerDTO answerDTO) {
		FreeAnswer freeAnswer = new FreeAnswer();
		freeAnswer.setId(answerDTO.getId());
		freeAnswer.setText(answerDTO.getText());
		return freeAnswer;
	}
	
	public MultipleChoiceAnswer DTOToEntity(MultipleChoiceAnswerDTO answerDTO) {
		MultipleChoiceAnswer multipleChoiceAnswer = new MultipleChoiceAnswer();
		
		List<Choice> choiceList = new ArrayList<>();
		answerDTO.getChoices().forEach(choiceDTO -> choiceList.add(choiceMapper.DTOToEntity(choiceDTO)));
		
		multipleChoiceAnswer.setId(answerDTO.getId());
		multipleChoiceAnswer.setChoices(choiceList);
		return multipleChoiceAnswer;
	}
	
	public UniqueChoiceAnswer DTOToEntity(UniqueChoiceAnswerDTO answerDTO) {
		UniqueChoiceAnswer uniqueChoiceAnswer = new UniqueChoiceAnswer();
		
		uniqueChoiceAnswer.setId(answerDTO.getId());
		uniqueChoiceAnswer.setChoice(choiceMapper.DTOToEntity(answerDTO.getChoice()));

		return uniqueChoiceAnswer;
	}
	
	public NumericalAnswer DTOToEntity(NumericalAnswerDTO answerDTO) {
		NumericalAnswer numericalAnswer = new NumericalAnswer();
		numericalAnswer.setId(answerDTO.getId());
		numericalAnswer.setValue(new BigDecimal(answerDTO.getValue()));
		return numericalAnswer;
	}
}
