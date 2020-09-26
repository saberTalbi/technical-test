package com.ncq.workflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncq.workflow.entities.WorkflowCategory;

@Repository
public interface WorkflowCategoryRepository extends JpaRepository<WorkflowCategory, Long> {
}
