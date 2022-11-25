package fr.paulitique.quiz.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.dto.ChoiceDTO;
import fr.paulitique.quiz.dto.FreeQuestionDTO;
import fr.paulitique.quiz.dto.MultipleChoiceQuestionDTO;
import fr.paulitique.quiz.dto.NumericalQuestionDTO;
import fr.paulitique.quiz.dto.QuestionDTO;
import fr.paulitique.quiz.dto.UniqueChoiceQuestionDTO;
import fr.paulitique.quiz.model.Choice;
import fr.paulitique.quiz.model.FreeQuestion;
import fr.paulitique.quiz.model.MultipleChoiceQuestion;
import fr.paulitique.quiz.model.NumericalQuestion;
import fr.paulitique.quiz.model.Question;
import fr.paulitique.quiz.model.UniqueChoiceQuestion;

@Component
public class QuestionMapper {

	@Autowired
	private ChoiceMapper choiceMapper;
	
	public QuestionDTO entityToDTO(Question question) {
		
		if (question instanceof FreeQuestion) return entityToDTO((FreeQuestion) question);
		if (question instanceof MultipleChoiceQuestion) return entityToDTO((MultipleChoiceQuestion) question);
		if (question instanceof UniqueChoiceQuestion) return entityToDTO((UniqueChoiceQuestion) question);
		if (question instanceof NumericalQuestion) return entityToDTO((NumericalQuestion) question);
		return null;
	}
	
	public FreeQuestionDTO entityToDTO(FreeQuestion question) {
		FreeQuestionDTO freeQuestionDTO = new FreeQuestionDTO();
		
		freeQuestionDTO.setId(question.getId());
		freeQuestionDTO.setText(question.getText());
		return freeQuestionDTO;
	}
	
	public MultipleChoiceQuestionDTO entityToDTO(MultipleChoiceQuestion question) {
		MultipleChoiceQuestionDTO multipleChoiceQuestionDTO = new MultipleChoiceQuestionDTO();
		
		List<ChoiceDTO> choiceDTOList = new ArrayList<>();
		
		question.getChoices().forEach(choice -> choiceDTOList.add(choiceMapper.entityToDTO(choice)));
		
		multipleChoiceQuestionDTO.setId(question.getId());
		multipleChoiceQuestionDTO.setChoices(choiceDTOList);
		multipleChoiceQuestionDTO.setText(question.getText());
		return multipleChoiceQuestionDTO;
	}
	
	public UniqueChoiceQuestionDTO entityToDTO(UniqueChoiceQuestion question) {
		UniqueChoiceQuestionDTO uniqueChoiceQuestionDTO = new UniqueChoiceQuestionDTO();
		List<ChoiceDTO> choiceDTOList = new ArrayList<>();
		
		question.getChoices().forEach(choice -> choiceDTOList.add(choiceMapper.entityToDTO(choice)));
		
		uniqueChoiceQuestionDTO.setId(question.getId());
		uniqueChoiceQuestionDTO.setChoices(choiceDTOList);
		uniqueChoiceQuestionDTO.setText(question.getText());
		return uniqueChoiceQuestionDTO;
	}
	
	public NumericalQuestionDTO entityToDTO(NumericalQuestion question) {
		NumericalQuestionDTO numericalQuestionDTO = new NumericalQuestionDTO();
		
		numericalQuestionDTO.setId(question.getId());
		numericalQuestionDTO.setText(question.getText());
		return numericalQuestionDTO;
	}
	
	
	
	
	public Question DTOToEntity(QuestionDTO questionDTO) {
		
		switch (questionDTO.getQuestionType()) {
		case FreeQuestion:
			if (questionDTO instanceof FreeQuestionDTO) return DTOToEntity((FreeQuestionDTO) questionDTO);
			break;

		case MultipleChoiceQuestion:
			if (questionDTO instanceof MultipleChoiceQuestionDTO) return DTOToEntity((MultipleChoiceQuestionDTO) questionDTO);
			break;
			
		case UniqueChoiceQuestion:
			if (questionDTO instanceof UniqueChoiceQuestionDTO) return DTOToEntity((UniqueChoiceQuestionDTO) questionDTO);
			break;
		
		case NumericalQuestion:
			if (questionDTO instanceof NumericalQuestionDTO) return DTOToEntity((NumericalQuestionDTO) questionDTO);
			break;
		}
		
		return null;
	}
	
	public FreeQuestion DTOToEntity(FreeQuestionDTO questionDTO) {
		FreeQuestion freeQuestion = new FreeQuestion();
		freeQuestion.setId(questionDTO.getId());
		freeQuestion.setText(questionDTO.getText());
		return freeQuestion;
	}
	
	public MultipleChoiceQuestion DTOToEntity(MultipleChoiceQuestionDTO questionDTO) {
		MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
		
		List<Choice> choiceList = new ArrayList<>();
		questionDTO.getChoices().forEach(choiceDTO -> choiceList.add(choiceMapper.DTOToEntity(choiceDTO)));
		
		multipleChoiceQuestion.setId(questionDTO.getId());
		multipleChoiceQuestion.setChoices(choiceList);
		multipleChoiceQuestion.setText(questionDTO.getText());
		return multipleChoiceQuestion;
	}
	
	public UniqueChoiceQuestion DTOToEntity(UniqueChoiceQuestionDTO questionDTO) {
		UniqueChoiceQuestion uniqueChoiceQuestion = new UniqueChoiceQuestion();
		
		List<Choice> choiceList = new ArrayList<>();
		questionDTO.getChoices().forEach(choiceDTO -> choiceList.add(choiceMapper.DTOToEntity(choiceDTO)));
		
		uniqueChoiceQuestion.setId(questionDTO.getId());
		uniqueChoiceQuestion.setChoices(choiceList);
		uniqueChoiceQuestion.setText(questionDTO.getText());
		return uniqueChoiceQuestion;
	}
	
	public NumericalQuestion DTOToEntity(NumericalQuestionDTO questionDTO) {
		NumericalQuestion numericalQuestion = new NumericalQuestion();
		numericalQuestion.setId(questionDTO.getId());
		numericalQuestion.setText(questionDTO.getText());
		return numericalQuestion;
	}
}
