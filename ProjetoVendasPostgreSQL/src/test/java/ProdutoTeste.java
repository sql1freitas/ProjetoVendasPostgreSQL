import Domain.Cliente;
import Domain.Produto;
import Service.ClienteService;
import Service.IClienteService;
import Service.IProdutoService;
import Service.ProdutoService;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @author Matheus
 */

public class ProdutoTeste {

    //Cada teste foi executado de maneira unitária

    @Test
    public void TesteDosMetodosDaTabelaProdutos() throws SQLException {

        IProdutoService produtoTest = new ProdutoService();

        IClienteService clienteTest = new ClienteService();
        Produto produto = new Produto(1L,"Placa de vídeo", 1200.00);

        produtoTest.cadastrarProduto(produto);
        Assert.assertNotNull(produto);

        Produto produtoConsultado = produtoTest.consultarProduto(produto.getId());
        Assert.assertNotNull(produto);


        produtoTest.excluirProduto(produto);
        Assert.assertNotNull(produto);

        produtoTest.consultarTodosProdutos();


        produtoTest.alterarValor(produto.getId());



    }
}
