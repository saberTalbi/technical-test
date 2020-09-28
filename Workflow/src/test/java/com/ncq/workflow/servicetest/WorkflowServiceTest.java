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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.ncq.workflow.entities.Workflow;
import com.ncq.workflow.entities.WorkflowCategory;
import com.ncq.workflow.entitiesbuilder.WorkFlowCategoryBuilder;
import com.ncq.workflow.entitiesbuilder.WorkflowBuilder;
import com.ncq.workflow.repositories.WorkflowRepository;
import com.ncq.workflow.services.WorkflowService;
import com.ncq.workflow.services.WorkflowServiceImpl;

public class WorkflowServiceTest {
	final String name = "w2";
	final int status = 2;

	private final WorkflowRepository workflowRepository = Mockito.mock(WorkflowRepository.class);

	private WorkflowService workflowService;

	@BeforeEach
	void init() {
		this.workflowService = new WorkflowServiceImpl(workflowRepository);
	}

	@Test
	void getWorkflowWithFilters_returnPageOfAllElementsIfNoFilters() {

		final int page = 0;
		final int size = 3;
		final List<Workflow> workflowList = createListeOfWorkflow();
		final Page<Workflow> expectedPageResult = new PageImpl(workflowList, PageRequest.of(page, size),
				workflowList.size());
		when(this.workflowRepository.findAll(Mockito.any(Example.class))).thenReturn(workflowList);

		final Page<Workflow> pageResult = workflowService.findAllWithOptionalFilter(null, null, null, page, size);

		assert (pageResult != null && pageResult.equals(expectedPageResult));
	}

	@Test
	void getWorkflowWithFilters_returnPageOfFiltredWorkFlow() {
		final Example<Workflow> filterModel = Example.of(new WorkflowBuilder().name(name).build());
		final List<Long> ids = new ArrayList();
		ids.add(new Long(1));
		ids.add(new Long(2));
		ids.add(new Long(3));
		final int page = 0;
		final int size = 3;

		final List<Workflow> workflowList = createListeOfWorkflow().stream()
				.filter(workflow -> this.name.equals(workflow.getName())).collect(Collectors.toList());
		when(this.workflowRepository.findAll(Mockito.any(Example.class))).thenReturn(workflowList);

		List<Workflow> workflowListClone = new ArrayList<Workflow>();
		workflowListClone.addAll(workflowList);
		workflowListClone = workflowListClone.stream().filter(workflow -> workflow.getWorkCategories().stream()
				.anyMatch(category -> ids.contains(category.getIdCategory()))).collect(Collectors.toList());

		final Page<Workflow> expectedPageResult = new PageImpl(workflowListClone, PageRequest.of(page, size),
				workflowListClone.size());

		final Page<Workflow> pageResult = workflowService.findAllWithOptionalFilter(ids, name, null, page, size);

		assert (pageResult != null && pageResult.equals(expectedPageResult));

	}

	private List<Workflow> createListeOfWorkflow() {
		final List<Workflow> result = new ArrayList();

		for (int i = 0; i < 6; i++) {
			final Set<WorkflowCategory> categories = new HashSet();
			final WorkflowCategory category = new WorkFlowCategoryBuilder().id(new Long(i)).build();
			categories.add(category);
			final WorkflowCategory category1 = new WorkFlowCategoryBuilder().id(new Long(i + 1)).build();
			categories.add(category1);

			final Set<Workflow> workflows = new HashSet();
			result.add(new WorkflowBuilder().name(name).status(status).setWorkflowCategories(categories).build());
		}
		return result;

	}

}
