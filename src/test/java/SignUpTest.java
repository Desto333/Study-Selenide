import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;

public class SignUpTest {
    SignUpPage page;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Programs\\Selenium\\chromedriver.exe");
        baseUrl = "https://www.spotify.com/us/signup/";
        browser = "chrome";
        startMaximized = true;
        page = new SignUpPage();
        page.open().closeCookiesWarning();
    }

    @Test
    public void typeInvalidYear() {
        page.setMonth("May")
                .typeDay("10")
                .typeYear("85")
                .setShare(true)
                .pressSignUpButton();
        page.getErrors().shouldHave(size(7));
        page.getErrorByText("Enter a valid year.").shouldBe(visible);
    }

    @Test
    public void confirmInvalidEmail() {
        page.typeEmail("test@mail.test")
                .typeConfirmEmail("wrong@mail.test")
                .typeName("TestName")
                .setGender("Male")
                .pressSignUpButton();
        page.getErrors().shouldHave(size(6));
        page.getErrorByText("The email addresses don't match.").shouldBe(visible);
        page.getErrorByText("Enter a name for your profile.").shouldNotBe(visible);
        page.getErrorByText("Select your gender.").shouldNotBe(visible);
    }

    @Test
    public void signUpWithEmptyPassword() {
        page.typeEmail("test@mail.test")
                .typeConfirmEmail("test@mail.test")
                .typeName("TestName")
                .pressSignUpButton();
        page.getErrors().shouldHave(size(6));
        page.getErrorByText("You need to enter a password.").shouldBe(visible);
        page.getErrorByText("Enter a name for your profile.").shouldNotBe(visible);
    }

    @Test
    public void typeInvalidValues() {
        page.typeEmail("testmail@gtrg.tv")
                .typeConfirmEmail("wrong@test.mail")
                .typePassword("")
                .typeName("")
                .typeDay("")
                .typeYear("")
                .setShare(true)
                .pressSignUpButton();
        page.getErrors().shouldHave(size(8));
        page.getErrorByNumber(8).shouldBe(exactText("Confirm you're not a robot."));
    }
}

