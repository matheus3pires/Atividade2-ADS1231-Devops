from tkinter import *
from tkinter import messagebox
from tkinter import simpledialog
import os
from cliente import Cliente
from cadastro_clientes import CadastroClientes

class Main:
    def __init__(self):
        self.cadastro_clientes = CadastroClientes()

    def adicionar_cliente(self):
        nome = input("Digite o nome do cliente: ")
        email = input("Digite o email do cliente: ")
        telefone = input("Digite o telefone do cliente: ")
        cliente = Cliente(nome, email, telefone)
        self.cadastro_clientes.adicionar_cliente(cliente)
        print("Cliente adicionado com sucesso!")

    def visualizar_clientes(self):
        print(self.cadastro_clientes.visualizar_clientes())

    def atualizar_cliente(self):
        nome = input("Digite o nome do cliente: ")
        email = input("Digite o email do cliente: ")
        telefone = input("Digite o telefone do cliente: ")
        cliente = Cliente(nome, email, telefone)
        try:
            index = int(input("Digite o índice do cliente a ser atualizado: "))
            if 0 <= index < len(self.cadastro_clientes.get_cliente_list()):
                self.cadastro_clientes.atualizar_cliente(index, cliente)
        except ValueError:
            print("Índice inválido. Digite um número inteiro.")

    def excluir_cliente(self):
        try:
            index = int(input("Digite o índice do cliente a ser excluído: "))
            if 0 <= index < len(self.cadastro_clientes.get_cliente_list()):
                self.cadastro_clientes.excluir_cliente(index)
        except ValueError:
            print("Índice inválido. Digite um número inteiro.")

    def salvar_clientes(self):
        file_path = "clientes.txt"
        try:
            with open(file_path, "a") as file:
                for cliente in self.cadastro_clientes.get_cliente_list():
                    file.write(str(cliente))
                    file.write("\n")
            print("Clientes salvos com sucesso no arquivo clientes.txt!")
        except IOError:
            print("Erro ao salvar clientes no arquivo!")


if __name__ == "__main__":
    main = Main()
    while True:
        print("x-------------------------------------x");
        print("|Escolha uma opção:                   |");
        print("|1 - Adicionar cliente                |");
        print("|2 - Visualizar clientes              |");
        print("|3 - Atualizar cliente                |");
        print("|4 - Excluir cliente                  |");
        print("|5 - Salvar clientes em arquivo       |");
        print("|0 - Sair                             |");
        print("x-------------------------------------x");

        opcao = input("Opção: ")

        if opcao == "0":
            print("Saindo...")
            break
        elif opcao == "1":
            main.adicionar_cliente()
        elif opcao == "2":
            main.visualizar_clientes()
        elif opcao == "3":
            main.atualizar_cliente()
        elif opcao == "4":
            main.excluir_cliente()
        elif opcao == "5":
            main.salvar_clientes()
        else:
            print("Opção inválida!")

# Interface Grafica:

"""
class Main:
    def __init__(self):
        self.root = Tk()
        self.root.title("Cadastro de Clientes")

        self.cadastro_clientes = CadastroClientes()

        formulario_frame = Frame(self.root)
        formulario_frame.pack()

        Label(formulario_frame, text="Nome:").grid(row=0, column=0)
        self.nome_text_field = Entry(formulario_frame)
        self.nome_text_field.grid(row=0, column=1)

        Label(formulario_frame, text="Email:").grid(row=1, column=0)
        self.email_text_field = Entry(formulario_frame)
        self.email_text_field.grid(row=1, column=1)

        Label(formulario_frame, text="Telefone:").grid(row=2, column=0)
        self.telefone_text_field = Entry(formulario_frame)
        self.telefone_text_field.grid(row=2, column=1)

        botoes_frame = Frame(self.root)
        botoes_frame.pack()

        self.adicionar_button = Button(botoes_frame, text="Adicionar", command=self.adicionar_cliente)
        self.adicionar_button.grid(row=0, column=0)

        self.visualizar_button = Button(botoes_frame, text="Visualizar", command=self.visualizar_clientes)
        self.visualizar_button.grid(row=0, column=1)

        self.atualizar_button = Button(botoes_frame, text="Atualizar", command=self.atualizar_cliente)
        self.atualizar_button.grid(row=0, column=2)

        self.excluir_button = Button(botoes_frame, text="Excluir", command=self.excluir_cliente)
        self.excluir_button.grid(row=0, column=3)

        self.salvar_button = Button(botoes_frame, text="Salvar", command=self.salvar_clientes)
        self.salvar_button.grid(row=0, column=4)

        self.clientes_text_area = Text(self.root, width=40, height=20)
        self.clientes_text_area.pack()

        self.root.mainloop()

    def adicionar_cliente(self):
        nome = self.nome_text_field.get()
        email = self.email_text_field.get()
        telefone = self.telefone_text_field.get()
        cliente = Cliente(nome, email, telefone)
        self.cadastro_clientes.adicionar_cliente(cliente)
        messagebox.showinfo("Sucesso", "Cliente adicionado com sucesso!")

    def visualizar_clientes(self):
        self.clientes_text_area.delete('1.0', END)
        self.clientes_text_area.insert(END, self.cadastro_clientes.visualizar_clientes())

    def atualizar_cliente(self):
        nome = self.nome_text_field.get()
        email = self.email_text_field.get()
        telefone = self.telefone_text_field.get()
        cliente = Cliente(nome, email, telefone)
        try:
            index = simpledialog.askinteger("Índice", "Digite o índice do cliente a ser atualizado:")
            if index is not None and 0 <= index < len(self.cadastro_clientes.get_cliente_list()):
                self.cadastro_clientes.atualizar_cliente(index, cliente)
        except ValueError:
            messagebox.showerror("Erro", "Índice inválido. Digite um número inteiro.")

    def excluir_cliente(self):
        try:
            index = simpledialog.askinteger("Índice", "Digite o índice do cliente a ser excluído:")
            if index is not None and 0 <= index < len(self.cadastro_clientes.get_cliente_list()):
                self.cadastro_clientes.excluir_cliente(index)
        except ValueError:
            messagebox.showerror("Erro", "Índice inválido. Digite um número inteiro.")

    def salvar_clientes(self):
        file_path = os.path.join(os.path.expanduser("~"), "Documents", "clientes.txt")
        try:
            with open(file_path, "a") as file:
                for cliente in self.cadastro_clientes.get_cliente_list():
                    file.write(str(cliente))
                    file.write("\n")
            messagebox.showinfo("Sucesso", "Clientes salvos com sucesso no arquivo clientes.txt!")
        except IOError:
            messagebox.showerror("Erro", "Erro ao salvar clientes no arquivo!")

if __name__ == "__main__":
    Main()

"""   