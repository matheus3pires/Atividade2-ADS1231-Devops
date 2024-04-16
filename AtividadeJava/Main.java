package AtividadeJava;

//import javax.swing.*;
//import javax.swing.border.TitledBorder;

//import java.awt.*;
//import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CadastroClientes cadastroClientes = new CadastroClientes();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("x-------------------------------------x");
            System.out.println("|Escolha uma opção:                   |");
            System.out.println("|1 - Adicionar cliente                |");
            System.out.println("|2 - Visualizar clientes              |");
            System.out.println("|3 - Atualizar cliente                |");
            System.out.println("|4 - Excluir cliente                  |");
            System.out.println("|5 - Salvar clientes em arquivo       |");
            System.out.println("|0 - Sair                             |");
            System.out.println("x-------------------------------------x");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (opcao) {
                case 0:
                System.out.println("Saindo...");
                return;
                case 1:
                    System.out.println("Digite o nome do cliente:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o email do cliente:");
                    String email = scanner.nextLine();
                    System.out.println("Digite o telefone do cliente:");
                    String telefone = scanner.nextLine();
                    Cliente novoCliente = new Cliente(nome, email, telefone);
                    cadastroClientes.adicionarCliente(novoCliente);
                    System.out.println("Cliente adicionado com sucesso!");
                    break;
                case 2:
                    System.out.println(cadastroClientes.visualizarClientes());
                    break;
                case 3:
                    System.out.println("Digite o índice do cliente a ser atualizado:");
                    int indiceAtualizar = scanner.nextInt();
                    scanner.nextLine(); 
                    if (indiceAtualizar >= 0 && indiceAtualizar < cadastroClientes.getClienteList().size()) {
                        System.out.println("Digite o novo nome do cliente:");
                        nome = scanner.nextLine();
                        System.out.println("Digite o novo email do cliente:");
                        email = scanner.nextLine();
                        System.out.println("Digite o novo telefone do cliente:");
                        telefone = scanner.nextLine();
                        Cliente clienteAtualizado = new Cliente(nome, email, telefone);
                        cadastroClientes.atualizarCliente(indiceAtualizar, clienteAtualizado);
                    } else {
                        System.out.println("Índice inválido. Cliente não encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Digite o índice do cliente a ser excluído:");
                    int indiceExcluir = scanner.nextInt();
                    scanner.nextLine(); 
                    if (indiceExcluir >= 0 && indiceExcluir < cadastroClientes.getClienteList().size()) {
                        cadastroClientes.excluirCliente(indiceExcluir);

                    } else {
                        System.out.println("Índice inválido. Cliente não encontrado.");
                    }
                    break;
                case 5:
                    Path caminho = Paths.get("clientes.txt");
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(caminho.toString(), true));
                        
                        for (Cliente cliente : cadastroClientes.getClienteList()) {
                            writer.write(cliente.toString());
                            writer.newLine();
                        }
                        
                        writer.close();
                        System.out.println("Clientes salvos com sucesso no arquivo clientes.txt!");
                    } catch (IOException ex) {
                        System.out.println("Erro ao salvar clientes no arquivo!");
                    }
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }   
}


// Interface Grafica:

/* 
public class Main extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new Main(); 
    }

    private CadastroClientes cadastroClientes;
    private JTextField nomeTextField;
    private JTextField emailTextField;
    private JTextField telefoneTextField;
    private JButton adicionarButton;
    private JButton visualizarButton;
    private JButton atualizarButton;
    private JButton excluirButton;
    private JButton salvarButton;
    private JTextArea clientesTextArea;

    public Main() {
        super("Cadastro de Clientes");
        cadastroClientes = new CadastroClientes();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel formularioPanel = new JPanel(new GridLayout(7, 4));
        formularioPanel.setBorder(new TitledBorder("Dados do Cliente"));
        formularioPanel.add(new JLabel("Nome:"));
        formularioPanel.add(nomeTextField = new JTextField());
        formularioPanel.add(new JLabel("Email:"));
        formularioPanel.add(emailTextField = new JTextField());
        formularioPanel.add(new JLabel("Telefone:"));
        formularioPanel.add(telefoneTextField = new JTextField());

        JPanel botoesPanel = new JPanel(new FlowLayout());
        adicionarButton = new JButton("Adicionar");
        adicionarButton.addActionListener(this);
        botoesPanel.add(adicionarButton);
        visualizarButton = new JButton("Visualizar");
        visualizarButton.addActionListener(this);
        botoesPanel.add(visualizarButton);
        atualizarButton = new JButton("Atualizar");
        atualizarButton.addActionListener(this);
        botoesPanel.add(atualizarButton);
        excluirButton = new JButton("Excluir");
        excluirButton.addActionListener(this);
        botoesPanel.add(excluirButton);
        salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(this);
        botoesPanel.add(salvarButton);

        clientesTextArea = new JTextArea();
        clientesTextArea.setEditable(false);
        clientesTextArea.setBorder(new TitledBorder("Lista de Clientes"));

        add(formularioPanel, BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);
        add(clientesTextArea, BorderLayout.EAST);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("Adicionar")) {
            String nome = nomeTextField.getText();
            String email = emailTextField.getText();
            String telefone = telefoneTextField.getText();
            Cliente cliente = new Cliente(nome, email, telefone);

            cadastroClientes.adicionarCliente(cliente);

            nomeTextField.setText("");
            emailTextField.setText("");
            telefoneTextField.setText("");

            JOptionPane.showMessageDialog(this, "Cliente adicionado com sucesso!");
        } else if (comando.equals("Visualizar")) {
            clientesTextArea.setText("");
            String clientesStr = cadastroClientes.visualizarClientes();

            clientesTextArea.setText(clientesStr);
        } else if (comando.equals("Atualizar")) {
            String nome = nomeTextField.getText();
            String email = emailTextField.getText();
            String telefone = telefoneTextField.getText();

            Cliente cliente = new Cliente(nome, email, telefone);

            int selectedIndex = -1;
            try {
                selectedIndex = Integer.parseInt(JOptionPane.showInputDialog(this, "Digite o índice do cliente a ser atualizado:"));
                if (selectedIndex >= 0 && selectedIndex < cadastroClientes.getClienteList().size()) {
                    cadastroClientes.atualizarCliente(selectedIndex, cliente);
                    JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Índice inválido. Cliente não encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Índice inválido. Digite um número inteiro.");
            }
        } else if (comando.equals("Excluir")) {
            int selectedIndex = -1;
            try {
                selectedIndex = Integer.parseInt(JOptionPane.showInputDialog(this, "Digite o índice do cliente a ser excluído:"));
                if (selectedIndex >= 0 && selectedIndex < cadastroClientes.getClienteList().size()) {
                    cadastroClientes.excluirCliente(selectedIndex);
                    JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Índice inválido. Cliente não encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Índice inválido. Digite um número inteiro.");
            }
        } else if (comando.equals("Salvar")) {
            Path caminho = Paths.get("C:/Users/20231012000053/Documents/clientes.txt");
            
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(caminho.toString(), true));

                for (Cliente cliente : cadastroClientes.getClienteList()) {
                    byte textoByte[] = cliente.toString().getBytes();
                    writer.write(new String(textoByte));
                    writer.newLine();
                }

                writer.close();
                JOptionPane.showMessageDialog(this, "Clientes salvos com sucesso no arquivo clientes.txt!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar clientes no arquivo!");
            }
        }    
    }
    
}
*/