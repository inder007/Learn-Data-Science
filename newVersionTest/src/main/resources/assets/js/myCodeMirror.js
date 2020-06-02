// var React = require("react");
import React from "react";
import ReactDOM from "react-dom";
import SplitPane from "react-split-pane";
import CodeForm from "./form";
// import { Divider, Grid } from "@material-ui/core";
// import CodeMirror from "react-codemirror";
// import { UnControlled as CodeMirror } from "react-codemirror2";

class CodeMirrorClass extends React.Component {
  constructor(props) {
    super(props);
    // this.state = {
    //   code: "# code",
    // };
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

ReactDOM.render(<CodeMirrorClass />, document.getElementById("code"));
