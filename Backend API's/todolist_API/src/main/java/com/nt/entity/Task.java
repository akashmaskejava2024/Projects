package com.nt.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor



@Entity
@Table(name = "task")
public class Task {

	private int id;
	private String name;
	private boolean isDone;
	private String dueDate;

	@ManyToOne
	@JoinColumn(name = "list_id", nullable = false)
	@JsonBackReference
	private TaskList taskList;
}
