$(document).ready(function(){
    // Extrair o ID do cliente da URL (por exemplo, "editar_cliente.html?id=123")
    var urlParams = new URLSearchParams(window.location.search);
    var clienteId = urlParams.get('id');


    if(clienteId) {
        // Requisição AJAX para obter os detalhes do cliente
        $.ajax({
            type: "GET",
            url: "/client/id/" + clienteId,
            success: function(cliente){

                $("#nome").val(cliente.nome);
                $("#cpf").val(cliente.cpf);
                $("#endereco").val(cliente.endereco);
                $("#telefone").val(cliente.telefone);
                $("#nomeAnimal").val(cliente.nomeAnimal);
                $("#tipoAnimal").val(cliente.tipoAnimal);
<<<<<<< HEAD


                preencherDropdown()
=======
<<<<<<< HEAD

                preencherDropdown()
=======
>>>>>>> 88c703ff0f97c8578d5db62097b022d9a6ecafb3
>>>>>>> f01ba6ec46c93442c77bceab52f70689f7337b0f
            },
            error: function(){
                alert("Erro ao carregar dados do cliente.");
            }
        });

<<<<<<< HEAD

=======
<<<<<<< HEAD
>>>>>>> f01ba6ec46c93442c77bceab52f70689f7337b0f
        function preencherDropdown() {
            $.ajax({
                type: 'GET',
                url: '/animal/types', // Endpoint para obter os tipos de animais
                success: function(tiposAnimais) {
                    const dropdown = $('#tipoAnimal');

                    // Limpa opções existentes, exceto a primeira (placeholder)
                    dropdown.empty();
                    dropdown.append($('<option></option>').val('').text('Selecione o tipo de animal'));

                    // Preenche as opções do dropdown com os tipos de animais
                    tiposAnimais.forEach(function(tipo) {
                        dropdown.append($('<option></option>').val(tipo).text(tipo));
                    });
                },
                error: function(error) {
                    console.error('Erro ao obter os tipos de animais:', error);
                }
            });
        }

<<<<<<< HEAD

=======
=======
>>>>>>> 88c703ff0f97c8578d5db62097b022d9a6ecafb3
>>>>>>> f01ba6ec46c93442c77bceab52f70689f7337b0f
        //envio do formulário de edição
        $("#editClientForm").submit(function(event){
            event.preventDefault(); // Impede o envio padrão do formulário

            // Obter os valores dos campos do formulário
            var formData = {
                nome: $("#nome").val(),
                cpf: $("#cpf").val(),
                endereco: $("#endereco").val(),
                telefone: $("#telefone").val(),
                nomeAnimal: $("#nomeAnimal").val(),
                tipoAnimal: $("#tipoAnimal").val()
            };

            // Enviar os dados como JSON para a rota de atualização do cliente
            $.ajax({
                type: "PUT",
                url: "/client/edit/" + clienteId,
                data: JSON.stringify(formData),
                contentType: "application/json",
                success: function(response){
                    alert("Cliente atualizado com sucesso!");
                    window.location.href = "listagem_clientes.html";
                },
                error: function(){
                    alert("Erro ao atualizar o cliente.");
                }
            });
        });
    } else {
        alert("ID do cliente não encontrado na URL.");
    }
});