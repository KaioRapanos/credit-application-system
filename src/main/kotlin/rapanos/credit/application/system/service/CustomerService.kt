package rapanos.credit.application.system.service

import rapanos.credit.application.system.entity.Customer

interface CustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun delete(id: Long)
}