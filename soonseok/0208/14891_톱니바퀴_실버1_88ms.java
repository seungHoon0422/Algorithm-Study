import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_백준_14891_톱니바퀴_실버1_88ms {
	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] gears = new String[4];

		int score = 0;

		for (int i = 0; i < 4; i++) { // gears left - to - right
			gears[i] = bf.readLine(); // N=0 S=1, 12'O = idx 0
		}

		int rotateNumber = Integer.parseInt(bf.readLine()); // number of rotates
		int rotateGear = 0;
		int rotateTo = 0; // -1=counterclock, 1=clockwise

		for (int i = 0; i < rotateNumber; i++) { // how to rotate

			String temp = bf.readLine();
			rotateGear = Integer.parseInt(temp.split(" ")[0]);
			rotateTo = Integer.parseInt(temp.split(" ")[1]);

			// important gear idx
			// gear1 : 2
			// gear2 : 6,2
			// gear3 : 6,2
			// gear4 : 6

			boolean[] edge = new boolean[3];
			// edge0 = between gear1,2
			// edge1 = between gear2,3
			// edge2 = between gear3,4

			// if different true(turn ok), same false(turn no)
			edge[0] = (gears[0].charAt(2) != gears[1].charAt(6) ? true : false);
			edge[1] = (gears[1].charAt(2) != gears[2].charAt(6) ? true : false);
			edge[2] = (gears[2].charAt(2) != gears[3].charAt(6) ? true : false);

			if (rotateGear == 1) { // rotate gear1
				if (edge[0]) { // gear1 <-> gear2

					if (edge[1]) { // gear2 <-> gear3

						if (edge[2]) { // gear3 <-> gear4
							gears[3] = rotate(gears[3], rotateTo * -1);
						}
						gears[2] = rotate(gears[2], rotateTo);
					}
					gears[1] = rotate(gears[1], rotateTo * -1);
				}
				gears[0] = rotate(gears[0], rotateTo); // roate gear1
			}

			else if (rotateGear == 2) { // rotate gear2
				if (edge[1]) { // gear2 <-> gear3
					if (edge[2]) { // gear3 <-> gear4
						gears[3] = rotate(gears[3], rotateTo);
					}
					gears[2] = rotate(gears[2], rotateTo * -1);
				}
				if (edge[0]) { // gear1 <-> gear2
					gears[0] = rotate(gears[0], rotateTo * -1);
				}
				gears[1] = rotate(gears[1], rotateTo); // rotate gear2
			}

			else if (rotateGear == 3) { // rotate gear3
				if (edge[1]) { // gear2 <-> gear3
					if (edge[0]) { // gear1 <-> gear2
						gears[0] = rotate(gears[0], rotateTo);
					}
					gears[1] = rotate(gears[1], rotateTo * -1);
				}
				if (edge[2]) { // gear3 <-> gear4
					gears[3] = rotate(gears[3], rotateTo * -1);
				}
				gears[2] = rotate(gears[2], rotateTo); // rotate gear3
			}

			else if (rotateGear == 4) { // rotate gear4
				if (edge[2]) { // gear3 <-> gear4
					if (edge[1]) { // gear2 <-> gear3
						if (edge[0]) { // gear1 <-> gear2
							gears[0] = rotate(gears[0], rotateTo * -1);
						}
						gears[1] = rotate(gears[1], rotateTo);
					}
					gears[2] = rotate(gears[2], rotateTo * -1);
				}
				gears[3] = rotate(gears[3], rotateTo); // rotate gear4
			}
		} // end of rotate for

		// scoring
		for (int j = 0; j < 4; j++) {
			score += (gears[j].charAt(0) - '0') * Math.pow(2.0, j);
		}

		System.out.println(score);

	} // end of main

	public static String rotate(String gear, int to) {

		StringBuilder sb = new StringBuilder();

		// counter clock
		// 10010010
		// -> 10010010
		// 00100101
		if (to == -1) { // first index to last
			sb.append(gear);
			sb.delete(0, 1);
			sb.append(gear.charAt(0));

			return sb.toString();
		}

		// clock
		// 00100101
		// -> 00100101
		// =10010010
		else if (to == 1) { // last index to first
			sb.append(gear.charAt(gear.length() - 1));
			sb.append(gear);
			sb.delete(sb.length() - 1, sb.length());

			return sb.toString();
		} else
			return gear;
	}

} // end of class
