package br.com.GerenciadorPetshop.service;

import br.com.GerenciadorPetshop.model.Client;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test()
    @DisplayName("Deve retornar um aviso quando o ID do cliente existir no repositório")
    void findByIdSuccess() {
        Long id = 1L;
        Client client = new Client(id, "Mateus", "Sunny", "Cachorro",
                "083.576.241-62", "Travessa Silva", "11933084785");

        Mockito.when(clientRepository.findById(id)).thenReturn(Optional.of(client));

        Client actualClient = clientService.findById(id);
        assertNotNull(actualClient);
        assertEquals(client, actualClient);
        Mockito.verify(clientRepository).findById(id);
    }

    @Test
    @DisplayName("Deve retornar o cliente criado")
    void createClientSuccess() {
        Client client = new Client(null, "Mateus", "Sunny", "Cachorro",
                "083.544.241-62", "Travessa Silva", "11933084785");

        Client clientWithId = new Client(null, "Mateus", "Sunny", "Cachorro",
                "013.524.141-32", "Travessa Silva", "11933084785");

        Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(clientWithId);
        Client createdClient = clientService.createClient(client);

        assertNotNull(createdClient);
        assertEquals(clientWithId.getId(), createdClient.getId());

        Mockito.verify(clientRepository).save(clientWithId);
    }

    @Test
    @DisplayName("Deve retornar cliente excluido com sucesso")
    void deleteByIdSuccess() {
        Long id = 1L;

        Mockito.doNothing().when(clientRepository).deleteById(id);
        clientService.deleteById(id);
        Mockito.verify(clientRepository).deleteById(id);
    }

    @Test
    @DisplayName("Deve atualizar cliente com sucesso")
    void shouldUpdateClientSuccessfully() {
        // Arrange
        Long clientId = 1L;
        Client existingClient = new Client(clientId, "Mateus", "Sunny", "Cachorro",
                "083.544.241-62", "Travessa Silva", "11933084785");

        Client updatedData = new Client(clientId, "Jorge", "Max", "Gato",
                "013.524.141-32", "Travessa Silva", "11933084785");

        Mockito.when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
        Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(updatedData);

        // Act
        Optional<Client> result = clientService.updateClient(clientId, updatedData);

        // Assert
        assertTrue(result.isPresent(), "O cliente não foi encontrado.");
        assertEquals(updatedData.getNome(), result.get().getNome(), "O nome do cliente não foi atualizado.");
        assertEquals(updatedData.getCpf(), result.get().getCpf(), "O CPF do cliente não foi atualizado.");
        assertEquals(updatedData.getEndereco(), result.get().getEndereco(), "O endereço do cliente não foi atualizado.");
        assertEquals(updatedData.getTelefone(), result.get().getTelefone(), "O telefone do cliente não foi atualizado.");
        assertEquals(updatedData.getNomeAnimal(), result.get().getNomeAnimal(), "O nome do animal do cliente não foi atualizado.");
        assertEquals(updatedData.getTipoAnimal(), result.get().getTipoAnimal(), "O tipo do animal do cliente não foi atualizado.");

        // Verificando as interações com o repositório
        Mockito.verify(clientRepository, Mockito.times(1)).findById(clientId);
        Mockito.verify(clientRepository, Mockito.times(1)).save(Mockito.any(Client.class));
    }

    @Test
    @DisplayName("Deve retornar NotFound quando cliente não existir")
    void shouldReturnNotFoundWhenClientDoesNotExist() {
        // Arrange
        Long clientId = 1L;
        Client updatedData = new Client(clientId, "Jorge", "Max", "Gato",
                "013.524.141-32", "Travessa Silva", "11933084785");

        Mockito.when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        // Act
        Optional<Client> result = clientService.updateClient(clientId, updatedData);

        // Assert
        assertFalse(result.isPresent());
        Mockito.verify(clientRepository, Mockito.times(1)).findById(clientId);
        Mockito.verify(clientRepository, Mockito.times(0)).save(Mockito.any(Client.class));
    }



    @Test
    @DisplayName("Deve converter CPF válido para Long")
    void shouldConvertValidCpfToLong() {
        String cpf = "083.544.241-62";
        Long expected = 8354424162L;

        Long result = clientService.stringToLong(cpf);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deve lançar exceção para CPF nulo")
    void shouldThrowExceptionForNullCpf() {
        String cpf = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> clientService.stringToLong(cpf));

        assertEquals("A string fornecida é nula ou vazia.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção para CPF com caracteres inválidos")
    void shouldThrowExceptionForInvalidCpf() {
        String cpf = "CPF: INVALID";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> clientService.stringToLong(cpf));

        assertEquals("A string fornecida contém valores inválidos para conversão.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve converter telefone válido para Long")
    void shouldConvertValidPhoneToLong() {
        String telefone = "(11) 93308-4785";
        Long expected = 11933084785L;

        Long result = clientService.StringToLongTelefone(telefone);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deve lançar exceção para telefone nulo")
    void shouldThrowExceptionForNullPhone() {
        String telefone = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> clientService.StringToLongTelefone(telefone));

        assertEquals("O telefone não pode ser nulo.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção para telefone com caracteres inválidos")
    void shouldThrowExceptionForInvalidPhone() {
        String telefone = "Telefone: INVALID";

        Exception exception = assertThrows(NumberFormatException.class, () -> clientService.StringToLongTelefone(telefone));

        assertTrue(exception.getMessage().contains("For input string"));
    }
}