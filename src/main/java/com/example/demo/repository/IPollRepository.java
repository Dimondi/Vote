package com.example.demo.repository;

import com.example.demo.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPollRepository extends JpaRepository<Poll,Long> {
    Poll findPollById(long id);
}
