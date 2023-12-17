package Service;

import Domain.Cliente;
import Jbdc.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Matheus
 */


public class ClienteService implements IClienteService{

//Métodos de manipulação de dados da tabela clientes.
    @Override
    public Integer cadastrarCliente(Cliente cliente) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO CLIENTES (CLIENTEID, NOME, EMAIL) VALUES(?,?,?)";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, cliente.getId());
            stm.setString(2, cliente.getNome());
            stm.setString(3, cliente.getEmail());
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
    public void excluirCliente(Cliente cliente) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;


        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM clientes WHERE clienteid = ?" ;
            stm = connection.prepareStatement(sql);
            stm.setLong(1, cliente.getId());
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
    public List<Cliente> consultarTodosClientes() throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> clienteList = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM clientes";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                clienteList.add(
                        new Cliente(rs.getLong("clienteid"), rs.getString("nome"), rs.getString("email"))
                );

            }
            System.out.println(clienteList);
            return clienteList;

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
    public Cliente alterarEmail(Long id) throws SQLException {

        Scanner input = new Scanner(System.in);
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try {

            String novoEmail = "email-modificado@ebac.com";

            connection = ConnectionFactory.getConnection();
            String sql = "UPDATE clientes SET nome = ? WHERE clienteid = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, novoEmail);
            stm.setLong(2, id);
            rs = stm.executeQuery();
            if (rs.next()){
                if (rs.next()){
                    cliente = new Cliente(rs.getLong("clienteid"),rs.getString("nome"),rs.getString("email"));
                }
            }
            return cliente;

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
    public Cliente consultarCliente(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM clientes WHERE clienteid = ?";
            stm = connection.prepareStatement(sql);
            stm.setLong(1, id);
            rs = stm.executeQuery();
            if (rs.next()){
                cliente = new Cliente(rs.getLong("clienteid"),rs.getString("nome"),rs.getString("email"));
            }
            return cliente;

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
