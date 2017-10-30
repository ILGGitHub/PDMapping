package es.enxenio.ilga.pdmapping.modelo.util.exceptions;

@SuppressWarnings("serial")
public abstract class InstanceException extends Exception {

	private final Object key;
	private final String className;

	protected InstanceException(String specificMessage, Object key,
			String className) {

		super(specificMessage + " (key = '" + key + "' - className = '"
				+ className + "')");
		this.key = key;
		this.className = className;
	}

	public String getClassName() {

		return this.className;
	}

	public Object getKey() {

		return this.key;
	}

}
