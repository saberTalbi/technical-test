package com.ncq.workflow.apitest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ncq.workflow.entities.Workflow;
import com.ncq.workflow.entitiesbuilder.WorkflowBuilder;
import com.ncq.workflow.services.WorkflowService;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkFlowControllerTest {

	private final int page = 0;
	private final int pageSize = 2;
	List<Workflow> resultList = new ArrayList();
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ApplicationContext applicationContext;
	@MockBean
	private WorkflowService workflowService;

	@BeforeEach
	void printApplicationContext() {
		Arrays.stream(applicationContext.getBeanDefinitionNames())
				.map(name -> applicationContext.getBean(name).getClass().getName()).sorted()
				.forEach(System.out::println);
	}

	@Test
	public void findAllWorkflowByOptionalParametre() throws Exception {
		final Page<Workflow> pageResult = getPageOfWorkflows();
		when(workflowService.findAllWithOptionalFilter(null, null, null, page, pageSize)).thenReturn(pageResult);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/workflows/0/3")).andExpect(status().isOk()).andReturn();
		mockMvc.perform(MockMvcRequestBuilders.get("/api/workflows/0/3?ids=1,2,3,4&?name=workflow2"))
				.andExpect(status().isOk()).andReturn();

	}

	public Page<Workflow> getPageOfWorkflows() {
		for (int i = 0; i < pageSize * 2; i++) {
			final Workflow workflow = new WorkflowBuilder().id(new Long(i)).name("name" + i).build();
			resultList.add(workflow);
		}

		final Page<Workflow> pageResult = new PageImpl<Workflow>(resultList, PageRequest.of(page, pageSize),
				resultList.size());
		return pageResult;
	}

}
