package br.com.fintech.bean;

import java.util.Calendar;

public class Receita {
	
	private String nomeCliente;
	
	private double salario;
	
	private double renda;
	
	private double extra;
	
	private Calendar dataReceita;
	
	private double total;
	
	private int codigo;

	public Receita() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Receita(String nomeCliente, double salario, double renda, double extra, Calendar dataReceita,
			double total,int codigo) {
		super();
		this.nomeCliente = nomeCliente;
		this.salario = salario;
		this.renda = renda;
		this.extra = extra;
		this.dataReceita = dataReceita;
		this.total = total;
		this.codigo = codigo;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getRenda() {
		return renda;
	}

	public void setRenda(double renda) {
		this.renda = renda;
	}

	public double getExtra() {
		return extra;
	}

	public void setExtra(double extra) {
		this.extra = extra;
	}

	public Calendar getDataReceita() {
		return dataReceita;
	}

	public void setDataReceita(Calendar dataReceita) {
		this.dataReceita = dataReceita;
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
