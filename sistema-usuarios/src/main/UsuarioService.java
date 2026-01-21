package br.com.projeto;

public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Usuario usuario) {

        if (usuario.getNome() == null || usuario.getNome().isBlank())
            throw new IllegalArgumentException("Nome inválido");

        if (usuario.getEmail() == null || usuario.getEmail().isBlank())
            throw new IllegalArgumentException("Email inválido");

        if (repository.existePorEmail(usuario.getEmail()))
            throw new IllegalArgumentException("Email já cadastrado");

        if (usuario.getCpf() == null || usuario.getCpf().isBlank())
            throw new IllegalArgumentException("CPF inválido");

        if (usuario.getCpf().length() != 11)
            throw new IllegalArgumentException("CPF deve ter 11 dígitos");

        if (repository.existePorCpf(usuario.getCpf()))
            throw new IllegalArgumentException("CPF já cadastrado");

        if (usuario.getEndereco() == null || usuario.getEndereco().isBlank())
            throw new IllegalArgumentException("Endereço inválido");

        if (usuario.getSenha() == null || usuario.getSenha().length() < 8)
            throw new IllegalArgumentException("Senha fraca");

        repository.salvar(usuario);
    }

    public void deletar(String cpf) {
        if (cpf == null || cpf.isBlank())
            throw new IllegalArgumentException("CPF inválido");

        Usuario usuario = repository.buscarPorCpf(cpf);

        if (usuario == null)
            throw new IllegalArgumentException("Usuário inexistente");

        repository.deletarPorCpf(cpf);
    }
}