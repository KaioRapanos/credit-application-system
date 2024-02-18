package rapanos.credit.application.system.service.impl

import org.springframework.stereotype.Service
import rapanos.credit.application.system.entity.Customer
import rapanos.credit.application.system.repository.CustomerRepository
import rapanos.credit.application.system.service.CustomerService
import java.lang.RuntimeException

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) : CustomerService {
    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)


    override fun findById(id: Long): Customer {
        val customer = this.customerRepository.findById(id)
        return customer.orElseThrow { RuntimeException("Id $id not found") }
    }

    override fun delete(id: Long) =
        this.customerRepository.deleteById(id)

}