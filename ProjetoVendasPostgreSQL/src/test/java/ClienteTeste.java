import Domain.Cliente;
import Service.ClienteService;
import Service.IClienteService;
import org.junit.Test;
import org.junit.Assert;

import java.sql.SQLException;

/**
 * @author Matheus
 */


public class ClienteTeste {


    @Test
    public void TesteDosMetodosDaTabelaClientes() throws SQLException {

        IClienteService clienteTest = new ClienteService();
        Cliente cliente = new Cliente(19L,"Gustavo", "teste@ebac.com");

        clienteTest.cadastrarCliente(cliente);
        Assert.assertNotNull(cliente);

        Cliente clienteConsultado = clienteTest.consultarCliente(cliente.getId());
        Assert.assertNotNull(cliente);


        clienteTest.excluirCliente(cliente);
        Assert.assertNotNull(cliente);

        clienteTest.consultarTodosClientes();


        clienteTest.alterarEmail(cliente.getId());



    }





}

