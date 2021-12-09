package nl.novi.TechItEasy.Repositories;

import nl.novi.TechItEasy.Models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

//importeer je Model

public interface TelevisionRepository extends JpaRepository<Television,Integer> {
}
