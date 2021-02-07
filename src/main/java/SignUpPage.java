import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;

public class SignUpPage {

    private final By emailField = By.cssSelector("input#email");
    private final By confirmEmailField = By.cssSelector("input#confirm");
    private final By passwordField = By.cssSelector("input#password");
    private final By nameField = By.cssSelector("input#displayname");
    private final By monthDropDown = By.cssSelector("select#month");
    private final By dayField = By.cssSelector("input#day");
    private final By yearField = By.cssSelector("input#year");
    private final String genderOption = "//label/span[text()='%s']";
    private final String shareCheckbox = "//span[contains(text(),'Share my registration')]";
    private final By signUpButton = By.xpath("//div[starts-with(@class,'SignupButton')]/button");
    private final String errorLabel = "//div[contains(@class,'InputErrorMessage') and string-length(text()>0)]";
    private final String errorLabelByText = "//div[starts-with(@class,'FormHelpText') and contains(text(),\"%s\")]";
    private final By cookiesMessageCloseButtonXpath = By.xpath("//div[@id='onetrust-close-btn-container']/button");

    public SignUpPage open() {
        Selenide.open("/");
        return this;
    }

    public SignUpPage closeCookiesWarning() {
        if ($(cookiesMessageCloseButtonXpath).isDisplayed()) {
            $(cookiesMessageCloseButtonXpath).click();
        }
        return this;
    }

    public SignUpPage typeEmail(String email) {
        $(emailField).setValue(email);
        return this;
    }

    public SignUpPage typeConfirmEmail(String email) {
        $(confirmEmailField).setValue(email);
        return this;
    }

    public SignUpPage typePassword(String password) {
        $(passwordField).setValue(password);
        return this;
    }

    public SignUpPage typeName(String name) {
        $(nameField).setValue(name);
        return this;
    }

    public SignUpPage setMonth(String month) {
        $(monthDropDown).selectOption(month);
        return this;
    }

    public SignUpPage typeDay(String day) {
        $(dayField).setValue(day);
        return this;
    }

    public SignUpPage typeYear(String year) {
        $(yearField).setValue(year);
        return this;
    }

    public SignUpPage setGender(String gender) {
        $(By.xpath(format(genderOption, gender))).click();
        return this;
    }

    public SignUpPage setShare(boolean value) {
        $(By.xpath(shareCheckbox)).doubleClick();
        return this;
    }

    public void pressSignUpButton() {
        $(signUpButton).click();
    }

    public ElementsCollection getErrors() {
        return $$(By.xpath(errorLabel));
    }

    public SelenideElement getErrorByText(String errorText) {
        return $(By.xpath(format(errorLabelByText, errorText)));
    }

    public SelenideElement getErrorByNumber(int number) {
        return getErrors().get(number - 1);
    }

    public boolean isErrorVisible(String errorMessage) {
        return $(By.xpath(format(errorLabelByText, errorMessage))).isDisplayed();
    }
}

