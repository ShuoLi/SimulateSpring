package phz.springframework.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Shuo
 * 
 */
public class BeanProcesser {

	public static Object newInstance(String className) {
		Class<?> cls = null;
		Object obj = null;
		try {
			cls = Class.forName(className);
			obj = cls.newInstance();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return obj;
	}

	public static void setProperty(Object obj, String name, String value) {
		Class<? extends Object> clazz = obj.getClass();
		try {
			String methodName = returnSetMthodName(name);
			Method[] ms = clazz.getMethods();
			for (Method m : ms) {
				if (m.getName().equals(methodName)) {
					if (m.getParameterTypes().length == 1) {
						Class<?> clazzParameterType = m.getParameterTypes()[0];
						setFieldValue(clazzParameterType.getName(), value, m,
								obj);
						break;
					}
				}
			}
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	private static void setFieldValue(String className, String value, Method m,
			Object obj) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		if (className.equals("byte"))
			m.invoke(obj, Byte.parseByte(value));
		if (className.equals("short"))
			m.invoke(obj, Short.parseShort(value));
		if (className.equals("int"))
			m.invoke(obj, Integer.parseInt(value));
		if (className.equals("long"))
			m.invoke(obj, Long.parseLong(value));
		if (className.equals("float"))
			m.invoke(obj, Float.parseFloat(value));
		if (className.equals("double"))
			m.invoke(obj, Double.parseDouble(value));

		if (className.equals("java.lang.Byte"))
			m.invoke(obj, new Byte(value));
		if (className.equals("java.lang.Short"))
			m.invoke(obj, new Short(value));
		if (className.equals("java.lang.Integer"))
			m.invoke(obj, new Integer(value));
		if (className.equals("java.lang.Long"))
			m.invoke(obj, new Long(value));
		if (className.equals("java.lang.Float"))
			m.invoke(obj, new Float(value));
		if (className.equals("java.lang.Double"))
			m.invoke(obj, new Double(value));
		if (className.equals("java.lang.String"))
			m.invoke(obj, new String(value));
	}

	public static void setProperty(Object obj, String methodName, Object beanObj) {
		Class<? extends Object> clazz = obj.getClass();
		try {
			methodName = returnSetMthodName(methodName);
			Method[] ms = clazz.getMethods();
			for (Method m : ms) {
				if (m.getName().equals(methodName)) {
					if (m.getParameterTypes().length == 1) {
						m.invoke(obj, beanObj);
						break;
					}
				}
			}
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}


	private static String returnSetMthodName(String methodName) {
		methodName = (methodName.substring(0, 1).toUpperCase())
				+ methodName.substring(1, methodName.length());
		methodName = "set" + methodName;
		return methodName;
	}
}
