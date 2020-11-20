package com.udacity.jwdnd.course1.cloudstorage.UI.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id="inputUsername")
    private WebElement inputUsername;

    @FindBy(id="inputPassword")
    private WebElement inputPassword;

    @FindBy(id="button-login")
    private WebElement buttonLogin;

    @FindBy(id="alert-error-login-invalid-username-password")
    private WebElement alertErrorLoginInvalidUsernamePassword;

    @FindBy(id="alert-successful-logout")
    private WebElement alertSuccessfulLogout;

    @FindBy(id="alert-successful-signup")
    private WebElement alertSuccessfulSignup;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password){
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonLogin.click();
    }

    public String getAlertSuccessfulSignupText(){
        return alertSuccessfulSignup.getText();
    }

    public String getAlertSuccessfulLogoutText(){
        return alertSuccessfulLogout.getText();
    }

    public String getAlertErrorLoginInvalidUsernamePassword(){
        return alertErrorLoginInvalidUsernamePassword.getText();
    }

}
