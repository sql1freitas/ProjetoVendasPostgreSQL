package Service;
import Domain.Cliente;
import Domain.Produto;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Matheus
 */


public interface IProdutoService {
    public Integer cadastrarProduto(Produto produto) throws SQLException;

    Produto consultarProduto(Long id) throws SQLException;
    void excluirProduto(Produto produto) throws SQLException;

    List<Produto> consultarTodosProdutos() throws SQLException;

    Produto alterarValor(Long id) throws SQLException;
}
