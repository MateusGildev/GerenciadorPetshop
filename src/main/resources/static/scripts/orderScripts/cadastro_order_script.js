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
                    var optionText = service.description;
                    dropdown.append($('<option></option>').val(service.id).text(optionText));
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
                    var optionText = client.nome;
                    dropdown.append($('<option></option>').val(client.id).text(optionText));
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
                    var optionText = product.name;
                    productDropdown.append($('<option></option>').val(product.id).text(optionText));
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

        var clientId = $("#clientsDropdown").val();
        console.log("ID do cliente selecionado:", clientId);

        var productId = $("#productDropdown").val();
        console.log("ID do produto selecionado:", productId);

        var serviceId = $("#serviceDropdown").val();
        console.log("ID do servico selecionado:", serviceId);

        var clientId = clientId;
        var serviceId = serviceId;
        var productId = productId;
        var staffNotes = $("#staffNotes").val();
        var paymentMethod = $("#paymentMethod").val();
        var status = $("#status").val();


        if (clientId && serviceId && productId) {

            var formData = {
                clientId: clientId,
                tarefasId: [serviceId], // Envie o ID do serviço selecionado como lista
                productsId: [productId], // Envie o ID do produto selecionado como lista
                staffNotes: staffNotes,
                paymentMethod: paymentMethod,
                status: status
            };

            $.ajax({
                type: "POST",
                url: "/order/createOrder/" + clientId,
                data: JSON.stringify(formData),
                contentType: "application/json",
                success: function(response){
                    alert("Pedido criado com sucesso!");
                    window.location.href = "listagem_order.html";
                },
                error: function(){
                    alert("Erro ao criar o pedido. Tente novamente.");
                }
            });
        } else {
            alert("Por favor, selecione um cliente, serviço e produto antes de enviar o pedido.");
        }
    });
});

