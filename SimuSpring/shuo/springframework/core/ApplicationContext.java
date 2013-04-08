package phz.springframework.core;


/**
 * 
 * @author Shuo  
 * 
 */
public interface ApplicationContext {
	/**
	 * @param beanId
	 * @return
	 */
	public Object getBean(String beanId);
}
