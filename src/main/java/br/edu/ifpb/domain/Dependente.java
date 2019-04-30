package br.edu.ifpb.domain;

import java.time.LocalDate;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/04/2019, 18:38:44
 */
public class Dependente {

    private String uuid;
    private String nome;
    private LocalDate dataDeNascimento;

    public Dependente() {}

    public Dependente(String uuid, String nome, LocalDate dataDeNascimento) {
		super();
		this.uuid = uuid;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
	}
    
	public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	@Override
	public String toString() {
		return "Dependente [uuid=" + uuid + ", nome=" + nome + ", dataDeNascimento=" + dataDeNascimento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataDeNascimento == null) ? 0 : dataDeNascimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dependente other = (Dependente) obj;
		if (dataDeNascimento == null) {
			if (other.dataDeNascimento != null)
				return false;
		} else if (!dataDeNascimento.equals(other.dataDeNascimento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

}
