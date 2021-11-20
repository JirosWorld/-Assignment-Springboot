package nl.novi.TechItEasy.repositories;

import nl.novi.TechItEasy.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

//importeer je Model

public interface TelevisionRepository extends JpaRepository<Television,Integer> {
}
