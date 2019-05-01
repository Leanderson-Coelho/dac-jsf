package br.edu.ifpb.infra.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="validator.nome")
public class ValidatorNome implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String nome = (String) value;
		if(nome == null || nome.length()==0)
			throw new ValidatorException(new FacesMessage("Insira um nome para o dependente"));
	}

}
