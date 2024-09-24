package UHCLSystem;
import javax.persistence.*;

@Entity
@Table(name="enrollment")
public class Enrollment {
	
	@Id
	@Column(name="autoID")
	private int autoID;
	
	@Column(name="studentID")
	private String sid;
	
	@Column(name="courseID")
	private String cid;
	
	public Enrollment() {
		
	}

	public Enrollment(int autoID, String sid, String cid) {
		super();
		this.autoID = autoID;
		this.sid = sid;
		this.cid = cid;
	}

	public int getAutoID() {
		return autoID;
	}

	public void setAutoID(int autoID) {
		this.autoID = autoID;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
}
