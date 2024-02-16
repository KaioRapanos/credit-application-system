package rapanos.credit.application.system.repository

import org.springframework.data.jpa.repository.JpaRepository
import rapanos.credit.application.system.entity.Customer

interface CutomerRepository: JpaRepository<Customer, Long>{
}