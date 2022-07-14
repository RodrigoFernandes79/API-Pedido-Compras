package com.rodrigohf.apicompras.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonIgnore // anotação para não serializar os objetos de pedido e nem de produto
	@EmbeddedId // id embutido em um tipo auxiliar (ItemPedidoPK)
	private ItemPedidoPK id = new ItemPedidoPK(); // o id será um objeto do ItemPedidoPK

	private Double desconto;
	private Integer quantidade;
	private Double preco;

	public ItemPedido() {

	}

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {

		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	

	//Inserindo e calculando o subtotal atraves do metodo getSubTotal()
	public double getSubTotal() {

		return (preco-desconto)*quantidade;
	}
	
	@JsonIgnore // para nao fazer a referencia ciclica no endpoint
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}

	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produto produto) {
		 id.setProduto(produto);
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		
		// biblioteca java.text que formata objetos em valores monetário (pt BR formata no padrão brasileiro R$)
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNome());
		builder.append(" , Qtd: ");
		builder.append(getQuantidade());
		builder.append(" , Preço unitário: ");
		builder.append(nf.format(getPreco()));
		builder.append(" , Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
	
	
	
	

}
