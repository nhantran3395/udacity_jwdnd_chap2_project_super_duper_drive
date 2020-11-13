// make sure filename appear on file input when user selected a file
$(".custom-file-input").on("change", function () {
  let fileName = $(this).val().split("\\").pop()
  $(this).next(".custom-file-label").addClass("selected").html(fileName)
})
