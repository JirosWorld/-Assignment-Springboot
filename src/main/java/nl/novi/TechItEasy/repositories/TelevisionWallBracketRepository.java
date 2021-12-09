package nl.novi.TechItEasy.repositories;

import nl.novi.TechItEasy.models.TelevisionWallBracketKey;
import nl.novi.TechItEasy.models.TelevisionWallbracket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TelevisionWallBracketRepository extends JpaRepository<TelevisionWallbracket, TelevisionWallBracketKey> {
    Collection<TelevisionWallbracket> findAllByTelevisionId(Long televisionId);
    Collection<TelevisionWallbracket> findAllByWallBracketId(Long wallBracketId);
}
