package page;

import page.base.FinalConfirmPageBase;

/**
 * 要素の操作
 */
public class FinalConfirmPage extends FinalConfirmPageBase {

    public String getErrorCheckResult() {
        return ErrorCheckResult().text();
    }
}
