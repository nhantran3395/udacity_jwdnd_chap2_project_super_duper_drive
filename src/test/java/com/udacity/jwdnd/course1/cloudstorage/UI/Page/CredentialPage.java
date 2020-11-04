package com.udacity.jwdnd.course1.cloudstorage.UI.Page;

import com.udacity.jwdnd.course1.cloudstorage.UI.Page.PageUtil.PageUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialPage {

    private PageUtil pageUtil;

    public CredentialPage(WebDriver driver) {
        this.pageUtil = new PageUtil(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="credential-create-button")
    private WebElement credentialCreateButton;

    @FindBy(id="credential-modal-cru")
    private WebElement credentialModalCRU;

    @FindBy(id="credential-modal-delete")
    private WebElement credentialModalD;

    @FindBy(id="credential-modal-url-input")
    private WebElement credentialModalUrlInput;

    @FindBy(id="credential-modal-username-input")
    private WebElement credentialModalUsernameInput;

    @FindBy(id="credential-modal-password-input")
    private WebElement credentialModalPasswordInput;

    @FindBy(id="credential-modal-save-button")
    private WebElement credentialModalSaveButton;

    @FindBy(id="credential-modal-delete-link")
    private WebElement credentialModalDeleteButton;

    @FindBy(id="credential-modal-close-button")
    private WebElement credentialModalCloseButton;

    @FindBy(id="alert-success-credential-create")
    private WebElement credentialCreateSuccessAlert;

    @FindBy(id="alert-success-credential-update")
    private WebElement credentialUpdateSuccessAlert;

    @FindBy(id="alert-success-credential-delete")
    private WebElement credentialDeleteSuccessAlert;

    @FindBy(id = "credential-data-url-5")
    private WebElement credentialUrl;

    @FindBy(id = "credential-data-username-5")
    private WebElement credentialUsername;

    @FindBy(id = "credential-data-password-5")
    private WebElement credentialPassword;

    @FindBy(id="credential-update-button-5")
    private WebElement credentialUpdateButton;

    @FindBy(id="credential-delete-button-5")
    private WebElement credentialDeleteButton;

    public String getCredentialUrlInput(){
        return credentialModalUrlInput.getAttribute("value");
    }

    public String getCredentialUsernameInput(){
        return credentialModalUsernameInput.getAttribute("value");
    }

    public String getCredentialPasswordInput(){
        return credentialModalPasswordInput.getAttribute("value");
    }

    public String getUrlOfCredential(){
        return credentialUrl.getText();
    }

    public String getUsernameOfCredential(){
        return credentialUsername.getText();
    }

    public String getPasswordOfCredential(){
        return credentialPassword.getText();
    }

    public void openCredentialCreateModal(){
        credentialCreateButton.click();
    }

    public void openCredentialUpdateModal(){
        credentialUpdateButton.click();
    }

    public void openCredentialDeleteModal(){
        credentialDeleteButton.click();
    }

    public void deleteCredential(){
        pageUtil.waitForVisibility(credentialModalD);

        credentialModalDeleteButton.click();
    }

    public void saveCredential(String credentialUrl, String credentialUsername, String credentialPassword){
        pageUtil.waitForVisibility(credentialModalCRU);

        credentialModalUrlInput.clear();
        credentialModalUrlInput.sendKeys(credentialUrl);

        credentialModalUsernameInput.clear();
        credentialModalUsernameInput.sendKeys(credentialUsername);

        credentialModalPasswordInput.clear();
        credentialModalPasswordInput.sendKeys(credentialPassword);

        credentialModalSaveButton.click();
    }

    public boolean checkIfCreateCredentialSuccessAlertIsShown(){
        return credentialCreateSuccessAlert.isDisplayed();
    }

    public boolean checkIfUpdateCredentialSuccessAlertIsShown(){
        return credentialUpdateSuccessAlert.isDisplayed();
    }

    public boolean checkIfDeleteCredentialSuccessAlertIsShown(){
        return credentialDeleteSuccessAlert.isDisplayed();
    }

}
