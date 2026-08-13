package com.rajbele.automation.tests;

import com.rajbele.automation.base.BaseTest;
import com.rajbele.automation.pages.HomePage;
import com.rajbele.automation.pages.ProductDetailsPage;
import com.rajbele.automation.pages.ProductsPage;
import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * ProductDetailsTest - Regression tests for product details page
 * Tests cover:
 * - Product information display
 * - Product reviews
 * - Product attributes verification
 * - Product navigation
 */
public class ProductDetailsTest extends BaseTest {

    /**
     * Test: Verify product details are displayed correctly
     */
    @Test(groups = "regression", description = "Verify product details are displayed correctly")
    public void testProductDetailsDisplay() {
        // Navigate to products page first
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded");
        
        homePage.navigateToProducts();

        // Navigate to first product details
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPageLoaded(), "Products page should be loaded");

        // Click first product
        homePage.clickViewProduct(1);

        // Verify product details page loads
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.isProductDetailsLoaded(), 
                "Product details page should be loaded");

        // Verify key product information is displayed
        String productName = productDetailsPage.getProductName();
        Assert.assertNotNull(productName, "Product name should not be null");
        Assert.assertTrue(!productName.trim().isEmpty(), "Product name should not be empty");

        String productPrice = productDetailsPage.getProductPrice();
        Assert.assertNotNull(productPrice, "Product price should not be null");
        Assert.assertTrue(productPrice.contains("Rs."), "Product price should contain currency");

        // Verify product is available
        String availability = productDetailsPage.getProductAvailability();
        Assert.assertNotNull(availability, "Product availability should be displayed");
    }

    /**
     * Test: Verify product category is displayed
     */
    @Test(groups = "regression", description = "Verify product category is displayed")
    public void testProductCategoryDisplay() {
        // Navigate to product details
        HomePage homePage = new HomePage(driver);
        homePage.navigateToProducts();
        homePage.clickViewProduct(1);

        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.isProductDetailsLoaded(), 
                "Product details page should be loaded");

        String category = productDetailsPage.getProductCategory();
        Assert.assertNotNull(category, "Product category should not be null");
        Assert.assertTrue(!category.trim().isEmpty(), "Product category should not be empty");
    }

    /**
     * Test: Verify product brand is displayed
     */
    @Test(groups = "regression", description = "Verify product brand is displayed")
    public void testProductBrandDisplay() {
        // Navigate to product details
        HomePage homePage = new HomePage(driver);
        homePage.navigateToProducts();
        homePage.clickViewProduct(1);

        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.isProductDetailsLoaded(), 
                "Product details page should be loaded");

        String brand = productDetailsPage.getProductBrand();
        Assert.assertNotNull(brand, "Product brand should not be null");
        Assert.assertTrue(!brand.trim().isEmpty(), "Product brand should not be empty");
    }

    /**
     * Test: Verify quantity adjustment functionality
     */
    @Test(groups = "regression", description = "Verify quantity adjustment functionality")
    public void testQuantityAdjustment() {
        // Navigate to product details
        HomePage homePage = new HomePage(driver);
        homePage.navigateToProducts();
        homePage.clickViewProduct(1);

        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.isProductDetailsLoaded(), 
                "Product details page should be loaded");

        // Set quantity to 3
        int targetQuantity = 3;
        productDetailsPage.setQuantity(targetQuantity);

        // Verify quantity was set
        int actualQuantity = productDetailsPage.getQuantity();
        Assert.assertEquals(actualQuantity, targetQuantity, 
                "Quantity should be set to " + targetQuantity);

        // Set quantity to 5
        targetQuantity = 5;
        productDetailsPage.setQuantity(targetQuantity);
        actualQuantity = productDetailsPage.getQuantity();
        Assert.assertEquals(actualQuantity, targetQuantity, 
                "Quantity should be updated to " + targetQuantity);
    }

    /**
     * Test: Verify add to cart button is clickable
     */
    @Test(groups = "regression", description = "Verify add to cart button is clickable")
    public void testAddToCartButtonClickable() {
        // Navigate to product details
        HomePage homePage = new HomePage(driver);
        homePage.navigateToProducts();
        homePage.clickViewProduct(1);

        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.isProductDetailsLoaded(), 
                "Product details page should be loaded");

        // Verify add to cart button exists and is clickable
        try {
            productDetailsPage.clickAddToCart();
            Assert.assertTrue(productDetailsPage.isAddToCartModalDisplayed(), 
                    "Add to cart modal should be displayed after clicking add to cart");
        } catch (Exception e) {
            Assert.fail("Add to cart button should be clickable: " + e.getMessage());
        }
    }

    /**
     * Test: Verify review form is displayed
     */
    @Test(groups = "regression", description = "Verify review form is displayed")
    public void testReviewFormDisplay() {
        // Navigate to product details
        HomePage homePage = new HomePage(driver);
        homePage.navigateToProducts();
        homePage.clickViewProduct(1);

        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.isProductDetailsLoaded(), 
                "Product details page should be loaded");

        // Click review tab to show review form
        productDetailsPage.clickReviewTab();

        // Verify review form is displayed
        Assert.assertTrue(productDetailsPage.isReviewFormDisplayed(), 
                "Review form should be displayed");
    }
}
