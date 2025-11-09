# Marketplace-Multi-Vendedor

Um e-commerce multi-vendedor onde vários vendedores podem cadastrar suas lojas virtuais e produtos. Clientes (compradores) conseguem adicionar produtos de diferentes vendedores em um único carrinho e finalizar pedidos; o sistema divide os itens do pedido por vendedor para que cada vendedor receba apenas as notificações relativas aos seus produtos.

# Perfis de Usuário

Administrador: aprova cadastros de novos vendedores, gerencia relatórios e configurações do marketplace.

Vendedor: cadastra e gerencia sua loja e produtos; só atua sobre recursos da própria loja (identificados por vendedorId).

Cliente (Comprador): navega, adiciona produtos de várias lojas a um único carrinho e finaliza pedidos.

# Regras de Negócio Principais

Somente o Administrador pode aprovar o cadastro de novos Vendedores.

Um Vendedor só pode criar/editar/excluir Produtos vinculados ao seu vendedorId (autorização por recurso).

O Cliente pode adicionar produtos de diferentes vendedores em um único Carrinho.

Ao fechar um Pedido, a aplicação cria uma entidade ItemPedido para cada produto; o Pedido mantém associação com múltiplos ItemPedido (relação many-to-many via entidade associativa). Cada ItemPedido registra o vendedorId do produto para permitir notificações/fluxo de envio por vendedor.
