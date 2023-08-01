
public class Sushi {
	//フィールド
	String category;
	String name;
	int price;

	//コンストラクタ
	Sushi(String category, String name, int price) {
		this.category = category;
		this.name = name;
		this.price = price;
	}

	//インスタンスメソッド
	String showInfo() {
		return String.format("%s(%d円)", this.name, this.price);
	}
}
