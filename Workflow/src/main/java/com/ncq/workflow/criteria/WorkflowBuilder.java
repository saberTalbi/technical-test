package com.ncq.workflow.criteria;

import com.ncq.workflow.entities.Workflow;

public class WorkflowBuilder {

	private Integer status;
	private String name;

	public WorkflowBuilder() {
	}

	public WorkflowBuilder name(String name) {
		this.name = name;
		return this;
	}

	public WorkflowBuilder status(Integer status) {
		this.status = status;
		return this;
	}

	public Workflow build() {

		return new Workflow(name, status);
	}

}
