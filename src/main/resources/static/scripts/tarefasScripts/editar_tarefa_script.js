$(document).ready(function(){
    var urlParams = new URLSearchParams(window.location.search);
    var serviceId = urlParams.get('id');


    if(serviceId) {
        $.ajax({
            type: "GET",
            url: "/service/id/" + serviceId,
            success: function(service){

                $("#description").val(service.description);
                $("#price").val(service.price);

            },
            error: function(){
                alert("Erro ao carregar dados do serviço.");
            }
        });


        $("#editTarefaForm").submit(function(event){
            event.preventDefault();


            var formData = {

                description: $("#description").val(),
                price: $("#price").val()
            };

            $.ajax({
                type: "PUT",
                url: "/service/edit/" + serviceId,
                data: JSON.stringify(formData),
                contentType: "application/json",
                success: function(response){
                    alert("Serviço atualizado com sucesso!");
                    window.location.href = "listagem_tarefa.html";
                },
                error: function(){
                    alert("Erro ao atualizar o serviço.");
                }
            });
        });
    } else {
        alert("ID do serviço não encontrado na URL.");
    }
});