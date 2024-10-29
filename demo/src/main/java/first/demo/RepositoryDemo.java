package first.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryDemo extends JpaRepository<EntityDemo, Long> {
}
