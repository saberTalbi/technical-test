package com.ncq.workflow.entitiesbuilder;

import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.ncq.workflow.entities.Workflow;
import com.ncq.workflow.entities.WorkflowCategory;

public class WorkflowBuilder {

	private Long idWorkflow;
	private @Max(5) @Min(1) Integer status;
	private String name;

	private Set<Workflow> associateWorkflows;
	private String description;
	private @NotEmpty Set<WorkflowCategory> workflowCategories;

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

	public @NotEmpty Set<WorkflowCategory> getWorkflowCategories() {
		return workflowCategories;
	}

	public WorkflowBuilder setWorkflowCategories(Set<WorkflowCategory> workflowCategories) {
		this.workflowCategories = workflowCategories;
		return this;
	}

	public Workflow build() {

		return new Workflow(idWorkflow, name, description, status, associateWorkflows, workflowCategories);
	}
}
