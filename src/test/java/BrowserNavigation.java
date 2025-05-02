import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BrowserNavigation {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()){
            Browser browser =playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("http://the-internet.herokuapp.com/");
            String title = page.title();
            System.out.println("Page title is: " + title);
            page.waitForTimeout(2000);


            // Check the main title of the page
            Locator heading = page.getByText("Welcome to the-internet");
            System.out.println(heading.textContent());
            assertThat(heading).isVisible();
            assertThat(heading).hasText("Welcome to the-internet");


            // Check the sub-title of the page
            Locator heading2 = page.getByText("Available Examples");
            System.out.println(heading2.textContent());

            // 1.1 Navigate to Challenging Dom
            Locator cDom = page.getByText("Challenging DOM");
            cDom.click();
            Locator cDomTitle = page.getByText("Challenging DOM");
            assertThat(cDomTitle).isVisible();
            System.out.println(cDomTitle.textContent());
            page.waitForTimeout(2000);

            // 1.2 Check the Challenging Description is visible
            Locator cDomText = page.getByText("The hardest part in automated web testing is finding the best locators");
            assertThat(cDomText).isVisible();
            page.waitForTimeout(2000);
            System.out.println(cDomText.textContent());


            //2. Navigate back to main page
            page.goBack();
            page.waitForTimeout(2000);
            assertThat(heading).isVisible();






        }
    }
}
