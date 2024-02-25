package rapanos.credit.application.system.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.validation.annotation.Validated
import rapanos.credit.application.system.entity.Credit
import rapanos.credit.application.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

@Validated
data class CreditDto(
    @field:NotNull(message = "Invalid Input") val creditValue: BigDecimal,
    @field:Future val dayFirstInstallment: LocalDate,
    @field:NotNull(message = "Value cannot be null")
    @field:Positive (message = "Value must be between 1 and 48")
    @field:Max(value = 48, message = "Value must be less than 48") val numberOfInstallment: Int,
    @field:NotNull(message = "Invalid Input") val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallments = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallment,
        customer = Customer(id = this.customerId)

    )

    init{
        require(dayFirstInstallment.isBefore(LocalDate.now().plusMonths(3))){
            "Date must be within the next 3 months"
        }
    }
}
