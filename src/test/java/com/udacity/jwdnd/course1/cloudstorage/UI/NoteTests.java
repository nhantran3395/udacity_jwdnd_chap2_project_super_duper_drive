package com.udacity.jwdnd.course1.cloudstorage.UI;

import com.udacity.jwdnd.course1.cloudstorage.UI.Page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.UI.Page.NotePage;
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
public class NoteTests {

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
    public void testCreateNewNoteSuccess() {
        String username = "marcianichols4792";
        String password = "mason1";

        String noteTitle = "Hello, Marcia!";
        String noteDescription = "How are you doing today?";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        driver.get(baseURL + "/notes");

        NotePage notePage = new NotePage(driver);

        //verify title and description is empty on modal opening (create new note)
        notePage.openNoteCreateModal();
        assertTrue(notePage.getNoteTitleInput().isEmpty());
        assertTrue(notePage.getNoteDescriptionInput().isEmpty());

        //verify create successful alert is shown and title & description of the new note is correctly displayed on Note List after save
        notePage.saveNote(noteTitle,noteDescription);
        assertTrue(notePage.checkIfCreateNoteSuccessAlertIsShown());
        assertEquals(noteTitle,notePage.getNoteDataTitle());
        assertEquals(noteDescription,notePage.getNoteDataDescription());
    }

    @Test
    @Order(2)
    public void testUpdateNoteSuccess() {
        String username = "marcianichols4792";
        String password = "mason1";

        String noteTitleCurrent = "Hello, Marcia!";
        String noteDescriptionCurrent = "How are you doing today?";

        String noteTitleUpdate = "Hello again, Marcia!";
        String noteDescriptionUpdate = "Thank you for using Super Duper Drive.";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        driver.get(baseURL + "/notes");

        NotePage notePage = new NotePage(driver);

        //verify title & description of the selected note is correctly displayed on modal opening (update note)
        notePage.openNoteUpdateModal();
        assertEquals(noteTitleCurrent,notePage.getNoteTitleInput());
        assertEquals(noteDescriptionCurrent,notePage.getNoteDescriptionInput());

        //verify update successful alert is shown and updated title & description of the note is correctly displayed on Note List after save
        notePage.saveNote(noteTitleUpdate,noteDescriptionUpdate);
        assertTrue(notePage.checkIfUpdateNoteSuccessAlertIsShown());
        assertEquals(noteTitleUpdate,notePage.getNoteDataTitle());
        assertEquals(noteDescriptionUpdate,notePage.getNoteDataDescription());

    }

    @Test
    @Order(3)
    public void testDeleteNoteSuccess() {
        String username = "marcianichols4792";
        String password = "mason1";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        driver.get(baseURL + "/notes");

        NotePage notePage = new NotePage(driver);

        //verify delete successful alert is shown and title & description of the selected note is removed from Note List (delete note)
        notePage.openNoteDeleteModal();
        notePage.deleteNote();

        assertThrows(NoSuchElementException.class,()->notePage.getNoteDataTitle());
        assertThrows(NoSuchElementException.class,()->notePage.getNoteDataDescription());
        assertTrue(notePage.checkIfDeleteNoteSuccessAlertIsShown());

    }
}
