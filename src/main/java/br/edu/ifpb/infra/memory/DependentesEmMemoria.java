package br.edu.ifpb.infra.memory;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.domain.Dependente;

public class DependentesEmMemoria implements br.edu.ifpb.domain.Dependentes {
	
	private final List<Dependente> dependentes = new ArrayList<>();
	
	@Override
	public void novo(Dependente dependente) {
		dependentes.add(dependente);
	}

	@Override
	public List<Dependente> todos() {
		return dependentes;
	}

	@Override
	public void excluir(Dependente dependente) {
		dependentes.remove(dependente);
	}

	@Override
	public void atualizar(Dependente dependente) {
		dependentes.remove(dependente);
		dependentes.add(dependente);
	}

	@Override
	public Dependente localizarDependenteComId(String uuid) {
		for(Dependente d: dependentes) {
			if(d.getUuid().equals(uuid))
				return d;
		}
		return null;
	}

}
