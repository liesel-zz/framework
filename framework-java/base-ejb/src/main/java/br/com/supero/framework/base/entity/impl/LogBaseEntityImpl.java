package br.com.supero.framework.base.entity.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.supero.framework.base.entity.BaseEntity;


/**
 * Entidade básica com log embutido. 
 **/
@MappedSuperclass
public abstract class LogBaseEntityImpl<ID extends Serializable> extends
		BaseEntityImpl<ID> implements BaseEntity<ID> {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "dt_created", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column(name = "dt_updated", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	@Column(name = "user_created")
	private Long userCreated;
	
	@Column(name = "user_updated")
	private Long userUpdated;

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(Long userCreated) {
		this.userCreated = userCreated;
	}

	public Long getUserUpdated() {
		return userUpdated;
	}

	public void setUserUpdated(Long userUpdated) {
		this.userUpdated = userUpdated;
	}

	@PrePersist
	protected void prePersistLog() {
		if (creationDate == null) {
			creationDate = new Date();
		}
		updatedDate = new Date();
	}

	@PreUpdate
	protected void preUpdatedLog() {
		updatedDate = new Date();
	}

}
