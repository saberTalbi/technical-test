package com.ncq.workflow.services;

import java.util.List;

import com.ncq.workflow.entities.WorkflowCategory;

public interface WorkflowCategoryService {
	public List<WorkflowCategory> findAllWorkflowCategoryByIds(List<Long> ids);

	public List<WorkflowCategory> findAllWorkflowCategory();
}
