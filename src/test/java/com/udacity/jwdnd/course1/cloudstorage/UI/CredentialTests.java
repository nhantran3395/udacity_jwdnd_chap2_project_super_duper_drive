package com.udacity.jwdnd.course1.cloudstorage.UI;

import com.udacity.jwdnd.course1.cloudstorage.UI.Page.CredentialPage;
import com.udacity.jwdnd.course1.cloudstorage.UI.Page.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CredentialTests {

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
    public void testCreateNewCredentialSuccess() {
        String username = "marcianichols4792";
        String password = "mason1";

        String credentialUrl = "https://randomuser.me/";
        String credentialUsername = "marcianichols4792";
        String credentialPassword = "mason1";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        driver.get(baseURL + "/credentials");

        CredentialPage credentialPage = new CredentialPage(driver);

        //verify URL & username & password is empty on modal opening (create new note)
        credentialPage.openCredentialCreateModal();
        assertTrue(credentialPage.getCredentialUrlInput().isEmpty());
        assertTrue(credentialPage.getCredentialUsernameInput().isEmpty());
        assertTrue(credentialPage.getCredentialPasswordInput().isEmpty());

        //verify create successful alert is shown and URL & username & password of the new credential is correctly displayed on Credential List after save
        credentialPage.saveCredential(credentialUrl,credentialUsername,credentialPassword);
        assertTrue(credentialPage.checkIfCreateCredentialSuccessAlertIsShown());
        assertEquals(credentialUrl,credentialPage.getUrlOfCredential());
        assertEquals(credentialUsername,credentialPage.getUsernameOfCredential());
        //password displayed on list should not equal to password inserted (since password on list is decrypted)
        assertNotEquals(credentialPassword,credentialPage.getPasswordOfCredential());
    }

    @Test
    @Order(2)
    public void testUpdateCredentialSuccess() {
        String username = "marcianichols4792";
        String password = "mason1";

        String credentialUrlCurrent = "https://randomuser.me/";
        String credentialUsernameCurrent = "marcianichols4792";
        String credentialPasswordCurrent = "mason1";

        String credentialUrlUpdate = "https://superduperdrive.com/";
        String credentialUsernameUpdate = "marcianichols_test";
        String credentialPasswordUpdate = "mason_test";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        driver.get(baseURL + "/credentials");

        CredentialPage credentialPage = new CredentialPage(driver);

        //verify URL & username & password of the selected credential is correctly displayed on modal opening (update credential)
        credentialPage.openCredentialUpdateModal();
        assertEquals(credentialUrlCurrent,credentialPage.getCredentialUrlInput());
        assertEquals(credentialUsernameCurrent,credentialPage.getCredentialUsernameInput());
        assertEquals(credentialPasswordCurrent,credentialPage.getCredentialPasswordInput());

        //verify update successful alert is shown and updated URL & username & password of the credential is correctly displayed on Credential List after save
        credentialPage.saveCredential(credentialUrlUpdate,credentialUsernameUpdate,credentialPasswordUpdate);
        assertTrue(credentialPage.checkIfUpdateCredentialSuccessAlertIsShown());
        assertEquals(credentialUrlUpdate,credentialPage.getUrlOfCredential());
        assertEquals(credentialUsernameUpdate,credentialPage.getUsernameOfCredential());
        //password displayed on list should not equal to password inserted (since password on list is decrypted)
        assertNotEquals(credentialPasswordUpdate,credentialPage.getPasswordOfCredential());
    }

    @Test
    @Order(3)
    public void testDeleteCredentialSuccess() {
        String username = "marcianichols4792";
        String password = "mason1";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        driver.get(baseURL + "/credentials");

        CredentialPage credentialPage = new CredentialPage(driver);

        //verify delete successful alert is shown and title & description of the selected note is removed from Note List (delete note)
        credentialPage.openCredentialDeleteModal();
        credentialPage.deleteCredential();

        assertThrows(NoSuchElementException.class,()->credentialPage.getUrlOfCredential());
        assertThrows(NoSuchElementException.class,()->credentialPage.getUsernameOfCredential());
        assertThrows(NoSuchElementException.class,()->credentialPage.getPasswordOfCredential());
        assertTrue(credentialPage.checkIfDeleteCredentialSuccessAlertIsShown());

    }
}

