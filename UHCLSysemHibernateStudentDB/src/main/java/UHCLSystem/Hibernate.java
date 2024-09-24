package UHCLSystem;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class Hibernate implements Data {

	protected SessionFactory sessionFactory;

	public void setup() {
		final StandardServiceRegistry reg = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(reg).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(reg);
		}
	}

	public void exit() {
		sessionFactory.close();
	}

	public uhcluser login(String id, String psw) {
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		uhcluser u = session.get(uhcluser.class, id);
		session.getTransaction().commit();

		if (u == null) {
			System.out.println("Your login ID is not found!");
			session.close();
			exit();
			return null;
		} else {
			if (psw.equals(u.getPassword())) {
				System.out.println("Your login is successful!");
				session.close();
				exit();
				return u;
			} else {
				System.out.println("Your password is incorrect!");
				session.close();
				exit();
				return null;
			}
		}

	}

	public ArrayList<String> getMyTeachingCourses(String sid) {

		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// course refers to the class, not the table
		String hql = "Select C.courseID from Course C where C.instructor=:instructor";
		Query query = session.createQuery(hql);
		query.setParameter("instructor", sid);

		ArrayList<String> courses = (ArrayList<String>) query.list();

		session.getTransaction().commit();
		session.close();
		exit();

		return courses;
	}

	public ArrayList<String> getEnrolledStudentNames(String cid) {

		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String hql = "Select E.sid from Enrollment E where E.cid=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", cid);

		ArrayList<String> names = (ArrayList<String>) query.list();

		session.getTransaction().commit();
		session.close();
		exit();

		return names;
	}

	public void registerCourse(String sid, String cid) {

		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Enrollment e = new Enrollment(0, sid, cid);
		Course c = session.load(Course.class, cid);

		c.setEnrolled(c.getEnrolled() + 1); // increase enrolled by 1

		if (c.getEnrolled() >= c.getCap()) {
			c.setStatus("close");
		}

		session.update(c);
		session.save(e);
		session.getTransaction().commit();
		session.clear();
		exit();
	}

	public ArrayList<String> getMyCourses(String sid) {

		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String hql = "Select E.cid from Enrollment E where E.sid=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", sid);

		ArrayList<String> courses = (ArrayList<String>) query.list();

		session.getTransaction().commit();
		session.close();
		exit();

		return courses;
	}

	public ArrayList<String> getOpenCourses(String sid) {

		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String hql = "SELECT C.courseID from Course C WHERE C.courseID not in ( SELECT E.cid from Enrollment E WHERE E.sid=:id ) and status = 'open'";
//		String hql = "SELECT C.courseID from Course C WHERE C.status = 'open'";
		Query query = session.createQuery(hql);
		query.setParameter("id", sid);

		ArrayList<String> courses = (ArrayList<String>) query.list();

		session.getTransaction().commit();
		session.close();
		exit();

		return courses;
	}

	public void writeNote(String writer, String content, String course, String datetime) {
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		CourseNote cn = new CourseNote(0, writer, course, content, datetime);
		session.save(cn);

		session.getTransaction().commit();
		session.clear();
		exit();
	}

	public ArrayList<CourseNote> getCourseNotes(String course) {

		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM CourseNote cn WHERE cn.courseID = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", course);

		ArrayList<CourseNote> courseNotes = (ArrayList<CourseNote>) query.list();

		session.getTransaction().commit();
		session.close();
		exit();

		return courseNotes;
	}
}
