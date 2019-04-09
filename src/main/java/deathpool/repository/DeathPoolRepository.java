package deathpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deathpool.beans.DeathPool;

@Repository
public interface DeathPoolRepository extends JpaRepository<DeathPool, Long> {

}
