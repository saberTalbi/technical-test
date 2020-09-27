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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ncq.workflow.entities.WorkflowCategory;
import com.ncq.workflow.services.WorkflowCategoryService;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkflowCategoryControllerTest {
	List<WorkflowCategory> resultList = new ArrayList();
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ApplicationContext applicationContext;
	@MockBean
	private WorkflowCategoryService workflowService;

	@BeforeEach
	void printApplicationContext() {
		Arrays.stream(applicationContext.getBeanDefinitionNames())
				.map(name -> applicationContext.getBean(name).getClass().getName()).sorted()
				.forEach(System.out::println);
	}

	@Test
	public void getAllWorkflowCategories() throws Exception {
		when(workflowService.findAllWorkflowCategory()).thenReturn(resultList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/workflowcategories")).andExpect(status().isOk());
	}

}
