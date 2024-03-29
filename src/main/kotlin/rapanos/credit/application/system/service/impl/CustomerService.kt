package rapanos.credit.application.system.service.impl

import org.springframework.stereotype.Service
import rapanos.credit.application.system.entity.Customer
import rapanos.credit.application.system.exception.BusinessException
import rapanos.credit.application.system.repository.CustomerRepository
import rapanos.credit.application.system.service.CustomerService

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) : CustomerService {
    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)


    override fun findById(id: Long): Customer {
        val customer = this.customerRepository.findById(id)
        return customer.orElseThrow { BusinessException("Id $id not found") }
    }

    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}