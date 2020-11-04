package com.udacity.jwdnd.course1.cloudstorage.UI.Page;

import com.udacity.jwdnd.course1.cloudstorage.UI.Page.PageUtil.PageUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class NotePage  {

    private PageUtil pageUtil;

    public NotePage(WebDriver driver) {
        this.pageUtil = new PageUtil(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="note-create-button")
    private WebElement noteCreateButton;

    @FindBy(id="note-modal-cru")
    private WebElement noteModalCRU;

    @FindBy(id="note-modal-delete")
    private WebElement noteModalD;

    @FindBy(id="note-modal-title-input")
    private WebElement noteModalTitleInput;

    @FindBy(id="note-modal-description-input")
    private WebElement noteModalDescriptionInput;

    @FindBy(id="note-modal-save-button")
    private WebElement noteModalSaveButton;

    @FindBy(id="note-modal-delete-link")
    private WebElement noteModalDeleteButton;

    @FindBy(id="note-modal-close-button")
    private WebElement noteModalCloseButton;

    @FindBy(id="alert-success-note-create")
    private WebElement noteCreateSuccessAlert;

    @FindBy(id="alert-success-note-update")
    private WebElement noteUpdateSuccessAlert;

    @FindBy(id="alert-success-note-delete")
    private WebElement noteDeleteSuccessAlert;

    @FindBy(id = "note-data-title-5")
    private WebElement noteDataTitle;

    @FindBy(id = "note-data-description-5")
    private WebElement noteDataDescription;

    @FindBy(id="note-update-button-5")
    private WebElement noteUpdateButton;

    @FindBy(id="note-delete-button-5")
    private WebElement noteDeleteButton;

    public String getNoteTitleInput(){
        return noteModalTitleInput.getAttribute("value");
    }

    public String getNoteDescriptionInput(){
        return noteModalDescriptionInput.getAttribute("value");
    }

    public String getNoteDataTitle(){
        return noteDataTitle.getText();
    }

    public String getNoteDataDescription(){
        return noteDataDescription.getText();
    }

    public void openNoteCreateModal(){
        noteCreateButton.click();
    }

    public void openNoteUpdateModal(){
        noteUpdateButton.click();
    }

    public void openNoteDeleteModal(){
        noteDeleteButton.click();
    }

    public void deleteNote(){
        pageUtil.waitForVisibility(noteModalD);

        noteModalDeleteButton.click();
    }

    public void saveNote(String noteTitle, String noteDescription){
        pageUtil.waitForVisibility(noteModalCRU);

        noteModalTitleInput.clear();
        noteModalTitleInput.sendKeys(noteTitle);

        noteModalDescriptionInput.clear();
        noteModalDescriptionInput.sendKeys(noteDescription);

        noteModalSaveButton.click();
    }

    public boolean checkIfUpdateNoteSuccessAlertIsShown(){
        return noteUpdateSuccessAlert.isDisplayed();
    }
    public boolean checkIfCreateNoteSuccessAlertIsShown(){
        return noteCreateSuccessAlert.isDisplayed();
    }
    public boolean checkIfDeleteNoteSuccessAlertIsShown(){
        return noteDeleteSuccessAlert.isDisplayed();
    }
}
