## Testes

- [Como fazer testes unitários](#unitario)

<div id=unitario>

### Como fazer testes unitários
Um teste unitário tem como finalidade testar a menor unidade do código, que neste caso, são os métodos. Por exemplo, o método `createUser`:
```
public class CreateUserInteractor {
  private final UserGateway userGateway;

  public CreateUserInteractor(UserGateway createUser) {
    this.userGateway = createUser;
  }

  public Optional<User> createUser(User user) {
    return userGateway.createUser(user);
  }
}
```
Esse método, pode retornar apenas dois possíveis resultados:

- Caso 1: retornar um User
- Caso 2: retornar null

Vamos cobrir esses dois casos:
```
public class CreateUserInteractorTest {

  @Mock
  private UserGateway userGateway;

  @BeforeEach
  privatevoid setup(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  private void createUserCase01(){
    User user = new User("Núcleo 42", "nucleo42@gmail.com")
    when(userGateway.createUser(user).thenReturn(user);
    Optional<User> result = this.userGateway.createUser(user);
    assertThat(result.isPresent()).isTrue();
  }

  @Test
  private void createUserCase02(){
    Optional<User> result = this.userGateway.createUser(null);
    assertThat(result.isEmpty()).isTrue();
  }
}
```
#### @BeforeEach
Essa anotação significa que o método declarado, vai ser executado antes de todos os testes, que neste caso, é o `setup()`. Esse método vai iniciar os mocks do mockito, e o `this`, significa que é para inicializar os mocks da classe atual. Se você não estiver usando mocks, pode omitir essa parte.
#### when(argumento).thenReturn(retorno)
Com esse método, podemos definir qual vai ser o retorno quando chamarmos o método `createUser()`.

Como `UserGateway` é uma dependência do método `createUser`, precisamos mocká-la. Um mock é uma forma de criar um outro ambiente apenas para fazer testes, ao invés de usar uma dependência real. Quando um teste tem uma dependência com a anotação @Mock, o mockito, vai retornar um mock. Dentro desses mocks, os métodos são vazios, que não retornam nada, quem vai definir os retornos, somos nós.

#### assertThat().isTrue()
Esse método é para você verificar o resultado esperado.
#### @ActiveProfiles() e @DataJpaTest e EntityManager

- @DataJpaTest

Ao adicionar essa anotação, você vai informar ao Spring que a classe atual é uma classe de testes que vai testar repositórios JPA.

- @ActiveProfiles()

Caso seu teste nescessite de interações com banco de dados, adicione a anotação @ActiveProfiles() na classe, passando `test` como argumento. Existe um arquivo `application-test.yml`, que tem um banco de dados em memória configurado, que vai servir exclusivamente para executar testes. Ao adicionar essa anotação, você vai informar ao JUnit que você quer usar esse arquivo ao rodar os testes de uma classe. 

- EntityManager

Essa classe nos permite persistir entidades no banco de dados.
```
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void findUserByEmailCase01() {
        UserDTO data = new UserDTO("nucleo42, "nucleo@gmail.com");
        User user = createUser(data);

        Optional<User> result = this.userRepository.findUserByEmail(user.getEmail());

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    private void findUserByEmailCase02() {
        Optional<User> result = this.userRepository.findUserByEmail("nucleo@gmail.com");

        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserDTO data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }
}
```
### Executar testes
Você pode usar a própria IDE ou executar por linha de comando:
```
./gradlew test
```

