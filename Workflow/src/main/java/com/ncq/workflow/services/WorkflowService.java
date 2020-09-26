package com.ncq.workflow.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ncq.workflow.entities.Workflow;

public interface WorkflowService {
	public Page<Workflow> findAllWithOptionalFilter(List<Long> ids, String name, Integer status, int page,
			int sizeOfPage);

}
