let urlParams = new URLSearchParams(window.location.search)
let credentialId = urlParams.get("id")

if (credentialId) {
  let button = $("#" + "credential-update-button-" + credentialId)
  let credentialUrl = button.data("credential-url")
  let credentialUsername = button.data("credential-username")
  let credentialPassword = button.data("credential-password")

  $("#credential-modal-url-input").val(credentialUrl ? credentialUrl : "")
  $("#credential-modal-username-input").val(
    credentialUsername ? credentialUsername : ""
  )
  $("#credential-modal-password-input").val(
    credentialPassword ? credentialPassword : ""
  )

  $("#credential-modal-form").attr(
    "action",
    `credentials/update/${credentialId}`
  )
  $("#credential-modal-cru").modal("show")
}

//case modal is opened for adding credential, clear all input, and set form action to credentials/add
$("#credential-modal-cru").on("show.bs.modal", function (event) {
  $("#credential-modal-url-input").val("")
  $("#credential-modal-username-input").val("")
  $("#credential-modal-password-input").val("")
  $("#credential-modal-form").attr("action", `credentials/add`)
})

$("#credential-modal-delete").on("show.bs.modal", function (event) {
  var button = $(event.relatedTarget)
  var credentialId = button.data("credential-id")
  var credentialUrl = button.data("credential-url")

  $("#credential-delete-confirmation-message").text(
    `Do you want to delete this credential on "${credentialUrl}" ?`
  )

  $("#credential-modal-delete-link").attr(
    "href",
    `credentials/delete/${credentialId}`
  )
})
