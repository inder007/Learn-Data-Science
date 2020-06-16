import React from "react";
import { BsTrash } from "react-icons/bs";
import { AiOutlineReload } from "react-icons/ai";
import { navigate } from "@reach/router";
import { GrAdd } from "react-icons/gr";

class ViewQuestions extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      loading: true,
    };

    this.deleteHandler = this.deleteHandler.bind(this);
  }

  componentDidMount() {
    fetch("api/question/viewAllQuestions")
      .then((response) => {
        return response.json();
      })
      .then((res) => {
        this.setState({ questions: res, loading: false });
      });
  }

  deleteHandler(e, question) {
    e.preventDefault();

    fetch("api/question/deleteQuestion", {
      method: "POST",
      body: question.questionId,
    })
      .then((response) => {
        var copy = this.state.questions;
        if (response.status == "200") {
          const index = copy.indexOf(question);
          if (index > -1) {
            copy.splice(index, 1);
          }
        } else if (response.status == "204") {
          alert("This question label is not present in the database");
        } else {
          alert("Error occured, try again");
        }
        return copy;
      })
      .then((copy) => this.setState({ questions: copy }))
      .catch((error) => {
        console.log(error);
      });
  }

  render() {
    if (this.state.loading) {
      return <div>loading...</div>;
    }

    const questions = this.state.questions.map((question, index) => (
      <tr key={question.questionId}>
        <td>{index + 1}</td>
        <td>
          <a href={`/${question.questionId}`}>{question.questionId}</a>
        </td>
        <td>
          <button
            onClick={(e) => {
              window.confirm(
                "Are you sure you want to delete this question?"
              ) && this.deleteHandler(e, question);
            }}
          >
            <BsTrash title="Delete Question" />
          </button>
        </td>
        <td>
          <button>
            <a href={`/addModifyQuestion/${question.questionId}`}>
              <AiOutlineReload title="Modify Question"></AiOutlineReload>
            </a>
          </button>
        </td>
      </tr>
    ));

    return (
      <div id="main-page" className="container">
        <h2 className="pull-left">List of Questions</h2>
        <button
          className="pull-right btn btn-lg btn-primary"
          onClick={() => navigate("/addModifyQuestion")}
        >
          <GrAdd /> &ensp; Add Question
        </button>
        <table id="main-page" className="table table-striped">
          <thead>
            <tr>
              <th className="text-center">#</th>
              <th className="text-center">Question Id</th>
              <th className="text-center">Delete Question</th>
              <th className="text-center">Modify Question</th>
            </tr>
          </thead>
          <tbody>{questions}</tbody>
        </table>
      </div>
    );
  }
}

export default ViewQuestions;
