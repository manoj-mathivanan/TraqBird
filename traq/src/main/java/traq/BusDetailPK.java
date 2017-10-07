package traq;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the bus_details database table.
 * 
 */
@Embeddable
public class BusDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="org_id", insertable=false, updatable=false)
	private String orgId;

	@Column(name="bus_id")
	private String busId;

	public BusDetailPK() {
	}
	public String getOrgId() {
		return this.orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getBusId() {
		return this.busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BusDetailPK)) {
			return false;
		}
		BusDetailPK castOther = (BusDetailPK)other;
		return 
			this.orgId.equals(castOther.orgId)
			&& this.busId.equals(castOther.busId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orgId.hashCode();
		hash = hash * prime + this.busId.hashCode();
		
		return hash;
	}
}