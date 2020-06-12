import React from "react";

class ViewQuestions extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      loading: true,
    };
  }

  componentDidMount() {
    fetch("api/question/viewAllQuestions")
      .then((response) => {
        // return response.json();
        return response.json();
        // console.log(response.json());
        // return response;
      })
      .then((res) => {
        // console.log(res);
        this.setState({ questions: res, loading: false });
      });
  }

  render() {
    if (this.state.loading) {
      return <div>loading...</div>;
    }

    const questions = this.state.questions.map((question) => (
      <div key={question.id}>
        <h1>{question.question}</h1>
      </div>
    ));

    return <div>{questions}</div>;
  }
}

export default ViewQuestions;
