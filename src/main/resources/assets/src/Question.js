import React from "react";
import SplitPane from "react-split-pane";
import CodeForm from "./CodeForm";

class Question extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loading: true,
    };
  }

  componentDidMount() {
    // console.log(`/api/question/${this.props.id}`);
    // eslint-disable-next-line react/prop-types
    fetch("/api/question/" + this.props.id)
      .then((res) => res.json())
      .then((response) => {
        // console.log(response);
        this.setState({
          question: response,
          loading: false,
        });
      });
    return;
  }

  render() {
    // return (
    //   <div>
    //     <pre>
    //       <code>{JSON.stringify(this.props, null, 4)}</code>
    //     </pre>
    //   </div>
    // );

    if (this.state.loading) {
      return <div>Loading..</div>;
    }

    return (
      <div>
        <SplitPane split="vertical">
          {/* <div>{this.state.question.question}</div> */}
          <div>
            <h1>{this.state.question.questionId}</h1>
            <h2>{this.state.question.question}</h2>
          </div>
          <div>
            <CodeForm />
          </div>
        </SplitPane>
      </div>
    );
  }
}

// ReactDOM.render(<CodeMirrorClass />, document.getElementById("code"));
export default Question;
