package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.TaskList;

public interface TaskListRepository extends JpaRepository<TaskList	, Integer> {

}
