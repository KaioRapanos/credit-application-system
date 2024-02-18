package rapanos.credit.application.system.dto

import rapanos.credit.application.system.entity.Credit
import rapanos.credit.application.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    val creditValue: BigDecimal,
    val dayFirstInstallment: LocalDate,
    val numberOfInstallment: Int,
    val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallments = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallment,
        customer = Customer(id = this.customerId)

    )
}
