package com.trabalho.br.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Prato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrato;

    private int status;

    @NotBlank(message = "Preencha o campo nome")
    private String nome;

    @NotBlank(message = "Preencha o campo descrição")
    private String descricao;

    @NotNull(message = "Preencha o campo preço")
    private Double preco;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Item> itemList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getIdPrato() {
        return idPrato;
    }

    public void setIdPrato(Long idPrato) {
        this.idPrato = idPrato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descrição) {
        this.descricao = descrição;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
