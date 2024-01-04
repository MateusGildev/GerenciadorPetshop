$(document).ready(function(){
    function carregarProducts() {
        $.ajax({
            type: "GET",
            url: "/product/allProducts",
            success: function(response){
                var tableBody = $("#productsTableBody");

                $.each(response, function(index, product){
                    var row = "<tr>" +
                        "<td>" + product.id + "</td>" +
                        "<td>" + product.name + "</td>" +
                        "<td>" + product.description + "</td>" +
                        "<td>" + "R$" + product.price + "</td>"+
                        "<td>" + product.quantity + "</td>" +
                        "<td>" + product.quantityMax + "</td>" +
                        "<td>" + product.quantityMin + "</td>" +
                        "<td>" +
                        "<button class='btn btn-primary btn-edit' data-product-id='" + product.id + "'>Editar</button>" +
                        "<button class='btn btn-danger btn-delete' data-product-id='" + product.id + "'>Excluir</button>" +
                        "</td>" +
                        "</tr>";

                    tableBody.append(row);
                });

                //botão "Editar"
                $(document).on("click", ".btn-edit", function() {
                    var productId = $(this).data("product-id");
                    window.location.href = "editar_product.html?id=" + productId;
                });

                //botão "Excluir"
                $(document).on("click", ".btn-delete", function() {
                    var productId = $(this).data("product-id");

                    $.ajax({
                        type: "DELETE",
                        url: "/product/deleteProduct/" + productId,
                        success: function(response){
                            console.log("Produto excluído com sucesso!");
                            window.location.href = window.location.href;
                        },
                        error: function(){
                            console.error("Erro ao excluir o produto.");
                        }
                    });
                });
            },
            error: function(){
                alert("Erro ao carregar produtos.");
            }
        });
    }
    carregarProducts();
    // Função para filtrar os produtos
        $("#searchInput").on("keyup", function() {
            var value = $(this).val().toLowerCase(); // Obtém o valor do campo de pesquisa em minúsculas
            $("#productsTableBody tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1); // Mostra/oculta as linhas conforme o filtro
            });
        });
    });