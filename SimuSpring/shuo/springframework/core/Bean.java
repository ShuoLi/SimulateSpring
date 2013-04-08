package phz.springframework.core;

import java.util.HashMap;
import java.util.Map;
/**
 * @author Shuo
 *
 */
public class Bean {
	/* Bean Id */
	private String id;
	/* Bean Class */
	private String type;
	/* Bean Property */
	private Map<String, Object> properties = new HashMap<String, Object>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> property) {
		properties = property;
	}
}
