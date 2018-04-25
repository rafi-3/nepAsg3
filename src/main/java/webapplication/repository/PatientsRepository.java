package webapplication.repository;

import org.springframework.data.repository.CrudRepository;

import webapplication.model.PatientsDetail;

public interface PatientsRepository extends CrudRepository<PatientsDetail, Long> {


}
