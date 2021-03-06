package com.example.demo.repository;

import com.example.demo.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOptionRepository extends JpaRepository<Option,Long> {
    Option findOptionById(long id);
}
