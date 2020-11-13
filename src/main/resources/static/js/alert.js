if (typeof urlParams === "undefined") {
  let urlParams = new URLSearchParams(window.location.search)
}
urlParams = new URLSearchParams(window.location.search)

if (typeof isAlertToBeOpened === "undefined") {
  let isAlertToBeOpened = urlParams.get("isAlertToBeOpened")
}
isAlertToBeOpened = urlParams.get("isAlertToBeOpened")

if (typeof alertType === "undefined") {
  let alertType = urlParams.get("alertType")
}
alertType = urlParams.get("alertType")

if (typeof alertForResource === "undefined") {
  let alertForResource = urlParams.get("alertForResource")
}
alertForResource = urlParams.get("alertForResource")

if (typeof alertContent === "undefined") {
  let alertContent = urlParams.get("alertContent")
}
alertContent = urlParams.get("alertContent")

if (typeof alertErrorMessage === "undefined") {
  let alertErrorMessage = urlParams.get("alertErrorMessage")
}
alertErrorMessage = urlParams.get("alertErrorMessage")

const closeAlert = () => {
  const alertCloseSelector = `-${alertType}-${alertForResource}-${alertContent}`
  $("#alert-close" + alertCloseSelector).click()
}

if (isAlertToBeOpened) {
  const alertSelector = `-${alertType}-${alertForResource}-${alertContent}`
  console.log(`from alert.js: alertSelector = ${alertSelector}`)

  if (alertErrorMessage) {
    $("#alert-message" + alertSelector + "-cause").text(
      `Cause: ${alertErrorMessage}`
    )
  }

  $("#alert" + alertSelector).prependTo("#alert-container")
  $("#alert" + alertSelector).removeClass("d-none")
  $("#alert" + alertSelector).addClass("show")

  setTimeout(closeAlert, 5000)

  console.log(alertErrorMessage)
}
