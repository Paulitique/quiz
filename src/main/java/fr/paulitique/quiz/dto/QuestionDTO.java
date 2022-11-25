package fr.paulitique.quiz.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "questionType")
		@JsonSubTypes({ 
		  @Type(value = FreeQuestionDTO.class, name = "FreeQuestion"), 
		  @Type(value = MultipleChoiceQuestionDTO.class, name = "MultipleChoiceQuestion"),
		  @Type(value = UniqueChoiceQuestionDTO.class, name = "UniqueChoiceQuestion"),
		  @Type(value = NumericalQuestionDTO.class, name = "NumericalQuestion")
		})
public abstract class QuestionDTO {
	
	private Integer id;
	private String text;
	private QuestionType questionType;
	
}
