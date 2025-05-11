import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DoubleClickTest {

    public static void main(String[] args) {
        // Create Playwright instance
        Playwright playwright = Playwright.create();

        // Launch Chromium browser (headless false to see it)
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // Create a new Page
        Page page = browser.newPage();

        // Navigate to the demo page
        page.navigate("https://demo.guru99.com/test/simple_context_menu.html");

        // Wait for the button to be visible
        page.waitForSelector("text=Double-Click Me To See Alert");

        // Locate the button
        Locator button = page.getByText("Double-Click Me To See Alert");

        // Assert the button is visible
        assertThat(button).isVisible();

        // Handle the JavaScript alert
        page.onceDialog(dialog -> {
            System.out.println("Alert message: " + dialog.message());
            dialog.accept();
        });

        // Perform the double-click
        button.dblclick();

        // Optional: wait for a few seconds before closing
        page.waitForTimeout(3000);

        // Close browser
        browser.close();
    }
}
