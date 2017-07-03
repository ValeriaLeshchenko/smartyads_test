# Test task for Smartyads
This is a *Maven + TestNG + Selenium Webdriver* testing project. Please follow the instructions to run the test suite.

### Install the JDK and set JAVA_HOME
Works with JDK 7 or later. You can download the JDK and install it following the instructions here http://www.oracle.com/technetwork/java/javase/downloads/index.html. Alternatively, you can install OpenJDK - http://openjdk.java.net/install/.
Don't forget to set JAVA_HOME environment variable that points to the jdk folder.

### Install Maven
You can download Maven here https://maven.apache.org/download.cgi. See the installation instructions: https://maven.apache.org/install.html.

### Install one of the latest versions of Chrome browser.
This project uses *WebDriverManager* which loads the latest *Chrome binary* needed to use WebDriver with Chrome. On the ChromeDriver downloads page you can see the supported Chrome browser versions: https://sites.google.com/a/chromium.org/chromedriver/downloads. Make sure to upgrade your Chrome browser if necessary.

### Now you're all set to run the tests in command line or IDE.
- To run in command line:

```mvn clean```

```mvn test-compile```

```mvn test```

You'll find test reports in Surefire report directory: *target/surefire-reports*.
- In Eclipse, right-click on *testng.xml* and select **Run as TestNG Suite**.
The reports will be in *test-output* folder.
