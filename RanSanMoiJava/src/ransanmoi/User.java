package ransanmoi;

public class User {
	
	private String name;
	private String level;
	private String diem;
	
	public User(String name, String level, String diem) {
		
		this.name = name;
		this.level = level;
		this.diem = diem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}

	public String getDiem() {
		return diem;
	}

	public void setDiem(String diem) {
		this.diem = diem;
	}
	
	public String toString() {
		return name + "  " + level + " " + diem;
	}
}
