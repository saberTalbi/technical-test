package com.ncq.workflow.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ncq.workflow.entities.Workflow;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, Long>{
	List<Workflow> findAllByStatus(Integer status);
}
