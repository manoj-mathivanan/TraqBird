package traq2;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the org_details database table.
 * 
 */
@Entity
@Table(name="org_details")
@NamedQuery(name="OrgDetail.findAll", query="SELECT o FROM OrgDetail o")
public class OrgDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="org_id")
	private String orgId;

	private String address;

	private String city;

	private String country;

	private String email;

	private String name;

	@Column(name="org_name")
	private String orgName;

	private String phone;

	private String pincode;

	private String state;

	//bi-directional many-to-one association to BusDetail
	@OneToMany(mappedBy="orgDetail")
	private List<BusDetail> busDetails;

	//bi-directional many-to-one association to DriverDetail
	@OneToMany(mappedBy="orgDetail")
	private List<DriverDetail> driverDetails;

	//bi-directional many-to-one association to EmpDetail
	@OneToMany(mappedBy="orgDetail")
	private List<EmpDetail> empDetails;

	//bi-directional many-to-one association to Route
	@OneToMany(mappedBy="orgDetail")
	private List<Route> routes;

	public OrgDetail() {
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPincode() {
		return this.pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<BusDetail> getBusDetails() {
		return this.busDetails;
	}

	public void setBusDetails(List<BusDetail> busDetails) {
		this.busDetails = busDetails;
	}

	public BusDetail addBusDetail(BusDetail busDetail) {
		getBusDetails().add(busDetail);
		busDetail.setOrgDetail(this);

		return busDetail;
	}

	public BusDetail removeBusDetail(BusDetail busDetail) {
		getBusDetails().remove(busDetail);
		busDetail.setOrgDetail(null);

		return busDetail;
	}

	public List<DriverDetail> getDriverDetails() {
		return this.driverDetails;
	}

	public void setDriverDetails(List<DriverDetail> driverDetails) {
		this.driverDetails = driverDetails;
	}

	public DriverDetail addDriverDetail(DriverDetail driverDetail) {
		getDriverDetails().add(driverDetail);
		driverDetail.setOrgDetail(this);

		return driverDetail;
	}

	public DriverDetail removeDriverDetail(DriverDetail driverDetail) {
		getDriverDetails().remove(driverDetail);
		driverDetail.setOrgDetail(null);

		return driverDetail;
	}

	public List<EmpDetail> getEmpDetails() {
		return this.empDetails;
	}

	public void setEmpDetails(List<EmpDetail> empDetails) {
		this.empDetails = empDetails;
	}

	public EmpDetail addEmpDetail(EmpDetail empDetail) {
		getEmpDetails().add(empDetail);
		empDetail.setOrgDetail(this);

		return empDetail;
	}

	public EmpDetail removeEmpDetail(EmpDetail empDetail) {
		getEmpDetails().remove(empDetail);
		empDetail.setOrgDetail(null);

		return empDetail;
	}

	public List<Route> getRoutes() {
		return this.routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public Route addRoute(Route route) {
		getRoutes().add(route);
		route.setOrgDetail(this);

		return route;
	}

	public Route removeRoute(Route route) {
		getRoutes().remove(route);
		route.setOrgDetail(null);

		return route;
	}

}