package traq;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the emp_details database table.
 * 
 */
@Embeddable
public class EmpDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="org_id", insertable=false, updatable=false)
	private String orgId;

	@Column(name="emp_id")
	private String empId;

	public EmpDetailPK() {
	}
	public String getOrgId() {
		return this.orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getEmpId() {
		return this.empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EmpDetailPK)) {
			return false;
		}
		EmpDetailPK castOther = (EmpDetailPK)other;
		return 
			this.orgId.equals(castOther.orgId)
			&& this.empId.equals(castOther.empId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orgId.hashCode();
		hash = hash * prime + this.empId.hashCode();
		
		return hash;
	}
}