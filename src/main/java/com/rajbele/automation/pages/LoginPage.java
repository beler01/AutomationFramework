package com.rajbele.automation.pages;

import com.rajbele.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * LoginPage - Represents the Login/Signup page of Automation Exercise
 * Contains login form and signup form
 */
public class LoginPage extends BasePage {

    // Login Form Elements
    private By loginEmailInput = By.cssSelector("input[data-qa='login-email']");
    private By loginPasswordInput = By.cssSelector("input[data-qa='login-password']");
    private By loginButton = By.cssSelector("button[data-qa='login-button']");
    private By loginFormTitle = By.xpath("//h2[contains(text(), 'Login to your account')]");

    // Signup Form Elements
    private By signupNameInput = By.cssSelector("input[data-qa='signup-name']");
    private By signupEmailInput = By.cssSelector("input[data-qa='signup-email']");
    private By signupButton = By.cssSelector("button[data-qa='signup-button']");
    private By signupFormTitle = By.xpath("//h2[contains(text(), 'New User Signup')]");

    // Error messages
    private By errorMessage = By.xpath("//p[contains(text(), 'Your email or password is incorrect')]");

    // Navigation
    private By homeLink = By.cssSelector("a[href='/']");

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Verify login form is displayed
     */
    public boolean isLoginFormDisplayed() {
        return isElementDisplayed(loginFormTitle);
    }

    /**
     * Verify signup form is displayed
     */
    public boolean isSignupFormDisplayed() {
        return isElementDisplayed(signupFormTitle);
    }

    /**
     * Enter email in login form
     * @param email the email address
     */
    public void enterLoginEmail(String email) {
        typeText(loginEmailInput, email);
    }

    /**
     * Enter password in login form
     * @param password the password
     */
    public void enterLoginPassword(String password) {
        typeText(loginPasswordInput, password);
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        clickElementAdSafe(loginButton);
    }

    /**
     * Perform login with email and password
     * @param email the email address
     * @param password the password
     */
    public void login(String email, String password) {
        enterLoginEmail(email);
        enterLoginPassword(password);
        clickLoginButton();
    }

    /**
     * Enter name in signup form
     * @param name the user's name
     */
    public void enterSignupName(String name) {
        typeText(signupNameInput, name);
    }

    /**
     * Enter email in signup form
     * @param email the email address
     */
    public void enterSignupEmail(String email) {
        typeText(signupEmailInput, email);
    }

    /**
     * Click signup button
     */
    public void clickSignupButton() {
        clickElementAdSafe(signupButton);
    }

    /**
     * Perform signup with name and email
     * @param name the user's name
     * @param email the email address
     */
    public void signup(String name, String email) {
        enterSignupName(name);
        enterSignupEmail(email);
        clickSignupButton();
    }

    /**
     * Verify login error message is displayed
     */
    public boolean isLoginErrorDisplayed() {
        try {
            return isElementDisplayed(errorMessage);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get login error message
     */
    public String getLoginErrorMessage() {
        return getText(errorMessage);
    }

    /**
     * Navigate to home page
     */
    public void navigateToHome() {
        clickElementAdSafe(homeLink);
    }
}
