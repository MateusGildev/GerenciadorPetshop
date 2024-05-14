$(document).ready(function(){
    var urlParams = new URLSearchParams(window.location.search);
    var orderId = urlParams.get('id');


    if(orderId) {
        $.ajax({
            type: "GET",
            url: "/order/updateOrderById/" + orderIdId,
            success: function(order){

                $("#clientsDropdown").val(clientId);
                $("#staffNotes").val(staffNotes);
                $("#paymentMethod").val(paymentMethod);
                $("#status").val(status);
                $("#serviceFields").val(serviceIds);
                $("#productFields").val(productsIds);

            },
            error: function(){
                alert("Erro ao carregar dados da ordem.");
            }
        });


        $("#editOrderForm").submit(function(event){
            event.preventDefault();


            var formData = {

               description: $("#clientsDropdown").val();
               staffNotes:  $("#staffNotes").val();
               paymentMethod: $("#paymentMethod").val();
               status: $("#status").val(status);
               serviceIds: $("#serviceFields").val();
               productsIds: $("#productFields").val();


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