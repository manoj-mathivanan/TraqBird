package traq2;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bus_details database table.
 * 
 */
@Entity
@Table(name="bus_details")
@NamedQuery(name="BusDetail.findAll", query="SELECT b FROM BusDetail b")
public class BusDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BusDetailPK id;

	private Integer available;

	private Integer capacity;

	private String make;

	@Column(name="reg_no")
	private String regNo;

	//bi-directional many-to-one association to OrgDetail
	@ManyToOne
	@JoinColumn(name="org_id")
	private OrgDetail orgDetail;

	public BusDetail() {
	}

	public BusDetailPK getId() {
		return this.id;
	}

	public void setId(BusDetailPK id) {
		this.id = id;
	}

	public Integer getAvailable() {
		return this.available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getRegNo() {
		return this.regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public OrgDetail getOrgDetail() {
		return this.orgDetail;
	}

	public void setOrgDetail(OrgDetail orgDetail) {
		this.orgDetail = orgDetail;
	}

}