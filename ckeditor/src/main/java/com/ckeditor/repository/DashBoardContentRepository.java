package com.ckeditor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ckeditor.entity.DashBoardContent;

@Repository
public interface DashBoardContentRepository extends JpaRepository<DashBoardContent,Long> {

	public DashBoardContent findContentById(long id);
}
