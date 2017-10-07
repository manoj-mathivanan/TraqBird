package traq2;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the driver_details database table.
 * 
 */
@Entity
@Table(name="driver_details")
@NamedQuery(name="DriverDetail.findAll", query="SELECT d FROM DriverDetail d")
public class DriverDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DriverDetailPK id;

	private String address;

	private String licence;

	private String mobile;

	private String name;

	//bi-directional many-to-one association to OrgDetail
	@ManyToOne
	@JoinColumn(name="org_id")
	private OrgDetail orgDetail;

	public DriverDetail() {
	}

	public DriverDetailPK getId() {
		return this.id;
	}

	public void setId(DriverDetailPK id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLicence() {
		return this.licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
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

	public OrgDetail getOrgDetail() {
		return this.orgDetail;
	}

	public void setOrgDetail(OrgDetail orgDetail) {
		this.orgDetail = orgDetail;
	}

}