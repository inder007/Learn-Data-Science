// var editor;
// $(document).ready(function () {
//   // var code = $(".codemirror-textarea")[0];
//   var code = document.getElementById("editor");
//   editor = CodeMirror.fromTextArea(code, {
//     mode: "python",
//     theme: "darcula",
//     lineNumbers: true,
//     autoCloseBrackets: true,
//   });
// });

function formSubmit() {
  var data = editor.getValue();
  $.post({
    type: "POST",
    url: "/api/formSubmit",
    headers: { "Content-Type": "application/json" },
    data: data,
    success: function (response) {
      response = response.replace(/\n/g, "<br/>");
      response = response.replace(" ", "&nbsp;");
      $("#output").html(response);
    },

    error: function (jqXHR, status, errorThrown) {
      alert("Error, status= " + status + "error thrown= " + errorThrown);
    },
  });
}
