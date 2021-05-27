package apileo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apileo.model.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {

}
