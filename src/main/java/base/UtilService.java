package base;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

public class UtilService {
	public static String getRandom(int padWithQuantityOfZeroes) {
		Random rand = new Random();

		Integer n = Math.abs(rand.nextInt());
		String formatted = String.format("%0" + padWithQuantityOfZeroes + "d", n);
		formatted = formatted.substring(formatted.length() - padWithQuantityOfZeroes, formatted.length());

		return formatted;
	}

	public static String getRandom() {
		return getRandom(5);
	}

	public static boolean selectMenuOption(WebElement ItemsList, String contains, String menuName) {		
		ExtentTestManager.getTest().log(Status.INFO, "Step: Trying to select an item that contains '" + contains + "' on the menu '" + menuName + "'");

		List<WebElement> items = ItemsList.findElements(By.xpath("li"));

		int i = 0;
		boolean found = false;
		contains = contains.toLowerCase();
		while ( (i < items.size()) && !found) {
			String temp = items.get(i).getText().toLowerCase();
			
			found = (temp.indexOf(contains) >= 0);
			i++;
		}
		if (found) {
			items.get(i-1).click();
		}
		return found;
	}
	
	public static boolean selectComboBoxOption(WebElement comboboxItemsList, String contains, String comboboxName, String container) {		
		boolean toReturn = false;

		ExtentTestManager.getTest().log(Status.INFO, "Step: Trying to select the item '" + contains + "' on the combobox '" + comboboxName + "'");
		try {
			WebElement dropdownValue = comboboxItemsList.findElement(By.xpath("//" + container + "[contains(text(),'" + contains + "')]"));
			dropdownValue.click();
			
			toReturn = true;
		} catch  (Exception e) {
			System.out.println(e.getMessage());
		}	
		return toReturn;
	}

	public static boolean selectComboBoxOption(WebElement comboboxItemsList, String contains, String comboboxName) {		
		return selectComboBoxOption(comboboxItemsList, contains, comboboxName, "div");
	}	
	
	public static boolean selectComboBoxOption(WebElement comboboxItemsList, String contains[], String comboboxName) {		
		boolean toReturn = false;

		ExtentTestManager.getTest().log(Status.INFO, "Step: Trying to select the item(s) ['" + String.join(",'", contains) + "'] on the combobox '" + comboboxName + "'");		
		try {
			WebElement dropdownValue = null;
			for(int i=0; i<contains.length; i++) {
				dropdownValue = comboboxItemsList.findElement(By.xpath("//div[contains(text(),'" + contains[i] + "')]"));
				dropdownValue.click();
			}
			toReturn = true;
		} catch  (Exception e) {
			System.out.println("Function selectComboBoxOption generates an error: " + e.getMessage());
		}
		return toReturn;
	}	
}
