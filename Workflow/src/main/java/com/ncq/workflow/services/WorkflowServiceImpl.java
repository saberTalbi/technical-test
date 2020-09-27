package com.ncq.workflow.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

	private final WorkflowCategoryService workflowCategoryService;

	@Autowired
	public WorkflowServiceImpl(WorkflowRepository workflowRepository, WorkflowCategoryService workflowCategoryService) {
		this.workflowRepository = workflowRepository;
		this.workflowCategoryService = workflowCategoryService;
	}

	@Override
	public Page<Workflow> findAllWithOptionalFilter(List<Long> ids, String name, Integer status, int page,
			int sizeOfPage) {
		Page<Workflow> pageResult;
		Set<Workflow> result = null;
		if (ids != null && !ids.isEmpty()) {
			result = workflowCategoryService.findAllWorkflowCategoryByIds(ids).stream()
					.map(element -> element.getWorkflows()).collect(Collectors.toList()).stream().flatMap(Set::stream)
					.collect(Collectors.toSet());
			if (result == null || result.isEmpty()) {
				return null;
			}
		}

		if (result != null) {
			if (name != null) {
				result = result.stream().filter(workflow -> name.equals(workflow.getName()))
						.collect(Collectors.toSet());
			}

			if (status != null) {
				result = result.stream().filter(workflow -> name.equals(workflow.getStatus()))
						.collect(Collectors.toSet());
			}

			pageResult = new PageImpl<Workflow>(result.parallelStream().collect(Collectors.toList()),
					PageRequest.of(page, sizeOfPage), result.size());
			return pageResult;
		}

		final Workflow filterModel = new WorkflowBuilder().name(name).status(status).build();

		return workflowRepository.findAll(Example.of(filterModel), PageRequest.of(page, sizeOfPage));
	}
}
