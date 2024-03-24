package AtividadeJava;

import java.io.Serializable;
import java.util.ArrayList;

public class CadastroClientes implements Serializable {

    private ArrayList<Cliente> clienteList;

    public CadastroClientes() {
        this.clienteList = new ArrayList<Cliente>();
    }

    public void adicionarCliente(Cliente cliente) {
        clienteList.add(cliente);
    }

    public String visualizarClientes() {
        StringBuilder sb = new StringBuilder();
        
        for (Cliente cliente : clienteList) {
            sb.append(cliente.toString()).append("\n");
        }
        
        return sb.toString();
    }

    public void atualizarCliente(int index, Cliente cliente) {
        if (index >= 0 && index < clienteList.size()) {
            clienteList.set(index, cliente);
            System.out.println("Cliente atualizado com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void excluirCliente(int index) {
        if (index >= 0 && index < clienteList.size()) {
            clienteList.remove(index);
            System.out.println("Cliente excluído com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public ArrayList<Cliente> getClienteList() {
        return clienteList;
    }
}