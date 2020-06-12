package com.example.LearnDataScience.core;

import org.bson.types.ObjectId;

import java.util.Objects;

public class Question {

//    @BsonId
    private ObjectId id;
//    @BsonProperty
    private String question;
//    @BsonProperty
    private String solution;
//    @BsonProperty
    private String testCases;
//    @BsonProperty
    private String outputAnswers;

    public  Question(){

    }

    public Question(String question, String solution) {
//        this._id = new ObjectId();
//        this.question = question;
//        this.solution = solution;
        this(question, solution, "", "");
    }

    public Question(String question, String solution, String testCases, String outputAnswers) {
//        this.id = new ObjectId();
        this.question = question;
        this.solution = solution;
        this.testCases = testCases;
        this.outputAnswers = outputAnswers;
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

    public String getTestCases() {
        return testCases;
    }

    public void setTestCases(String testCases) {
        this.testCases = testCases;
    }

    public String getOutputAnswers() {
        return outputAnswers;
    }

    public void setOutputAnswers(String outputAnswers) {
        this.outputAnswers = outputAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(id, question1.id) &&
                Objects.equals(question, question1.question) &&
                Objects.equals(solution, question1.solution) &&
                Objects.equals(testCases, question1.testCases) &&
                Objects.equals(outputAnswers, question1.outputAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, solution, testCases, outputAnswers);
    }

    @Override
    public String toString() {
//        System.out.println(id);
//        System.out.println(question);
//        System.out.println(solution);
//        System.out.println(testCases);
//        System.out.println(outputAnswers);
        return "Questions{" +
                "questionNumber='" + this.id + '\'' +
                ", question='" + this.question + '\'' +
                ", solution='" + this.solution + '\'' +
                ", testCases='" + (this.testCases == null ? "none": this.testCases) + '\'' +
                ", outputAnswers='" + (this.outputAnswers == null ? "none": this.outputAnswers) + '\'' +
                '}';
    }
}
