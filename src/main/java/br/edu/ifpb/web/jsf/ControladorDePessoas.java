package br.edu.ifpb.web.jsf;

import java.io.Serializable;
import java.util.List;
//import javax.faces.bean.RequestScoped;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Pessoa;
import br.edu.ifpb.domain.Pessoas;
import br.edu.ifpb.infra.memory.PessoasEmMemoria;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 25/04/2019, 10:23:24
 */
//@RequestScoped
//@ManagedBean
//@RequestScoped
@SessionScoped
@Named
public class ControladorDePessoas implements Serializable {

    private Pessoa pessoa = new Pessoa();

//    @Inject
    private Pessoas service = new PessoasEmMemoria();

    //actionListeners
//    public void exemplo(ActionEvent ev) {
//        
//    }
    public String salvar() {
        this.service.nova(pessoa);
        this.pessoa = new Pessoa();
//        return null; //        return "index.xhtml";
//        return "list";
        return "listPessoa.xhtml?faces-redirect=true";
    }

    public String atualizar() {
        this.service.atualizar(pessoa);
        this.pessoa = new Pessoa();
        return null;
    }

    public String excluir(Pessoa pessoa) {
        this.service.excluir(pessoa);
        return null;
    }

    public String editar(Pessoa pessoa) {
        this.pessoa = pessoa;
        return null;
    }

    public List<Pessoa> getTodasAsPessoas() {
        return this.service.todas();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
