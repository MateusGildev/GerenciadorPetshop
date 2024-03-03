$(document).ready(function(){
    // Extrair o ID do cliente da URL (por exemplo, "editar_cliente.html?id=123")
    var urlParams = new URLSearchParams(window.location.search);
    var productId = urlParams.get('id');


    if(productId) {
        // Requisição AJAX para obter os detalhes do produto
        $.ajax({
            type: "GET",
            url: "/product/id/" + productId,
            success: function(product){

                $("#name").val(product.name);
                $("#description").val(product.description);
                $("#price").val(product.price);
                $("#quantity").val(product.quantity);
                $("#quantityMax").val(product.quantityMax);
                $("#quantityMin").val(product.quantityMin);
            },
            error: function(){
                alert("Erro ao carregar dados do produto.");
            }
        });

        //envio do formulário de edição
        $("#editProductForm").submit(function(event){
            event.preventDefault(); // Impede o envio padrão do formulário

            // Obter os valores dos campos do formulário
            var formData = {
                name: $("#name").val(),
                description: $("#description").val(),
                price: $("#price").val(),
                quantity: $("#quantity").val(),
                quantityMax: $("#quantityMax").val(),
                quantityMin: $("#quantityMin").val()
            };

            // Enviar os dados como JSON para a rota de atualização do cliente
            $.ajax({
                type: "PUT",
                url: "/product/edit/" + productId,
                data: JSON.stringify(formData),
                contentType: "application/json",
                success: function(response){
                    alert("Produto atualizado com sucesso!");
                    window.location.href = "listagem_product.html";
                },
                error: function(){
                    alert("Erro ao atualizar o produto.");
                }
            });
        });
    } else {
        alert("ID do produto não encontrado na URL.");
    }
});