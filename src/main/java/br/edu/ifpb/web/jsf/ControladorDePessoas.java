package br.edu.ifpb.web.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//import javax.faces.bean.RequestScoped;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

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
    
//    valor informado para a busca por cpf
    private String valorBusca = new String();
    
//    Guarda as pessoas que tem o cpf informado na busca
    private List<Pessoa> resultadoBuscaPorCPF = new ArrayList<>();

    //actionListeners
//    public void exemplo(ActionEvent ev) {
//        
//    }
    public String salvar() {
//    	verifica se o cpf não foi cadastrado e nesse caso o cadastra
    	if(service.buscarPorCPF(pessoa.getCpf().valor())==null) {
    		this.service.nova(pessoa);
            this.pessoa = new Pessoa();
    	}else {
//    		atualiza o cadastro caso o cpf já exista
    		atualizar();
    	}
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
        return "cadastroPessoa?faces-redirect=true";
    }

    public List<Pessoa> getTodasAsPessoas() {
        return this.service.todas();
    }
    
    public void buscaCPF() {
    	resultadoBuscaPorCPF = service.buscarPorCPF(valorBusca);
    	valorBusca = "";
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

	public String getValorBusca() {
		return valorBusca;
	}

	public void setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
	}

	public List<Pessoa> getResultadoBuscaPorCPF() {
		return resultadoBuscaPorCPF;
	}

	public void setResultadoBuscaPorCPF(List<Pessoa> resultadoBuscaPorCPF) {
		this.resultadoBuscaPorCPF = resultadoBuscaPorCPF;
	}
}
