package com.udacity.jwdnd.course1.cloudstorage.UI.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilePage {

    public FilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="fileUpload")
    private WebElement fileInput;

    @FindBy(id="file-upload-button")
    private WebElement fileUploadButton;

    @FindBy(id="alert-success-file-upload")
    private WebElement alertSuccessFileUpload;

    @FindBy(id="alert-success-file-remove")
    private WebElement alertSuccessFileRemove;

    @FindBy(id="alert-error-file-storageFailure")
    private WebElement alertErrorFileStorageFailure;

    @FindBy(id="alert-error-file-sizeLimitExceeded")
    private WebElement alertErrorFileSizeLimitExceeded;

    @FindBy(id="uploaded-filename-bear.jpg")
    private WebElement uploadedFilename;

    @FindBy(id="uploaded-filename-empty.txt")
    private WebElement uploadedFilenameEmptyFile;

    @FindBy(id="uploaded-filename-heavy-carrot.txt")
    private WebElement uploadedFilenameSizeLimitExceeded;

    @FindBy(id="uploaded-file-delete-button-bear.jpg")
    private WebElement uploadedFileDeleteButton;

    @FindBy(id="uploaded-file-view-button-bear.jpg")
    private WebElement uploadedFileViewButton;


    public void uploadFile(String filepath){
        fileInput.sendKeys(filepath);
        fileUploadButton.click();
    }

    public void deleteFile(){
        uploadedFileDeleteButton.click();
    }

    public String getAlertSuccessFileUploadText(){
        return alertSuccessFileUpload.getText();
    }

    public String getAlertSuccessFileRemoveText(){
        return alertSuccessFileRemove.getText();
    }

    public boolean checkIfAlertErrorFileStorageFailureIsDisplayed(){
        return alertErrorFileStorageFailure.isDisplayed();
    }

    public String getAlertErrorFileStorageFailureText(){
        return alertErrorFileStorageFailure.getText();
    }

    public boolean checkIfAlertErrorFileSizeLimitExceededIsDisplayed(){
        return alertErrorFileSizeLimitExceeded.isDisplayed();
    }

    public String getAlertErrorFileSizeLimitExceededText(){
        return alertErrorFileSizeLimitExceeded.getText();
    }


    public String getUploadFilename(){
        return uploadedFilename.getText();
    }

    public String getUploadFilenameEmptyFile(){
        return uploadedFilenameEmptyFile.getText();
    }

    public String getUploadFilenameSizeLimitExceeded(){
        return uploadedFilenameSizeLimitExceeded.getText();
    }

}
