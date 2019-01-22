package br.com.repository;

import br.com.model.Lancamento;

import java.util.List;

public interface IDaoLancamento {
    List<Lancamento> consultarNotas(Long codUser);
}
