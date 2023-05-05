package br.com.fintech.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fintech.singleton.ConnectionManager;
import br.com.fintch.dao.CadastroDespesaDAO;
import br.com.fintech.bean.Despesa;
import br.com.fintech.exception.DBException;

public class OracleCadastroDespesaDAO implements CadastroDespesaDAO{
	
	private Connection conexao = null;

	@Override
	public void cadastrar(Despesa despesa) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_DESPESA (CD_DESPESA, NM_CLIENTE, VL_CONTAS, VL_PRODUTOS, VL_LAZER, DT_CAD_DESPESA, VL_TOTAL) VALUES (SQ_T_DESPESA.NEXTVAL,?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, despesa.getNomeCliente());
			stmt.setDouble(2, despesa.getContas());
			stmt.setDouble(3, despesa.getProdutos());
			stmt.setDouble(4, despesa.getLazer());
			java.sql.Date data = new java.sql.Date(despesa.getDataDespesa().getTimeInMillis());
			stmt.setDate(5, data);
			stmt.setDouble(6, despesa.getTotal());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void atualizar(Despesa despesa) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_DESPESA SET NM_CLIENTE = ?, VL_CONTAS = ?, VL_PRODUTOS = ?, VL_LAZER = ? , DT_CAD_DESPESA = ?, VL_TOTAL = ? WHERE CD_DESPESA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, despesa.getNomeCliente());
			stmt.setDouble(2, despesa.getContas());
			stmt.setDouble(3, despesa.getProdutos());
			stmt.setDouble(4, despesa.getLazer());
			java.sql.Date data = new java.sql.Date(despesa.getDataDespesa().getTimeInMillis());
			stmt.setDate(5, data);
			stmt.setDouble(6, despesa.getTotal());
			stmt.setInt(7, despesa.getCodigo());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void remover(int codigo) throws DBException {
			PreparedStatement stmt = null;

			try {
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = "DELETE FROM T_DESPESA WHERE CD_DESPESA = ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, codigo);
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException("Erro ao remover.");
			} finally {
				try {
					stmt.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}

	@Override
	public Despesa buscar(int id) {
		Despesa despesa = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_DESPESA WHERE T_DESPESA.CD_DESPESA = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				String nome = rs.getString("NM_CLIENTE");
				double contas = rs.getDouble("VL_CONTAS");
				double produtos = rs.getDouble("VL_PRODUTOS");
				double lazer = rs.getDouble("VL_LAZER");
				java.sql.Date data = rs.getDate("DT_CAD_DESPESA");
				Calendar dataDespesa = Calendar.getInstance();
				dataDespesa.setTimeInMillis(data.getTime());
				double total = rs.getDouble("VL_TOTAL");
				int codigo = rs.getInt("CD_DESPESA");
				
				despesa = new Despesa(nome,contas,produtos,lazer,dataDespesa,total,codigo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return despesa;
	}

	@Override
	public List<Despesa> listar() {
		List<Despesa> lista = new ArrayList<Despesa>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_DESPESA ");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				String nomeCliente = rs.getString("NM_CLIENTE");
				double contas = rs.getDouble("VL_CONTAS");
				double produtos = rs.getDouble("VL_PRODUTOS");
				double lazer = rs.getDouble("VL_LAZER");
				java.sql.Date data = rs.getDate("DT_CAD_DESPESA");
				Calendar dataDespesa = Calendar.getInstance();
				dataDespesa.setTimeInMillis(data.getTime());
				double total = rs.getDouble("VL_TOTAL");
				int codigo = rs.getInt	("CD_DESPESA");
				
				Despesa despesa = new Despesa(nomeCliente, contas, produtos, lazer,dataDespesa,total,codigo);
				lista.add(despesa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

}
