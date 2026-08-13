package com.rajbele.automation.pages;

import com.rajbele.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * CheckoutPage - Represents the Checkout page of Automation Exercise
 * Contains order review, address verification, and order placement
 */
public class CheckoutPage extends BasePage {

    // Page Elements
    private By checkoutTitle = By.xpath("//h2[contains(text(), 'Checkout')]");
    private By deliveryAddressSection = By.xpath("//div[@id='address_delivery' or contains(text(), 'Delivery Address')]");
    private By billingAddressSection = By.xpath("//div[@id='address_invoice' or contains(text(), 'Billing Address')]");
    
    // Review Order Section
    private By reviewOrderTable = By.xpath("//table[contains(@class, 'table')] | //table");
    private By reviewOrderProductRows = By.xpath("//tr[contains(@class, 'product')] | //tr[contains(., 'product')]");
    private By orderSummaryPrice = By.xpath("//span[contains(@class, 'amount')] | //td[contains(text(), 'Rs.')]");
    
    // Order Total
    private By cartTotalPrice = By.xpath("//p[contains(@class, 'cart_total_price')] | //td[contains(@class, 'cart_total')] | //tr[contains(., 'Total')]");
    private By orderTotalLabel = By.xpath("//label[contains(., 'Order Total')] | //li[contains(., 'Total')]");
    
    // Comments Section
    private By commentsTextarea = By.xpath("//textarea[@class='form-control' and @name='comment']");
    private By commentsSection = By.xpath("//h4[contains(text(), 'Add comments about your order')]");
    
    // Checkout Payment Section
    private By paymentSection = By.xpath("//div[contains(@id, 'payment') or contains(., 'Payment')] | //section[contains(., 'Payment')]");
    private By placeOrderButton = By.xpath("//a[contains(., 'Place Order') or contains(@class, 'check_out')] | //button[contains(., 'Place Order')]");
    
    // Navigation
    private By backToCartLink = By.xpath("//a[contains(@href, '/view_cart')]");
    private By continueShoppingLink = By.xpath("//a[contains(@href, '/products')]");
    
    // Breadcrumb
    private By checkoutBreadcrumb = By.xpath("//li[@class='active' and contains(text(), 'Checkout')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Verify checkout page is loaded
     */
    public boolean isCheckoutPageLoaded() {
        try {
            return isElementDisplayed(checkoutTitle) || isElementDisplayed(deliveryAddressSection);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify delivery address section is displayed
     */
    public boolean isDeliveryAddressDisplayed() {
        try {
            return isElementDisplayed(deliveryAddressSection);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify billing address section is displayed
     */
    public boolean isBillingAddressDisplayed() {
        try {
            return isElementDisplayed(billingAddressSection);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get delivery address text
     */
    public String getDeliveryAddress() {
        return getText(deliveryAddressSection);
    }

    /**
     * Get billing address text
     */
    public String getBillingAddress() {
        return getText(billingAddressSection);
    }

    /**
     * Verify review order section is displayed
     */
    public boolean isReviewOrderDisplayed() {
        try {
            return isElementDisplayed(reviewOrderTable);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Alias for isReviewOrderDisplayed - verify order review table displayed
     */
    public boolean isOrderReviewTableDisplayed() {
        return isReviewOrderDisplayed();
    }

    /**
     * Get number of products in order review
     */
    public int getNumberOfProductsInReview() {
        try {
            return findElements(reviewOrderProductRows).size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Verify product exists in review
     * @param productName the name of the product
     */
    public boolean isProductInReview(String productName) {
        try {
            By product = By.xpath("//table[@class='table table-condensed table-responsive']//a[contains(text(), '" + productName + "')]");
            return isElementDisplayed(product);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get order total price
     */
    public String getOrderTotalPrice() {
        return getText(cartTotalPrice);
    }

    /**
     * Enter comments for order
     * @param comments the comments text
     */
    public void enterOrderComments(String comments) {
        try {
            typeText(commentsTextarea, comments);
        } catch (Exception e) {
            // Comments field might be optional
        }
    }

    /**
     * Verify payment section is displayed
     */
    public boolean isPaymentSectionDisplayed() {
        try {
            return isElementDisplayed(paymentSection);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click place order button
     */
    public void clickPlaceOrder() {
        clickElementAdSafe(placeOrderButton);
    }

    /**
     * Verify place order button is enabled
     */
    public boolean isPlaceOrderButtonEnabled() {
        try {
            return findElement(placeOrderButton).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Go back to cart
     */
    public void goBackToCart() {
        clickElementAdSafe(backToCartLink);
    }

    /**
     * Continue shopping
     */
    public void continueShopping() {
        clickElementAdSafe(continueShoppingLink);
    }

    /**
     * Verify checkout breadcrumb is displayed
     */
    public boolean isCheckoutBreadcrumbDisplayed() {
        try {
            return isElementDisplayed(checkoutBreadcrumb);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for checkout page to load
     */
    public void waitForCheckoutPageLoad() {
        try {
            waitForVisibility(deliveryAddressSection);
        } catch (Exception e) {
            // Alternative: wait for review order table
            try {
                waitForVisibility(reviewOrderTable);
            } catch (Exception ex) {
                // Both failed, page might not load
            }
        }
    }
}
