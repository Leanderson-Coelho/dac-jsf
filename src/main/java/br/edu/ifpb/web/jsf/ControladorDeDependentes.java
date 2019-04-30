package br.edu.ifpb.web.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.infra.memory.DependentesEmMemoria;

@SessionScoped
@Named
public class ControladorDeDependentes implements Serializable{
	private DependentesEmMemoria service = new DependentesEmMemoria();
	
	private Dependente dependente = new Dependente();
	
	public String salvar() {
		if(service.localizarDependenteComId(dependente.getUuid())==null) {
			service.novo(dependente);
			dependente = new Dependente();
		}else {
			service.atualizar(dependente);
		}
		return null;
	}
	
	public List<Dependente> listarDependentes() {
		return service.todos();
	}
	
	public String excluir(Dependente dependente) {
		service.excluir(dependente);
		return null;
	}
	
	public String selecionar(Dependente dependente) {
		this.dependente = dependente;
		return null;
	}

	public Dependente getDependente() {
		return dependente;
	}

	public void setDependente(Dependente dependente) {
		this.dependente = dependente;
	}
}
