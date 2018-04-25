package webapplication.repository;

import org.springframework.data.repository.CrudRepository;

import webapplication.model.DoctorsDetail;

public interface DoctorsRepository extends CrudRepository<DoctorsDetail, Long> {

}
