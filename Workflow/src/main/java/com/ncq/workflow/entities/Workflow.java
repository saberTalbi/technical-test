package com.ncq.workflow.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@ManyToMany
	@NotEmpty
	private Set<WorkflowCategory> workCategories;

	public Workflow() {
	}

	public Workflow(Long idWorkflow, String name, String description, @Min(1) @Max(5) Integer status,
			Set<Workflow> associateWorkflows, @NotEmpty Set<WorkflowCategory> workCategories) {
		this.idWorkflow = idWorkflow;
		this.name = name;
		this.description = description;
		this.status = status;
		this.associateWorkflows = associateWorkflows;
		this.workCategories = workCategories;
	}

	public Long getIdWorkflow() {
		return idWorkflow;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Integer getStatus() {
		return status;
	}

	public Set<Workflow> getAssociateWorkflows() {
		return associateWorkflows;
	}

	@JsonIgnore
	public Set<WorkflowCategory> getWorkCategories() {
		return workCategories;
	}

	public void setIdWorkflow(Long idWorkflow) {
		this.idWorkflow = idWorkflow;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setAssociateWorkflows(Set<Workflow> associateWorkflows) {
		this.associateWorkflows = associateWorkflows;
	}

	public void setWorkCategories(Set<WorkflowCategory> workCategories) {
		this.workCategories = workCategories;
	}

}