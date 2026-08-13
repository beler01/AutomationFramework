@echo off
cd c:\Users\Raj\eclipse-workspace\AutomationFramework
echo Running 10 Failed Tests Only...
c:\apache-maven-3.9.12\bin\mvn.cmd clean test -Dtest=LoginTest#testLoginWithValidCredentials,LoginTest#testLogoutAfterLogin,ProductDetailsTest#testProductBrandDisplay,CartAndCheckoutTest#testCartTotalCalculation,CartAndCheckoutTest#testCheckoutAddressDisplay,CartAndCheckoutTest#testCheckoutOrderReview,CartAndCheckoutTest#testRemoveProductFromCart,NavigationAndSearchTest#testCategoryNavigation,ContactUsAndReviewTest#testContactUsFormSubmission,ContactUsAndReviewTest#testContactUsFormWithFileAttachment > maven_failed_tests_output.txt 2>&1
echo Failed Tests Execution Completed >> maven_failed_tests_output.txt
echo Exit code: %ERRORLEVEL% >> maven_failed_tests_output.txt
