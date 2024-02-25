package rapanos.credit.application.system.service

import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.test.context.ActiveProfiles
import rapanos.credit.application.system.entity.Address
import rapanos.credit.application.system.entity.Customer
import rapanos.credit.application.system.exception.BusinessException
import rapanos.credit.application.system.repository.CustomerRepository
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    private var customerRepository: CustomerRepository = mockk()
    private var customerService: CustomerService = CustomerService(CustomerRepository)

    @Test
    fun `should create customer` (){

        //given
        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns  fakeCustomer

        //when
        val actual: Customer = customerService.save(fakeCustomer)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify (exactly = 1) {customerRepository.save(fakeCustomer)}
    }

    @Test
    fun `should find customer by id`(){
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id =  fakeId)

        every { customerRepository.findById(fakeId) } returns  Optional.of(fakeCustomer)

        //when
        val actual: Customer = customerService.findById(fakeId)

        //then
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        Assertions.assertThat(actual).isNotNull
        verify (exactly = 1) {customerRepository.findById(fakeId)}
    }

    @Test
    fun `should not find customer by invalid id and throw BusinessException`(){
        //given
        val fakeId: Long = Random().nextLong()

        every { customerRepository.findById(fakeId) } returns  Optional.empty()

        //when
        val actual: Customer = customerService.findById(fakeId)

        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        verify (exactly = 1) {customerRepository.findById(fakeId)}

    }

    @Test
    fun `should delete custome by id`(){
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id =  fakeId)
        every { customerRepository.findById(fakeId) } returns  Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs

        //when
        customerService.delete(fakeId)

        //then
        verify (exactly = 1) {customerRepository.findById(fakeId)}
        verify (exactly = 1) {customerRepository.delete(fakeCustomer)}

    }
}



private fun buildCustomer(
    firstName: String = "Cami",
    lastName: String = "Cavalcante",
    cpf: String = "28475934625",
    income: BigDecimal = BigDecimal.valueOf(1000.0),
    email: String = "camila@gmail.com",
    password: String = "12345",
    zipCode: String = "12345",
    street: String = "Rua da Cami",

    id: Long? = 1L
) = Customer(
    firstName = firstName,
    lastName = lastName,
    cpf = cpf,
    income = income,
    email = email,
    password = password,
    address = Address(
        zipCode = zipCode,
        street = street
    ),
    id = id
)