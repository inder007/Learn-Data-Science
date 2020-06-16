import React from "react";
import QuestionForm from "./QuestionForm";
import { navigate } from "@reach/router";

class AddModifyQuestion extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      questionId: "",
      question: "",
      solution: "",
      judgeCode: "",
      solutionFunction: "",
      loading: this.props.id == undefined ? false : true,
    };

    this.onSubmit = this.onSubmit.bind(this);
    this.onChange = this.onChange.bind(this);
    this.submitRequest = this.submitRequest.bind(this);
  }

  componentDidMount() {
    if (this.props.id != undefined) {
      // console.log(this.props.id);
      fetch("/api/question/" + this.props.id)
        .then((res) => res.json())
        .then((response) => {
          // console.log(response);
          this.setState({
            questionId: response.questionId,
            question: response.question,
            solution: response.solution,
            judgeCode: response.judgeCode,
            solutionFunction: response.solutionFunction,
            loading: false,
          });
        });
    }
    return;
  }

  submitRequest() {
    let isSubmit = false;
    const url =
      this.props.id == undefined
        ? "api/question/addQuestion"
        : "/api/question/modifyQuestion/" + this.props.id;
    fetch(url, {
      method: "POST",
      headers: new Headers({
        "content-type": "application/json",
      }),
      body: JSON.stringify(this.state),
    })
      .then((response) => {
        if (response.status == "200") {
          isSubmit = true;
          return response.text();
        } else {
          alert("Error occured, try again");
        }
      })
      .then((res) => {
        if (isSubmit) {
          alert("Code output: " + res);
        }
      })
      .then(() => {
        if (isSubmit) {
          navigate("/");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }

  onSubmit(state) {
    this.setState(
      {
        questionId: state.questionId,
        question: state.question,
        judgeCode: state.judgeCode,
        solution: state.solution,
        solutionFunction: state.solutionFunction,
      },
      () => {
        this.submitRequest();
      }
    );
  }

  onChange(event) {
    const target = event.target;
    const name = target.name;
    const value = target.value;
    this.setState({
      [name]: value,
    });
  }

  render() {
    if (this.state.loading) {
      return <div>loading...</div>;
    }

    return (
      <QuestionForm
        questionId={this.state.questionId}
        question={this.state.question}
        solution={this.state.solution}
        judgeCode={this.state.judgeCode}
        solutionFunction={this.state.solutionFunction}
        onSubmit={this.onSubmit}
        // onChange={this.onChange}
      />
    );
  }
}

export default AddModifyQuestion;
