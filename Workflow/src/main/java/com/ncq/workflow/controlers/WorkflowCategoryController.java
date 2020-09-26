package com.ncq.workflow.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncq.workflow.entities.WorkflowCategory;
import com.ncq.workflow.services.WorkflowCategoryService;

@RestController
@RequestMapping("/api/workflowcategories")
public class WorkflowCategoryController extends RootController {

	@Autowired
	WorkflowCategoryService WorkflowCategoryService;

	@GetMapping(path = "")
	public ResponseEntity<List<WorkflowCategory>> getAll() {
		return ResponseEntity.ok(WorkflowCategoryService.findAllWorkflowCategory());
	}
}
