package br.com.supero.framework.security.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.supero.framework.base.entity.impl.LogBaseEntityImpl;

@Entity
//schema = "SECURITY",
@Table(name = "PLAN")
@NamedQueries({
		@NamedQuery(name = "PLAN.FIND_ALL", query = "SELECT s FROM Plan s "),
		@NamedQuery(name = "PLAN.LIKE_BY_NAME", query = "SELECT s FROM Plan s WHERE UPPER(s.name) like UPPER(:name)"),
		@NamedQuery(name = "PLAN.COUNT_LIKE_BY_NAME", query = "SELECT count(*) FROM Plan S WHERE UPPER(S.name) like UPPER(:name)") })
// webservice
@XmlRootElement
public class Plan extends LogBaseEntityImpl<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* PK */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Size(min = 1, max = 128)
	@Column(name = "name")
	private String name;

	@NotNull
	@Size(min = 1, max = 256)
	@Column(name = "description")
	private String description;

	@NotNull
	@Column(name = "days_for_expire")
	private Long daysForExpire;

	@NotNull
	@Column(name = "value")
	private BigDecimal value;

	@Override
	@Transient
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDaysForExpire() {
		return daysForExpire;
	}

	public void setDaysForExpire(Long daysForExpire) {
		this.daysForExpire = daysForExpire;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Plan other = (Plan) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("\n            id=" + id);
		str.append("\n          name=" + name);
		str.append("\n   description=" + description);
		str.append("\n daysForExpire=" + daysForExpire);
		str.append("\n         value=" + value);

		return str.toString();
	}

}
