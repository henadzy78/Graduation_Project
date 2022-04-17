package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddTestCasePage extends BasePage {
    private static final By PAGE_OPENED_IDENTIFIER = By.id("accept");

    protected By titleTestCase_Selector = By.id("title");
    protected By entityAttachmentList_Selector = By.id("entityAttachmentListEmptyIcon");
    protected By chooseFileUploadButton_Selector = By.id("libraryAttachmentsAddItem");
    protected By addTestCaseButton_Selector = By.id("accept");
    protected By attachButton_Selector = By.id("attachmentNewSubmit");
    protected By fileUploadAttach_Selector = By.id("libraryAttachment-09fdff1f-d365-46b1-b259-2ed219d06da7");
    protected By fileUploadImage_Selector = By.id("attachment-0bde364a-e4f3-451a-9f47-cc0789dc279b");

    public AddTestCasePage(WebDriver driver) {
        super(driver);
    }

    public AddTestCasePage(WebDriver driver, boolean openPageByUrl) {
        super(driver, openPageByUrl);
    }

    @Override
    protected void openPage() {
    }

    @Override
    protected boolean isPageOpened() {
        return waits.waitForVisibility(PAGE_OPENED_IDENTIFIER).isDisplayed();
    }

    public WebElement getTitleTestCaseField() {
        return driver.findElement(titleTestCase_Selector);
    }

    public WebElement getFileUploadButton(){
        return driver.findElement(entityAttachmentList_Selector);
    }

    public WebElement getAddTestCaseButton() {
        return driver.findElement(addTestCaseButton_Selector);
    }

    public WebElement getFileUploadAttach() {
        waits.waitForVisibility(fileUploadAttach_Selector);
        return driver.findElement(fileUploadAttach_Selector);}

    public WebElement getFileUploadImage () {
        waits.waitForVisibility(fileUploadImage_Selector);
        return driver.findElement(fileUploadImage_Selector);}


    public WebElement getAttachButton () {
        waits.waitForClickable(attachButton_Selector);
        return driver.findElement(attachButton_Selector);}


    public WebElement getChooseFileUploadButton () {
        waits.waitForVisibility(chooseFileUploadButton_Selector).isDisplayed();
        return driver.findElement(chooseFileUploadButton_Selector);
    }

}
