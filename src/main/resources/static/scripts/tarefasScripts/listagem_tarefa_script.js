$(document).ready(function(){
    function carregarTarefas() {
        $.ajax({
            type: "GET",
            url: "/service/allServices",
            success: function(response){
                var tableBody = $("#tarefasTableBody");

                $.each(response, function(index, service){
                    var row = "<tr>" +
                        "<td>" + service.id + "</td>" +
                        "<td>" + service.description + "</td>" +
                        "<td>" + "R$" + service.price + "</td>"+
                        "<td>" +
                        "<button class='btn btn-primary btn-edit' data-service-id='" + service.id + "'>Editar</button>" +
                        "<button class='btn btn-danger btn-delete' data-service-id='" + service.id + "'>Excluir</button>" +
                        "</td>" +
                        "</tr>";

                    tableBody.append(row);
                });

                //botão "Editar"
                $(document).on("click", ".btn-edit", function() {
                    var serviceId = $(this).data("service-id");
                    window.location.href = "editar_tarefa.html?id=" + serviceId;
                });

                //botão "Excluir"
                $(document).on("click", ".btn-delete", function() {
                    var serviceId = $(this).data("service-id");

                    $.ajax({
                        type: "DELETE",
                        url: "/service/delete/" + serviceId,
                        success: function(response){
                            console.log("Serviço excluído com sucesso!");
                            window.location.href = window.location.href;
                        },
                        error: function(){
                            console.error("Erro ao excluir o serviço.");
                        }
                    });
                });
            },
            error: function(){
                alert("Erro ao carregar serviços.");
            }
        });
    }
    carregarTarefas();
    // Função para filtrar os serviços
        $("#searchInput").on("keyup", function() {
            var value = $(this).val().toLowerCase(); // Obtém o valor do campo de pesquisa em minúsculas
            $("#tarefasTableBody tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1); // Mostra/oculta as linhas conforme o filtro
            });
        });
    });