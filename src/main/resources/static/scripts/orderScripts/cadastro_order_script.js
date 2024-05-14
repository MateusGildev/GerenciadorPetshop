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

        var clientId = $("#clientsDropdown").val();
        var staffNotes = $("#staffNotes").val();
        var paymentMethod = $("#paymentMethod").val();
        var status = $("#status").val();

        var serviceIds = [];
                $("#serviceFields .form-group select").each(function() {
                    var serviceId = $(this).val();
                    if (serviceId) {
                        serviceIds.push(serviceId);
                    }
                });


        // Obtém os IDs de todos os produtos selecionados
        var productsIds = [];
        $("#productFields .form-group select").each(function() {
            var productId = $(this).val();
            if (productId) {
                productsIds.push(productId);
            }
        });

        if (clientId) { // Verifica se a lista de IDs de produtos não está vazia
            var formData = {
                clientId: clientId,
                tarefasId: serviceIds,
                productsId: productsIds, // Envia todos os IDs dos produtos selecionados
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
            alert("Por favor, selecione um cliente, serviço e pelo menos um produto antes de enviar o pedido.");
        }
    });

    // Lida com a adição de produtos
    $(document).on("click", "#addProductBtn", function() {
            var clonedFields = $("#productFields").children().last().clone();
            clonedFields.find("select").val("");
            clonedFields.find("input").val("");
            $("#productFields").append(clonedFields);
    });

    $(document).on("click", "#addServiceBtn", function() {
                var clonedFields = $("#serviceFields").children().last().clone();
                clonedFields.find("select").val("");
                clonedFields.find("input").val("");
                $("#serviceFields").append(clonedFields);
        });
});
