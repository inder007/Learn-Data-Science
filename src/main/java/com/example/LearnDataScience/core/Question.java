package com.example.LearnDataScience.core;

import org.bson.types.ObjectId;

import java.io.InvalidObjectException;
import java.util.Objects;

public class Question {

    private ObjectId id;
    private String questionId;
    private String question;
    private String solution;
    private String judgeCode;
    private String solutionFunction;

    public  Question(){

    }

    public Question(String questionId, String question, String solution) throws InvalidObjectException{
        this(questionId, question, solution, "", "");
    }

    public Question(String questionId, String question, String solution, String judgeCode, String solutionFunction) throws InvalidObjectException{
        if(questionId == null || questionId == ""){
            throw new InvalidObjectException("Question title can't be empty");
        }
        this.questionId = questionId;
        this.question = question;
        this.solution = solution;
        this.judgeCode = judgeCode;
        this.solutionFunction = solutionFunction;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
//        this._id = new ObjectId();
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getJudgeCode() {
        return judgeCode;
    }

    public void setJudgeCode(String judgeCode) {
        this.judgeCode = judgeCode;
    }

    public String getSolutionFunction() {
        return solutionFunction;
    }

    public void setSolutionFunction(String solutionFunction) {
        this.solutionFunction = solutionFunction;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(id, question1.id) &&
                Objects.equals(question, question1.question) &&
                Objects.equals(solution, question1.solution) &&
                Objects.equals(judgeCode, question1.judgeCode) &&
                Objects.equals(solutionFunction, question1.solutionFunction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, solution, judgeCode, solutionFunction);
    }

    @Override
    public String toString() {
        return "Questions{" +
                "questionNumber='" + this.id + '\'' +
                "questionId='" + this.questionId + '\'' +
                ", question='" + this.question + '\'' +
                ", solution='" + this.solution + '\'' +
                ", judgeCode='" + (this.judgeCode == null ? "none": this.judgeCode) + '\'' +
                ", solutionFunction='" + (this.solutionFunction == null ? "none": this.solutionFunction) + '\'' +
                '}';
    }
}
