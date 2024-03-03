$(document).ready(function() {
    preencherDropdown();
});

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

// Evento de submissão do formulário após preencher os tipos de animais
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
        data: JSON.stringify(formData),
        contentType: "application/json",
        success: function(response){
            alert("Cadastro realizado com sucesso!");
            window.location.href = "listagem_clientes.html";
        },
        error: function(){
            alert("Erro ao cadastrar. Tente novamente.");
        }
    });
});
