package com.rajbele.automation.pages;

import com.rajbele.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * ProductsPage - Represents the Products page of Automation Exercise
 * Contains product search, category filtering, brand filtering, and product listing
 */
public class ProductsPage extends BasePage {

    // Search Elements
    private By searchProductInput = By.cssSelector("input[name='search'], input#search_product, input[placeholder*='Search']");
    private By submitSearchButton = By.cssSelector("button#submit_search, button[name='submit_search'], button[type='submit']");
    private By searchResultsTitle = By.xpath("//h2[contains(., 'Search Results') or contains(., 'Searched Products')]");

    // Category Elements
    private By categoryAccordion = By.id("accordian");
    private By womenCategoryLink = By.xpath("//a[contains(@href, '#Women')]");
    private By menCategoryLink = By.xpath("//a[contains(@href, '#Men')]");
    private By kidsCategoryLink = By.xpath("//a[contains(@href, '#Kids')]");

    // Brand Elements
    private By brandsSection = By.xpath("//div[@class='brands_products']");
    private By brandProductLink = By.xpath("//a[contains(@href, '/brand_products/')]");

    // Product Listing Elements
    private By productList = By.xpath("//div[@class='productinfo text-center']");
    private By addToCartButtons = By.xpath("//a[contains(@class, 'add-to-cart')]");
    private By viewProductLinks = By.xpath("//a[contains(@href, '/product_details/')]");
    private By productName = By.xpath("//p[contains(text(), '')]");
    private By productPrice = By.xpath("//h2");

    // Pagination
    private By paginationContainer = By.xpath("//ul[@class='pagination']");
    private By paginationButton = By.xpath("//ul[@class='pagination']//li");

    // Page title
    private By allProductsTitle = By.xpath("//h2[contains(text(), 'All Products')]");

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Verify all products page is loaded
     */
    public boolean isAllProductsPageLoaded() {
        return isElementDisplayed(allProductsTitle);
    }

    /**
     * Alias for isAllProductsPageLoaded for consistency
     */
    public boolean isProductsPageLoaded() {
        return isAllProductsPageLoaded();
    }

    /**
     * Search for a product
     * @param productName the name of the product to search
     */
    public void searchProduct(String productName) {
        typeText(searchProductInput, productName);
        clickElementAdSafe(submitSearchButton);
        waitForVisibility(searchResultsTitle);
    }

    /**
     * Verify search results are displayed
     */
    public boolean areSearchResultsDisplayed() {
        try {
            return isElementDisplayed(searchResultsTitle);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click on Women category
     */
    public void clickWomenCategory() {
        clickElementAdSafe(womenCategoryLink);
    }

    /**
     * Click on Men category
     */
    public void clickMenCategory() {
        clickElementAdSafe(menCategoryLink);
    }

    /**
     * Click on Kids category
     */
    public void clickKidsCategory() {
        clickElementAdSafe(kidsCategoryLink);
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
     * Get number of products displayed
     */
    public int getNumberOfProducts() {
        return findElements(productList).size();
    }

    /**
     * Click view product for a specific product
     * @param productId the ID of the product
     */
    public void clickViewProduct(int productId) {
        By productLink = By.xpath("//a[contains(@href, '/product_details/" + productId + "')]");
        clickElementAdSafe(productLink);
    }

    /**
     * Click view product by index (1-based)
     * @param index 1-based index of the product
     */
    public void clickViewProductByIndex(int index) {
        clickElementAdSafe(findElements(viewProductLinks).get(index - 1));
    }

    /**
     * Add product to cart by index
     * @param index 1-based index of the product
     */
    public void addProductToCartByIndex(int index) {
        clickElementAdSafe(findElements(addToCartButtons).get(index - 1));
    }

    /**
     * Add product to cart by product ID
     * @param productId the ID of the product
     */
    public void addProductToCart(int productId) {
        By addToCart = By.xpath("//a[@href='#' and @data-product-id='" + productId + "' and contains(@class, 'add-to-cart')]");
        clickElementAdSafe(addToCart);
    }

    /**
     * Get price of a product by index
     * @param index 1-based index of the product
     */
    public String getProductPrice(int index) {
        return findElements(productPrice).get(index).getText();
    }

    /**
     * Navigate to next page in pagination
     */
    public void navigateToNextPage() {
        // Find the next button in pagination and click it
        By nextPageButton = By.xpath("//ul[@class='pagination']//li/a[contains(text(), 'Next') or contains(@aria-label, 'Next')]");
        clickElementAdSafe(nextPageButton);
        waitForPageLoad();
    }

    /**
     * Navigate to pagination page
     * @param pageNumber the page number to navigate to
     */
    public void navigateToPage(int pageNumber) {
        By paginationLink = By.xpath("//ul[@class='pagination']//li[" + pageNumber + "]/a");
        clickElementAdSafe(paginationLink);
    }

    /**
     * Check if pagination exists
     */
    public boolean doesPaginationExist() {
        try {
            return isElementDisplayed(paginationContainer);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify product name contains search term
     * @param productIndex the index of the product (1-based)
     * @param searchTerm the search term to verify
     */
    public boolean verifyProductNameContains(int productIndex, String searchTerm) {
        try {
            String productNameText = findElements(productName).get(productIndex - 1).getText();
            return productNameText.toLowerCase().contains(searchTerm.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }
}
