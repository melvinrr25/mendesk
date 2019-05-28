package com.mendesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendesk.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
