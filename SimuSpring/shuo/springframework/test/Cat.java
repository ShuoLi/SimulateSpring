package phz.springframework.test;

public class Cat implements Animal {

	private String name;

	public void say() {
		System.out.println("I am " + name + "!");
	}

	public void setName(String name) {
		this.name = name;
	}

}
