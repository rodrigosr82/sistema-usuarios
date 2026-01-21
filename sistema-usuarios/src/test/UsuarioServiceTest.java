package br.com.projeto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Test
    void deveCadastrarUsuarioComSucesso() {
        Usuario usuario = new Usuario("João","joao@email.com","12345678901","Rua A","senha123");
        when(repository.existePorEmail(usuario.getEmail())).thenReturn(false);
        when(repository.existePorCpf(usuario.getCpf())).thenReturn(false);
        service.cadastrar(usuario);
        verify(repository, times(1)).salvar(any());
    }

    @Test
    void deveLancarErroQuandoEmailJaExistir() {
        Usuario usuario = new Usuario("João","existe@x.com","12345678901","Rua A","senha123");
        when(repository.existePorEmail(usuario.getEmail())).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> service.cadastrar(usuario));
    }
}