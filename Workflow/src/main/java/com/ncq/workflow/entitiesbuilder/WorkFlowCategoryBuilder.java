package com.ncq.workflow.entitiesbuilder;

import java.security.Timestamp;
import java.util.Set;

import com.ncq.workflow.entities.Workflow;
import com.ncq.workflow.entities.WorkflowCategory;

public class WorkFlowCategoryBuilder {
	private Long idCategory;

	private String name;
	private String description;
	private Timestamp createAt;
	private Timestamp updateAt;

	private boolean enabled;
	private WorkflowCategory parentCategory;
	private Set<Workflow> workflows;

	public WorkFlowCategoryBuilder id(Long idCategory) {
		this.idCategory = idCategory;
		return this;
	}

	public WorkFlowCategoryBuilder name(String name) {
		this.name = name;
		return this;
	}

	public WorkFlowCategoryBuilder description(String description) {
		this.description = description;
		return this;
	}

	public WorkFlowCategoryBuilder createAt(Timestamp createAt) {
		this.createAt = createAt;
		return this;
	}

	public WorkFlowCategoryBuilder updateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
		return this;
	}

	public WorkFlowCategoryBuilder enabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public WorkFlowCategoryBuilder setParentCategory(WorkflowCategory parentCategory) {
		this.parentCategory = parentCategory;
		return this;
	}

	public WorkFlowCategoryBuilder setWorkflows(Set<Workflow> workflows) {
		this.workflows = workflows;
		return this;
	}

	public WorkflowCategory build() {
		return new WorkflowCategory(idCategory, name, description, createAt, updateAt, enabled, parentCategory,
				workflows);

	}
}
