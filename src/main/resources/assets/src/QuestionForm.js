import React from "react";
// import MyCodeMirror from "./MyCodeMirror";
import CodeMirror from "react16-codemirror";
require("codemirror/lib/codemirror.css");
require("codemirror/mode/python/python.js");
require("codemirror/addon/edit/closebrackets");
require("codemirror/theme/darcula.css");

class QuestionForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      questionId: props.questionId,
      question: props.question,
      solution: props.solution,
      judgeCode: props.judgeCode,
      solutionFunction: props.solutionFunction,
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onChange(event) {
    const target = event.target;
    const name = target.name;
    const value = target.value;
    this.setState({
      [name]: value,
    });
  }

  onSubmit(event) {
    event.preventDefault();
    this.props.onSubmit(this.state);
  }

  render() {
    const textBoxStyle = {
      width: "100%",
      height: "300px",
      overflowy: "scroll",
      backgroundColor: "#f8f8f8",
    };

    var options = {
      lineNumbers: true,
      autoCloseBrackets: true,
      theme: "darcula",
      mode: "python",
    };

    return (
      <div className="container">
        <form onSubmit={this.onSubmit} className="form-group">
          <label htmlFor="questionId">Write Question Id</label>
          <br />
          <input
            id="questionId"
            name="questionId"
            value={this.state.questionId}
            onChange={this.onChange}
            className="form-control"
          />
          <br />
          <br />
          <label htmlFor="question">Write Question</label>
          <br />
          <textarea
            id="question"
            style={textBoxStyle}
            name="question"
            value={this.state.question}
            onChange={this.onChange}
          />
          <br />
          <br />
          <label htmlFor="judgeCode">
            Provide the judging code. Put function to be implemented by user in
            #% solution %# on new line
          </label>
          <br />
          <CodeMirror
            id="judgeCode"
            name="judgeCode"
            options={options}
            value={this.state.judgeCode}
            onChange={(newCode) => {
              this.setState({ judgeCode: newCode });
            }}
          />
          <br />
          <br />
          <label htmlFor="solutionFunction">
            Provide the function that user has to implement to run the code,
            which will be replaced with #% solution %# in the judge code
          </label>
          <br />
          <CodeMirror
            id="solutionFunction"
            name="solutionFunction"
            options={options}
            value={this.state.solutionFunction}
            onChange={(newCode) => {
              this.setState({ solutionFunction: newCode });
            }}
          />
          <br />
          <br />
          <label htmlFor="solution">Implement the correct function here</label>
          <br />
          <CodeMirror
            id="solution"
            style={textBoxStyle}
            name="solution"
            options={options}
            value={this.state.solution}
            onChange={(newCode) => {
              this.setState({ solution: newCode });
            }}
          />
          <br />
          <input
            type="submit"
            value="Submit"
            className="btn btn-primary btn-lg"
            id="search"
          ></input>
        </form>
      </div>
    );
  }
}

export default QuestionForm;
