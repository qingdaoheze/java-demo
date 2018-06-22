package lfg.demo.jdbc;

import lombok.Data;

@Data
public class Student {
	private int id;
	private String name;
	private String sex;
	private int age;
	/** Blob字段 */
	private String avator;

	Student(String name, String sex, int age, String avator) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.avator = avator;
	}

}