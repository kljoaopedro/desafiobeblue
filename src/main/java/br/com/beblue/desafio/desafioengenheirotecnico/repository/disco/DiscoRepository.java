package br.com.beblue.desafio.desafioengenheirotecnico.repository.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, String> {

}
