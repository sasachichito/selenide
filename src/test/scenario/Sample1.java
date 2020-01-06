package scenario;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import page.CheckInfoPage;
import page.FinalConfirmPage;
import page.InputPage;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class Sample1 {

    @Before
    public void before() throws MalformedURLException {
//        Configuration.reportsFolder = "/Users/sanakagawa/Documents/Selenium関連";
//        Configuration.browser = WebDriverRunner.CHROME;
//        System.setProperty("webdriver.chrome.driver","/Users/sanakagawa/Documents/Selenium関連/chromedriver_win32/chromedriver.exe");
//        System.setProperty("webdriver.gecko.driver","/Users/sanakagawa/Documents/Selenium関連/geckodriver-v0.26.0-win64/geckodriver.exe");
        String urlToRemoteWD = "http://localhost:4444/wd/hub";
        RemoteWebDriver driver = new RemoteWebDriver(new URL(urlToRemoteWD), DesiredCapabilities.edge());
        WebDriverRunner.setWebDriver(driver);
        // TODO セッション切る
    }

    @Test
    public void 正常系確認() {
        InputPage inputPage = open("http://example.selenium.jp/reserveApp/", InputPage.class);
        inputPage.setReserveYear("2020");
        inputPage.setReserveMonth("2");
        inputPage.setReserveDay("1");
        inputPage.setReserveTerm("2");
        inputPage.setBreakfastOn();
        inputPage.setGuestName("東京太郎");
        inputPage.setPlanA(false);
        inputPage.setPlanA(false);
//        Selenide.screenshot("inputPage");

        CheckInfoPage checkPage = inputPage.clickGotoNext();
        assertThat(checkPage.getErrorCheckResult(), is(""));
        assertThat(checkPage.getDateFrom(), is("2020年2月1日"));
        assertThat(checkPage.getDateTo(), is("2020年2月3日"));
        assertThat(checkPage.getDaysCount(), is("2"));
        assertThat(checkPage.getHeadcount(), is("1"));
        assertThat(checkPage.getBfOrder(), is("あり"));
        assertThat(checkPage.getGuestName(), is("東京太郎"));
        assertThat(checkPage.getPrice(), is("19500"));
//        Selenide.screenshot("CheckInfoPage");

        FinalConfirmPage finalPage = checkPage.doCommit();
        assertThat(finalPage.getErrorCheckResult(), is(""));
//        Selenide.screenshot("FinalConfirmPage");
    }
}
