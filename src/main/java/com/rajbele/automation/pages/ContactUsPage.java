package com.rajbele.automation.pages;

import com.rajbele.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * ContactUsPage - Represents the Contact Us page of Automation Exercise
 * Contains contact form with file upload capability
 */
public class ContactUsPage extends BasePage {

    // Page Elements
    private By contactFormTitle = By.xpath("//h2[contains(., 'Get In Touch')]");
    private By pageHeading = By.xpath("//h2[contains(., 'Contact Us')]");

    // Contact Form Elements
    private By contactForm = By.id("contact-us-form");
    private By nameInput = By.cssSelector("input[placeholder='Name']");
    private By emailInput = By.cssSelector("input[placeholder='Email']");
    private By subjectInput = By.cssSelector("input[placeholder='Subject']");
    private By messageTextarea = By.cssSelector("textarea[placeholder='Your Message Here']");
    private By fileUploadInput = By.xpath("//input[@type='file' and @name='upload_file']");
    private By submitButton = By.xpath("//button[contains(normalize-space(), 'Submit')]");

    // Success Message
    private By successMessage = By.xpath("//div[@class='status alert alert-success']");
    private By homeButtonAfterSubmit = By.xpath("//a[@class='btn btn-success' and contains(@href, '/')]");

    // Form section
    private By formSection = By.id("form-section");

    // Navigation
    private By homeLink = By.cssSelector("a[href='/']");

    public ContactUsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Verify contact us page is loaded
     */
    public boolean isContactUsPageLoaded() {
        try {
            return isElementDisplayed(pageHeading) || isElementDisplayed(contactFormTitle);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify contact form is displayed
     */
    public boolean isContactFormDisplayed() {
        return isElementDisplayed(contactForm);
    }

    /**
     * Enter name in contact form
     * @param name the name
     */
    public void enterName(String name) {
        typeText(nameInput, name);
    }

    /**
     * Enter email in contact form
     * @param email the email address
     */
    public void enterEmail(String email) {
        typeText(emailInput, email);
    }

    /**
     * Enter subject in contact form
     * @param subject the subject
     */
    public void enterSubject(String subject) {
        typeText(subjectInput, subject);
    }

    /**
     * Enter message in contact form
     * @param message the message text
     */
    public void enterMessage(String message) {
        typeText(messageTextarea, message);
    }

    /**
     * Upload file in contact form
     * @param filePath the path to the file to upload
     */
    public void uploadFile(String filePath) {
        try {
            findElement(fileUploadInput).sendKeys(filePath);
        } catch (Exception e) {
            // File upload might fail if element is not found
            throw new RuntimeException("Failed to upload file: " + e.getMessage());
        }
    }

    /**
     * Click submit button
     */
    public void clickSubmit() {
        clickElement(submitButton);
    }

    /**
     * Alias for clickSubmit
     */
    public void submitForm() {
        clickSubmit();
    }

    /**
     * Fill and submit contact form
     * @param name the name
     * @param email the email address
     * @param subject the subject
     * @param message the message text
     */
    public void fillAndSubmitContactForm(String name, String email, String subject, String message) {
        enterName(name);
        enterEmail(email);
        enterSubject(subject);
        enterMessage(message);
        clickSubmit();
    }

    /**
     * Fill and submit contact form with file upload
     * @param name the name
     * @param email the email address
     * @param subject the subject
     * @param message the message text
     * @param filePath the path to the file to upload
     */
    public void fillAndSubmitContactFormWithFile(String name, String email, String subject, String message, String filePath) {
        enterName(name);
        enterEmail(email);
        enterSubject(subject);
        enterMessage(message);
        uploadFile(filePath);
        clickSubmit();
    }

    /**
     * Verify success message is displayed after submission
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            // Handle confirm dialog if present
            try {
                handleAlertIfPresent();
            } catch (Exception e) {
                // No alert
            }
            return isElementDisplayed(successMessage);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get success message text
     */
    public String getSuccessMessage() {
        return getText(successMessage);
    }

    /**
     * Click home button after successful submission
     */
    public void clickHomeAfterSubmission() {
        try {
            clickElement(homeButtonAfterSubmit);
        } catch (Exception e) {
            clickElement(homeLink);
        }
    }

    /**
     * Handle alert dialog if present (for contact form confirmation)
     */
    private void handleAlertIfPresent() {
        try {
            // Try to accept any alert that might appear
            getDriver().switchTo().alert().accept();
        } catch (Exception e) {
            // No alert present, continue
        }
    }

    /**
     * Clear all form fields
     */
    public void clearForm() {
        clearText(nameInput);
        clearText(emailInput);
        clearText(subjectInput);
        clearText(messageTextarea);
    }

    /**
     * Verify name field is required
     */
    public boolean isNameFieldRequired() {
        return getAttribute(nameInput, "required") != null;
    }

    /**
     * Verify email field is required
     */
    public boolean isEmailFieldRequired() {
        return getAttribute(emailInput, "required") != null;
    }

    /**
     * Verify subject field is present
     */
    public boolean isSubjectFieldPresent() {
        try {
            return isElementDisplayed(subjectInput);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify message field is present
     */
    public boolean isMessageFieldPresent() {
        try {
            return isElementDisplayed(messageTextarea);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Navigate to home from contact page
     */
    public void navigateToHome() {
        clickElement(homeLink);
    }
}
