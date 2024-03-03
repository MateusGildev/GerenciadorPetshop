$(document).ready(function(){
    function carregarClientes() {
        $.ajax({
            type: "GET",
            url: "/client/clients",
            success: function(response){
                var tableBody = $("#clientesTableBody");

                $.each(response, function(index, client){
                    var row = "<tr>" +
                        "<td>" + client.id + "</td>" +
                        "<td>" + client.nome + "</td>" +
                        "<td>" + client.cpf + "</td>" +
                        "<td>" + client.nomeAnimal + "</td>" +
                        "<td>" + client.tipoAnimal + "</td>" +
                        "<td>" + client.endereco + "</td>" +
                        "<td>" + client.telefone + "</td>" +
                        "<td>" +
                        "<button class='btn btn-primary btn-edit' data-client-id='" + client.id + "'>Editar</button>" +
                        "<button class='btn btn-danger btn-delete' data-client-id='" + client.id + "'>Excluir</button>" +
                        "</td>" +
                        "</tr>";

                    tableBody.append(row);
                });

                //botão "Editar"
                $(document).on("click", ".btn-edit", function() {
                    var clientId = $(this).data("client-id");
                    window.location.href = "editar_cliente.html?id=" + clientId;
                });

                //botão "Excluir"
                $(document).on("click", ".btn-delete", function() {
                    var clientId = $(this).data("client-id");

                    $.ajax({
                        type: "DELETE",
                        url: "/client/idDelete/" + clientId,
                        success: function(response){
                            console.log("Cliente excluído com sucesso!");
                            window.location.href = window.location.href;
                        },
                        error: function(){
                            console.error("Erro ao excluir o cliente.");
                        }
                    });
                });
            },
            error: function(){
                alert("Erro ao carregar clientes.");
            }
        });
    }
    carregarClientes();


    // Função para filtrar os clientes
        $("#searchInput").on("keyup", function() {
            var value = $(this).val().toLowerCase(); // Obtém o valor do campo de pesquisa em minúsculas
            $("#clientesTableBody tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1); // Mostra/oculta as linhas conforme o filtro
            });
        });
    });