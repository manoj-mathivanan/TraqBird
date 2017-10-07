package traq2;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the route_details database table.
 * 
 */
@Entity
@Table(name="route_details")
@NamedQuery(name="RouteDetail.findAll", query="SELECT r FROM RouteDetail r")
public class RouteDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long ppid;

	private String pplat;

	private String pplong;

	private String ppname;

	private String time;

	//bi-directional many-to-one association to Route
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="org_id", referencedColumnName="org_id"),
		@JoinColumn(name="route_id", referencedColumnName="route_id")
		})
	private Route route;

	public RouteDetail() {
	}

	public Long getPpid() {
		return this.ppid;
	}

	public void setPpid(Long ppid) {
		this.ppid = ppid;
	}

	public String getPplat() {
		return this.pplat;
	}

	public void setPplat(String pplat) {
		this.pplat = pplat;
	}

	public String getPplong() {
		return this.pplong;
	}

	public void setPplong(String pplong) {
		this.pplong = pplong;
	}

	public String getPpname() {
		return this.ppname;
	}

	public void setPpname(String ppname) {
		this.ppname = ppname;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

}