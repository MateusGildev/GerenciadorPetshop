$(document).ready(function(){
    $("#cadastroFormTarefa").submit(function(event){
        event.preventDefault();

        var data = {
            description: $("#description").val(),
            price: $("#price").val(),
        };

        console.log($("#description").val());
        console.log($("#price").val());

        $.ajax({
            type: "POST",
            url: "/service/createServ",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function(response){
                alert("Cadastro realizado com sucesso!");
                window.location.href = "listagem_tarefa.html";
            },
            error: function(){
                alert("Erro ao cadastrar. Tente novamente.");
            }
        });
    });
});
