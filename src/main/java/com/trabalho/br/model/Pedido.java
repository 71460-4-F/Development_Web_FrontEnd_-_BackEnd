package com.trabalho.br.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataPedido = new java.sql.Date(System.currentTimeMillis());

    private Double total;

    private int pendente;
    
    
    public Pedido() {
    	
    }

    public Pedido(Long idPedido, Cliente cliente, Date dataPedido, Double total, int pendente, List<Item> itemList) {
		super();
		this.idPedido = idPedido;
		this.cliente = cliente;
		this.dataPedido = dataPedido;
		this.total = total;
		this.pendente = pendente;
		this.itemList = itemList;
	}

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Item> itemList;

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getPendente() {
        return pendente;
    }

    public void setPendente(int pendente) {
        this.pendente = pendente;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
    
    public double arredondaTotal(double num) {
		int decimalPlace = 2;
		BigDecimal bd = new BigDecimal(num);
		bd = bd.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);
		num = bd.doubleValue();
		return num;
	}
}
