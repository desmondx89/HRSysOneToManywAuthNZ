package com.ntuc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "semp_details")
public class SempDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 45, nullable = false)
	private String name;

	@Column(length = 45, nullable = false)
	private String value;

	@ManyToOne
	@JoinColumn(name = "empid")
	private Semps semp;

	public SempDetails() {
	}

	public SempDetails(Integer id, String name, String value, Semps semp) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.semp = semp;
	}

	public SempDetails(String name, String value, Semps semp) {
		this.name = name;
		this.value = value;
		this.semp = semp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Semps getSemp() {
		return semp;
	}

	public void setSemp(Semps semp) {
		this.semp = semp;
	}

	@Override
	public String toString() {
		return name + "=" + value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((semp == null) ? 0 : semp.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		SempDetails other = (SempDetails) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (semp == null) {
			if (other.semp != null)
				return false;
		} else if (!semp.equals(other.semp))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
