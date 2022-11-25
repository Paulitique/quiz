package fr.paulitique.quiz.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fr.paulitique.quiz.model.FreeAnswer;
import fr.paulitique.quiz.dao.IQuizDAO;
import fr.paulitique.quiz.dto.FreeAnswerDTO;
import fr.paulitique.quiz.model.MultipleChoiceAnswer;
import fr.paulitique.quiz.dto.MultipleChoiceAnswerDTO;
import fr.paulitique.quiz.model.UniqueChoiceAnswer;
import fr.paulitique.quiz.dto.UniqueChoiceAnswerDTO;
import fr.paulitique.quiz.model.NumericalAnswer;
import fr.paulitique.quiz.dto.NumericalAnswerDTO;
import fr.paulitique.quiz.model.QuizAnswer;
import fr.paulitique.quiz.dto.QuizAnswerDTO;

@Component
public class AnswerMapper {
	
	@Autowired
	private IQuizDAO quizDAO;
	
	public FreeAnswerDTO entityToDTO(FreeAnswer answer) {
		
		FreeAnswerDTO answerDTO = new FreeAnswerDTO();
		
		answerDTO.setId(answer.getId());
		answerDTO.setContent(answer.getContent());
		answerDTO.setIdQuestion(answer.getQuestion().getId());
		
		return answerDTO;
	}
	
	public FreeAnswer DTOToEntity(FreeAnswerDTO answerDTO) {
		FreeAnswer answer = new FreeAnswer();
		
		answer.setId(answerDTO.getId());
		answer.setContent(answerDTO.getContent());
		// TODO answer.setQuestion();
		
		return answer;
	}
	
public NumericalAnswerDTO entityToDTO(NumericalAnswer answer) {
		
		NumericalAnswerDTO answerDTO = new NumericalAnswerDTO();
		
		answerDTO.setId(answer.getId());
		answerDTO.setNum(answer.getNum());
		answerDTO.setIdQuestion(answer.getQuestion().getId());
		
		return answerDTO;
	}
	
	public NumericalAnswer DTOToEntity(NumericalAnswerDTO answerDTO) {
		NumericalAnswer answer = new NumericalAnswer();
		
		answer.setId(answerDTO.getId());
		answer.setNum(answerDTO.getNum());
		// TODO answer.setQuestion();
		
		return answer;
	}
	
public UniqueChoiceAnswerDTO entityToDTO(UniqueChoiceAnswer answer) {
		
		UniqueChoiceAnswerDTO answerDTO = new UniqueChoiceAnswerDTO();
		
		answerDTO.setId(answer.getId());
		answerDTO.setIdChoice(answer.getChoice().getId());
		answerDTO.setIdQuestion(answer.getQuestion().getId());
		
		return answerDTO;
	}
	
	public UniqueChoiceAnswer DTOToEntity(UniqueChoiceAnswerDTO answerDTO) {
		UniqueChoiceAnswer answer = new UniqueChoiceAnswer();
		
		answer.setId(answerDTO.getId());
		// TODO answer.setChoice();
		// TODO answer.setQuestion();
		
		return answer;
	}
	
public QuizAnswerDTO entityToDTO(QuizAnswer answer) {
		
		QuizAnswerDTO answerDTO = new QuizAnswerDTO();
		
		answerDTO.setId(answer.getId());
		answerDTO.setIdQuiz(answer.getQuiz().getId());
		//TODO answer
		
		return answerDTO;
	}
	
	public QuizAnswer DTOToEntity(QuizAnswerDTO answerDTO) {
		QuizAnswer answer = new QuizAnswer();
		
		answer.setId(answerDTO.getId());
		answer.setQuiz(quizDAO.findQuizById(answerDTO.getIdQuiz()));
		//TODO answer.setAnswer();
		
		return answer;
	}
	
	//TODO MultipleChoiceAnswer
	
	//
	//
	//  TODO : vérifier que tous les attributs y sont pour chaque type !!
	//
	//

}
