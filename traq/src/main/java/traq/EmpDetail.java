package traq;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the emp_details database table.
 * 
 */
@Entity
@Table(name="emp_details")
@NamedQuery(name="EmpDetail.findAll", query="SELECT e FROM EmpDetail e")
public class EmpDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmpDetailPK id;

	private String mobile;

	private String name;

	//bi-directional many-to-many association to Route
	@ManyToMany(mappedBy="empDetails")
	private List<Route> routes;

	public EmpDetail() {
	}

	public EmpDetailPK getId() {
		return this.id;
	}

	public void setId(EmpDetailPK id) {
		this.id = id;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Route> getRoutes() {
		return this.routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

}