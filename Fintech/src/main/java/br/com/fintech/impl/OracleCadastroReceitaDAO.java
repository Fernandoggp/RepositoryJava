package br.com.fintech.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fintch.dao.CadastroReceitaDAO;
import br.com.fintech.bean.Despesa;
import br.com.fintech.bean.Receita;
import br.com.fintech.exception.DBException;
import br.com.fintech.singleton.ConnectionManager;

public class OracleCadastroReceitaDAO implements CadastroReceitaDAO{

	private Connection conexao = null;

	@Override
	public void cadastrar(Receita receita) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_RECEITA (CD_RECEITA, NM_CLIENTE, VL_SALARIO, VL_RENDA_PASSIVA, VL_EXTRA, DT_CAD_RECEITA, VL_TOTAL) VALUES (SQ_T_RECEITA.NEXTVAL,?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, receita.getNomeCliente());
			stmt.setDouble(2, receita.getSalario());
			stmt.setDouble(3, receita.getRenda());
			stmt.setDouble(4, receita.getExtra());
			java.sql.Date data = new java.sql.Date(receita.getDataReceita().getTimeInMillis());
			stmt.setDate(5, data);
			stmt.setDouble(6, receita.getTotal());

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
	public void atualizar(Receita receita) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_RECEITA SET NM_CLIENTE = ?, VL_SALARIO = ?, VL_RENDA_PASSIVA = ?, VL_EXTRA = ? , DT_CAD_RECEITA = ?, VL_TOTAL = ? WHERE CD_RECEITA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, receita.getNomeCliente());
			stmt.setDouble(2, receita.getSalario());
			stmt.setDouble(3, receita.getRenda());
			stmt.setDouble(4, receita.getExtra());
			java.sql.Date dataReceita = new java.sql.Date(receita.getDataReceita().getTimeInMillis());
			stmt.setDate(5, dataReceita);
			stmt.setDouble(6, receita.getTotal());
			stmt.setInt(7, receita.getCodigo());

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
				String sql = "DELETE FROM T_RECEITA WHERE CD_RECEITA = ?";
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
	public Receita buscar(int id) {
		Receita receita = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_RECEITA WHERE CD_RECEITA = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				
				String nomeCliente = rs.getString("NM_CLIENTE");
				double salario = rs.getDouble("VL_SALARIO");
				double renda = rs.getDouble("VL_RENDA_PASSIVA");
				double extra = rs.getDouble("VL_EXTRA");
				java.sql.Date data = rs.getDate("DT_CAD_RECEITA");
				Calendar dataReceita = Calendar.getInstance();
				dataReceita.setTimeInMillis(data.getTime());
				double total = rs.getDouble("VL_TOTAL");
				int codigo = rs.getInt("CD_RECEITA");
				
				receita = new Receita(nomeCliente,salario,renda,extra,dataReceita,total,codigo);
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
			return receita;
		}


	@Override
	public List<Receita> listar() {
		List<Receita> lista = new ArrayList<Receita>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_RECEITA ");
			rs = stmt.executeQuery();

				while (rs.next()) {
					String nomeCliente = rs.getString("NM_CLIENTE");
					double salario = rs.getDouble("VL_SALARIO");
					double renda = rs.getDouble("VL_RENDA_PASSIVA");
					double extra = rs.getDouble("VL_EXTRA");
					java.sql.Date data = rs.getDate("DT_CAD_RECEITA");
					Calendar dataReceita = Calendar.getInstance();
					dataReceita.setTimeInMillis(data.getTime());
					double total = rs.getDouble("VL_TOTAL");
					int codigo = rs.getInt("CD_RECEITA");
					
					Receita receita = new Receita(nomeCliente,salario,renda,extra,dataReceita,total,codigo);
				    lista.add(receita);
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
