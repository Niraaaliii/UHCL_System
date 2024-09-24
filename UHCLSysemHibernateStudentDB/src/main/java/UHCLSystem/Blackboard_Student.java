package UHCLSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackboard_Student extends Blackboard{

	/**
	 * @param id
	 * @param d
	 */
	public Blackboard_Student(String id, Data d) {
		super(id, d);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void viewMyCourses() {
		Scanner input = new Scanner(System.in);

		ArrayList<String> mycourses = getD().getMyCourses(getId());

		for (int i = 0; i < mycourses.size(); i++) {
			//System.out.printf("%d: %s\n", i + 1, mycourses.get(i));
			System.out.println(i + 1 + " " + mycourses.get(i));
		}

		System.out.println("select course you want to view: ");
		String intSel = input.nextLine();
		String selectionCourse = "";

		if (isInteger.test(intSel)) {
			int sel = Integer.parseInt(intSel);
			if (sel > 0 && sel <= mycourses.size()) {
				selectionCourse = mycourses.get(sel - 1);
				System.out.println("Coursenotes for " + selectionCourse + ": ");

				for (CourseNote cn : super.getD().getCourseNotes(selectionCourse)) {
					System.out.println("-----------------");
					System.out.println("From : "+cn.getUserID()+ " For : "+ cn.getCourseID());
					System.out.println(cn.getContent());
					System.out.println(cn.getDatetime());
					System.out.println("-----------------");
				}

			}

		}
		
	}

}
