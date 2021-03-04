package com.example.demo.repository;


import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IUserRepository extends JpaRepository<User,Long> {

    List<User> findByFirstname(String name);

    User findByUsername(String username);

    List<User> findByIdGreaterThan(Long valueOf);

    @Query("from User where firstname=?1 order by lastname")
    List<User> findByFirstnameSorted(String name);

}
