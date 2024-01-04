$(document).ready(function(){
    $("#cadastroFormProduct").submit(function(event){
        event.preventDefault(); // Impede o envio padrão do formulário

        var data = {
            name: $("#name").val(),
            description: $("#description").val(),
            price: $("#price").val(),
            quantity: $("#quantity").val(),
            quantityMax: $("#quantityMax").val(),
            quantityMin: $("#quantityMin").val()
        };

        $.ajax({
            type: "POST",
            url: "/product/newProduct",
            data: JSON.stringify(data), // Enviar os dados como JSON
            contentType: "application/json", // Definir o tipo de conteúdo como JSON
            success: function(response){
                alert("Cadastro realizado com sucesso!");
                window.location.href = "listagem_product.html";
            },
            error: function(){
                alert("Erro ao cadastrar. Tente novamente.");
            }
        });
    });
});
