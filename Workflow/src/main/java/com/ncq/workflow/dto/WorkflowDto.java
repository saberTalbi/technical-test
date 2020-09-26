package com.ncq.workflow.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class WorkflowDto {

	Long idWorkflow;
	String name;
	String description;

	@Min(1)
	@Max(5)
	Integer status;

}
