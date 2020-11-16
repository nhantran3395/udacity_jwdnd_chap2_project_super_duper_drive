package com.udacity.jwdnd.course1.cloudstorage.UI;

import com.udacity.jwdnd.course1.cloudstorage.UI.Page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.UI.Page.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SignupLoginTests {

    @LocalServerPort
    public int port;

    public static WebDriver driver;

    public String baseURL;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
        driver = null;
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;
    }

    @Test
    @Order(1)
    public void testRegisterUserAndLoginSuccess() {
        String firstname = "Antonio";
        String lastname = "Moore";
        String username = "antoniomoore";
        String password = "brad";

        String alertSuccessfulSignupText = "You successfully signed up! Please continue to the login page.";

        driver.get(baseURL + "/signup");

        SignupPage signupPage = new SignupPage(driver);
        signupPage.registerUser(firstname,lastname,username, password);

        assertEquals(alertSuccessfulSignupText,signupPage.getAlertSuccessfulSignupText());

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        assertEquals(driver.getCurrentUrl(),baseURL+"/");
    }

    @Test
    @Order(2)
    public void testRegisterUserWithDuplicatedFirstnameLastnameAndLoginSuccess() {
        String firstname = "Antonio";
        String lastname = "Moore";
        String usernameNew = "antonio.moore.1";
        String password = "brad";

        String alertSuccessfulSignupText = "You successfully signed up! Please continue to the login page.";

        driver.get(baseURL + "/signup");

        SignupPage signupPage = new SignupPage(driver);
        signupPage.registerUser(firstname,lastname,usernameNew, password);

        assertEquals(alertSuccessfulSignupText,signupPage.getAlertSuccessfulSignupText());

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(usernameNew, password);

        assertEquals(driver.getCurrentUrl(),baseURL+"/");
    }


    @Test
    @Order(3)
    public void testRegisterUserWithDuplicatedUsernameAndCannotLogin(){
        String firstname = "Antonio";
        String lastname = "Moore";
        String usernameDuplicated = "antoniomoore";
        String password = "duplicatedUser";

        String alertErrorSignupText = "The username already exists";

        String alertErrorLoginInvalidUsernamePassword = "Invalid username or password";

        driver.get(baseURL + "/signup");

        SignupPage signupPage = new SignupPage(driver);
        signupPage.registerUser(firstname,lastname,usernameDuplicated, password);

        assertEquals(alertErrorSignupText,signupPage.getAlertErrorSignupText());

        //verify that attempt to login with the duplicated user name will fail
        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(usernameDuplicated,password);

        assertEquals(alertErrorLoginInvalidUsernamePassword,loginPage.getAlertErrorLoginInvalidUsernamePassword());
    }

    @Test
    public void testLoginWithUsernameThatDoesNotExist(){
        String usernameNotExist = "none.exist.user";
        String password = "password";

        String alertErrorLoginInvalidUsernamePassword = "Invalid username or password";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(usernameNotExist,password);

        assertEquals(alertErrorLoginInvalidUsernamePassword,loginPage.getAlertErrorLoginInvalidUsernamePassword());
    }

    @Test
    @Order(4)
    public void testLoginWithIncorrectPassword(){
        String username = "antoniomoore";
        String password = "password";

        String alertErrorLoginInvalidUsernamePassword = "Invalid username or password";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username,password);

        assertEquals(alertErrorLoginInvalidUsernamePassword,loginPage.getAlertErrorLoginInvalidUsernamePassword());
    }

    @Test
    public void testHomePageIsNotAccessiblePriorLogin(){
        driver.get(baseURL + "/");

        //verify that user is redirected to login page
        assertEquals(driver.getCurrentUrl(),baseURL+"/login");
    }

    @Test
    @Order(5)
    public void testHomePageIsNotAccessibleAfterLogout(){
        String username = "antoniomoore";
        String password = "brad";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username,password);

        assertEquals(driver.getCurrentUrl(),baseURL+"/");

        driver.get(baseURL + "/logout");

        //verify that user will be redirected to login page after logout
        assertEquals(driver.getCurrentUrl(),baseURL+"/login?logout");

        String alertSuccessfulLogout = "You have been logged out";

        assertEquals(alertSuccessfulLogout,loginPage.getAlertSuccessfulLogoutText());

        driver.get(baseURL + "/");

        //verify that user will be redirected to login page when trying to access home page without logging in
        assertEquals(driver.getCurrentUrl(),baseURL+"/login");

    }

}
