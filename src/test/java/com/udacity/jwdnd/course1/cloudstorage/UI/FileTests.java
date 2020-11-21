package com.udacity.jwdnd.course1.cloudstorage.UI;

import com.udacity.jwdnd.course1.cloudstorage.UI.Page.FilePage;
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
public class FileTests {

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
    public void testUploadFileSuccess() {
        String username = "marcianichols4792";
        String password = "mason1";

        String filePath = "D:\\Back_end\\Java\\udacity_jwdnd_super_duper_drive\\file_upload_test_data\\bear.jpg";
        String fileName = "bear.jpg";

        String alertSuccessFileUploadText = "Great! The file has been stored.\n×";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        driver.get(baseURL + "/files");

        FilePage filePage = new FilePage(driver);

        filePage.uploadFile(filePath);

        assertEquals(alertSuccessFileUploadText,filePage.getAlertSuccessFileUploadText());
        assertEquals(fileName,filePage.getUploadFilename());
    }

    @Test
    @Order(3)
    public void testDeleteFileSuccess() {
        String username = "marcianichols4792";
        String password = "mason1";

        String alertSuccessFileRemoveText = "The file has been removed\n×";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        driver.get(baseURL + "/files");

        FilePage filePage = new FilePage(driver);

        filePage.deleteFile();

        assertEquals(alertSuccessFileRemoveText,filePage.getAlertSuccessFileRemoveText());
    }

    @Test
    public void testUploadEmptyFile() {
        String username = "marcianichols4792";
        String password = "mason1";

        String filePathEmptyFile = "D:\\Back_end\\Java\\udacity_jwdnd_super_duper_drive\\file_upload_test_data\\empty.txt";

        String alertErrorFileStorageFailure = "Oops! Something went wrong and we could not store the file. Please try again.\n"
                +"Cause: Failed to store empty file empty.txt\n×";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        driver.get(baseURL + "/files");

        FilePage filePage = new FilePage(driver);

        filePage.uploadFile(filePathEmptyFile);

        assertTrue(filePage.checkIfAlertErrorFileStorageFailureIsDisplayed());
        assertEquals(alertErrorFileStorageFailure,filePage.getAlertErrorFileStorageFailureText());

        assertThrows(NoSuchElementException.class,()->filePage.getUploadFilenameEmptyFile());
    }

    @Test
    public void testUploadFileExceedLimit() {
        String username = "marcianichols4792";
        String password = "mason1";

        String filePathExceedLimitFile = "D:\\Back_end\\Java\\udacity_jwdnd_super_duper_drive\\file_upload_test_data\\video.mp4";

        String alertErrorFileSizeLimitExceeded = "Oops! Something went wrong and we could not get the file from storage. Please try again.\n"
                +"Cause: Maximum upload size exceeded\n×";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        driver.get(baseURL + "/files");

        FilePage filePage = new FilePage(driver);

        filePage.uploadFile(filePathExceedLimitFile);

        assertTrue(filePage.checkIfAlertErrorFileSizeLimitExceededIsDisplayed());
        assertEquals(alertErrorFileSizeLimitExceeded,filePage.getAlertErrorFileSizeLimitExceededText());

        assertThrows(NoSuchElementException.class,()->filePage.getUploadFilenameSizeLimitExceeded());
    }

}
