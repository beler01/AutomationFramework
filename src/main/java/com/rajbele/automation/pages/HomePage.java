package com.rajbele.automation.pages;

import com.rajbele.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * HomePage - Represents the home page of Automation Exercise
 * Contains featured products, category navigation, brand navigation, and subscription
 */
public class HomePage extends BasePage {

    // Navigation Links
    private By homeLink = By.xpath("//a[contains(., 'Home') and (contains(@href, '/') or @href='/')]");
    private By productsLink = By.xpath("//a[contains(normalize-space(), 'Products')]");
    private By cartLink = By.xpath("//a[contains(normalize-space(), 'Cart')]");
    private By signupLoginLink = By.xpath("//a[contains(normalize-space(), 'Signup') and contains(normalize-space(), 'Login')]");
    private By contactUsLink = By.xpath("//a[contains(normalize-space(), 'Contact') and contains(normalize-space(), 'us')]");

    // Category Accordion
    private By categoryPanel = By.id("accordian");
    private By womenCategory = By.xpath("//a[contains(@href, '#Women')]");
    private By menCategory = By.xpath("//a[contains(@href, '#Men')]");
    private By kidsCategory = By.xpath("//a[contains(@href, '#Kids')]");

    // Brands Section
    private By brandsSection = By.xpath("//div[@class='brands_products']");
    private By brandLink = By.xpath("//a[contains(@href, '/brand_products/')]");

    // Featured Products
    private By featuredItemsTitle = By.xpath("//h2[@class='title text-center' and contains(text(), 'Features')]");
    private By addToCartButton = By.cssSelector("a.btn.btn-default.add-to-cart");
    private By viewProductLink = By.xpath("//a[contains(@href, '/product_details/')]");
    private By productPrice = By.xpath("//div[@class='productinfo text-center']/h2");

    // Footer Subscription
    private By subscriptionEmail = By.id("susbscribe_email");
    private By subscribeButton = By.id("subscribe");

    // Carousel
    private By carouselContainer = By.id("slider-carousel");

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Verify that home page is loaded
     */
    public boolean isHomePageLoaded() {
        return isElementDisplayed(featuredItemsTitle);
    }

    /**
     * Navigate to Products page
     */
    public void navigateToProducts() {
        clickElementAdSafe(productsLink);
        waitForUrlToContain("/products");
    }

    /**
     * Navigate to Login/Signup page
     */
    public void navigateToLoginSignup() {
        clickElementAdSafe(signupLoginLink);
        waitForUrlToContain("/login");
    }

    /**
     * Navigate to Cart page
     */
    public void navigateToCart() {
        clickElementAdSafe(cartLink);
        waitForUrlToContain("/view_cart");
    }

    /**
     * Navigate to Contact Us page
     */
    public void navigateToContactUs() {
        clickElementAdSafe(contactUsLink);
        waitForUrlToContain("/contact_us");
    }

    /**
     * Navigate to home by clicking home link
     */
    public void navigateToHome() {
        clickElementAdSafe(homeLink);
        waitForUrlToContain("/");
    }

    /**
     * Expand Women category
     */
    public void expandWomenCategory() {
        clickElementAdSafe(womenCategory);
    }

    /**
     * Expand Men category
     */
    public void expandMenCategory() {
        clickElementAdSafe(menCategory);
    }

    /**
     * Expand Kids category
     */
    public void expandKidsCategory() {
        clickElementAdSafe(kidsCategory);
    }

    /**
     * Click on a specific category product link
     * @param categoryProductId the ID of the category product
     */
    public void clickCategoryProduct(int categoryProductId) {
        // Try multiple category link patterns for resilience against DOM changes
        By[] categoryLinkPatterns = {
            By.xpath("//a[contains(@href, '/category_products/" + categoryProductId + "')]"),
            By.xpath("(//div[contains(@class, 'categories')]//a)[" + categoryProductId + "]"),
            By.xpath("(//nav//a[@href])[" + (categoryProductId + 3) + "]") // Offset for nav links
        };
        
        for (By pattern : categoryLinkPatterns) {
            try {
                clickElementAdSafe(pattern);
                return; // Success
            } catch (Exception e) {
                // Try next pattern
            }
        }
        // If all patterns fail, throw error
        clickElementAdSafe(categoryLinkPatterns[0]);
    }

    /**
     * Click on a specific brand
     * @param brandName the name of the brand
     */
    public void clickBrand(String brandName) {
        By brand = By.xpath("//a[contains(@href, '/brand_products/') and contains(text(), '" + brandName + "')]");
        clickElementAdSafe(brand);
    }

    /**
     * Get the number of featured products displayed
     */
    public int getNumberOfFeaturedProducts() {
        return findElements(viewProductLink).size();
    }

    /**
     * Click View Product for a specific featured product
     * @param productId the ID of the product
     */
    public void clickViewProduct(int productId) {
        By productLink = By.xpath("//a[contains(@href, '/product_details/" + productId + "')]");
        clickElementAdSafe(productLink);
    }

    /**
     * Add featured product to cart
     * @param productIndex 0-based index of the product
     */
    public void addFeaturedProductToCart(int productIndex) {
        clickElementAdSafe(findElements(addToCartButton).get(productIndex));
    }

    /**
     * Subscribe to newsletter with email
     * @param email the email address to subscribe
     */
    public void subscribeNewsletter(String email) {
        typeText(subscriptionEmail, email);
        clickElementAdSafe(subscribeButton);
    }

    /**
     * Get the price of a featured product
     * @param productIndex 0-based index of the product
     */
    public String getProductPrice(int productIndex) {
        return findElements(productPrice).get(productIndex).getText();
    }

    /**
     * Wait for carousel to load
     */
    public void waitForCarouselLoad() {
        waitForVisibility(carouselContainer);
    }
}
