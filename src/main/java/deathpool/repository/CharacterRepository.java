package deathpool.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deathpool.beans.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

}
