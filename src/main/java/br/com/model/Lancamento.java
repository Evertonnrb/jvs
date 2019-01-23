package br.com.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "lancamento")
@Table(name = "lancamento", schema = "public")
public class Lancamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numeroNotaFiscal;
    private String empresaOrigem;
    private String empresaDestino;
    @ManyToOne(optional = false)
    @org.hibernate.annotations.ForeignKey(name = "usuario_fk")
    private Usuario usuario;
    private String auditada;
    private String[] transportadora;
    @Temporal(TemporalType.DATE)
    private Date dataVecimento;
    private String cep;
    private String lagradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String unidade;
    private String ibge;
    private String gia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroNotaFiscal() {
        return numeroNotaFiscal;
    }

    public void setNumeroNotaFiscal(String numeroNotaFiscal) {
        this.numeroNotaFiscal = numeroNotaFiscal;
    }

    public String getEmpresaOrigem() {
        return empresaOrigem;
    }

    public void setEmpresaOrigem(String empresaOrigem) {
        this.empresaOrigem = empresaOrigem;
    }

    public String getEmpresaDestino() {
        return empresaDestino;
    }

    public void setEmpresaDestino(String empresaDestino) {
        this.empresaDestino = empresaDestino;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAuditada() {
        return auditada;
    }

    public void setAuditada(String auditada) {
        this.auditada = auditada;
    }

    public String[] getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(String[] transportadora) {
        this.transportadora = transportadora;
    }

    public Date getDataVecimento() {
        return dataVecimento;
    }

    public void setDataVecimento(Date dataVecimento) {
        this.dataVecimento = dataVecimento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLagradouro() {
        return lagradouro;
    }

    public void setLagradouro(String lagradouro) {
        this.lagradouro = lagradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lancamento that = (Lancamento) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(numeroNotaFiscal, that.numeroNotaFiscal) &&
                Objects.equals(empresaOrigem, that.empresaOrigem) &&
                Objects.equals(empresaDestino, that.empresaDestino) &&
                Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
