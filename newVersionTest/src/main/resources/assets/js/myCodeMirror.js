// var React = require("react");
import React from "react";
import ReactDOM from "react-dom";
import SplitPane from "react-split-pane";
// import { Divider, Grid } from "@material-ui/core";
// import CodeMirror from "react-codemirror";
var CodeMirror = require("react-codemirror");
// import { UnControlled as CodeMirror } from "react-codemirror2";
require("codemirror/lib/codemirror.css");
require("codemirror/mode/python/python");
require("codemirror/addon/edit/closebrackets");
require("codemirror/theme/darcula.css");

class CodeMirrorClass extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      code: "# code",
    };

    // this.updateCode = this.updateCode.bind(this);
  }

  //   updateCode(newCode) {
  //     this.setState({
  //       code: newCode,
  //     });
  //   }

  render() {
    var options = {
      lineNumbers: true,
      autoCloseBrackets: true,
      theme: "darcula",
      mode: "python",
    };
    return (
      <div>
        <SplitPane split="vertical">
          <div>HERE WILL BE THE QUESTION</div>
          <div>
            <form>
              <CodeMirror
                value={this.state.code}
                //   onChange={this.updateCode}
                onChange={(newCode) => {
                  this.setState({ code: newCode });
                }}
                options={options}
              />
              <input type="submit" value="Submit" />
            </form>
          </div>
        </SplitPane>
      </div>
    );
  }
}

ReactDOM.render(<CodeMirrorClass />, document.getElementById("code"));
