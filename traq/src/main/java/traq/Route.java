package traq;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the route database table.
 * 
 */
@Entity
@NamedQuery(name="Route.findAll", query="SELECT r FROM Route r")
public class Route implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoutePK id;

	//bi-directional many-to-one association to Mapper
	@OneToMany(mappedBy="route")
	private List<Mapper> mappers;

	//bi-directional many-to-many association to EmpDetail
	@ManyToMany
	@JoinTable(
		name="emp_mapping"
		, joinColumns={
			@JoinColumn(name="org_id", referencedColumnName="org_id"),
			@JoinColumn(name="route_id", referencedColumnName="route_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="emp_id", referencedColumnName="emp_id"),
			@JoinColumn(name="org_id", referencedColumnName="org_id")
			}
		)
	private List<EmpDetail> empDetails;

	//bi-directional many-to-one association to RouteDetail
	@OneToMany(mappedBy="route")
	private List<RouteDetail> routeDetails;

	public Route() {
	}

	public RoutePK getId() {
		return this.id;
	}

	public void setId(RoutePK id) {
		this.id = id;
	}

	public List<Mapper> getMappers() {
		return this.mappers;
	}

	public void setMappers(List<Mapper> mappers) {
		this.mappers = mappers;
	}

	public Mapper addMapper(Mapper mapper) {
		getMappers().add(mapper);
		mapper.setRoute(this);

		return mapper;
	}

	public Mapper removeMapper(Mapper mapper) {
		getMappers().remove(mapper);
		mapper.setRoute(null);

		return mapper;
	}

	public List<EmpDetail> getEmpDetails() {
		return this.empDetails;
	}

	public void setEmpDetails(List<EmpDetail> empDetails) {
		this.empDetails = empDetails;
	}

	public List<RouteDetail> getRouteDetails() {
		return this.routeDetails;
	}

	public void setRouteDetails(List<RouteDetail> routeDetails) {
		this.routeDetails = routeDetails;
	}

	public RouteDetail addRouteDetail(RouteDetail routeDetail) {
		getRouteDetails().add(routeDetail);
		routeDetail.setRoute(this);

		return routeDetail;
	}

	public RouteDetail removeRouteDetail(RouteDetail routeDetail) {
		getRouteDetails().remove(routeDetail);
		routeDetail.setRoute(null);

		return routeDetail;
	}

}