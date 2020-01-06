package page.base;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * 要素の取得
 */
public abstract class FinalConfirmPageBase {

    protected SelenideElement ErrorCheckResult() {
        return $("#errorcheck_result");
    }

    protected SelenideElement ReturnToCheckInfo() {
        return $("#returnto_checkInfo");
    }
}
