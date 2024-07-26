package br.com.projeto.ecommerce.repository;

import br.com.projeto.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
