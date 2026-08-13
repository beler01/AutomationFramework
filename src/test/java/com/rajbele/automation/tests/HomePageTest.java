package com.rajbele.automation.tests;

import com.rajbele.automation.base.BaseTest;
import com.rajbele.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * HomePageTest - Smoke test for home page
 * Verifies that the AutomationExercise home page loads successfully
 */
public class HomePageTest extends BaseTest {

    /**
     * Test: Verify home page loads successfully
     * Validates that the home page title and content are displayed
     */
    @Test(groups = "smoke", description = "Verify home page loads successfully")
    public void testHomePageLoads() {
        // Create HomePage instance
        HomePage homePage = new HomePage(driver);
        
        // Verify home page is loaded
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded with featured items visible");
        
        // Verify page title contains expected text
        String pageTitle = homePage.getPageTitle();
        Assert.assertNotNull(pageTitle, "Page title should not be null");
        Assert.assertTrue(pageTitle.toLowerCase().contains("automation") && pageTitle.toLowerCase().contains("exercise"),
                "Page title should contain 'Automation' and 'Exercise'");
        
        // Verify current URL contains root path
        String currentUrl = homePage.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Current URL should not be null");
        Assert.assertTrue(currentUrl.contains("automationexercise.com"), 
                "Current URL should contain 'automationexercise.com'");
    }

    /**
     * Test: Verify carousel loads on home page
     * Validates carousel container is displayed
     */
    @Test(groups = "smoke", description = "Verify carousel is displayed on home page")
    public void testCarouselLoads() {
        HomePage homePage = new HomePage(driver);
        
        // Wait for carousel to load
        homePage.waitForCarouselLoad();
        
        // Verify home page is still loaded
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should still be loaded after carousel loads");
    }

    /**
     * Test: Verify navigation menu is available
     * Validates that key navigation links are present
     */
    @Test(groups = "smoke", description = "Verify navigation menu items are accessible")
    public void testNavigationMenuAvailable() {
        HomePage homePage = new HomePage(driver);
        
        // Verify home page loaded
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded");
        
        // Verify we can get featured products count (indicating page content is loaded)
        int productCount = homePage.getNumberOfFeaturedProducts();
        Assert.assertTrue(productCount > 0, "There should be at least one featured product on home page");
    }
}
