package br.com.alex.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alex.info.model.Cartucho;

public interface CartuchosRepo extends JpaRepository<Cartucho, Long>{

}
