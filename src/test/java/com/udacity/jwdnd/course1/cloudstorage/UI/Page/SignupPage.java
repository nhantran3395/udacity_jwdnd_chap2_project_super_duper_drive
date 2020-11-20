package com.udacity.jwdnd.course1.cloudstorage.UI.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id="inputFirstname")
    private WebElement inputFirstname;

    @FindBy(id="inputLastname")
    private WebElement inputLastname;

    @FindBy(id="inputUsername")
    private WebElement inputUsername;

    @FindBy(id="inputPassword")
    private WebElement inputPassword;

    @FindBy(id="buttonSignup")
    private WebElement buttonSignup;

    @FindBy(id="linkBackToLogin")
    private WebElement linkBackToLogin;

    @FindBy(id="alert-error-signup")
    private WebElement alertErrorSignup;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void registerUser(String firstname, String lastname, String username, String password){
        inputFirstname.sendKeys(firstname);
        inputLastname.sendKeys(lastname);
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonSignup.click();
    }

    public String getAlertErrorSignupText(){
        return alertErrorSignup.getText();
    }
}
