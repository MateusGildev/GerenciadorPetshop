package br.com.GerenciadorPetshop.service;

import br.com.GerenciadorPetshop.model.Pet;
import br.com.GerenciadorPetshop.repository.PetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @Test
    @DisplayName("Deve criar um novo pet com sucesso")
    void createNewPetSuccess() {
        Pet pet1 = new Pet(1L, "Cachorro");
        when(petRepository.save(pet1)).thenReturn(pet1);

        Pet result = petService.createNewPet(pet1);

        assertEquals(1L, result.getId(), "Os Ids NÃO conferem");
        assertEquals("Cachorro", result.getTipoAnimal(), "O tipo de Animal não confere");
        verify(petRepository, times(1)).save(pet1);
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar criar um pet quando ocorre erro no repositório")
    void createNewPetRepositoryError() {
        Pet pet1 = new Pet(1L, "Cachorro");
        when(petRepository.save(pet1)).thenThrow(new RuntimeException("Erro no repositório"));

        Exception exception = assertThrows(RuntimeException.class, () -> petService.createNewPet(pet1));
        assertEquals("Erro no repositório", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar erro quando os dados do pet forem inválidos")
    void createNewPetInvalidInput() {
        Pet pet1 = new Pet(null, null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> petService.createNewPet(pet1));
        assertEquals("Dados Inválidos", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar todos os pets com sucesso")
    void findAllPetsSuccess() {
        List<Pet> listPets = new ArrayList<>();
        listPets.add(new Pet(1L, "Cachorro"));
        listPets.add(new Pet(2L, "Gato"));

        when(petRepository.findAll()).thenReturn(listPets);
        List<Pet> result = petService.findAll();

        assertEquals(2, result.size());
        assertEquals("Cachorro", result.get(0).getTipoAnimal(), "O primeiro pet não é o esperado");
        assertEquals("Gato", result.get(1).getTipoAnimal(), "O segundo pet não é o esperado");

        verify(petRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar encontrar pets quando ocorre erro no repositório")
    void findAllPetsFailRepositoryError() {
        List<Pet> listPets = new ArrayList<>();
        listPets.add(new Pet(1L, "Cachorro"));
        listPets.add(new Pet(2L, "Gato"));

        when(petRepository.findAll()).thenThrow(new RuntimeException("Erro no repositorio"));
        Exception exception = assertThrows(RuntimeException.class, () -> petService.findAll());
        assertEquals("Erro no repositorio", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar todos os tipos distintos de animais com sucesso")
    void findAllDistinctTypesSuccess() {
        List<String> listTypes = new ArrayList<>();
        listTypes.add("Cachorro");
        listTypes.add("Gato");

        when(petRepository.findDistinctByTipoAnimal()).thenReturn(listTypes);
        List<String> result = petService.findAllDistinctTypes();

        assertEquals(2, result.size());
        assertTrue(result.contains("Cachorro"));
        assertTrue(result.contains("Gato"));
        verify(petRepository, times(1)).findDistinctByTipoAnimal();
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar encontrar tipos distintos de animais quando ocorre erro no repositório")
    void findAllDistinctTypesFailRepositoryError() {
        List<String> listTypes = new ArrayList<>();
        listTypes.add("Cachorro");
        listTypes.add("Gato");

        when(petRepository.findDistinctByTipoAnimal()).thenThrow(new RuntimeException("Erro no repositorio"));
        Exception exception = assertThrows(RuntimeException.class, () -> petService.findAllDistinctTypes());

        assertEquals("Erro no repositorio", exception.getMessage());
    }
}