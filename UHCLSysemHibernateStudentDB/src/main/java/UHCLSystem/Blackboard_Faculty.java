package UHCLSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackboard_Faculty extends Blackboard {

	/**
	 * @param id
	 * @param d
	 */
	public Blackboard_Faculty(String id, Data d) {
		super(id, d);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void viewMyCourses() {
		Scanner input = new Scanner(System.in);

		ArrayList<String> teachings = getD().getMyTeachingCourses(getId());

		for (int i = 0; i < teachings.size(); i++) {
			// System.out.printf("%d: %s\n",i+1,teachings.get(i));
			System.out.println(i + 1 + " " + teachings.get(i));

		}

		System.out.println("Please select a course to post course notes: ");
		String intSel = input.nextLine();
		String selectionCourse = "";

		if (isInteger.test(intSel)) {
			int sel = Integer.parseInt(intSel);
			if (sel > 0 && sel <= teachings.size()) {
				selectionCourse = teachings.get(sel - 1);

				System.out.println("Enter Content: ");
				String content = input.nextLine();

				String date = DateAndTime.DateTime();
				getD().writeNote(getId(), content, selectionCourse, date);
			}
			
			System.out.println("Previous Notes for this course : ");
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
