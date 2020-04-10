$(document).ready(function(){
	// var code = $(".codemirror-textarea")[0];
	var code = document.getElementById('editor');
	var editor = CodeMirror.fromTextArea(code, {
		mode: "python",
		theme: "darcula",
		lineNumbers : true,
		autoCloseBrackets: true
	});
})