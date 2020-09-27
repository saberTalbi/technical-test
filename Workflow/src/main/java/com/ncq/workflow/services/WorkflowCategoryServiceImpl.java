package com.ncq.workflow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncq.workflow.entities.WorkflowCategory;
import com.ncq.workflow.repositories.WorkflowCategoryRepository;

@Service
public class WorkflowCategoryServiceImpl implements WorkflowCategoryService {

	private final WorkflowCategoryRepository workflowCategoryRepository;

	@Autowired
	public WorkflowCategoryServiceImpl(WorkflowCategoryRepository workflowCategoryRepository) {
		this.workflowCategoryRepository = workflowCategoryRepository;
	}

	@Override
	public List<WorkflowCategory> findAllWorkflowCategoryByIds(List<Long> ids) {
		return workflowCategoryRepository.findAllById(ids);
	}

	@Override
	public List<WorkflowCategory> findAllWorkflowCategory() {
		return workflowCategoryRepository.findAll();
	}

}
