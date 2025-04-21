package com.umbandanet.caboclopenabranca.repository;

import com.umbandanet.caboclopenabranca.dto.MaterialPessoaDTO;
import com.umbandanet.caboclopenabranca.model.MaterialPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialPessoasRepository extends JpaRepository<MaterialPessoa, Long> {
    @Query(value =  "SELECT m.item, case when p.nome is null then 'Sem medium' else p.nome end as medium " +
            "FROM db_caboclo.materiais m " +
            "LEFT JOIN db_caboclo.materialpessoa mp ON m.id = mp.material_id " +
            "LEFT JOIN db_caboclo.pessoa p ON mp.pessoa_id = p.id " +
            "ORDER BY m.item ASC ",  nativeQuery = true)
    List<MaterialPessoaDTO> findMaterialPessoas();
}