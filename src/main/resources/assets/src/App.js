import React from "react";
import ReactDOM from "react-dom";
import { Router } from "@reach/router";
import Question from "./Question";
import AddQuestion from "./AddQuestion";
import ViewQuestions from "./ViewQuestions";

class App extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <Router>
        <Question path="/" />
        <AddQuestion path="addQuestion" />
        <ViewQuestions path="viewQuestion" />
      </Router>
    );
  }
}

ReactDOM.render(<App />, document.getElementById("code"));
