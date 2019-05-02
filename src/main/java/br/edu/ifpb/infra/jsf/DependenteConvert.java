package br.edu.ifpb.infra.jsf;

import java.time.LocalDate;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.ifpb.domain.Dependente;

@FacesConverter(value="convert.dependente")
public class DependenteConvert implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Dependente dep = new Dependente(value,LocalDate.now());	
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
