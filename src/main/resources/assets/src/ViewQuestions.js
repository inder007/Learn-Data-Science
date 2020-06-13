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
        // console.log(JSON.stringify(res[0].id));
        this.setState({ questions: res, loading: false });
      });
  }

  render() {
    if (this.state.loading) {
      return <div>loading...</div>;
    }

    const questions = this.state.questions.map((question, index) => (
      // {
      //   <div key={question.id}>
      // id = JSON.stringify(question.id);
      //   return(
      <tr key={question.questionId}>
        <td>{index + 1}</td>
        <td>
          <a href={`/${question.questionId}`}>{question.questionId}</a>
        </td>
      </tr>
      //   );

      // }
      //   </div>
    ));

    // return <div>{questions}</div>;

    return (
      <div>
        <h2>
          <a href="/addQuestion">Add question</a>
        </h2>
        <br />

        <h2>
          <a href="/deleteQuestion">Delete question</a>
        </h2>
        <br />

        <h2>List of Questions</h2>
        <table>
          <thead>
            <tr>
              <th>#</th>
              <th>Question Id</th>
            </tr>
          </thead>
          <tbody>{questions}</tbody>
        </table>
      </div>
    );
  }
}

export default ViewQuestions;
