package ru.sfu.waffflezz.rkis6.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sfu.waffflezz.rkis6.models.MyUser;

@Repository
public interface MyUsersRepository extends JpaRepository<MyUser, Integer> {
  Optional<MyUser> findByUsername(String username);
}
