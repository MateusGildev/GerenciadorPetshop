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
            event.preventDefault();


            var clientId = $("#clientId").val();
            var totalPrice = $("#totalPrice").val();
            var orderDate = $("#orderDate").val();
            var staffNotes = $("#staffNotes").val();
            var paymentMethod = $("#paymentMethod").val();
            var status = $("#status").val();

            var tarefaIds = parseInt($("#serviceDropdown").val());
            var productIds = parseInt($("#productDropdown").val());

            var orderData = {
                clientId: clientId,
                tarefaIds: tarefaIds,
                productIds: productIds,
                totalPrice: totalPrice,
                orderDate: orderDate,
                staffNotes: staffNotes,
                paymentMethod: paymentMethod,
                status: status
            };

            // Cria a ordem após buscar serviços e produtos
            $.ajax({
                type: "POST",
                url: "/order/createOrder/" + clientId,
                data: JSON.stringify(orderData),
                contentType: "application/json",
                success: function(response){
                    alert("Pedido criado com sucesso!");
                    window.location.href = "listagem_order.html";
                },
                error: function(){
                    alert("Erro ao criar o pedido. Tente novamente.");
                }
            });
        });
    });