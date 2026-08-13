package com.rajbele.automation.pages;

import com.rajbele.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * ProductDetailsPage - Represents the Product Details page of Automation Exercise
 * Contains product information, add to cart, quantity selection, and review form
 */
public class ProductDetailsPage extends BasePage {

    // Product Information Elements
    private By productName = By.xpath("//div[@class='product-details']//h2");
    private By productPrice = By.xpath("//span/span[contains(text(), 'Rs.')]");
    private By productCategory = By.xpath("//p[contains(text(), 'Category:')]");
    private By productAvailability = By.xpath("//p[contains(., 'Availability')]");
    private By productCondition = By.xpath("//p[contains(text(), 'Condition:')]");
    private By productBrand = By.xpath("//div[@class='product-details']//p[contains(., 'Brand')]//text()");

    // Add to Cart Elements
    private By quantityInput = By.id("quantity");
    private By addToCartButton = By.xpath("//button[contains(@class, 'cart')]");
    private By productIdInput = By.id("product_id");

    // Cart Modal
    private By cartModal = By.id("cartModal");
    private By cartModalMessage = By.xpath("//div[@class='modal-body']/p[1]");
    private By viewCartLink = By.xpath("//div[@class='modal-body']//a[contains(@href, '/view_cart')]");
    private By continueShoppingButton = By.xpath("//button[@class='btn btn-success close-modal btn-block']");

    // Review Form Elements
    private By reviewNameInput = By.id("name");
    private By reviewEmailInput = By.id("email");
    private By reviewTextarea = By.id("review");
    private By submitReviewButton = By.id("button-review");
    private By reviewForm = By.id("review-form");
    private By reviewSuccessMessage = By.xpath("//div[@id='review-section']//div[@class='alert-success alert']");

    // Tabs
    private By reviewTab = By.xpath("//a[@href='#reviews']");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Get product name
     */
    public String getProductName() {
        return getText(productName);
    }

    /**
     * Get product price
     */
    public String getProductPrice() {
        return getText(productPrice);
    }

    /**
     * Get product category
     */
    public String getProductCategory() {
        return getText(productCategory);
    }

    /**
     * Get product availability
     */
    public String getProductAvailability() {
        return getText(productAvailability);
    }

    /**
     * Get product condition
     */
    public String getProductCondition() {
        return getText(productCondition);
    }

    /**
     * Get product brand
     */
    public String getProductBrand() {
        return getText(productBrand);
    }

    /**
     * Set quantity for product
     * @param quantity the quantity to add
     */
    public void setQuantity(int quantity) {
        clearText(quantityInput);
        typeText(quantityInput, String.valueOf(quantity));
    }

    /**
     * Get current quantity value
     */
    public int getQuantity() {
        return Integer.parseInt(getAttribute(quantityInput, "value"));
    }

    /**
     * Click add to cart button
     */
    public void clickAddToCart() {
        clickElement(addToCartButton);
    }

    /**
     * Add product to cart with quantity
     * @param quantity the quantity to add
     */
    public void addProductToCart(int quantity) {
        setQuantity(quantity);
        clickAddToCart();
    }

    /**
     * Verify add to cart modal is displayed
     */
    public boolean isAddToCartModalDisplayed() {
        try {
            waitForVisibility(cartModal);
            return isElementDisplayed(cartModal);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get cart modal message
     */
    public String getCartModalMessage() {
        return getText(cartModalMessage);
    }

    /**
     * Click view cart link from modal
     */
    public void clickViewCartFromModal() {
        clickElement(viewCartLink);
    }

    /**
     * Click continue shopping from modal
     */
    public void clickContinueShopping() {
        clickElement(continueShoppingButton);
    }

    /**
     * Click on review tab
     */
    public void clickReviewTab() {
        clickElement(reviewTab);
    }

    /**
     * Verify review form is displayed
     */
    public boolean isReviewFormDisplayed() {
        return isElementDisplayed(reviewForm);
    }

    /**
     * Enter review name
     * @param name the reviewer's name
     */
    public void enterReviewName(String name) {
        typeText(reviewNameInput, name);
    }

    /**
     * Enter review email
     * @param email the reviewer's email
     */
    public void enterReviewEmail(String email) {
        typeText(reviewEmailInput, email);
    }

    /**
     * Enter review text
     * @param reviewText the review text
     */
    public void enterReviewText(String reviewText) {
        typeText(reviewTextarea, reviewText);
    }

    /**
     * Click submit review button
     */
    public void clickSubmitReview() {
        clickElement(submitReviewButton);
    }

    /**
     * Submit a product review
     * @param name the reviewer's name
     * @param email the reviewer's email
     * @param reviewText the review text
     */
    public void submitReview(String name, String email, String reviewText) {
        enterReviewName(name);
        enterReviewEmail(email);
        enterReviewText(reviewText);
        clickSubmitReview();
    }

    /**
     * Verify review success message is displayed
     */
    public boolean isReviewSuccessMessageDisplayed() {
        try {
            return isElementDisplayed(reviewSuccessMessage);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get review success message
     */
    public String getReviewSuccessMessage() {
        return getText(reviewSuccessMessage);
    }

    /**
     * Get product ID
     */
    public String getProductId() {
        return getAttribute(productIdInput, "value");
    }

    /**
     * Verify product details are loaded
     */
    public boolean isProductDetailsLoaded() {
        return isElementDisplayed(productName) && 
               isElementDisplayed(productPrice) && 
               isElementDisplayed(addToCartButton);
    }
}
