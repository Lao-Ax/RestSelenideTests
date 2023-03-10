package com.alex.jobs.innotech.selenide.pages.locators;

import org.openqa.selenium.By;

// In the future it should implement an interface in case
// if different platforms would have different locators.
public class GismeteoLocators {

    public static By searchInputField() {
        return By.cssSelector("input[type]");
    }

    public static By searchTips() {
        return By.cssSelector("a.search-item");
    }

    public static By cityTitleText() {
        return By.cssSelector("div.page-title");
    }
}
