package fr.paulitique.quiz.mapper;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.paulitique.quiz.model.Answer;
import fr.paulitique.quiz.model.Choice;
import fr.paulitique.quiz.model.FreeAnswer;
import fr.paulitique.quiz.dao.IAnswerDAO;
import fr.paulitique.quiz.dao.IChoiceDAO;
import fr.paulitique.quiz.dao.IQuestionDAO;
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
	
	@Autowired
	private IQuestionDAO questionDAO;
	
	@Autowired
	private IChoiceDAO choiceDAO;
	
	@Autowired 
	private IAnswerDAO answerDAO;
	
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
		answer.setQuestion(questionDAO.findQuestionById(answerDTO.getIdQuestion()));
		
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
		answer.setQuestion(questionDAO.findQuestionById(answerDTO.getIdQuestion()));
		
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
		answer.setChoice(choiceDAO.findChoiceById(answerDTO.getIdChoice()));
		answer.setQuestion(questionDAO.findQuestionById(answerDTO.getIdQuestion()));
		
		return answer;
	}
	
	public QuizAnswerDTO entityToDTO(QuizAnswer answer) {
		
		QuizAnswerDTO answerDTO = new QuizAnswerDTO();
		
		answerDTO.setId(answer.getId());
		answerDTO.setIdQuiz(answer.getQuiz().getId());
		// answers :
		Iterator<Answer> iterator = answer.getAnswer().iterator();
		ArrayList<Integer> answerIdList = new ArrayList<Integer>();
		while( iterator.hasNext() ) {
			answerIdList.add( iterator.next().getId() );
		}
		answerDTO.setIdAnswerList(answerIdList);
		
		return answerDTO;
	}
	
	public QuizAnswer DTOToEntity(QuizAnswerDTO answerDTO) {
		QuizAnswer answer = new QuizAnswer();
		
		answer.setId(answerDTO.getId());
		answer.setQuiz(quizDAO.findQuizById(answerDTO.getIdQuiz()));
		//answers :
		Iterator<Integer> iterator = answerDTO.getIdAnswerList().iterator();
		ArrayList<Answer> answerList = new ArrayList<Answer>();
		while( iterator.hasNext() ) {
			answerList.add( answerDAO.findAnswerById(iterator.next()) );
		}
		answer.setAnswer( answerList );
		return answer;
	}
	
	public MultipleChoiceAnswerDTO entityToDTO(MultipleChoiceAnswer answer) {
		
		MultipleChoiceAnswerDTO answerDTO = new MultipleChoiceAnswerDTO();
		
		answerDTO.setId(answer.getId());
		answerDTO.setIdQuestion(answer.getQuestion().getId());
		// choices :
		Iterator<Choice> iterator = answer.getChoices().iterator();
		ArrayList<Integer> choiceIdList = new ArrayList<Integer>();
		while( iterator.hasNext() ) {
			choiceIdList.add( iterator.next().getId() );
		}
		answerDTO.setIdChoice(choiceIdList);
		
		return answerDTO;
	}
	
	public MultipleChoiceAnswer DTOToEntity(MultipleChoiceAnswerDTO answerDTO) {
		MultipleChoiceAnswer answer = new MultipleChoiceAnswer();
		
		answer.setId(answerDTO.getId());
		answer.setQuestion(questionDAO.findQuestionById(answerDTO.getIdQuestion()));
		// choices :
		Iterator<Integer> iterator = answerDTO.getIdChoice().iterator();
		ArrayList<Choice> choiceList = new ArrayList<Choice>();
		while( iterator.hasNext() ) {
			choiceList.add( choiceDAO.findChoiceById(iterator.next()) );
		}
		answer.setChoices(choiceList);
		
		return answer;
	}
	
	
	//
	//
	//  TODO : vérifier que tous les attributs y sont pour chaque type !!
	//
	//

}
