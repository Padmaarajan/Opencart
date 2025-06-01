package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage
{
    public SearchPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='content']/div[3]//img")
    List<WebElement> searchProducts;

    @FindBy(name = "quantity")
    WebElement txtQuantity;

    @FindBy(xpath = "//button[@id='button-cart']")
    WebElement btnAddToCart;

    @FindBy(xpath = "//div[contains(text(),'Success: You have added')]")
    WebElement cnfMsg;

    // ✅ Checks whether a product exists based on its 'title' attribute
    public boolean isProductExist(String productName)
    {
        for (WebElement product : searchProducts)
        {
            if (product.getAttribute("title").equalsIgnoreCase(productName))
            {
                return true;
            }
        }  
        return false;
    }

    // ✅ Clicks the first matching product based on its 'title' attribute
    public void selectProduct(String productName)
    {
        for (WebElement product : searchProducts)
        {
            if (product.getAttribute("title").equalsIgnoreCase(productName))
            {
                product.click();
                break; // Stop after first match
            }
        }
    }

    public void setQuantity(String qty)
    {
        txtQuantity.clear();
        txtQuantity.sendKeys(qty);
    }

    public void addToCart()
    {
        btnAddToCart.click();
    }

    public boolean checkConfMsg()
    {
        try
        {
            return cnfMsg.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
