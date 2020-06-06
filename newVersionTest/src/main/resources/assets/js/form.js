import React from "react";
import CodeMirror from "react-codemirror";
import axios from "axios";
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

    // try {
    //   let response = await fetch("api/formSubmit", {
    //     method: "POST",
    //     body: this.state.code,
    //   });
    //   let responseJson = await response.json();
    //   console.log(responseJson);
    // } catch (err) {
    //   console.log(err);
    // }

    // axios
    //   .post("/formSubmit", {
    //     body: this.state.code,
    //   })
    //   .then((response) => console.log(response));

    fetch("api/formSubmit", {
      method: "POST",
      body: this.state.code,
    })
      .then((res) => {
         console.log("check");
         console.log(res);
//        return res.json();
        // console.log("check");
      })
//      .then((response) => {
//        console.log(response);
//         console.log(response.body);
//      })
      .catch((error) => {
        console.log("error");
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
