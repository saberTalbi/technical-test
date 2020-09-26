package com.ncq.workflow.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Workflow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idWorkflow;
	private String name;
	private String description;

	@Min(1)
	@Max(5)
	private Integer status;

	@ManyToMany
	private Set<Workflow> associateWorkflows;

	public Workflow() {
	}

	public Workflow(String name, @Min(1) @Max(5) Integer status) {
		this.name = name;
		this.status = status;
	}

	public Workflow(Long idWorkflow, String name, String description, @Min(1) @Max(5) Integer status,
			Set<Workflow> associateWorkflows) {
		this.idWorkflow = idWorkflow;
		this.name = name;
		this.description = description;
		this.status = status;
		this.associateWorkflows = associateWorkflows;
	}

	public Long getIdWorkflow() {
		return idWorkflow;
	}

	public void setIdWorkflow(Long idWorkflow) {
		this.idWorkflow = idWorkflow;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<Workflow> getAssociateWorkflows() {
		return associateWorkflows;
	}

	public void setAssociateWorkflows(Set<Workflow> associateWorkflows) {
		this.associateWorkflows = associateWorkflows;
	}
}