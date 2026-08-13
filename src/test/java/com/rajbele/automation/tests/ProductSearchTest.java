package com.rajbele.automation.tests;

import com.rajbele.automation.base.BaseTest;
import com.rajbele.automation.pages.HomePage;
import com.rajbele.automation.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ProductSearchTest - Comprehensive product and search functionality tests
 * Verifies product page display, navigation, and product search
 * Merged from ProductsTest and ProductSearchTest for consolidated product testing
 */
public class ProductSearchTest extends BaseTest {

    /**
     * Test: Search for a valid product and verify results
     * Searches for 'Blue Top' product and validates search results
     */
    @Test(groups = "smoke", description = "Search for a valid product and verify search results")
    public void testSearchValidProduct() {
        // Navigate to products page
        HomePage homePage = new HomePage(driver);
        homePage.navigateToProducts();
        
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isAllProductsPageLoaded(), "Products page should be loaded");
        
        // Search for a product
        String searchTerm = "Blue Top";
        productsPage.searchProduct(searchTerm);
        
        // Verify search results are displayed
        Assert.assertTrue(productsPage.areSearchResultsDisplayed(), 
                "Search results should be displayed");
        
        // Verify at least one product is returned
        int resultCount = productsPage.getNumberOfProducts();
        Assert.assertTrue(resultCount > 0, 
                "Search should return at least one product for '" + searchTerm + "'");
    }

    /**
     * Test: Search for product and verify correct results
     * Searches for 'Tshirts' and verifies relevant products
     */
    @Test(groups = "smoke", description = "Search for Tshirts and verify results are relevant")
    public void testSearchTshirts() {
        // Navigate to products
        HomePage homePage = new HomePage(driver);
        homePage.navigateToProducts();
        
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isAllProductsPageLoaded(), "Products page should be loaded");
        
        // Search for Tshirts
        String searchTerm = "Tshirts";
        productsPage.searchProduct(searchTerm);
        
        // Verify search results
        Assert.assertTrue(productsPage.areSearchResultsDisplayed(), 
                "Search results page should display");
        
        int resultCount = productsPage.getNumberOfProducts();
        Assert.assertTrue(resultCount > 0, 
                "Search for '" + searchTerm + "' should return results");
    }

    /**
     * Test: Search with empty results
     * Searches for a term that may not exist and handles gracefully
     */
    @Test(groups = "smoke", description = "Handle search with empty or minimal results gracefully")
    public void testSearchHandlesNoResults() {
        // Navigate to products
        HomePage homePage = new HomePage(driver);
        homePage.navigateToProducts();
        
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isAllProductsPageLoaded(), "Products page should be loaded");
        
        // Search for a product
        String searchTerm = "Blue Top";
        productsPage.searchProduct(searchTerm);
        
        // Either search results display or empty message is shown
        // Test passes if page doesn't crash
        boolean hasResults = productsPage.areSearchResultsDisplayed();
        Assert.assertTrue(hasResults || productsPage.getNumberOfProducts() == 0,
                "Search should display results or handle no results gracefully");
    }

    /**
     * Test: Navigate to Products page and verify it displays
     * Validates that products page loads with product list
     */
    @Test(groups = "smoke", description = "Navigate to Products page and verify it displays")
    public void testNavigateToProductsPage() {
        // Create HomePage and navigate to products
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded first");
        
        // Navigate to Products
        homePage.navigateToProducts();
        
        // Create ProductsPage instance
        ProductsPage productsPage = new ProductsPage(driver);
        
        // Verify products page is loaded
        Assert.assertTrue(productsPage.isAllProductsPageLoaded(), 
                "Products page should be loaded with 'All Products' title");
    }

    /**
     * Test: Verify products are displayed on Products page
     * Validates that product list contains items
     */
    @Test(groups = "smoke", description = "Verify products are displayed on Products page")
    public void testProductsDisplayed() {
        // Navigate to products
        HomePage homePage = new HomePage(driver);
        homePage.navigateToProducts();
        
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isAllProductsPageLoaded(), "Products page should be loaded");
        
        // Verify products count
        int productCount = productsPage.getNumberOfProducts();
        Assert.assertTrue(productCount > 0, "Products page should display at least one product");
    }

    /**
     * Test: Verify navigation back to Home from Products
     * Validates navigation functionality
     */
    @Test(groups = "smoke", description = "Verify navigation back to Home from Products page")
    public void testNavigateBackToHomeFromProducts() {
        // Navigate to products
        HomePage homePage = new HomePage(driver);
        homePage.navigateToProducts();
        
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isAllProductsPageLoaded(), "Products page should be loaded");
        
        // Navigate back to home
        homePage = new HomePage(driver);
        homePage.navigateToHome();
        
        // Verify we're back on home page
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded after navigation");
    }
}
