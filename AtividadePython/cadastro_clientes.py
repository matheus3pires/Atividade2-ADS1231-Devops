class CadastroClientes:
    def __init__(self):
        self.cliente_list = []

    def adicionar_cliente(self, cliente):
        self.cliente_list.append(cliente)

    def visualizar_clientes(self):
        return '\n'.join(map(str, self.cliente_list))

    def atualizar_cliente(self, index, cliente):
        if 0 <= index < len(self.cliente_list):
            self.cliente_list[index] = cliente
            print("Cliente atualizado com sucesso.")
        else:
            print("Índice inválido.")

    def excluir_cliente(self, index):
        if 0 <= index < len(self.cliente_list):
            del self.cliente_list[index]
            print("Cliente excluído com sucesso.")
        else:
            print("Índice inválido.")

    def get_cliente_list(self):
        return self.cliente_list