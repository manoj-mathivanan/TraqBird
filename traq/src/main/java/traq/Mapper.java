package traq;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mapper database table.
 * 
 */
@Entity
@NamedQuery(name="Mapper.findAll", query="SELECT m FROM Mapper m")
public class Mapper implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MapperPK id;

	//bi-directional many-to-one association to BusDetail
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="bus_id", referencedColumnName="bus_id"),
		@JoinColumn(name="org_id", referencedColumnName="org_id")
		})
	private BusDetail busDetail;

	//bi-directional many-to-one association to DriverDetail
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="driver_id", referencedColumnName="driver_id"),
		@JoinColumn(name="org_id", referencedColumnName="org_id")
		})
	private DriverDetail driverDetail;

	//bi-directional many-to-one association to Route
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="org_id", referencedColumnName="org_id"),
		@JoinColumn(name="route_id", referencedColumnName="route_id")
		})
	private Route route;

	public Mapper() {
	}

	public MapperPK getId() {
		return this.id;
	}

	public void setId(MapperPK id) {
		this.id = id;
	}

	public BusDetail getBusDetail() {
		return this.busDetail;
	}

	public void setBusDetail(BusDetail busDetail) {
		this.busDetail = busDetail;
	}

	public DriverDetail getDriverDetail() {
		return this.driverDetail;
	}

	public void setDriverDetail(DriverDetail driverDetail) {
		this.driverDetail = driverDetail;
	}

	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

}