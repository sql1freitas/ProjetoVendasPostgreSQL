package Service;

import Domain.Cliente;
import Domain.Produto;
import Jbdc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matheus
 */

public class ProdutoService implements IProdutoService{

    //Métodos de manipulação de dados da tabela produtos.
    @Override
    public Integer cadastrarProduto(Produto produto) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO produtos (PRODUTOID, NOME, VALOR) VALUES(?,?,?)";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, produto.getId());
            stm.setString(2, produto.getNome());
            stm.setDouble(3, produto.getValor());
            stm.executeUpdate();


        }catch (Exception e){
            throw e;

        } finally {
            if (stm != null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }

        }
        return 1;
    }

    @Override
    public Produto consultarProduto(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto produto = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM produtos WHERE produtoid = ?";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, id);
            rs = stm.executeQuery();
            if (rs.next()){
                produto = new Produto(rs.getLong("produtoid"),rs.getString("nome"),rs.getDouble("valor"));
            }
            return produto;

        }catch (Exception e){
            throw e;

        } finally {
            if (stm != null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }

        }

    }

    @Override
    public void excluirProduto(Produto produto) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;


        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM produtos WHERE produtoid = ?" ;
            stm = connection.prepareStatement(sql);
            stm.setLong(1, produto.getId());
            stm.executeUpdate();


        }catch (Exception e){
            throw e;

        } finally {
            if (stm != null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }

        }

    }

    @Override
    public List<Produto> consultarTodosProdutos() throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto produto = null;
        List<Produto> produtoList = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM produtos";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                produtoList.add(
                        new Produto(rs.getLong("produtoid"), rs.getString("nome"), rs.getDouble("valor"))
                );

            }
            System.out.println(produtoList);
            return produtoList;

        }catch(Exception e){
            throw e;

        } finally{
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }

        }


    }

    @Override
    public Produto alterarValor(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto produto = null;

        try {

            Double novoValor = 1500.00;

            connection = ConnectionFactory.getConnection();
            String sql = "UPDATE produtos SET valor = ? WHERE produtoid = ?";
            stm = connection.prepareStatement(sql);
            stm.setDouble(1, novoValor);
            stm.setLong(2, id);
            rs = stm.executeQuery();
            if (rs.next()){
                if (rs.next()){
                    produto = new Produto(rs.getLong("produtoid"),rs.getString("nome"),rs.getDouble("valor"));
                }
            }
            return produto;

        }catch (Exception e){
            throw e;

        } finally {
            if (stm != null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }

        }
    }
}
