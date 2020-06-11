import React from "react";
import SplitPane from "react-split-pane";
import CodeForm from "./CodeForm";

class Question extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <SplitPane split="vertical">
          <div>HERE WILL BE THE QUESTION</div>
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
