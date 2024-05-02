$(document).ready(function() {
    carregarOrdens();

    function carregarOrdens() {
        $.ajax({
            type: "GET",
            url: "/order/findAll",
            contentType: "application/json",
            dataType: "json",
            success: function(response) {
                exibirOrdens(response);
            },
            error: function() {
                alert("Erro ao carregar ordens.");
            }
        });
    }

    function exibirOrdens(ordens) {
        var tableBody = $("#orderTableBody");
        tableBody.empty();

        ordens.forEach(function(order) {
            var row = `
                <tr>
                    <td>${order.id}</td>
<<<<<<< HEAD
                    <td>${order.client.id}</td>
=======
>>>>>>> f01ba6ec46c93442c77bceab52f70689f7337b0f
                    <td>${order.client.nome}</td>
                    <td>${order.status}</td>
                    <td>
                        <button class='btn btn-primary btn-info' data-order-id='${order.id}'>Ver info</button>
                        <button class='btn btn-danger btn-delete' data-order-id='${order.id}'>Excluir</button>
                    </td>
                </tr>
            `;
            tableBody.append(row);
        });
    }

    $(document).on("click", ".btn-delete", function() {
        var orderId = $(this).data("order-id");
        excluirOrdem(orderId);
    });

    $(document).on("click", ".btn-info", function() {
        var orderId = $(this).data("order-id");
        var row = $(this).closest("tr");
        var infoRow = `<tr class='info-row'><td colspan='4'><div class='order-info' id='order-info-${orderId}'>Carregando informações...</div></td></tr>`;

        if (row.next().hasClass("info-row")) {
            row.next().remove();
        } else {
            row.after(infoRow);
            carregarDetalhesOrdem(orderId);
        }
    });

    function carregarDetalhesOrdem(orderId) {
        $.ajax({
            type: "GET",
            url: "/order/findOrderById/" + orderId,
            contentType: "application/json",
            dataType: "json",
            success: function(response) {
                exibirDetalhesOrdem(orderId, response);
            },
            error: function() {
                $("#order-info-" + orderId).html("Erro ao carregar informações da ordem.");
            }
        });
    }

   function exibirDetalhesOrdem(orderId, response) {
       var formattedInfo = `
           <p><strong>Cliente:</strong> ${response.client.nome}</p>
           <p><strong>Telefone:</strong> ${response.client.telefone}</p>
           <p><strong>CPF:</strong> ${response.client.cpf}</p>
           <p><strong>Produtos:</strong></p><ul>`;

<<<<<<< HEAD
       if (response.products && response.products.length > 0) {
               response.products.forEach(function(product) {
                   formattedInfo += `<li>${product.name} - ${product.description}</li>`;
               });
           } else {
               formattedInfo += `<li>Nenhum produto encontrado</li>`;
           }

           formattedInfo += `</ul>
               <p><strong>Serviços:</strong></p><ul>`;

           if (response.tarefas && response.tarefas.length > 0) {
               response.tarefas.forEach(function(tarefa) {
                   formattedInfo += `<li>${tarefa.description}</li>`;
               });
           } else {
               formattedInfo += `<li>Nenhuma tarefa encontrada</li>`;
           }
=======
       response.products.forEach(function(product) {
           formattedInfo += `<li>${product.name} - ${product.description}</li>`;
       });

       formattedInfo += `</ul>
           <p><strong>Serviços:</strong></p><ul>`;

       response.tarefas.forEach(function(tarefa) {
           formattedInfo += `<li>${tarefa.description}</li>`;
       });
>>>>>>> f01ba6ec46c93442c77bceab52f70689f7337b0f

       formattedInfo += `</ul>
           <p><strong>Preço Total:</strong> ${response.totalPrice}</p>
           <p><strong>Forma de Pagamento:</strong> ${response.paymentMethod}</p>
           <p><strong>Data:</strong> ${response.orderDate}</p>
           <p><strong>Staff Notes:</strong> ${response.staffNotes}</p>
           <p><strong>Status:</strong> ${response.status}</p>`;

       $("#order-info-" + orderId).html(formattedInfo);
   }


    $("#searchInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#orderTableBody tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });

    function excluirOrdem(orderId) {
        $.ajax({
            type: "DELETE",
            url: "/order/deleteOrderById/" + orderId,
            success: function(response) {
                console.log("Ordem excluída com sucesso!");
                window.location.href = window.location.href;
            },
            error: function() {
                console.error("Erro ao excluir a ordem.");
            }
        });
    }
});

