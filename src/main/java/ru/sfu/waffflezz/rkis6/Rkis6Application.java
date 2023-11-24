package ru.sfu.waffflezz.rkis6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Класс, являющейся точкой входа в программу
 */
@SpringBootApplication
public class Rkis6Application {

	/**
	 * Главный метод, запускается java
	 *
	 * @param args аргументы, которые пробрасываются в приложения через командную строку при запуске
	 */
	public static void main(String[] args) {
		SpringApplication.run(Rkis6Application.class, args);
	}
}
