package traq;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	//bi-directional many-to-one association to Mapper
	@OneToMany(mappedBy="driverDetail")
	private List<Mapper> mappers;

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

	public List<Mapper> getMappers() {
		return this.mappers;
	}

	public void setMappers(List<Mapper> mappers) {
		this.mappers = mappers;
	}

	public Mapper addMapper(Mapper mapper) {
		getMappers().add(mapper);
		mapper.setDriverDetail(this);

		return mapper;
	}

	public Mapper removeMapper(Mapper mapper) {
		getMappers().remove(mapper);
		mapper.setDriverDetail(null);

		return mapper;
	}

}