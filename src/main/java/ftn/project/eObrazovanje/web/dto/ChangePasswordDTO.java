package ftn.project.eObrazovanje.web.dto;

public class ChangePasswordDTO {

	private Long userID;
	private String newPassword;
	private String reapeatPassword;
	
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getReapeatPassword() {
		return reapeatPassword;
	}
	public void setReapeatPassword(String reapeatPassword) {
		this.reapeatPassword = reapeatPassword;
	}
	public ChangePasswordDTO(Long userID, String newPassword, String reapeatPassword) {
		super();
		this.userID = userID;
		this.newPassword = newPassword;
		this.reapeatPassword = reapeatPassword;
	}
	public ChangePasswordDTO() {
		super();
	}
	
}
