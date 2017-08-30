package org.accion.repository;

import java.util.List;

import org.accion.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository

public interface UserRepository extends JpaRepository<UserDetails,String> {
	public UserDetails findByFullName(@Param("fullName")String fullName);
	  
	  public List<UserDetails> findAll();
	  public UserDetails save(UserDetails user);
	  public void delete(UserDetails user);

}
