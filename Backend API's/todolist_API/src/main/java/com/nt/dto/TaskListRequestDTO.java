package com.nt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class TaskListRequestDTO {

	private int id;
	private String listname;
	private boolean isListed;
	private String username;
	
}
