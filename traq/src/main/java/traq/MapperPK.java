package traq;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the mapper database table.
 * 
 */
@Embeddable
public class MapperPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="org_id", insertable=false, updatable=false)
	private String orgId;

	@Column(name="bus_id", insertable=false, updatable=false)
	private String busId;

	@Column(name="gps_id", insertable=false, updatable=false)
	private String gpsId;

	public MapperPK() {
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
	public String getGpsId() {
		return this.gpsId;
	}
	public void setGpsId(String gpsId) {
		this.gpsId = gpsId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MapperPK)) {
			return false;
		}
		MapperPK castOther = (MapperPK)other;
		return 
			this.orgId.equals(castOther.orgId)
			&& this.busId.equals(castOther.busId)
			&& this.gpsId.equals(castOther.gpsId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orgId.hashCode();
		hash = hash * prime + this.busId.hashCode();
		hash = hash * prime + this.gpsId.hashCode();
		
		return hash;
	}
}