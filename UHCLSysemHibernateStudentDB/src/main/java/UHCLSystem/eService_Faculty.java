package UHCLSystem;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;

public class eService_Faculty extends eService{
	
	public eService_Faculty(String i, Data d)
	{
		super(i, d);
	}
	
	public void welcome()
	{
		String selection = "";
		Scanner input = new Scanner(System.in);
		
		while(!selection.equals("x"))
		{
			System.out.println();
			System.out.println("Welcome to UHCL eService: ");
			System.out.println("v: view my courses");
			System.out.println("x: Logout");
			
			selection = input.nextLine();
			
			if(selection.equals("v"))
			{
				viewMyCourses();
			}
			 
		}
	}

	@Override
	public void viewMyCourses() {
		
		Scanner input = new Scanner(System.in);
		
		ArrayList<String> teachings = getD().getMyTeachingCourses(getId());
		
		for(int i = 0;i<teachings.size();i++) {
//			System.out.printf("%d: %s\n",i+1,teachings.get(i));
			System.out.println(i+1 + " " + teachings.get(i));
		}
		
		System.out.println("Please select a course of your teachings to view: ");
		String intSel = input.nextLine();
		String selectionCourse = "";
		
		if(isInteger.test(intSel)) {
			int sel = Integer.parseInt(intSel);
			if(sel > 0 && sel <= teachings.size()) {
				selectionCourse = teachings.get(sel-1);
				System.out.println("Student enrolled into "+ selectionCourse + ": ");
				
				for(String s : super.getD().getEnrolledStudentNames(selectionCourse)) {
					System.out.println(s);
				}
				
			}
		
		}
		
	}
	
}
