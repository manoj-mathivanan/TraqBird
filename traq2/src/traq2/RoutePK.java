package traq2;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the route database table.
 * 
 */
@Embeddable
public class RoutePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="org_id", insertable=false, updatable=false)
	private String orgId;

	@Column(name="route_id")
	private String routeId;

	public RoutePK() {
	}
	public String getOrgId() {
		return this.orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getRouteId() {
		return this.routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RoutePK)) {
			return false;
		}
		RoutePK castOther = (RoutePK)other;
		return 
			this.orgId.equals(castOther.orgId)
			&& this.routeId.equals(castOther.routeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orgId.hashCode();
		hash = hash * prime + this.routeId.hashCode();
		
		return hash;
	}
}