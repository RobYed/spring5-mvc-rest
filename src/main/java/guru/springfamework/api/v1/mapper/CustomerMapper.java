package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mappings({
        @Mapping(source = "firstName", target = "firstname"),
        @Mapping(source = "lastName", target = "lastname"),
        @Mapping(source = "customerUrl", target = "customer_url")
    })
    CustomerDTO customerToCustomerDTO(Customer customer);
}
