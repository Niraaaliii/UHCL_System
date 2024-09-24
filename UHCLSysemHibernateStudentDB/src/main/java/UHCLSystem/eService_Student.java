package UHCLSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class eService_Student extends eService {

	public eService_Student(String i, Data d) {
		super(i, d);

	}

	@Override
	public void welcome() {
		String selection = "";
		Scanner input = new Scanner(System.in);

		while (!selection.equals("x")) {
			System.out.println();
			System.out.println("Welcome to UHCL eService: ");
			System.out.println("v: view my courses");
			System.out.println("r: register a course");
			System.out.println("x: Logout");

			selection = input.nextLine();

			if (selection.equals("v")) {
				viewMyCourses();
			} else if (selection.equals("r")) {
				register();
			}
		}

	}

	public void register() {

		Scanner input = new Scanner(System.in);

		ArrayList<String> opencourses = getD().getOpenCourses(getId());

		for (int i = 0; i < opencourses.size(); i++) {
			//System.out.printf("%d: %s\n", i + 1, opencourses.get(i));
			System.out.println(i + 1 + " " + opencourses.get(i));
		}

		System.out.println("Please select a course you want to register for: ");
		String intSel = input.nextLine();
		String selectionCourse = "";

		if (isInteger.test(intSel)) {
			int sel = Integer.parseInt(intSel);
			if (sel > 0 && sel <= opencourses.size()) {

				selectionCourse = opencourses.get(sel - 1);
				getD().registerCourse(getId(), selectionCourse);
				System.out.println("Registered for " + selectionCourse);
			}

		}

	}

	@Override
	public void viewMyCourses() {

		Scanner input = new Scanner(System.in);

		ArrayList<String> mycourses = getD().getMyCourses(getId());

		for (int i = 0; i < mycourses.size(); i++) {
			//System.out.printf("%d: %s\n", i + 1, mycourses.get(i));
			System.out.println(i + 1 + " " + mycourses.get(i));
		}


	}

}
