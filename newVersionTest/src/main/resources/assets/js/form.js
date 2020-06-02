import React from "react";
import CodeMirror from "react-codemirror";
// var CodeMirror = require("react-codemirror");
require("codemirror/lib/codemirror.css");
require("codemirror/mode/python/python.js");
require("codemirror/addon/edit/closebrackets");
require("codemirror/theme/darcula.css");

class CodeForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      code: "# code",
    };

    this.submitHandler = this.submitHandler.bind(this);
    this.onChange = this.onChange.bind(this);
  }

  submitHandler(event) {
    event.preventDefault();
    // console.log(event.target);
    console.log(this.state);
    fetch("http://localhost:8080/api/formSubmit", {
      method: "POST",
      origin: "http://localhost:1234/",
      body: this.state.code,
    }).then((response) => {
      console.log(response.json());
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

    return (
      <form onSubmit={this.submitHandler}>
        <CodeMirror
          value={this.state.code}
          //   onChange={this.updateCode}
          onChange={this.onChange}
          options={options}
        />
        <input type="submit" value="Submit" />
      </form>
    );
  }
}

export default CodeForm;
