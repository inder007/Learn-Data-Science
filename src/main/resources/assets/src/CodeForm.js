import React from "react";
// import MyCodeMirror from "./MyCodeMirror";
import CodeMirror from "react16-codemirror";
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
      loading: true,
    };

    this.submitHandler = this.submitHandler.bind(this);
    this.onChange = this.onChange.bind(this);
    this.solutionClick = this.solutionClick.bind(this);
  }

  componentDidMount() {
    // eslint-disable-next-line react/prop-types
    fetch("api/question/" + this.props.id)
      .then((res) => res.json())
      .then((res) => {
        this.setState({
          code: res.solutionFunction,
          solution: res.solution,
          loading: false,
        });
      });
  }

  submitHandler(event) {
    event.preventDefault();

    fetch("api/formSubmit/" + this.props.id, {
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

  solutionClick(event) {
    event.preventDefault();
    alert(this.state.solution);
  }

  render() {
    if (this.state.loading) {
      return <div>loading...</div>;
    }

    var options = {
      lineNumbers: true,
      autoCloseBrackets: true,
      theme: "darcula",
      mode: "python",
    };

    let output = "";
    if (this.state.output.toLowerCase() == "pass\n") {
      output = (
        <div className="alert alert-success">
          <strong>Pass!</strong> You have pass all the test cases!!
        </div>
      );
    } else if (this.state.output != "") {
      output = (
        <div className="alert alert-danger">
          <strong>Fail!</strong>
          <br />
          {this.state.output}
        </div>
      );
    }

    return (
      <div>
        <form onSubmit={this.submitHandler}>
          <CodeMirror
            value={this.state.code}
            onChange={this.onChange}
            options={options}
          />
          <br />
          <input
            type="submit"
            value="Submit"
            className="btn btn-primary btn-large"
          />
          <button
            type="button"
            className="btn btn-info btn-large pull-right"
            data-toggle="modal"
            data-target="#myModal"
          >
            Solution
          </button>
        </form>
        <br />

        <div>{output}</div>

        <div id="myModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">
                  &times;
                </button>
                <h4 className="modal-title">Python Solution</h4>
              </div>
              <div className="modal-body">
                <p>{this.state.solution}</p>
              </div>
              <div className="modal-footer">
                <button
                  type="button"
                  className="btn btn-default"
                  data-dismiss="modal"
                >
                  Close
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default CodeForm;
