package rapanos.credit.application.system.service

import rapanos.credit.application.system.entity.Credit
import java.util.UUID

interface CreditService {
    fun save(Credit: Credit): Credit
    fun findAllByCustomer(customerId: Long): List<Credit>
    fun findByCreditCode(customerId:Long, creditCode: UUID): Credit
}