package guru.springfamework.api.v1.model;

import guru.springfamework.domain.Gender;
import lombok.Data;

@Data
public class CustomerDTO {

    private String firstname;
    private String lastname;
    private String customer_url;
}
