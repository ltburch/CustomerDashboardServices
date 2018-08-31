package com.bhc.customerdashboard.web.model;

import java.util.ArrayList;
import java.util.Date;

public class BHUser {
    String uid;
    String userName;
    String emailAddress;
    String primaryPhone;
    String firstName;
    String lastName;
    Date creationDt;
    Date modifyDt;
    String groups;
    String companyId;
    String businessName;
    String userType;
    String userProfileStatus;
    ArrayList<String> accountNbrs;
    
    public BHUser() {
    	
    }
    
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPrimaryPhone() {
		return primaryPhone;
	}
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getCreationDt() {
		return creationDt;
	}
	public void setCreationDt(Date creationDt) {
		this.creationDt = creationDt;
	}
	public Date getModifyDt() {
		return modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserProfileStatus() {
		return userProfileStatus;
	}
	public void setUserProfileStatus(String userProfileStatus) {
		this.userProfileStatus = userProfileStatus;
	}
	public ArrayList<String> getAccountNbrs() {
		return accountNbrs;
	}
	public void setAccountNbrs(ArrayList<String> accountNbrs) {
		this.accountNbrs = accountNbrs;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNbrs == null) ? 0 : accountNbrs.hashCode());
		result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((creationDt == null) ? 0 : creationDt.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((modifyDt == null) ? 0 : modifyDt.hashCode());
		result = prime * result + ((primaryPhone == null) ? 0 : primaryPhone.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userProfileStatus == null) ? 0 : userProfileStatus.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BHUser other = (BHUser) obj;
		if (accountNbrs == null) {
			if (other.accountNbrs != null)
				return false;
		} else if (!accountNbrs.equals(other.accountNbrs))
			return false;
		if (businessName == null) {
			if (other.businessName != null)
				return false;
		} else if (!businessName.equals(other.businessName))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (creationDt == null) {
			if (other.creationDt != null)
				return false;
		} else if (!creationDt.equals(other.creationDt))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (modifyDt == null) {
			if (other.modifyDt != null)
				return false;
		} else if (!modifyDt.equals(other.modifyDt))
			return false;
		if (primaryPhone == null) {
			if (other.primaryPhone != null)
				return false;
		} else if (!primaryPhone.equals(other.primaryPhone))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userProfileStatus == null) {
			if (other.userProfileStatus != null)
				return false;
		} else if (!userProfileStatus.equals(other.userProfileStatus))
			return false;
		if (userType == null) {
			if (other.userType != null)
				return false;
		} else if (!userType.equals(other.userType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BHUser [uid=" + uid + ", userName=" + userName + ", emailAddress=" + emailAddress + ", primaryPhone="
				+ primaryPhone + ", firstName=" + firstName + ", lastName=" + lastName + ", creationDt=" + creationDt
				+ ", modifyDt=" + modifyDt + ", groups=" + groups + ", companyId=" + companyId + ", businessName="
				+ businessName + ", userType=" + userType + ", userProfileStatus=" + userProfileStatus
				+ ", accountNbrs=" + accountNbrs + "]";
	}

}
