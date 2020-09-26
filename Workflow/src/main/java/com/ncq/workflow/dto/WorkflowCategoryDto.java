package com.ncq.workflow.dto;

import java.security.Timestamp;

public class WorkflowCategoryDto {

	Long idCategory;

	String name;
	String description;

	Timestamp createAt;
	Timestamp updateAt;

	boolean enabled;

}
