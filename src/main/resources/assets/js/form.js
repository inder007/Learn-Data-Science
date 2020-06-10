import React from "react";
import CodeMirror from "react-codemirror";
import axios from "axios";
require("codemirror/lib/codemirror.css");
require("codemirror/mode/python/python.js");
require("codemirror/addon/edit/closebrackets");
require("codemirror/theme/darcula.css");

class CodeForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      code: "# code",
      output: "",
    };

    this.submitHandler = this.submitHandler.bind(this);
    this.onChange = this.onChange.bind(this);
  }

  submitHandler(event) {
    event.preventDefault();

    fetch("api/formSubmit", {
      method: "POST",
      body: this.state.code,
    })
      .then((res) => {
        return res.text();
      })
      .then((response) => {
        this.setState({
          output: response,
        });
      })
      .catch((error) => {
        console.log(error);
      });
  }

  onChange(newCode) {
    this.setState({ code: newCode });
  }

  render() {
    var options = {
      lineNumbers: true,
      autoCloseBrackets: true,
      theme: "darcula",
      mode: "python",
    };

    const outStyle = {
      whiteSpace: "pre-line",
    };

    return (
      <div>
        <form onSubmit={this.submitHandler}>
          <CodeMirror
            value={this.state.code}
            onChange={this.onChange}
            options={options}
          />
          <input type="submit" value="Submit" />
        </form>
        <div style={outStyle}>
          <p>{this.state.output}</p>
        </div>
      </div>
    );
  }
}

export default CodeForm;
