package com.ncq.workflow.controlers;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncq.workflow.entities.Workflow;
import com.ncq.workflow.services.WorkflowService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/workflows")
public class WorkflowController {

	WorkflowService workflowService;

	public WorkflowController(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	@GetMapping(path = "/{page}/{size}")
	@ApiOperation(value = "get all workflow with optional filter", notes = "this api return all workflow with given filters")
	public ResponseEntity<Page<Workflow>> getAll(@PathVariable(value = "page") Integer page,
			@PathVariable(value = "size") Integer size,
			@RequestParam(value = "ids", required = false) ArrayList<Long> ids,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "status", required = false) Integer status) {

		return ResponseEntity.ok(workflowService.findAllWithOptionalFilter(ids, name, status, page, size));
	}
}
