package com.ncq.workflow.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncq.workflow.entities.WorkflowCategory;
import com.ncq.workflow.repositories.WorkflowCategoryRepository;
import com.ncq.workflow.repositories.WorkflowRepository;

@RestController
public class WorkflowCategoryController {

	@Autowired
	WorkflowRepository workflowRepository;
	
	@Autowired
	WorkflowCategoryRepository WorkflowCategoryRepository;
	
	@GetMapping(path = "/workflowcategories")
	public ResponseEntity getAll() {
		return ResponseEntity.ok(WorkflowCategoryRepository.findAll());
	}
}
