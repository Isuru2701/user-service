package User.repository;


import User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUsername(String username);

    Optional<User> findOneByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
