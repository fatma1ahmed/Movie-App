package com.fatma.movie_app.repository;

import com.fatma.movie_app.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
   User findByUserName(String userName);
}
