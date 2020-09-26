package com.ncq.workflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncq.workflow.entities.Workflow;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
	List<Workflow> findAllByStatus(Integer status);
}
