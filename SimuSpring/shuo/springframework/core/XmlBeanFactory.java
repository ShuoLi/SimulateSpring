package phz.springframework.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class XmlBeanFactory implements BeanFactory {
	protected static Logger log = Logger.getLogger(XmlBeanFactory.class
			.getName());

	private static Map<String, Bean> beansProsMap;
	private static Map<String, Object> beansMap;

	public XmlBeanFactory(String fileName) {
		beansProsMap = XmlBeanDefinitionReader.readXmlFile(fileName);
		beansMap = new HashMap<String, Object>();
		Iterator<Entry<String, Bean>> entryIterator = beansProsMap.entrySet()
				.iterator();
		while (entryIterator.hasNext()) {
			Entry<String, Bean> entryMap = entryIterator.next();
			String beanName = entryMap.getKey();
			Object object = getObject(entryMap.getKey());
			beansMap.put(beanName, object);
			log.info(object.getClass().getName() + " --> "
					+ beanName);
		}
	}

	public Object getBean(String name) {
		return beansMap.get(name);
	}

	private Object getObject(String name) {
		Bean bean = (Bean) beansProsMap.get(name);
		Object obj = BeanProcesser.newInstance(bean.getType());
		setProperty(bean, obj);
		return obj;
	}

	private void setProperty(Bean bean, Object obj) {

		Map<String, Object> propertiesMap = bean.getProperties();

		Iterator<String> keysIterator = propertiesMap.keySet().iterator();
		while (keysIterator.hasNext()) {
			String property = keysIterator.next();
			Object value = propertiesMap.get(property);

			if (value instanceof String) {
				BeanProcesser.setProperty(obj, property, (String) value);
			}

			if (value instanceof String[]) {
				String[] strsValue = (String[]) value;
				if (strsValue.length == 1) {
					String beanId = ((String[]) value)[0];
					BeanProcesser.setProperty(obj, property, getObject(beanId));
				}
			}

			if (value instanceof List) {
				Iterator<?> valuesIterator = ((List<?>) value).iterator();
				List<Object> valuesList = new ArrayList<Object>();
				while (valuesIterator.hasNext()) {
					Object valueList = (Object) valuesIterator.next();
					if (valueList instanceof String[]) {
						valuesList.add(getObject(((String[]) valueList)[0]));
					}
				}
				BeanProcesser.setProperty(obj, property, valuesList);
			}

			if (value instanceof Map) {
				Iterator<?> entryIterator = ((Map<?, ?>) value).entrySet()
						.iterator();
				Map<Object, Object> map = new HashMap<Object, Object>();
				while (entryIterator.hasNext()) {
					Entry<?, ?> entryMap = (Entry<?, ?>) entryIterator.next();
					if (entryMap.getValue() instanceof String[]) {
						map.put(entryMap.getKey(),
								getObject(((String[]) entryMap.getValue())[0]));
					}
				}
				BeanProcesser.setProperty(obj, property, map);
			}

		}
	}

}
