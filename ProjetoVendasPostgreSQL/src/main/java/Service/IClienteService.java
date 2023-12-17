package Service;

import Domain.Cliente;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Matheus
 */

public interface IClienteService {


    public Integer cadastrarCliente(Cliente cliente) throws SQLException;

    Cliente consultarCliente(Long id) throws SQLException;
    void excluirCliente(Cliente cliente) throws SQLException;

    List<Cliente> consultarTodosClientes() throws SQLException;

    Cliente alterarEmail(Long id) throws SQLException;
}
