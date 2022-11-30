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
		  property = "answerType")
		@JsonSubTypes({ 
		  @Type(value = FreeAnswerDTO.class, name = "FreeAnswer"), 
		  @Type(value = MultipleChoiceAnswerDTO.class, name = "MultipleChoiceAnswer"),
		  @Type(value = UniqueChoiceAnswerDTO.class, name = "UniqueChoiceAnswer"),
		  @Type(value = NumericalAnswerDTO.class, name = "NumericalAnswer")
		})
public class AnswerDTO {

	private Integer id;
	private AnswerType answerType;

}
