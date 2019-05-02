package br.edu.ifpb.infra.memory;

import br.edu.ifpb.domain.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 25/04/2019, 09:57:57
 */
public class PessoasEmMemoria implements Pessoas {

	private final List<Pessoa> pessoas = new ArrayList<>();

	private static PessoasEmMemoria instance = null;

	public static PessoasEmMemoria getInstance() {
		if (instance == null) {
			synchronized (PessoasEmMemoria.class) {
				if (instance == null) {
					instance = new PessoasEmMemoria();
				}
			}
		}
		return instance;
	}

	public void nova(Pessoa pessoa) {
		pessoas.add(pessoa);
	}

	public List<Pessoa> todas() {
		return pessoas;
	}

	@Override
	public List<Pessoa> buscarPorCPF(String cpf) {
		List<Pessoa> resultadoBusca = new ArrayList<>();
		for (Pessoa p : pessoas) {
//			faz um substring em todos os cpf cadastrados para deixar do mesmo tamanho
//			que o usuário digitou, e compara para ver se é igual;
			String comparar = p.getCpf().valor().substring(0, cpf.length());
			if (comparar.equals(cpf)) {
				resultadoBusca.add(p);
			}
		}
		return resultadoBusca;
	}

	public void excluir(Pessoa pessoa) {
		pessoas.remove(pessoa);
	}

	public void atualizar(Pessoa pessoa) {
		pessoas.remove(pessoa);
		pessoas.add(pessoa);
	}

	@Override
	public List<Dependente> todosOsDepentendes() {
		// TODO
		return null;
	}

	@Override
	public Dependente localizarDependenteComId(String uuid) {
		// TODO: implementar
		return null;
	}

}
