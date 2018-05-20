import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class FloodInThejungle {
	public static void main(String args[]) throws Exception {

		Scanner scan = new Scanner(System.in);
		// System.out.println("Enter Number of tree ");
		int noOftree = scan.nextInt();
		// System.out.println("Enter capcity - ");
		double capacity = scan.nextDouble();
		List<String> l = new ArrayList<String>();

		String str = "";
		for (int j = 0; j < noOftree; j++) {
			for (int i = 0; i < 7; i++) {
				str += scan.next() + " ";
				i++;
			}
			l.add(str);
			str = "";
		}

		if (noOftree <= 200 && noOftree >= 1 && capacity >= 0
				&& capacity <= 10000) {

			if (noOftree == 1) {
				System.out.println(0);
			} else {
				meetingTrees(l, capacity, noOftree);

			}
		} else {
			System.out.println(-1);
		}

		scan.close();
	}

	private static void meetingTrees(List<String> input, double capacity,
			int noOftree) throws Exception {
		Set<Integer> treepositionfromWhereMonkeyCannotJump = new HashSet<Integer>();
		Set<Integer> treeposition = new HashSet<Integer>();
		Set<String> cannotJumpByMonkeySet = new HashSet<String>();
		Set<Integer> canJumpSet = new HashSet<Integer>();
		Set<String> cannotJumpByEDSet = new HashSet<String>();
		int duptree = 0;
		int noMeeting = 0;
		for (String p : input) {
			String[] pStr = p.split(" ");
			int canJump = 0;

			for (String c : input) {
				String[] cStr = c.split(" ");

				if (pStr[0].equals(cStr[0]) && pStr[1].equals(cStr[1])) {
					duptree++;
				}

				if (!p.equals(c) && Integer.parseInt(cStr[0]) <= 10000
						&& Integer.parseInt(cStr[0]) >= -10000
						&& Integer.parseInt(cStr[1]) <= 10000
						&& Integer.parseInt(cStr[1]) >= -10000
						&& Integer.parseInt(cStr[2]) <= 15
						&& Integer.parseInt(cStr[2]) >= 0
						&& Integer.parseInt(cStr[3]) >= 1
						&& Integer.parseInt(cStr[3]) <= 200) {

					if (euclideanDistance(pStr[0], pStr[1], cStr[0], cStr[1]) > capacity
							&& Integer.parseInt(pStr[2]) != 0
							&& Integer.parseInt(pStr[3]) != 0) {
						cannotJumpByEDSet.add(p);
						cannotJumpByEDSet.add(c);
					} else if (Integer.parseInt(cStr[2]) > Integer
							.parseInt(cStr[3])) {
						if (Integer.parseInt(pStr[2]) <= Integer
								.parseInt(pStr[3])) {
							treepositionfromWhereMonkeyCannotJump.add(input
									.indexOf(c));
							canJumpSet.add(canJump);
						} else if (Integer.parseInt(pStr[2]) > Integer
								.parseInt(pStr[3])) {
							cannotJumpByMonkeySet.add(p);
						}

					} else if (Integer.parseInt(pStr[2]) <= Integer
							.parseInt(pStr[3])) {
						if (Integer.parseInt(cStr[2]) > Integer
								.parseInt(cStr[3])) {
							treeposition.add(input.indexOf(c));
							treeposition.add(input.indexOf(p));
							canJump++;
							canJumpSet.add(canJump);
						} else if (Integer.parseInt(cStr[2]) <= Integer
								.parseInt(cStr[3])) {
							treeposition.add(input.indexOf(c));
							treeposition.add(input.indexOf(p));
							canJump++;
							canJumpSet.add(canJump);
						}

					}
				}/*
				 * else { noMeeting++; }
				 */

			}
		}

		if (cannotJumpByMonkeySet.size() >= 2 || duptree > noOftree
				|| cannotJumpByMonkeySet.isEmpty() && canJumpSet.isEmpty()
				|| noMeeting > 2 * noOftree
				|| treepositionfromWhereMonkeyCannotJump.size() > 1
				|| cannotJumpByEDSet.size() > 2) {
			System.out.println(-1);
		} else if (treepositionfromWhereMonkeyCannotJump.size() == 1) {
			for (Integer treeNo : treepositionfromWhereMonkeyCannotJump) {
				System.out.print(treeNo + " ");
			}
		} else if (treeposition.size() > 1) {
			for (Integer treeNo : treeposition) {
				System.out.print(treeNo + " ");
			}
		} else {
			System.out.println(-1);
		}

	}

	private static double euclideanDistance(String x1, String y1, String x2,
			String y2) {

		double x = Math.pow((Double.parseDouble(x1) - Double.parseDouble(x2)),
				2);
		double y = Math.pow((Double.parseDouble(y1) - Double.parseDouble(y2)),
				2);
		double z = x + y;
		return Math.sqrt(z);

	}
}