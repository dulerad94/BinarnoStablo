
public class Main {

	public static void main(String[] args) {
		BinarnoStablo stablo=new BinarnoStablo();
		stablo.ubaci("B");
		stablo.ubaci("A");
		stablo.ubaci("E");
		stablo.ubaci("D");
		stablo.izbaci("F");
		stablo.ubaci("Z");
		stablo.ubaci("K");
		stablo.ubaci("H");
		stablo.izbaci("Z");
		stablo.izbaci("K");
		stablo.izbaci("H");
		stablo.prefix(stablo.getKoren());
		System.out.println();
		stablo.infix(stablo.getKoren());
		System.out.println();
		stablo.postfix(stablo.getKoren());
		System.out.println();
		System.out.println(stablo.visina(stablo.getKoren()));
	}

}
