package com.ncq.workflow.services;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ncq.workflow.criteria.WorkflowBuilder;
import com.ncq.workflow.entities.Workflow;
import com.ncq.workflow.repositories.WorkflowRepository;

@Service
public class WorkflowService {

	@Autowired
	private WorkflowRepository workflowRepository;

	@Autowired
	private WorkflowCategoryService workflowCategoryService;

	public Page<Workflow> findAllWithOptionalFilter(List<Long> ids, String name, Integer status, int page,
			int sizeOfPage) {
		List<Workflow> result = null;
		if (ids != null && ids.isEmpty()) {
			result = workflowCategoryService.findAllWorkflowCategory().stream().map(element -> element.getWorkflows())
					.collect(Collectors.toList()).stream().flatMap(Set::stream).collect(Collectors.toList());
			if (result == null || result.isEmpty()) {
				return null;
			}
		}

		if (result != null) {
			if (name != null) {
				result = result.stream().filter(workflow -> name.equals(workflow.getName()))
						.collect(Collectors.toList());
			}
			if (status != null) {
				result = result.stream().filter(workflow -> name.equals(workflow.getStatus()))
						.collect(Collectors.toList());
			}
			//return result;
		}
		final Workflow filterModel = new WorkflowBuilder().name(name).status(status).build();
		result = workflowRepository.findAll(Example.of(filterModel), PageRequest.of(page, sizeOfPage)).stream().collect(Collectors.toList());
		return workflowRepository.findAll(Example.of(filterModel), PageRequest.of(page, sizeOfPage));
	}
}
