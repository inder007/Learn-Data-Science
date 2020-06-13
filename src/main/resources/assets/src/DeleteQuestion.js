import React from "react";

class DeleteQuestion extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      inputLabel: "",
    };

    this.onSubmit = this.onSubmit.bind(this);
  }

  onSubmit(event) {
    event.preventDefault();

    fetch("api/question/deleteQuestion", {
      method: "POST",
      body: this.state.inputLabel,
    })
      .then((response) => {
        if (response.status == "200") {
          alert("Submitted");
        } else if (response.status == "204") {
          alert("This question label is not present in the database");
        } else {
          alert("Error occured, try again");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }

  render() {
    return (
      <div>
        <form onSubmit={this.onSubmit}>
          <label htmlFor="deleteQuestion">Give question label to delete</label>
          <br />
          <input
            type="input"
            id="deleteQuestion"
            value={this.state.inputLabel}
            onChange={(e) => {
              this.setState({ inputLabel: e.target.value });
            }}
          />
          <br />
          <input type="submit" value="SUbmit" />
        </form>
      </div>
    );
  }
}

export default DeleteQuestion;
