$(document).ready(function(){

    // Função para buscar e preencher os dropdowns de Serviço e Produto
    function carregarDropdowns() {
        // Busca todos os serviços do servidor
        $.ajax({
            type: 'GET',
            url: '/service/allServices',
            success: function(services) {
                const dropdown = $("#serviceDropdown");
                dropdown.empty();
                dropdown.append($('<option></option>').val('').text('Selecione um Serviço'));

                services.forEach(function(service) {
                    dropdown.append($('<option></option>').val(service.id).text(service.description));
                });
            },
            error: function(error) {
                console.error('Erro ao obter os tipos de serviços:', error);
            }
        });

        // Busca todos os clientes do servidor
        $.ajax({
            type: 'GET',
            url: '/client/clients',
            success: function(clients) {
                const dropdown = $("#clientsDropdown");
                dropdown.empty();
                dropdown.append($('<option></option>').val('').text('Selecione um Cliente'));

                clients.forEach(function(client) {
                    dropdown.append($('<option></option>').val(client.id).text(client.nome));
                });
            },
            error: function(error) {
                console.error('Erro ao obter os clientes do banco de dados:', error);
            }
        });

        // Busca todos os produtos do servidor
        $.ajax({
            type: "GET",
            url: "/product/allProducts",
            success: function(products) {
                var productDropdown = $("#productDropdown");
                productDropdown.empty();
                productDropdown.append($('<option></option>').val('').text('Selecione um Produto'));

                products.forEach(function(product) {
                    productDropdown.append($('<option></option>').val(product.id).text(product.name));
                });
            },
            error: function() {
                console.error("Erro ao buscar produtos.");
            }
        });
    }

    // Chama a função para carregar os dropdowns ao carregar a página
    carregarDropdowns();

    // Lida com o envio do formulário
    $("#orderForm").submit(function(event){
        event.preventDefault(); // Impede o envio padrão do formulário

        // Obtém os valores selecionados nos dropdowns e outros campos do formulário
        var clientId = $("#clientsDropdown").val();
        var tarefaNames = $("#serviceDropdown").val(); // Obtém os valores dos serviços selecionados
        var productNames = $("#productDropdown").val(); // Obtém os valores dos produtos selecionados
        var staffNotes = $("#staffNotes").val();
        var paymentMethod = $("#paymentMethod").val();
        var status = $("#status").val();

        // Cria o objeto de dados a ser enviado
        var formData = {
            clientId: clientId,
            tarefaNames: tarefaNames, // Envie os valores dos serviços selecionados como lista
            productNames: productNames, // Envie os valores dos produtos selecionados como lista
            staffNotes: staffNotes,
            paymentMethod: paymentMethod,
            status: status
        };

        // Envia a requisição POST para o backend
        $.ajax({
            type: "POST",
            url: "/order/createOrder/" + clientId,
            data: JSON.stringify(formData), // Converte o objeto para JSON
            contentType: "application/json", // Define o tipo de conteúdo como JSON
            success: function(response){
                alert("Pedido criado com sucesso!");
                // Redireciona para a página de listagem de pedidos
                window.location.href = "listagem_order.html";
            },
            error: function(){
                alert("Erro ao criar o pedido. Tente novamente.");
            }
        });
    });
});
