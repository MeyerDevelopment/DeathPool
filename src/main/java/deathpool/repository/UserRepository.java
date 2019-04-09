package deathpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deathpool.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
