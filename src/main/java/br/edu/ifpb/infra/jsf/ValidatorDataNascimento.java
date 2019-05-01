package br.edu.ifpb.infra.jsf;

import java.time.LocalDate;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="validator.data")
public class ValidatorDataNascimento implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		LocalDate data = (LocalDate) value;
		if(data == null || data.isAfter(LocalDate.now()))
			throw new ValidatorException(new FacesMessage("Data de nascimento inválida"));
		
	}

}
