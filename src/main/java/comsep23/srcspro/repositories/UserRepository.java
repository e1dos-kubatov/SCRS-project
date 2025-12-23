package comsep23.srcspro.repositories;



import comsep23.srcspro.entity.User;
import comsep23.srcspro.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
