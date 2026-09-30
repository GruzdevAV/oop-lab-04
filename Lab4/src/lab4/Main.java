package lab4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

public class Main {

	public static double mean(List<Integer> ints) {
		return ints.stream().mapToInt(x->x).average().getAsDouble();
	}
	public static List<String> capper(List<String> strings) {
		return strings.stream()
			.map(s -> String.format("_new_%s", s.toUpperCase()))
			.toList();
	}
	public static List<Double> squarer(List<Double> numbers) {
		var counts = numbers.stream()
			.collect(Collectors.groupingBy(x->x, Collectors.counting()));

		return numbers.stream()
//			.filter(x -> numbers.indexOf(x) == numbers.lastIndexOf(x))
			.filter(x -> counts.get(x)==1)
			.map(x -> x * x)
			.toList();
	}
	public static Object last_or_exception(Collection<Object> col) {
		return col.stream().reduce((a,b) -> b).orElseThrow();
	}
	public static double sum_of_even(List<Integer> ints) {
		return ints.stream().mapToInt(x->x)
			.filter(x -> x%2==0).sum();
//			.reduce((a,b) -> a+b)
//			.orElse(0);
	}
	public static Map<Character, String> mapper(List<String> strings) {

		return strings.stream()
			.collect(
				Collectors.toMap(
					x->x.charAt(0),
					x->x.substring(1),
		// Чтобы строки с одинаковыи первым символом не вызывали исключение.
					(a,b) -> a 
				)
			);
	}
	
	public static void main() {
		var list1 = new ArrayList<Integer>();
		Collections.addAll(list1, 1, 2, 3, 4);
		System.out.println("1) метод, возвращающий среднее значение списка целых чисел");
		System.out.print("список целых чисел: ");
		System.out.println(list1.toString());
		System.out.print("среднее: ");
		System.out.println(mean(list1));
		System.out.println();
		
		var list2 = new ArrayList<String>();
		Collections.addAll(list2, "Lalala", "land", "Грибы");
		System.out.println("2) метод, приводящий все строки в списке в верхний регистр и \r\n"
		+ "добавляющий к ним префикс «_new_»");
		System.out.print("список строк: ");
		System.out.println(list2.toString());
		System.out.print("новый список строк: ");
		System.out.println(capper(list2));
		System.out.println();

		var list3 = new ArrayList<Double>();
		Collections.addAll(list3, 1., 2., 2., 3., 50., 50., 10., -1., 0.);
		System.out.println("3) метод, возвращающий список квадратов всех встречающихся \r\n"
			+ "только один раз элементов списка");
		System.out.print("список: ");
		System.out.println(list3.toString());
		System.out.print("новый список квадратов: ");
		System.out.println(squarer(list3));
		System.out.println();
		
		var list4_1 = new ArrayList<Object>();
		var list4_2 = new ArrayList<Object>();
		Collections.addAll(list4_1, 1, 2, 3, "last");
		System.out.println("4) метод, принимающий на вход коллекцию и возвращающий ее \r\n"
			+ "последний элемент или кидающий исключение, если коллекция \r\n"
			+ "пуста");
		System.out.print("коллекция: ");
		System.out.println(list4_1.toString());
		System.out.print("последний элемент: ");
		System.out.println(last_or_exception(list4_1));
		try {
			System.out.print("пустая коллекция: ");
			System.out.println(list4_2.toString());
			System.out.print("последний элемент: ");
			System.out.println(last_or_exception(list4_2));
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println();


		var list5 = new ArrayList<Integer>();
		Collections.addAll(list5, 1, 3);
		System.out.println("5) метод, принимающий на вход массив целых чисел, возвращающий \r\n"
			+ "сумму чётных чисел или 0, если чётных чисел нет");
		System.out.print("список: ");
		System.out.println(list1.toString());
		System.out.print("сумма чётных: ");
		System.out.println(sum_of_even(list1));
		System.out.print("список: ");
		System.out.println(list5.toString());
		System.out.print("сумма чётных: ");
		System.out.println(sum_of_even(list5));
		System.out.println();
		
		var list6 = new ArrayList<String>();
		Collections.addAll(list6, "Aaron", "iPhone", "Knight", "Key");
		System.out.println("6) метод, преобразовывающий все строки в списке в Map, где первый \r\n"
			+ "символ – ключ, оставшиеся – значение");
		System.out.print("список: ");
		System.out.println(list6.toString());
		System.out.print("map: ");
		System.out.println(mapper(list6));
		System.out.println();
	}
}
