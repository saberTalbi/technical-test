package com.ncq.workflow.controlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncq.workflow.criteria.WorkflowBuilder;
import com.ncq.workflow.entities.Workflow;
import com.ncq.workflow.repositories.WorkflowCategoryRepository;
import com.ncq.workflow.repositories.WorkflowRepository;
import com.ncq.workflow.services.WorkflowService;

@RestController
public class WorkflowController {

	@Autowired
	WorkflowRepository workflowRepository;

	@Autowired
	WorkflowCategoryRepository WorkflowCategoryRepository;

	@Autowired
	WorkflowService work;

	@GetMapping(path = "/getall/{page}/{size}")
	public ResponseEntity<?> getAll(@PathVariable(value = "page") Integer page,
			@PathVariable(value = "size") Integer size,
			@RequestParam(value = "ids", required = false) ArrayList<Long> ids,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "status", required = false) Integer status) {
		List<Workflow> result = new ArrayList();
		WorkflowCategoryRepository.findAll();
		//result = work.findAllWithOptionalFilter(ids, name, status, page, size);
		// workflowRepository.findAllById(ids).forEach(result::add);
		// workflowRepository.findAllByIdInAndNameAndStatus(ids, name, status,
		// PageRequest.of(page, size)).forEach(result::add);
		return ResponseEntity.ok(work.findAllWithOptionalFilter(ids, name, status, page, size));
	}
}
