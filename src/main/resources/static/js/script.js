$(document).ready(function(){
    $("#cadastroForm").submit(function(event){
        event.preventDefault(); // Impede o envio padrão do formulário

        var formData = {
            nome: $("#nome").val(),
            cpf: $("#cpf").val(),
            endereco: $("#endereco").val(),
            telefone: $("#telefone").val(),
            nomeAnimal: $("#nomeAnimal").val(),
            tipoAnimal: $("#tipoAnimal").val()
        };

        $.ajax({
            type: "POST",
            url: "/client/user",
            data: JSON.stringify(formData), // Enviar os dados como JSON
            contentType: "application/json", // Definir o tipo de conteúdo como JSON
            success: function(response){
                alert("Cadastro realizado com sucesso!");
                window.location.href = "listagem_clientes.html";
            },
            error: function(){
                alert("Erro ao cadastrar. Tente novamente.");
            }
        });
    });
});