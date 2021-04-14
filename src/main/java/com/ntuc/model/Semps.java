package com.ntuc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="semps")
public class Semps {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empid;
	
	@Column(length = 150, nullable = false, unique=true)
	private String name;
	private float price;
	
	@ManyToOne
	@JoinColumn(name= "dept_id")
	private Sdept sdept;
	
	@OneToMany(mappedBy ="semp", cascade = CascadeType.ALL)
	private List<SempDetails> details = new ArrayList<>();


	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	
	public Sdept getSdept() {
		return sdept;
	}

	public void setSdept(Sdept sdept) {
		this.sdept = sdept;
	}

	
	
	public List<SempDetails> getDetails() {
		return details;
	}

	public void setDetails(List<SempDetails> details) {
		this.details = details;
	}

	
	
	public void setDetails(Integer id,String name, String value) {
		this.details.add(new SempDetails(id,name,value,this)  );
		
	}

	public void addDetails(String name, String value) {
		this.details.add(new SempDetails(name,value,this));
	}

	
	
	public Semps(Integer id, String name, float price, Sdept sdept, List<SempDetails> details) {
		this.empid = id;
		this.name = name;
		this.price = price;
		this.sdept = sdept;
		this.details = details;
	}

	public Semps(String name, float price, Sdept sdept, List<SempDetails> details) {
		this.name = name;
		this.price = price;
		this.sdept = sdept;
		this.details = details;
	}
	
	public Semps() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((empid == null) ? 0 : empid.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((sdept == null) ? 0 : sdept.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Semps other = (Semps) obj;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (empid == null) {
			if (other.empid != null)
				return false;
		} else if (!empid.equals(other.empid))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (sdept == null) {
			if (other.sdept != null)
				return false;
		} else if (!sdept.equals(other.sdept))
			return false;
		return true;
	}

	
	
	
}
