package rapanos.credit.application.system.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import rapanos.credit.application.system.entity.Address
import java.math.BigDecimal
import rapanos.credit.application.system.entity.Customer

data class CustomerDto(
    @field:NotEmpty(message = "Invalid Input") val firstName: String,
    @field:NotEmpty(message = "Invalid Input") val lastName: String,
    @field:NotEmpty(message = "Invalid Input")
    @field:CPF(message = "This invalid CPF") val cpf: String,
    @field:NotNull(message = "Invalid Input") val income: BigDecimal,
    @field:Email(message = "Invalid email")
    @field:NotEmpty(message = "Invalid Input") val email: String,
    @field:NotEmpty(message = "Invalid Input") val password: String,
    @field:NotEmpty(message = "Invalid Input") val zipCode: String,
    @field:NotEmpty(message = "Invalid Input") val street: String,

) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        ))
}

