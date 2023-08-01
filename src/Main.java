import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Map<String, List<Sushi>> categories = new LinkedHashMap<String, List<Sushi>>();
		List<String> categoryList = new ArrayList<String>();
		List<Sushi> orderList = new ArrayList<Sushi>();
		String path = "sushi.csv";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] datas = line.split(",");
				String category = datas[0];
				String menu = datas[1];
				int price = Integer.parseInt(datas[2]);
				Sushi sushi = new Sushi(category, menu, price);
				if (categories.containsKey(category)) {
					categories.get(category).add(sushi);
				} else {
					categories.put(category, new ArrayList<Sushi>());
					categoryList.add(category);
					List<Sushi> list = categories.get(category);
					list.add(sushi);
				}
			}
		} catch (IOException e) {
			;
		}

		System.out.println("***カテゴリ一覧***");
		for (int i = 0; i < categoryList.size(); i++) {
			System.out.printf("%d.%s\n", i, categoryList.get(i));
		}
		System.out.print("番号を入力(e:注文完了)>>");
		int select = scan.nextInt();
		String categoryName = categoryList.get(select);
		List<Sushi> sushiList = categories.get(categoryName);

		System.out.println("***にぎり***");
		for (int i = 0; i < sushiList.size(); i++) {
			Sushi sushi = sushiList.get(i);
			System.out.printf("%d.%s\n", i, sushi.showInfo());
		}
		System.out.print("番号をカンマ区切りで入力(c:カテゴリ一覧)>>");
		String order = scan.next();
		String[] orders = order.split(",");
		for (int i = 0; i < orders.length; i++) {
			Sushi sushi = sushiList.get(Integer.parseInt(orders[i]));
			orderList.add(sushi);
		}

		System.out.println("-----注文表-----");
		for (Sushi sushi : orderList) {
			System.out.println(sushi.showInfo());
		}
		System.out.println("これでよろしいですか(b:戻る,c:カテゴリ一覧,e:注文完了)>>");
		String menu = scan.next();
		switch (menu) {
		case "b":
			
		case "c":
			break;
		case "e":
			System.out.println("-----注文表-----");
			int sum = 0;
			for (Sushi sushi : orderList) {
				System.out.println(sushi.showInfo());
				sum += sushi.price;
			}
			System.out.printf("合計 %d円\n", sum);
			return;
		}
	}
}
