import com.microsoft.playwright.*;


public class FirstPlaywrightTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            System.out.println("Title of the page: " + page.title());

            // wait for username input to be visible
            


            browser.close();
        }
    }
}
