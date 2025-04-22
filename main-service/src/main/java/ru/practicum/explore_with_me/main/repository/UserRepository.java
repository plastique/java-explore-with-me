package ru.practicum.explore_with_me.main.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explore_with_me.main.dto.admin.user.AdminUserDto;
import ru.practicum.explore_with_me.main.model.User;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    List<AdminUserDto> findAllByIdIn(Collection<Long> ids, Pageable pageable);

}
