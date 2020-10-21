let urlParams = new URLSearchParams(window.location.search)
let credentialId = urlParams.get("id")

if (credentialId) {
  let button = $("#" + "credential-button-" + credentialId)
  let credentialUrl = button.data("credential-url")
  let credentialUsername = button.data("credential-username")
  let credentialPassword = button.data("credential-password")

  $("#credential-id").val(credentialId ? credentialId : "")
  $("#credential-url").val(credentialUrl ? credentialUrl : "")
  $("#credential-username").val(credentialUsername ? credentialUsername : "")
  $("#credential-password").val(credentialPassword ? credentialPassword : "")

  $("#credential-modal-form").attr(
    "action",
    `credentials/update/${credentialId}`
  )
  $("#credential-modal").modal("show")
}

//case modal is opened for adding credential, clear all input, and set form action to credentials/add
$("#credential-modal").on("show.bs.modal", function (event) {
  $("#credential-id").val("")
  $("#credential-url").val("")
  $("#credential-username").val("")
  $("#credential-password").val("")
  $("#credential-modal-form").attr("action", `credentials/add`)
})

$("#credential-delete-modal").on("show.bs.modal", function (event) {
  var button = $(event.relatedTarget)
  var credentialId = button.data("credential-id")
  var credentialUrl = button.data("credential-url")

  $("#credential-delete-confirmation-message").text(
    `Do you want to delete this credential on "${credentialUrl}" ?`
  )

  $("#credential-delete-link").attr(
    "href",
    `credentials/delete/${credentialId}`
  )
})
