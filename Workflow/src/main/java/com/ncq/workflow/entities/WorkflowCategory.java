package com.ncq.workflow.entities;

import java.security.Timestamp;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
public class WorkflowCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idCategory;

	private String name;
	private String description;

	private Timestamp createAt;
	private Timestamp updateAt;

	private boolean enabled;

	@ManyToOne
	private WorkflowCategory parentCategory;
	
	@ManyToMany(mappedBy = "workflowCategories")
	private Set<Workflow> workflows;

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
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

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public WorkflowCategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(WorkflowCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Set<Workflow> getWorkflows() {
		return workflows;
	}

	public void setWorkflows(Set<Workflow> workflows) {
		this.workflows = workflows;
	}
	
}
