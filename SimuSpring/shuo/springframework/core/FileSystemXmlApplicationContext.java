package phz.springframework.core;

/**
 * 
 * @author Shuo  
 * 
 */
public class FileSystemXmlApplicationContext implements ApplicationContext {

	private BeanFactory beanFactory;

	/**
	 * 读取配置文件
	 */
	public FileSystemXmlApplicationContext(String fileName) {
		beanFactory = new XmlBeanFactory(fileName);
	}

	public Object getBean(String name) {
		return beanFactory.getBean(name);
	}

}
