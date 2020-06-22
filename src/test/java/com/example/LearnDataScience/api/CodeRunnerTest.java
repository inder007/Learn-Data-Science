package com.example.LearnDataScience.api;

import com.example.LearnDataScience.core.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import java.io.InvalidObjectException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

public class CodeRunnerTest {


  @ParameterizedTest
    @CsvFileSource(resources="/data.csv", numLinesToSkip = 1)
  public void testActualPythonCodeRunner(String id, String ques, String sol, String jcode, String solFunc, String code, String expected) throws Exception{
      Question question = new Question(id, ques, sol, jcode, solFunc);
      CodeRunner codeRunner = new CodeRunner(question, code);
      String out = codeRunner.pythonCodeRunner();
      if(out.endsWith("\n")){
          out = out.substring(0, out.length()-1);
      }
      assertEquals(expected, out);
  }

  @Test
  public void testMockPythonCodeRunner() throws Exception{
      Question question = new Question("1","","", "#% solution %#", "");
      String code = "Hi";
      CodeRunner codeRunner = new CodeRunner(question, code);
      CodeRunner codeRunner1 = Mockito.spy(codeRunner);
      Mockito.doReturn("Hi").when(codeRunner1).runDocker();
      assertEquals("Hi", codeRunner1.pythonCodeRunner());
  }

  @Test
  public void mockQuestion() throws Exception{
      Question question = Mockito.mock(Question.class);
      when(question.getJudgeCode()).thenReturn("#% solution %#");
      String code = "Hi";
      CodeRunner codeRunner = new CodeRunner(question, code);
      CodeRunner codeRunner1 = Mockito.spy(codeRunner);
      Mockito.doReturn("Hi").when(codeRunner1).runDocker();
      assertEquals("Hi", codeRunner1.pythonCodeRunner());
  }

    @Test
    public void checkEmptyQuestion(){
      try{
          Question question = new Question("", "", "");
          Question question1 = new Question(null, "", "");
          fail("This should throw exception");
      }
      catch (InvalidObjectException e) {

      }
    }

}