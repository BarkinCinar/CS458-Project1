import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.logging.*;
import java.util.Date;
import java.util.TimeZone;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;

public class Test {

    // Create logger
    private static final Logger LOGGER = Logger.getLogger(Test.class.getName());

    public static void main(String[] args) {
        // Set up logger
        setupLogger();

        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        LOGGER.info("---------------------- Start of the script at " + LocalDate.now().toString() + " ----------------------");

        //////////////////////////////// TC-1 Correct/incorrect email/phone/password //////////////////////////////////////////
        LOGGER.info("--------------- TEST CASE 1 ---------------");

        // Test case: Correct email and password
        login(1, driver, "user@intros.com", "password1");
        login(1, driver, "user2@intros.com", "password2");

        // Test case: Correct phone and password
        

        // Test case: Incorrect email
        login(1, driver, "incorrectemail@example.com", "password123");

        // Test case: Incorrect phone


        // Test case: Correct email and incorrect password
        login(1, driver, "user@intros.com", "incorrectpassword");

        // Test case: Correct phone and incorrect password


        // Test case: Try ten random credentials
        for (int i = 0; i < 10; i++) {
            String emailOrPhone = generateRandomEmailOrPhone();
            String password = generateRandomPassword();

            System.out.println("Trying credentials: " + emailOrPhone + " - " + password);

            login(1, driver, emailOrPhone, password);
        }

        //////////////////////////////////// TC-2 Valid/invalid email/phone/password format //////////////////////////////////////////////
        LOGGER.info("--------------- TEST CASE 2 ---------------");

        // Test case: Valid email format
        login(2, driver, "incorrectemail@example.com", "üas?sword123@,");

        // Test case: Invalid email format
        login(2, driver, "incorrectemailformatexample.com", "üas?sword123@,");

        // Test case: Invalid email format
        login(2, driver, "", "password1");

        // Test case: Valid phone format
        login(2, driver, "5305302525", "üas?sword123@,");

        // Test case: Invalid phone format
        login(2, driver, "530530252", "password123");

        // Test case: Invalid password format
        login(2, driver, "incorrectemail@example.com", "");

        ////////////////////////////////// TC-3 Excessive length email/phone/password ///////////////////////////////////////////
        LOGGER.info("--------------- TEST CASE 3 ---------------");
        // Test case: Excessive length usernames and passwords
        login(3, driver, repeatString("a", 250), repeatString("password", 250));
        login(3, driver, repeatString("user@intros.com", 100), repeatString("password1", 100));

        //////////////////////////////////////// TC-4 Login when already logged in //////////////////////////////////////////////
        LOGGER.info("--------------- TEST CASE 4 ---------------");

        // Test case: Login with the same user when already logged in
        if (login(4, driver, "user@intros.com", "password1") && login(4, driver, "user@intros.com", "password1")) {
            System.err.println("Logged in with already logged in user!");
            LOGGER.warning("Logged in with already logged in user!");
        } else {
            System.out.println("Could not log in with already logged in user.");
            LOGGER.info("Could not log in with already logged in user.");
        }

        // Test case: Login with different user when already logged in
        if (login(4, driver, "user@intros.com", "password1") && login(4, driver, "user2@intros.com", "password2")) {
            System.err.println("Logged in with already logged in user!");
            LOGGER.warning("Logged in with already logged in user!");
        } else {
            System.out.println("Could not log in with already logged in user.");
            LOGGER.info("Could not log in with already logged in user.");
        }

        //////////////////////////////// TC-5 Case sensitivity for email/phone/password /////////////////////////////////////////
        LOGGER.info("--------------- TEST CASE 5 ---------------");

        // Test case: Correct email and password with case insensitive email
        login(5, driver, "uSeR@inTroS.com", "password1");

        // Test case: Correct email and password with case insensitive password
        login(5, driver, "uses@intros.com", "PAssWord1");

        // Close the browser
        LOGGER.info("---------------------- End of the script at " + LocalDate.now() + " ----------------------");
        driver.quit();
    }

    public static boolean login(int caseNo, WebDriver driver, String emailOrPhone, String password) {
        // Open the login page
        driver.get("https://a-react-alpha.vercel.app");

        // Find email/phone and password fields
        WebElement emailPhoneField = driver.findElement(By.id("email-address"));
        WebElement passwordField = driver.findElement(By.id("password"));

        // Enter email/phone and password
        emailPhoneField.sendKeys(emailOrPhone);
        passwordField.sendKeys(password);

        // Find and click the login button
        WebElement loginButton = driver.findElement(By.id("signinButton"));
        
        loginButton.click();
        
        try {
            // Wait for the login page to load (or timeout after 1 seconds)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            if (wait.until(ExpectedConditions.urlToBe("https://a-react-alpha.vercel.app/target")) == true)
                System.out.println("Login successful with email/phone: " + emailOrPhone);
            else
                System.out.println("Login failed with email/phone: " + emailOrPhone);
            
            // Proceed with the next test case or actions for successful login
        } catch (Exception e) {
            System.out.println("Login failed with email/phone: " + emailOrPhone);
            // Handle the failure or exit the test
            // For example, you can log the failure, capture error messages, take screenshots, etc.
        }
        

        // Log the login attempt result
        if (isLoggedIn(driver)) {
            LOGGER.info("Case - " + caseNo + " Login successful for: " + emailOrPhone + " : " + password);
            return true;
        } else {
            LOGGER.warning("Case - " + caseNo + " Login failed for: " + emailOrPhone + " : " + password);
            return false;
        }

        // Add verification steps here based on your application's behavior after login attempt
        // For example, you can check for error messages, successful login redirection, etc.
    }

    private static boolean isLoggedIn(WebDriver driver) {
        // Get the current URL
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl + "******************************");
        
        // Check if the current URL matches the expected URL
        return currentUrl.equals("https://a-react-alpha.vercel.app/target");
    }    

    private static void setupLogger() {
        // Configure logger
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        LOGGER.addHandler(consoleHandler);
        try {
            FileHandler fileHandler = new FileHandler("test.log", true);
            fileHandler.setLevel(Level.ALL);
            LOGGER.addHandler(fileHandler);
            
            // Set formatter with custom date format and timezone
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
            fileHandler.setFormatter(new SimpleFormatter() {
                @Override
                public synchronized String format(LogRecord record) {
                    return dateFormat.format(new Date()) + " " + record.getMessage() + "\n";
                }
            });
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error creating log file", e);
        }
    }

    public static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static String generateRandomEmailOrPhone() {
        // Generate a random number between 0 and 1
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        return "user" + randomNumber + "@example.com";
        
    }

    public static String generateRandomPassword() {
        // Generate a random password
        return "password" + (new Random().nextInt(1000) + 1);
    }
}
