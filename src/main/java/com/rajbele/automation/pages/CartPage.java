package com.rajbele.automation.pages;

import com.rajbele.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * CartPage - Represents the Shopping Cart page of Automation Exercise
 * Contains cart items, quantity adjustment, remove product, and checkout
 */
public class CartPage extends BasePage {

    // Cart Container
    private By cartContainer = By.id("cart_info");
    private By emptyCartMessage = By.id("empty_cart");
    private By cartTable = By.xpath("//table[contains(@class,'table') or @id='cart_info']");

    // Cart Items Elements
    private By cartProductRows = By.xpath("//table//tbody/tr | //tbody/tr");
    private By productImage = By.xpath("//td[contains(@class,'cart_product')]//img");
    private By productName = By.xpath("//td[contains(@class,'cart_description')]//a | //td[contains(@class,'cart_description')]//h4//a");
    private By productPrice = By.xpath("//td[contains(@class,'cart_price')]//p | //td[contains(@class,'cart_total_price')] | //p[contains(@class,'cart_total_price')]");
    private By quantityInput = By.xpath("//td[contains(@class,'cart_quantity')]//button | //input[contains(@class,'cart_quantity_input')] ");
    private By removeProductButton = By.cssSelector("a.cart_quantity_delete, a[data-product-id]");

    // Total Price
    private By cartTotalPrice = By.xpath("//p[contains(@class,'cart_total_price')] | //td[contains(@class,'cart_total')] | //tr[contains(., 'Total')]");
    private By cartTotalLabel = By.xpath("//label[contains(text(), 'Cart Total')] | //li[contains(., 'Cart Total')]");

    // Checkout and Navigation
    private By proceedToCheckoutButton = By.xpath("//a[contains(., 'Proceed To Checkout') or contains(@class, 'check_out') or contains(@href, '/checkout')]");
    private By continueShopping = By.xpath("//a[contains(@href, '/products') or contains(., 'Continue Shopping')]");

    // Checkout Modal (for non-logged-in users)
    private By checkoutModal = By.id("checkoutModal");
    private By loginLink = By.xpath("//a[contains(@href, '/login') and contains(., 'Login')]");
    private By closeCheckoutModalButton = By.xpath("//button[@class='close close-checkout-modal']");

    // Breadcrumb
    private By shoppingCartBreadcrumb = By.xpath("//li[@class='active' and contains(text(), 'Shopping Cart')]");

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Verify cart page is loaded
     */
    public boolean isCartPageLoaded() {
        return isElementDisplayed(shoppingCartBreadcrumb);
    }

    /**
     * Check if cart is empty
     */
    public boolean isCartEmpty() {
        try {
            return isElementDisplayed(emptyCartMessage);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get number of items in cart
     */
    public int getNumberOfItemsInCart() {
        if (isCartEmpty()) {
            return 0;
        }
        return findElements(cartProductRows).size();
    }

    /**
     * Verify product exists in cart by name
     * @param productName the name of the product
     */
    public boolean isProductInCart(String productName) {
        try {
            By product = By.xpath("//td[@class='cart_description']//a[contains(text(), '" + productName + "')]");
            return isElementDisplayed(product);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get product price in cart
     * @param productName the name of the product
     */
    public String getProductPrice(String productName) {
        By price = By.xpath("//td[@class='cart_description']//a[contains(text(), '" + productName + "')]/../..//td[@class='cart_price']//p");
        return getText(price);
    }

    /**
     * Get product quantity in cart
     * @param productIndex 1-based index of the product
     */
    public int getProductQuantity(int productIndex) {
        String quantityValue = getAttribute(findElements(quantityInput).get(productIndex - 1), "value");
        return Integer.parseInt(quantityValue);
    }

    /**
     * Update product quantity
     * @param productIndex 1-based index of the product
     * @param newQuantity the new quantity
     */
    public void updateProductQuantity(int productIndex, int newQuantity) {
        By quantityField = By.xpath("(//input[@class='cart_quantity_input'])[" + productIndex + "]");
        clearText(quantityField);
        typeText(quantityField, String.valueOf(newQuantity));
    }

    /**
     * Remove product from cart by index
     * @param productIndex 1-based index of the product
     */
    public void removeProductByIndex(int productIndex) {
        clickElementAdSafe(findElements(removeProductButton).get(productIndex - 1));
        waitForPageLoad();
    }

    /**
     * Remove product from cart by name
     * @param productName the name of the product
     */
    public void removeProductByName(String productName) {
        By removeButton = By.xpath("//td[@class='cart_description']//a[contains(text(), '" + productName + "')]/../..//td[@class='cart_delete']//a");
        clickElementAdSafe(removeButton);
        waitForPageLoad();
    }

    /**
     * Get cart total price
     */
    public String getCartTotalPrice() {
        return getText(cartTotalPrice);
    }

    /**
     * Click proceed to checkout button
     */
    public void clickProceedToCheckout() {
        clickElementAdSafe(proceedToCheckoutButton);
    }

    /**
     * Verify checkout modal is displayed (for non-logged-in users)
     */
    public boolean isCheckoutModalDisplayed() {
        try {
            waitForVisibility(checkoutModal);
            return isElementDisplayed(checkoutModal);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click login link from checkout modal
     */
    public void clickLoginFromCheckoutModal() {
        clickElementAdSafe(loginLink);
    }

    /**
     * Close checkout modal
     */
    public void closeCheckoutModal() {
        clickElementAdSafe(closeCheckoutModalButton);
    }

    /**
     * Verify product table is displayed
     */
    public boolean isProductTableDisplayed() {
        try {
            return !isCartEmpty() && isElementDisplayed(cartTable);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Continue shopping (navigate back to products)
     */
    public void continueShopping() {
        clickElementAdSafe(continueShopping);
    }

    /**
     * Get total price for all items
     * @param productIndex 1-based index of the product
     */
    public String getProductTotalPrice(int productIndex) {
        By totalPrice = By.xpath("(//tbody/tr//td[@class='cart_total'])[" + productIndex + "]");
        return getText(totalPrice);
    }

    /**
     * Verify cart breadcrumb is present
     */
    public boolean isCartBreadcrumbDisplayed() {
        return isElementDisplayed(shoppingCartBreadcrumb);
    }
}
