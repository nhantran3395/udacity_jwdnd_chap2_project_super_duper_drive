$("#note-modal-cru").on("show.bs.modal", function (event) {
  console.log("Hello world")

  let button = $(event.relatedTarget)
  let noteId = button.data("note-id")
  let noteTitle = button.data("note-title")
  let noteDescription = button.data("note-description")

  $("#note-id").val(noteId ? noteId : "")
  $("#note-modal-title-input").val(noteTitle ? noteTitle : "")
  $("#note-modal-description-input").val(noteDescription ? noteDescription : "")
  $("#note-modal-form").attr(
    "action",
    noteId ? `notes/update/${noteId}` : `notes/add`
  )
})

$("#note-modal-delete").on("show.bs.modal", function (event) {
  let button = $(event.relatedTarget)
  let noteId = button.data("note-id")
  let noteTitle = button.data("note-title")

  $("#note-delete-confirmation-message").text(
    `Do you want to delete this note with title "${noteTitle}" ?`
  )
  $("#note-modal-delete-link").attr("href", `notes/delete/${noteId}`)
})
