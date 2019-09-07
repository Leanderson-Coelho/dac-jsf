package br.edu.ifpb.infra.jsf;

import java.time.LocalDate;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Dependentes;
import br.edu.ifpb.infra.memory.DependentesEmMemoria;

@FacesConverter(value="convert.dependente")
public class DependenteConvert implements Converter{

//	busca a instancia da lista de dependentes
	private Dependentes instance = CDI.current().select(Dependentes.class).get();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
//		verifica se foi selecionado um dependente
		if(value.equals("-- NENHUM DEPENDENTE --")) {
			return null;
		}
		Dependente dep = instance.localizarDependenteComId(value);
		return dep;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
//		verifica se foi selecionado um dependente
		if(value==null) {
			return null;
		}
		Dependente dep = (Dependente) value;
		return dep.getUuid();
	}

}
