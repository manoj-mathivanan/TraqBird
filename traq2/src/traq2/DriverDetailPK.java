package traq2;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the driver_details database table.
 * 
 */
@Embeddable
public class DriverDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="org_id", insertable=false, updatable=false)
	private String orgId;

	@Column(name="driver_id")
	private String driverId;

	public DriverDetailPK() {
	}
	public String getOrgId() {
		return this.orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getDriverId() {
		return this.driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DriverDetailPK)) {
			return false;
		}
		DriverDetailPK castOther = (DriverDetailPK)other;
		return 
			this.orgId.equals(castOther.orgId)
			&& this.driverId.equals(castOther.driverId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orgId.hashCode();
		hash = hash * prime + this.driverId.hashCode();
		
		return hash;
	}
}