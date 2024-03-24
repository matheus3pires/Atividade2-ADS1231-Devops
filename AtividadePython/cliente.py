class Cliente:
    def __init__(self, nome, email, telefone):
        self.nome = nome
        self.email = email
        self.telefone = telefone

    def __str__(self):
        return f"\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\nNome: {self.nome}\nEmail: {self.email}\nTelefone: {self.telefone}\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"