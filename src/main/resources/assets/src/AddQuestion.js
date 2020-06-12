import React from "react";

class AddQuestion extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      question: "",
      solution: "",
      testCases: "",
      outputAnswers: "",
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

    fetch("api/question/addQuestion", {
      method: "POST",
      headers: new Headers({
        "content-type": "application/json",
      }),
      body: JSON.stringify(this.state),
    })
      .then((response) => {
        if (response.ok) {
          alert("Submitted");
        } else {
          alert("Error occured, try again");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }

  render() {
    const textBoxStyle = {
      width: "100%",
      height: "300px",
      overflowy: "scroll",
      backgroundColor: "#f8f8f8",
    };

    return (
      <div>
        <form onSubmit={this.onSubmit}>
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
          <label htmlFor="testCases">Write input cases</label>
          <br />
          <textarea
            id="testCases"
            style={textBoxStyle}
            name="testCases"
            value={this.state.testCases}
            onChange={this.onChange}
          />
          <br />
          <br />
          <label htmlFor="outputAnswers">
            Write output answers for the input test cases
          </label>
          <br />
          <textarea
            id="outputAnswers"
            style={textBoxStyle}
            name="outputAnswers"
            value={this.state.outputAnswers}
            onChange={this.onChange}
          />
          <br />
          <br />
          {/* <label htmlFor="inputCases">Select Input cases File</label>
          <br />
          <input type="file" id="inputCases" name="inputCases"></input>
          <br />
          <br />
          <label htmlFor="outputCases">Select Output cases File</label>
          <br />
          <input type="file" id="outputCases" name="outputCases"></input>
          <br />
          <br /> */}
          <label htmlFor="solution">Write Solution</label>
          <br />
          <textarea
            id="solution"
            style={textBoxStyle}
            name="solution"
            value={this.state.solution}
            onChange={this.onChange}
          />
          <br />
          <input type="submit" value="Submit"></input>
        </form>
      </div>
    );
  }
}

export default AddQuestion;
