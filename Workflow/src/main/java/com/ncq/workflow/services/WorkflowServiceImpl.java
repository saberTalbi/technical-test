package com.ncq.workflow.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ncq.workflow.entities.Workflow;
import com.ncq.workflow.entitiesbuilder.WorkflowBuilder;
import com.ncq.workflow.repositories.WorkflowRepository;

@Service
public class WorkflowServiceImpl implements WorkflowService {

	private final WorkflowRepository workflowRepository;

	public WorkflowServiceImpl(WorkflowRepository workflowRepository) {
		this.workflowRepository = workflowRepository;
	}

	@Override
	public Page<Workflow> findAllWithOptionalFilter(List<Long> ids, String name, Integer status, int page,
			int sizeOfPage) {
		Page<Workflow> pageResult;
		List<Workflow> result = null;

		final Workflow filterModel = new WorkflowBuilder().name(name).status(status).build();
		result = workflowRepository.findAll(Example.of(filterModel));
		if (ids == null || ids.isEmpty() || result.isEmpty()) {
			pageResult = new PageImpl<Workflow>(result, PageRequest.of(page, sizeOfPage), result.size());
			return pageResult;
		}

		result = result.stream().filter(workflow -> workflow.getWorkCategories().stream()
				.anyMatch(category -> ids.contains(category.getIdCategory()))).collect(Collectors.toList());

		pageResult = new PageImpl<Workflow>(result, PageRequest.of(page, sizeOfPage), result.size());
		return pageResult;
	}
}
