package com.ncq.workflow.servicetest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.ncq.workflow.entities.Workflow;
import com.ncq.workflow.entities.WorkflowCategory;
import com.ncq.workflow.entitiesbuilder.WorkFlowCategoryBuilder;
import com.ncq.workflow.entitiesbuilder.WorkflowBuilder;
import com.ncq.workflow.repositories.WorkflowRepository;
import com.ncq.workflow.services.WorkflowCategoryService;
import com.ncq.workflow.services.WorkflowService;
import com.ncq.workflow.services.WorkflowServiceImpl;

public class WorkflowServiceTest {

	private final WorkflowRepository workflowRepository = Mockito.mock(WorkflowRepository.class);

	private final WorkflowCategoryService workflowCategoryService = Mockito.mock(WorkflowCategoryService.class);

	private WorkflowService workflowService;

	@BeforeEach
	void init() {
		this.workflowService = new WorkflowServiceImpl(workflowRepository, workflowCategoryService);
	}

	@Test
	void getWorkflowWithFilters() {
		final List<Long> ids = new ArrayList();
		ids.add(new Long(1));
		ids.add(new Long(2));
		ids.add(new Long(3));

		final String name = "w2";
		final int status = 2;
		final int page = 0;
		final int size = 3;
		final List<WorkflowCategory> workflowCategoryList = createListeOfWorkflowCategory();
		final List<WorkflowCategory> resultWorkflowCategoryList = workflowCategoryList.stream()
				.filter(element -> ids.contains(element.getIdCategory())).collect(Collectors.toList());
		when(workflowCategoryService.findAllWorkflowCategoryByIds(ids)).thenReturn(resultWorkflowCategoryList);

		final Set<Workflow> workflowList = resultWorkflowCategoryList.stream().map(element -> element.getWorkflows())
				.collect(Collectors.toList()).stream().flatMap(Set::stream).collect(Collectors.toSet());
		final Page<Workflow> workflowPage = new PageImpl<Workflow>(
				workflowList.parallelStream().collect(Collectors.toList()), PageRequest.of(page, size),
				workflowList.size());
		Page<Workflow> result = workflowService.findAllWithOptionalFilter(ids, null, null, page, size);

		assert (result != null && result.equals(workflowPage));

		final List<Workflow> expectedList = workflowList.stream().filter(workflow -> workflow.getName().equals(name))
				.collect(Collectors.toList());
		final Page<Workflow> expectedResult = new PageImpl<Workflow>(expectedList, PageRequest.of(page, size),
				expectedList.size());

		result = workflowService.findAllWithOptionalFilter(ids, name, null, page, size);

		assert (result != null && result.equals(expectedResult));
	}

	private List<WorkflowCategory> createListeOfWorkflowCategory() {
		final List<WorkflowCategory> result = new ArrayList();
		for (int i = 0; i < 6; i++) {
			final WorkflowCategory category = new WorkFlowCategoryBuilder().id(new Long(i)).build();
			final Set<Workflow> workflows = new HashSet();
			workflows.add(new WorkflowBuilder().name("w" + i).status(2).build());
			workflows.add(new WorkflowBuilder().name("w" + (i + 1)).status(2).build());
			category.setWorkflows(workflows);
			result.add(category);
		}
		return result;

	}

}
