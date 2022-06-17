package com.tencoding.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Movie {
	@Id
	@Column(nullable = false, length = 30)
	private String title;
	@Column(columnDefinition = "TEXT")
	private String image;
	@Column(columnDefinition = "DATE")
	private String releaseDate;
	private float starScore;
	
//	@OneToMany
//	private Role role;
}
