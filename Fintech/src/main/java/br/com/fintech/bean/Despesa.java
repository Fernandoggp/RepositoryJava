package br.com.fintech.bean;

import java.util.Calendar;

public class Despesa {
	
	private String nomeCliente;
	
	private double contas;
	
	private double produtos;
	
	private double lazer;
	
	private Calendar dataDespesa;
	
	private double total;
	
	private int codigo;

	

	public Despesa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Despesa(String nomeCliente, double contas, double produtos, double lazer, Calendar dataDespesa,
			double total,int codigo) {
		super();
		this.nomeCliente = nomeCliente;
		this.contas = contas;
		this.produtos = produtos;
		this.lazer = lazer;
		this.dataDespesa = dataDespesa;
		this.total = total;
		this.codigo = codigo;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public double getContas() {
		return contas;
	}

	public void setContas(double contas) {
		this.contas = contas;
	}

	public double getProdutos() {
		return produtos;
	}

	public void setProdutos(double produtos) {
		this.produtos = produtos;
	}

	public double getLazer() {
		return lazer;
	}

	public void setLazer(double lazer) {
		this.lazer = lazer;
	}

	public Calendar getDataDespesa() {
		return dataDespesa;
	}

	public void setDataDespesa(Calendar dataDespesa) {
		this.dataDespesa = dataDespesa;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	

}
