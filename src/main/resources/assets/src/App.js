import React from "react";
import ReactDOM from "react-dom";
import { Router, Link } from "@reach/router";
import Question from "./Question";
import AddModifyQuestion from "./AddModifyQuestion";
import ViewQuestions from "./ViewQuestions";
// import DeleteQuestion from "./DeleteQuestion";

class App extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        {/* <header> */}
        <Link to="/">
          <h1 id="main-page">Learn Python</h1>
        </Link>
        {/* </header> */}
        <br></br>
        <div>
          <Router>
            <ViewQuestions path="/" />
            <AddModifyQuestion path="addModifyQuestion" />
            <Question path="/:id" />
            {/* <DeleteQuestion path="deleteQuestion" /> */}
            <AddModifyQuestion path="addModifyQuestion/:id" />
          </Router>
        </div>
      </div>
    );
  }
}

ReactDOM.render(<App />, document.getElementById("code"));
