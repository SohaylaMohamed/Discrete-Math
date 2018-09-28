package eval.id32;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static Scanner scan;

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		String s = scan.nextLine();
		CallTable.getInstance().setTable(s);
		Expression t  =  CallTable.getInstance().getTables().get(0);

		System.out.println(t.getTruthTable());
		s = scan.nextLine();
		CallTable.getInstance().setTable(s);
		System.out.println(t.getTruthTable());
		
		System.out.println(CallTable.getInstance().checkEquality(0, 1));
		System.out.println(CallTable.getInstance().testForContr(0));


	}

}
