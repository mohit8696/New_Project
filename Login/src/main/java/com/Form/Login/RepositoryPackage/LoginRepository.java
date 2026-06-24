package com.Form.Login.RepositoryPackage;

import com.Form.Login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<User, String> {



    @Query("SELECT u FROM User u WHERE " +
            "LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.userId) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.userType)LIKE LOWER(CONCAT('%', :keyword, '%'))"
    )
    public List<User> findByKeyword(String keyword);
}
