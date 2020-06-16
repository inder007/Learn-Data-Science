import React from "react";
import CodeForm from "./CodeForm";

class Question extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loading: true,
    };
  }

  componentDidMount() {
    // eslint-disable-next-line react/prop-types
    fetch("/api/question/" + this.props.id)
      .then((res) => res.json())
      .then((response) => {
        this.setState({
          question: response,
          loading: false,
        });
      });
    return;
  }

  render() {
    if (this.state.loading) {
      return <div>Loading..</div>;
    }

    const outStyle = {
      whiteSpace: "pre-wrap",
    };

    return (
      <div className="container" style={outStyle}>
        <div className="codeDiv">
          <div>
            <h1>{this.state.question.questionId}</h1>
            <p>{this.state.question.question}</p>
          </div>
        </div>
        <div>
          <CodeForm id={this.props.id} />
        </div>
      </div>
    );
  }
}

export default Question;
