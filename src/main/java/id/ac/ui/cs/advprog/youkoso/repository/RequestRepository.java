package id.ac.ui.cs.advprog.youkoso.repository;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{

}