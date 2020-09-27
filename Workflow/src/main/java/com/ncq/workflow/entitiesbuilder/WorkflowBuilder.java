package com.ncq.workflow.entitiesbuilder;

import java.util.Set;

import com.ncq.workflow.entities.Workflow;

public class WorkflowBuilder {

	private Long idWorkflow;
	private Integer status;
	private String name;
	private Set<Workflow> associateWorkflows;
	private String description;

	public WorkflowBuilder name(String name) {
		this.name = name;
		return this;
	}

	public WorkflowBuilder description(String description) {
		this.description = description;
		return this;
	}

	public WorkflowBuilder status(Integer status) {
		this.status = status;
		return this;
	}

	public WorkflowBuilder id(Long idWorkflow) {
		this.idWorkflow = idWorkflow;
		return this;
	}

	public WorkflowBuilder associateWorkflows(Set<Workflow> associateWorkflows) {
		this.associateWorkflows = associateWorkflows;
		return this;
	}

	public Workflow build() {

		return new Workflow(idWorkflow, name, description, status, associateWorkflows);
	}

}
