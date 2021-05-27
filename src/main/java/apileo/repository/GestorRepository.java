package apileo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apileo.model.Gestor;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Long> {

	Optional<Gestor> findByEmail(String email);
}
