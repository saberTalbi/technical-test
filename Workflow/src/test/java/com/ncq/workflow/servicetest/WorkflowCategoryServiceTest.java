package com.ncq.workflow.servicetest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.ncq.workflow.entities.WorkflowCategory;
import com.ncq.workflow.entitiesbuilder.WorkFlowCategoryBuilder;
import com.ncq.workflow.repositories.WorkflowCategoryRepository;
import com.ncq.workflow.services.WorkflowCategoryService;
import com.ncq.workflow.services.WorkflowCategoryServiceImpl;

public class WorkflowCategoryServiceTest {

	private final WorkflowCategoryRepository workflowCategoryRepository = Mockito
			.mock(WorkflowCategoryRepository.class);

	private WorkflowCategoryService workflowCategoryService;

	@BeforeEach
	public void init() {
		this.workflowCategoryService = new WorkflowCategoryServiceImpl(workflowCategoryRepository);
	}

	@Test
	public void getWorkflowCategories() {
		final List<WorkflowCategory> workflowCategoryList = createListeOfWorkFlowCategory();
		when(workflowCategoryRepository.findAll()).thenReturn(workflowCategoryList);

		List<WorkflowCategory> result = workflowCategoryService.findAllWorkflowCategory();
		assert (result != null && result.containsAll(workflowCategoryList) && workflowCategoryList.containsAll(result));

		final List<Long> ids = new ArrayList();
		ids.add(new Long(1));
		ids.add(new Long(2));
		ids.add(new Long(3));
		final List<WorkflowCategory> workflowCategoryByIds = result.stream()
				.filter(element -> ids.contains(element.getIdCategory())).collect(Collectors.toList());
		when(workflowCategoryRepository.findAllById(ids)).thenReturn(workflowCategoryByIds);
		result = workflowCategoryService.findAllWorkflowCategoryByIds(ids);
		assert (result.size() == 3 && result.stream().allMatch(element -> ids.contains(element.getIdCategory())));
	}

	private List<WorkflowCategory> createListeOfWorkFlowCategory() {

		final List<WorkflowCategory> workflowCategoryList = new ArrayList();
		for (int i = 0; i < 6; i++) {
			workflowCategoryList.add(new WorkFlowCategoryBuilder().id(new Long(i)).build());
		}
		return workflowCategoryList;
	}
}
