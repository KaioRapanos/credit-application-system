package rapanos.credit.application.system.service.impl

import org.springframework.stereotype.Service
import rapanos.credit.application.system.entity.Credit
import rapanos.credit.application.system.repository.CreditRepository
import rapanos.credit.application.system.service.CreditService
import rapanos.credit.application.system.service.CustomerService
import java.lang.RuntimeException
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): CreditService {
    override fun save(credit: Credit): Credit {
        credit.apply{
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> =
        this.creditRepository.findAllByCustomer(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit = (this.creditRepository.findByCreditCode(creditCode)
            ?: throw RuntimeException("Creditcode $creditCode not found"))
        return if(credit.customer?.id == customerId) credit else throw RuntimeException("Contact admin")
    }
}