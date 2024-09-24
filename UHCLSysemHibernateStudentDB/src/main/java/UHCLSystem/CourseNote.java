package UHCLSystem;
import javax.persistence.*;

@Entity
@Table(name="coursenote")
public class CourseNote {
	
	@Id
	@Column(name="autoID")
	private int autoID;
	
	@Column(name="userID")
	private String userID;
	
	@Column(name="courseID")
	private String courseID;
	
	@Column(name="content")
	private String content;

	@Column(name="datetime")
	private String datetime;
	
	public CourseNote() {
		
	}

	public CourseNote(int autoID, String userID, String courseID, String content, String datetime) {
		super();
		this.autoID = autoID;
		this.userID = userID;
		this.courseID = courseID;
		this.content = content;
		this.datetime = datetime;
	}

	public int getAutoID() {
		return autoID;
	}

	public void setAutoID(int autoID) {
		this.autoID = autoID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	
}
