package md.orange.app.repository;


import java.util.Optional;
import md.orange.app.model.entity.Role;
import md.orange.app.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByRole(ERole name);
}