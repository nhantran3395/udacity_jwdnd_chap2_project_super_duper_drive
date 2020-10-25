$("#note-modal").on("show.bs.modal", function (event) {
  let button = $(event.relatedTarget)
  let noteId = button.data("note-id")
  let noteTitle = button.data("note-title")
  let noteDescription = button.data("note-description")

  $("#note-id").val(noteId ? noteId : "")
  $("#note-title").val(noteTitle ? noteTitle : "")
  $("#note-description").val(noteDescription ? noteDescription : "")
  $("#note-modal-form").attr(
    "action",
    noteId ? `notes/update/${noteId}` : `notes/add`
  )
})

$("#note-delete-modal").on("show.bs.modal", function (event) {
  let button = $(event.relatedTarget)
  let noteId = button.data("note-id")
  let noteTitle = button.data("note-title")

  $("#note-delete-confirmation-message").text(
    `Do you want to delete this message with title "${noteTitle}" ?`
  )
  $("#note-delete-link").attr("href", `notes/delete/${noteId}`)
})
