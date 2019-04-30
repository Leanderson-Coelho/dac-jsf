package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.List;

public interface Dependentes extends Serializable{
	public void novo(Dependente dependente);

    public List<Dependente> todos() ;

    public void excluir(Dependente dependente);

    public void atualizar(Dependente dependente);
    
    public Dependente localizarDependenteComId(String uuid);
}
