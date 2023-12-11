import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import javax.persistence.*

// Anotação que marca a classe como um aplicativo Spring Boot.
@SpringBootApplication
class AuthenticationServiceApplication

// Função principal para iniciar o aplicativo Spring Boot.
fun main(args: Array<String>) {
    runApplication<AuthenticationServiceApplication>(*args)
}

// Entidade JPA representando um usuário. Anotada com @Entity para indicar que é uma tabela de banco de dados.
@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // Chave primária autoincrementada.
    val id: Long = 0,
    var name: String, // Nome do usuário.
    var email: String, // Email do usuário.
    var password: String, // Senha do usuário. Deve ser armazenada de forma criptografada.
    var status: String = "ativo" // Estado do usuário: ativo ou inativo.
)

// Entidade JPA representando um pagamento.
@Entity
class Payment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // Chave primária autoincrementada.
    val id: Long = 0,
    var dueDate: LocalDate, // Data de vencimento do pagamento.
    var amount: Double, // Valor do pagamento.
    var status: String = "pendente", // Estado do pagamento: pago, pendente, atrasado.
    @ManyToOne // Relacionamento muitos-para-um com a entidade User.
    var user: User
)

// Interface do repositório para a entidade User. Estende JpaRepository para operações de banco de dados.
interface UserRepository : JpaRepository<User, Long>

// Interface do repositório para a entidade Payment. Estende JpaRepository.
interface PaymentRepository : JpaRepository<Payment, Long>

// Classe de serviço que contém a lógica de negócios.
@Service
class AuthenticationService(private val userRepository: UserRepository) {
    // Método para registrar um novo usuário.
    fun registerUser(name: String, email: String, password: String): User {
        val encryptedPassword = encryptPassword(password) // Criptografa a senha.
        val newUser = User(name = name, email = email, password = encryptedPassword) // Cria um novo usuário.
        return userRepository.save(newUser) // Salva o usuário no banco de dados.
    }

    // Método para criptografar a senha.
    private fun encryptPassword(password: String): String {
        // Implemente a criptografia da senha aqui.
        return password // Retorna a senha criptografada (a implementar).
    }
}

// Controlador REST que gerencia as requisições HTTP para usuários.
@RestController
@RequestMapping("/users") // Mapeia as requisições para '/users'.
class UserController(private val authenticationService: AuthenticationService) {

    // Método POST para registrar um novo usuário.
    @PostMapping("/register")
    fun registerUser(@RequestBody registrationRequest: RegistrationRequest): User {
        // Chama o serviço para registrar o usuário e retorna o usuário registrado.
        return authenticationService.registerUser(
            registrationRequest.name,
            registrationRequest.email,
            registrationRequest.password
        )
    }

    // Classe de dados para a solicitação de registro.
    data class RegistrationRequest(val name: String, val email: String, val password: String)
}


/*
   Objetivo Resumido do Código:

   Este código é um exemplo básico de um microserviço de autenticação para uma academia, 
   utilizando Kotlin e Spring Boot. O principal objetivo é gerenciar o registro de usuários, 
   armazenar informações sobre pagamentos, e implementar um controlador REST para interagir com esses dados. 
   O código cobre a criação de entidades para usuários e pagamentos, repositórios para operações de banco de dados, 
   um serviço de autenticação para lógica de negócios relacionada ao registro de usuários, e um controlador REST 
   para gerenciar as requisições HTTP relacionadas ao usuário.

   Por que da Escolha da Estrutura do Código:

   A estrutura do código foi escolhida para refletir as práticas comuns em projetos Spring Boot, 
   oferecendo uma organização clara e modular:

   1. Entidades (`User` e `Payment`): Representam as tabelas do banco de dados e são fundamentais 
      para o mapeamento objeto-relacional (ORM) usando JPA.
   
   2. Repositórios (`UserRepository` e `PaymentRepository`): Fornecem uma abstração para operações de banco de dados, 
      facilitando a criação, leitura, atualização e exclusão de dados sem a necessidade de escrever SQL explícito.
   
   3. Serviço (`AuthenticationService`): Contém a lógica de negócios, isolando-a do controlador e tornando o código mais testável 
      e fácil de manter.
   
   4. Controlador REST (`UserController`): Gerencia as requisições e respostas HTTP, atuando como a interface entre o usuário 
      (ou cliente da API) e o aplicativo.
*/
