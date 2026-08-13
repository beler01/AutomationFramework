package com.rajbele.automation.tests;

import com.rajbele.automation.base.BaseTest;
import com.rajbele.automation.pages.HomePage;
import com.rajbele.automation.pages.CartPage;
import com.rajbele.automation.pages.ProductDetailsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * CartPageTest - Comprehensive cart functionality tests
 * Verifies cart page display, cart operations, and adding products to cart
 * Merged from CartTest and CartPageTest for consolidated cart testing
 */
public class CartPageTest extends BaseTest {

    /**
     * Test: Navigate to empty cart and verify empty state
     * Navigates to cart page and verifies empty cart message
     */
    @Test(groups = "smoke", description = "Navigate to empty cart and verify empty state")
    public void testEmptyCartDisplay() {
        // Create HomePage and navigate to cart
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded");
        
        // Navigate to cart
        homePage.navigateToCart();
        
        // Create CartPage
        CartPage cartPage = new CartPage(driver);
        
        // Verify cart page is loaded
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page should be loaded");
        
        // Verify empty cart message is displayed
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty initially");
        
        // Verify cart breadcrumb is displayed
        Assert.assertTrue(cartPage.isCartBreadcrumbDisplayed(), "Cart breadcrumb should be displayed");
    }

    /**
     * Test: Verify cart page navigation elements
     * Validates that cart page has expected navigation and UI elements
     */
    @Test(groups = "smoke", description = "Verify cart page has proper navigation elements")
    public void testCartPageNavigation() {
        // Navigate to cart
        HomePage homePage = new HomePage(driver);
        homePage.navigateToCart();
        
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page should be loaded");
        
        // Verify cart breadcrumb
        Assert.assertTrue(cartPage.isCartBreadcrumbDisplayed(), 
                "Shopping Cart breadcrumb should be visible");
        
        // Verify empty cart (no products added yet)
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty");
    }

    /**
     * Test: Verify cart URL is correct
     * Validates that navigating to cart shows correct URL
     */
    @Test(groups = "smoke", description = "Verify cart page URL is correct")
    public void testCartPageUrl() {
        // Navigate to cart
        HomePage homePage = new HomePage(driver);
        homePage.navigateToCart();
        
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page should be loaded");
        
        // Verify URL contains /view_cart
        String currentUrl = cartPage.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Current URL should not be null");
        Assert.assertTrue(currentUrl.contains("view_cart"), 
                "Cart URL should contain '/view_cart'");
    }

    /**
     * Test: Continue shopping from empty cart
     * Verifies navigation back to products from empty cart
     */
    @Test(groups = "smoke", description = "Navigate from cart back to products")
    public void testContinueShoppingFromCart() {
        // Navigate to cart
        HomePage homePage = new HomePage(driver);
        homePage.navigateToCart();
        
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page should be loaded");
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty");
        
        // Continue shopping
        cartPage.continueShopping();
        
        // Verify we're on products page
        String currentUrl = cartPage.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Current URL should not be null");
        Assert.assertTrue(currentUrl.contains("products"), 
                "Should navigate to products page");
    }

    /**
     * Test: Add featured product to cart and verify in cart
     * Opens home page, adds featured product to cart, and verifies it appears in cart
     */
    @Test(groups = "smoke", description = "Add featured product to cart and verify it appears in cart")
    public void testAddProductToCart() {
        // Create HomePage and verify it's loaded
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded");
        
        // Get number of featured products
        int productCount = homePage.getNumberOfFeaturedProducts();
        Assert.assertTrue(productCount > 0, "There should be featured products on home page");
        
        // Click on first featured product to view details
        homePage.clickViewProduct(1);
        
        // Create ProductDetailsPage and verify it's loaded
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.isProductDetailsLoaded(), 
                "Product details page should be loaded");
        
        // Get product details before adding to cart
        String productName = productDetailsPage.getProductName();
        String productPrice = productDetailsPage.getProductPrice();
        
        Assert.assertNotNull(productName, "Product name should not be null");
        Assert.assertNotNull(productPrice, "Product price should not be null");
        
        // Add product to cart with quantity 1
        productDetailsPage.addProductToCart(1);
        
        // Verify add to cart modal appears
        Assert.assertTrue(productDetailsPage.isAddToCartModalDisplayed(), 
                "Add to cart modal should be displayed");
        
        // Click view cart from modal
        productDetailsPage.clickViewCartFromModal();
        
        // Verify we're on cart page
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page should be loaded");
        
        // Verify cart is not empty
        Assert.assertFalse(cartPage.isCartEmpty(), "Cart should not be empty after adding product");
        
        // Verify product is in cart
        Assert.assertTrue(cartPage.isProductInCart(productName), 
                "Product '" + productName + "' should be in cart");
    }

    /**
     * Test: Add multiple products to cart
     * Adds two products to cart and verifies both appear
     */
    @Test(groups = "smoke", description = "Add multiple products to cart")
    public void testAddMultipleProductsToCart() {
        // Navigate to home
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded");
        
        // Get featured products count
        int productCount = homePage.getNumberOfFeaturedProducts();
        Assert.assertTrue(productCount >= 2, 
                "Should have at least 2 featured products to test");
        
        // Add first product
        homePage.clickViewProduct(1);
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.isProductDetailsLoaded(), 
                "First product details page should load");
        
        String firstProductName = productDetailsPage.getProductName();
        productDetailsPage.addProductToCart(1);
        Assert.assertTrue(productDetailsPage.isAddToCartModalDisplayed(), 
                "Modal should appear after adding first product");
        
        // Continue shopping
        productDetailsPage.clickContinueShopping();
        
        // Navigate directly to product 2 details page to avoid ad overlays
        driver.navigate().to("https://automationexercise.com/product_details/2");
        productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.isProductDetailsLoaded(), 
                "Second product details page should load");
        
        String secondProductName = productDetailsPage.getProductName();
        productDetailsPage.addProductToCart(1);
        Assert.assertTrue(productDetailsPage.isAddToCartModalDisplayed(), 
                "Modal should appear after adding second product");
        
        // View cart
        productDetailsPage.clickViewCartFromModal();
        
        // Verify both products are in cart
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page should be loaded");
        Assert.assertFalse(cartPage.isCartEmpty(), "Cart should not be empty");
        
        // Verify both products in cart by count
        int cartItemCount = cartPage.getNumberOfItemsInCart();
        Assert.assertEquals(cartItemCount, 2, 
                "Cart should contain 2 products");
    }
}
