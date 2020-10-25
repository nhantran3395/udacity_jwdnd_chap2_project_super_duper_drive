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

const closeAlert = () => {
  const alertCloseSelector = `-${alertType}-${alertForResource}-${alertContent}`
  $("#alert-close" + alertCloseSelector).click()
}

if (isAlertToBeOpened) {
  const alertSelector = `-${alertType}-${alertForResource}-${alertContent}`
  console.log(`from alert.js: alertSelector = ${alertSelector}`)
  $("#alert" + alertSelector).prependTo("#alert-container")
  $("#alert" + alertSelector).addClass("show")
  setTimeout(closeAlert, 2000)
}
