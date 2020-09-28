package com.ncq.workflow.entities;

import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class WorkflowCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idCategory;

	private String name;
	private String description;

	@Lob
	private Byte[] logo;

	private Timestamp createAt;
	private Timestamp updateAt;

	private boolean enabled;

	@ManyToOne
	private WorkflowCategory parentCategory;

	public WorkflowCategory(Long idCategory, String name, String description, Byte[] logo, Timestamp createAt,
			Timestamp updateAt, boolean enabled, WorkflowCategory parentCategory) {
		super();
		this.idCategory = idCategory;
		this.name = name;
		this.description = description;
		this.logo = logo;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.enabled = enabled;
		this.parentCategory = parentCategory;
	}

	public WorkflowCategory() {
	}

	public Long getIdCategory() {
		return idCategory;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Byte[] getLogo() {
		return logo;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public WorkflowCategory getParentCategory() {
		return parentCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLogo(Byte[] logo) {
		this.logo = logo;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setParentCategory(WorkflowCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

}
