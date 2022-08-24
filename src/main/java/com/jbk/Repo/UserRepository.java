package com.jbk.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbk.Model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
