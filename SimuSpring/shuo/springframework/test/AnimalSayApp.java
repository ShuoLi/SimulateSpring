/**
 * 
 */
package phz.springframework.test;

import phz.springframework.core.ApplicationContext;
import phz.springframework.core.FileSystemXmlApplicationContext;

/**
 * 
 * @author Shuo  
 *
 */
public class AnimalSayApp {
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"applicationContext.xml");
		AnimalSay animalSay = (AnimalSay) context.getBean("animalSay");
		animalSay.say();
	}
}
