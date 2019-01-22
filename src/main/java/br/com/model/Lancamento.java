package br.com.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "lancamento")
@Table(name = "lancamento",schema = "public")
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
